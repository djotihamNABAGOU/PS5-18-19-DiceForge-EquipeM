package PlayerStrategy;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Island;
import diceforge.Temple;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class RandomStrategy extends Strategy {

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
                        System.out.println("\t->ENHANCEMENT<-");
                        //il les active dans l'ordre de son choix, et donc ici, dans un ordre aléatoire
                        callTheReinforcements(temple, bot, numberOfTheBot, listFaces, data);
                    }
                }
            }

            //2->choix de faire une action, LE JOUEUR ACTIF PEUT EFFECTUER UNE ACTION
            choice = random.nextInt(2); // 0 pour oui et 1 pour non
            if (choice == 0) {

                //Choix de l'action à effectuer (forge ou exploit)
                choice = random.nextInt(2); // 0 pour forge et 1 pour exploit

                switch (choice) {
                    case 0://forge
                        if (supActionDone == false)
                            System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FORGE");
                        else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FORGE");

                        //Tant qu'il a les ressources, il peut forger plusieurs faces de sanctuaire
                        //Choix de forger plusieurs faces
                        choice = random.nextInt(2); // 0 pour oui, 1 pour non
                        if (choice == 0) {//forge de plusieurs faces
                            SanctuarysFaces face;
                            int nbPurchase = 1;//indice de forge
                            while (!(face = FaceToBuy(bot, temple, bassin)).getName().equals("null")) {
                                bassin = temple.giveMeTheBasin(face);//enregistrement du bassin de la nouvelle face
                                if (temple.buyFace(face)) {
                                    System.out.println("PURCHASE " + nbPurchase);
                                    ForgeDice(face);
                                    bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                                    nbPurchase++;
                                } else {
                                    System.out.println("Purchase failed");
                                }
                            }
                        } else {//forge d'une seule face
                            SanctuarysFaces face;
                            if (!(face = FaceToBuy(bot, temple, bassin)).getName().equals("null")) {
                                if (temple.buyFace(face)) {
                                    ForgeDice(face);
                                    bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                                } else {
                                    System.out.println("Purchase failed");
                                }
                            }
                        }
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
                        if (supActionDone == false)
                            System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                        else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");

                        /**
                         * s'il est ici, c'est qu'il a les ressources pour faire de l'exploit car l'exploit est privilégié
                         * par rapport à la forge pour maximiser les gains
                         */

                        if (supActionDone == false)
                            System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                        else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");

                        /*Card card;
                        if (!(card = bestCardToBuy(potentialCardsToBuy)).getName().equals("")) {
                            if (island.buyCard(card, temple, numberOfTheBot, listFaces, data)) {
                                feat(card, temple, bot, numberOfTheBot, listFaces, data);
                                if (card.getType().equals("M")) bot.getHerosInventory().DecreaseMoonPoints(card.getPrice());
                                if (card.getType().equals("S")) bot.getHerosInventory().DecreaseSunPoints(card.getPrice());
                                if (card.getType().equals("M+S")) {
                                    bot.getHerosInventory().DecreaseMoonPoints(5);
                                    bot.getHerosInventory().DecreaseSunPoints(5);
                                }
                            } else {
                                System.out.println("Purchase failed");
                            }
                        }

                        //Fin exploit, Action supplémentaire si joueur actif
                        if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                            //il l'effectue
                            supActionDone = true;
                            apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                            supActionDone = false;
                        }*/
                        /*System.out.println("No implemantation for now");

                        Card card;
                        if (!(card = CardToBuy(bot, island)).getName().equals("")) {
                            if (island.buyCard(card)) {
                                feat(card);
                                bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                            } else {
                                System.out.println("Purchase failed");
                            }
                        }

                        //Fin exploit, Action supplémentaire si joueur actif
                        if (bot.getHerosInventory().getSunPoints()>=2 && supActionDone == false){//il a les conditions requises pour effectuer une action supplémenatire
                            int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                            if (choiceSupAction == 0){//On choisit alors quelle action supplémentaire effectuer
                                supActionDone = true;
                                apply(temple, island, numberOfTheBot,listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                                supActionDone = false;
                            }
                        }*/
                        break;
                }

            }
        }
    }

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'APPEL DES RENFORTS       *********************/
    /***********************************************************************************************/
    @Override
    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data){
        Random random = new Random();
        int size = bot.getReinforcementCard().size();
        int i = size;
        int last = 0, toGet = 0;
        while (i != 0) {
            toGet = random.nextInt(size);
            if (toGet != last) {
                bot.getReinforcementCard().get(toGet).capacity(temple, bot, numberOfTheBot, listFaces, data);
                last = toGet;
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
        if (bot.getHerosInventory().getGloryPoints()>=3) {
            Random random = new Random();
            choice = random.nextInt(2); // 0 pour oui, 1 pour non
        }else System.out.println("Not enough gold to apply TheFormer effect card");
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
            System.out.println("\tFORGE ON FIRST DICE");
            System.out.println("\tFACE OUT: " + bot.getFirstDice().getFaces()[numberOfFace].toString());
            System.out.println("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getFirstDice().getFaces()[numberOfFace]); //Ajout dans la liste des faces enlevées
            bot.getFirstDice().setFaces(face, numberOfFace);
        } else { // Second dé
            System.out.println("\tFORGE ON SECOND DICE");
            System.out.println("\tFACE OUT: " + bot.getSecondDice().getFaces()[numberOfFace].toString());
            System.out.println("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getSecondDice().getFaces()[numberOfFace]);
            bot.getSecondDice().setFaces(face, numberOfFace);
        }
    }

    /**
     * retourne le nom de la face à payer choisie au hasard selon les ressources disponibles pour payer la face
     *
     * @param bot    utilisé pour avoir accès à l'inventaire du bot
     * @param temple ustilisé pour rechercher les faces disponibles
     * @return la face à payer choisie au hasard
     * en gros, on stocke les faces du sanctuaire disponibles dans une liste FacesAvailable puis on choisit au hasard la face à retourner
     */
    @Override
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple, int bassin) {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
            if (a != bassin) {//car il ne peut retirer de faces d'un même bassin consécutivement
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        FacesAvailable.add(sanctuary[a].get(i));
                    }
                }
            }
        }

        Random randomFace = new Random();

        if (FacesAvailable.size() == 0) return new SanctuarysFaces();
        else {
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //System.out.println("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }

    }

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'EXPLOIT         ******************************/
    /***********************************************************************************************/
    /*
    private Card CardToBuy(Bot bot, Island island) {
        int gold = bot.getHerosInventory().getGoldPoints();
        int sun = bot.getHerosInventory().getSunPoints();
        int moon = bot.getHerosInventory().getMoonPoints();
        ArrayList<Card> CardAvailable = new ArrayList<>();
        ArrayList<Card> sanctuary = island.getCards();
        for (int a = 0; a < 10; a++) {
            if (a != bassin) {//car il ne peut retirer de faces d'un même bassin consécutivement
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        FacesAvailable.add(sanctuary[a].get(i));
                    }
                }
            }
        }

        Random randomFace = new Random();

        if (FacesAvailable.size() == 0) return new SanctuarysFaces();
        else {
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //System.out.println("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }
    }*/

    @Override
    public int giveMeYourChoice(ArrayList<SimpleFace> Offered, int whichAction) {
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
