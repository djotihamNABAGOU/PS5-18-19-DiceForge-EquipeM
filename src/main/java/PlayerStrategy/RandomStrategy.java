package PlayerStrategy;

import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Random;

public class RandomStrategy extends Strategy {
    private boolean supActionDone = false;

    public RandomStrategy(Bot bot) {
        super(bot);
    }

    /**
     * méthode qui applique la stratégie du bot
     *
     * @param temple qui servira à réaliser des exploits
     */
    public void apply(Temple temple, int numberOfTheBot, int actionNumber) {
        //Seul le joueur actif peut appliquer une stratégie après le lancé des dés
        if (bot.isActive()) {
            //1->Choix d'appel des renforts, LE JOUEUR ACTIF PEUT APPELER DES RENFORTS
            Random random = new Random();
            int choice = random.nextInt(2); // 0 pour oui et 1 pour non
            if (choice == 0) {
                if (bot.getEnhancementCard().size() != 0) {
                    System.out.println("\t->ENHANCEMENT<-");
                    //il les active dans l'ordre de son choix, et donc ici, dans un ordre aléatoire
                    int size = bot.getEnhancementCard().size();
                    int i = size;
                    int last = 0, toGet = 0;
                    while (i != 0) {
                        toGet = random.nextInt(size);
                        if (toGet != last) {
                            if (bot.getEnhancementCard().get(toGet).getCapacityNeeds() == 0) {//cas de la carte L'ANCIEN
                                bot.getEnhancementCard().get(toGet).capacity(bot);
                            }
                            if (bot.getEnhancementCard().get(toGet).getCapacityNeeds() == 1) {//cas de la carte LES AILES DE LA GUARDIENNE
                                choice = random.nextInt(3); // 0 pour Gold, 1 pour Moon, 2 pour Sun
                                if (choice == 0) bot.getEnhancementCard().get(toGet).capacity(bot, 0);
                                if (choice == 1) bot.getEnhancementCard().get(toGet).capacity(bot, 1);
                                if (choice == 2) bot.getEnhancementCard().get(toGet).capacity(bot, 2);
                            }
                            if (bot.getEnhancementCard().get(toGet).getCapacityNeeds() == 2) {//cas des cartes de faveur mineure
                                choice = random.nextInt(2); // 0 pour le premier dé, 1 pour le second
                                if (choice == 0) bot.getEnhancementCard().get(toGet).capacity(bot, bot.getFirstDice());
                                else bot.getEnhancementCard().get(toGet).capacity(bot, bot.getSecondDice());
                            }
                            last = toGet;
                            i--;
                        }
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
                            System.out.println("*ACTION " + actionNumber + " FOR BOT NUMBER " + numberOfTheBot + ": FORGE");
                        else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FORGE");

                        SanctuarysFaces face = FaceToBuy(bot, temple);
                        if (face.getPrice() != 0) {
                            if (temple.buyFace(face)) {
                                ForgeDice(face);
                                bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                            } else {
                                System.out.println("Purchase failed");
                            }
                        }
                        //Fin forge, Action supplémentaire si joueur actif
                        if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                            int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                            if (choiceSupAction == 0) {//On choisit alors quelle action supplémentaire effectuer
                                supActionDone = true;
                                apply(temple, numberOfTheBot, actionNumber);//On réappelle la fonction pour éviter de la duplication de code
                                supActionDone = false;
                            }
                        }
                        break;

                    case 1://exploit
                        if (supActionDone == false)
                            System.out.println("*ACTION " + actionNumber + " FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                        else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");

                        System.out.println("No implemantation for now");

                        /*if (bot.getHerosInventory().getSunPoints()>=2 && supActionDone == false){//il a les conditions requises pour effectuer une action supplémenatire
                            int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                            if (choiceSupAction == 0){//On choisit alors quelle action supplémentaire effectuer
                                supActionDone = true;
                                apply(temple,numberOfTheBot,actionNumber);//On réappelle la fonction pour éviter de la duplication de code
                                supActionDone = false;
                            }
                        }*/
                        break;
                }

            }
        }
    }

    /**
     * Permet au bot de remplacer une face de son dé avec une nouvelle face venant du sanctuaire
     *
     * @param face de remplacement
     * @param position
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
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple) {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
            for (int i = 0; i < sanctuary[a].size(); i++) {
                if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                    FacesAvailable.add(sanctuary[a].get(i));
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

}
