package PlayerStrategy;
import Card.Card;
import Faces.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.GlobalConstants;
import diceforge.Island;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy extends Strategy implements GlobalConstants {

    public RandomStrategy(Bot bot) {
        super(bot);
    }

    /**************************************************************************************************/
    /***************************      METHODE PRINCIPALE DE LA CLASSE       **************************/
    /*************************************************************************************************/
    @Override
    public void apply(Temple temple, Island island, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //Seul le joueur actif peut appliquer une stratégie après le lancé des dés
        if (bot.isActive()) {
            //1->Choix d'appel des renforts, LE JOUEUR ACTIF PEUT APPELER DES RENFORTS
            Random random = new Random();
            int choice = random.nextInt(2); // 0 pour oui et 1 pour non
            if (supActionDone == false) {//on ne doit pas appeler des renforts lors d'une action sup
                if (choice == 0) {
                    if (bot.getReinforcementCard().size() != 0) {
                        Print.PrintMessage("\t->ENHANCEMENT<-");
                        //il les active dans l'ordre de son choix, et donc ici, dans un ordre aléatoire
                        callTheReinforcements(temple, bot, numberOfTheBot, listFaces, data);
                    }
                }
            }

            //2->choix de faire une action, LE JOUEUR ACTIF PEUT EFFECTUER UNE ACTION
            choice = random.nextInt(2); // 0 pour oui et 1 pour non
            if (choice == 0) {
                //Avant de choisir l'action à effectuer, le bot doit au préalable savoir s'il a les ressources requises pour un exploit
                ArrayList<Card> potentialCardsToBuy = new ArrayList<>();
                potentialCardsToBuy = potentialCardsToBuy(bot, island);
                if (potentialCardsToBuy.size() == 0) choice = 0;
                else choice = 1;

                /*Choix de l'action à effectuer (forge ou exploit) en fonction des ressources qu'il a.
                * S'il n'a pas de ressources, ce sera la forge alors, mais dans le cas contraire, ce sera un random*/

                if (choice == 0) choice = 0;
                else choice = random.nextInt(2); // 0 pour forge et 1 pour exploit

                switch (choice) {
                    case 0://forge
                        if (supActionDone == false)
                            Print.PrintMessage("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FORGE");
                        else Print.PrintMessage("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FORGE");

                        //Tant qu'il a les ressources, il peut forger plusieurs faces de sanctuaire
                        //Choix de forger plusieurs faces
                        choice = random.nextInt(2); // 0 pour oui, 1 pour non

                        forgeHowManyTimes(temple, choice);

                        //Fin forge, Action supplémentaire si joueur actif
                        if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                            //choix de faire une action sup
                            int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                            if (choiceSupAction == 0) {//Action sup
                                bot.getHerosInventory().IncreaseSunPoints(2);//Il paie
                                supActionDone = true;
                                apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                                supActionDone = false;
                            }
                        }
                        break;

                    case 1://exploit
                        /**
                         * s'il est ici, c'est qu'il a les ressources pour faire de l'exploit car l'exploit est privilégié
                         * par rapport à la forge pour maximiser les gains
                         */

                        if (supActionDone == false)
                            Print.PrintMessage("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                        else Print.PrintMessage("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");

                        Card card;
                        if (!(card = cardToBuy(potentialCardsToBuy)).getName().equals("")) {
                            feat(card, temple, island, bot, numberOfTheBot, listFaces, data);
                        }

                        //Fin exploit, Action supplémentaire si joueur actif
                        if (bot.getHerosInventory().getSunPoints()>=2 && supActionDone == false){//il a les conditions requises pour effectuer une action supplémenatire
                            int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                            if (choiceSupAction == 0){//On choisit alors quelle action supplémentaire effectuer
                                supActionDone = true;
                                apply(temple, island, numberOfTheBot,listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                                supActionDone = false;
                            }
                        }
                        break;
                }

            }
        }
    }

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'APPEL DES RENFORTS       *********************/
    /***********************************************************************************************/
    @Override
    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        /*Affichage
        for (int a = 0; a < listFaces.length; a++) {
            Print.PrintMessage(listFaces[a].toString());
        }*/
        Random random = new Random();
        int size = bot.getReinforcementCard().size();
        int i = size;
        ArrayList<Integer> last = new ArrayList<>();
        int toGet = 0;
        while (i != 0) {
            toGet = random.nextInt(size);
            if (!last.contains(toGet)) {
                bot.getReinforcementCard().get(toGet).capacity(temple, bot, numberOfTheBot, listFaces, data);
                last.add(toGet);
                i--;
            }
        }
    }

    @Override
    public int whichResource() {
        Random random = new Random();
        int choice = -1;
        choice = random.nextInt(3); // 0 pour Gold, 1 pour Moon, 2 pour Sun
        return choice;
    }

    @Override
    public int applyFormerEffect() {
        int choice = -1;
        if (bot.getHerosInventory().getGloryPoints() >= 3) {
            Random random = new Random();
            choice = random.nextInt(2); // 0 pour oui, 1 pour non
        } else Print.PrintMessage("Not enough gold to apply TheFormer effect card");
        return choice;
    }

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT LA FORGE         ******************************/
    /***********************************************************************************************/

    /**
     * Permet au bot de remplacer une face de son dé avec une nouvelle face venant du sanctuaire
     *
     * @param face de remplacement
     */
    @Override
    public void ForgeDice(GeneralFace face) {

        Random randomFace = new Random();
        int numberOfFace = randomFace.nextInt(6); //Random pour prendre la face a enlever
        Random randomDice = new Random();
        int numberOfDice = randomDice.nextInt(2) + 1; //Random pour prendre le dé sur lequel il faut forger

        if (numberOfDice == 1) { //Premier dé
            Print.PrintMessage("\tFORGE ON FIRST DICE");
            Print.PrintMessage("\tFACE OUT: " + bot.getFirstDice().getFaces()[numberOfFace].toString());
            Print.PrintMessage("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getFirstDice().getFaces()[numberOfFace]); //Ajout dans la liste des faces enlevées
            bot.getFirstDice().setFaces(face, numberOfFace);
        } else { // Second dé
            Print.PrintMessage("\tFORGE ON SECOND DICE");
            Print.PrintMessage("\tFACE OUT: " + bot.getSecondDice().getFaces()[numberOfFace].toString());
            Print.PrintMessage("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getSecondDice().getFaces()[numberOfFace]);
            bot.getSecondDice().setFaces(face, numberOfFace);
        }
    }

    /**
     * retourne le nom de la face à payer choisie au hasard selon les ressources disponibles pour payer la face
     *
     * @param FacesAvailable
     * @return la face à payer choisie au hasard
     *
     */
    @Override
    public SanctuarysFaces FaceToBuy(ArrayList<SanctuarysFaces> FacesAvailable) {
        Random randomFace = new Random();

        if (FacesAvailable.size() == 0) return new SanctuarysFaces();
        else {
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //Print.PrintMessage("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }

    }

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'EXPLOIT         ******************************/
    /***********************************************************************************************/

    /**
     * Aucune règle, que du random !!!
     *
     * @param potentialCardsToBuy
     * @return
     */
    private Card cardToBuy(ArrayList<Card> potentialCardsToBuy) {
        int size = potentialCardsToBuy.size();
        Random random = new Random();
        int indexOfCard = random.nextInt(size);
        return potentialCardsToBuy.get(indexOfCard);
    }

    @Override
    public int giveMeYourChoice(ArrayList<GeneralFace> Offered, int whichAction) {
        Random random = new Random();
        int choice = -1;
        choice = random.nextInt(Offered.size());
        return choice;
    }

    @Override
    public int throwWhichDice() {
        Random random = new Random();
        int choice = -1;
        choice = random.nextInt(2); // 0 pour le premier dé, 1 pour le second
        return choice;
    }
}
