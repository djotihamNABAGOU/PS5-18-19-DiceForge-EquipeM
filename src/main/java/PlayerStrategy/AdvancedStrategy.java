package PlayerStrategy;

import Card.Card;
import Card.Reinforcement;
import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Island;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Random;

/**
 * 1-On décide de privilégier les points de gloire par rapport aux autres types de points pour cette stratégie
 * 2-On décide également de privilégier l'exploit à la forge pour maximiser les gains
 */
public class AdvancedStrategy extends Strategy {

    public AdvancedStrategy(Bot bot) {
        super(bot);
    }

    /**************************************************************************************************/
    /***************************      METHODE PRINCIPALE DE LA CLASSE       **************************/
    /*************************************************************************************************/

    public void apply(Temple temple, Island island, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //Seul le joueur actif peut appliquer une stratégie après le lancé des dés
        if (bot.isActive()) {

            //1->Le bot avancé appelle forcément ses renforts
            if (supActionDone == false) {//on ne doit pas appeler des renforts lors d'une action sup
                if (bot.getReinforcementCard().size() != 0) {
                    System.out.println("\t->ENHANCEMENT<-");
                    //il les active dans l'ordre de son choix, et donc ici, il analyse le meilleur ordre
                    callTheReinforcements(temple, bot, numberOfTheBot, listFaces, data);
                }
            }

            //2->LE JOUEUR ACTIF PEUT EFFECTUE UNE ACTION

            //Choix de l'action à effectuer (forge ou exploit), il fait un exploit s'il a assez de ressources, sinon il forge
            int choice = -1;// 0 pour forge et 1 pour exploit

            ArrayList<Card> potentialCardsToBuy = new ArrayList<>();
            potentialCardsToBuy = potentialCardsToBuy(bot, island);
            if (potentialCardsToBuy.size() == 0) choice = 0;
            else choice = 1;

            switch (choice) {
                case 0://forge
                    if (supActionDone == false)
                        System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FORGE");
                    else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FORGE");

                    //Tant qu'il a les ressources, il forge plusieurs faces de sanctuaire
                    SanctuarysFaces face;
                    int nbPurchase = 1;//indice de forge

                    forgeHowManyTimes(temple, 0);

                    //Fin forge, Action supplémentaire si joueur actif
                    if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        bot.getHerosInventory().IncreaseSunPoints(2);//Il paie
                        supActionDone = true;
                        apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }
                    break;

                case 1://exploit
                    /**
                     * s'il est ici, c'est qu'il a les ressources pour faire de l'exploit car l'exploit est privilégié
                     * par rapport à la forge pour maximiser les gains
                     */

                    if (supActionDone == false)
                        System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                    else System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");

                    Card card;
                    if (!(card = bestCardToBuy(potentialCardsToBuy)).getName().equals("")) {
                        feat(card, temple, island, bot, numberOfTheBot, listFaces, data);
                    }

                    //Fin exploit, Action supplémentaire si joueur actif
                    if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        //il l'effectue
                        supActionDone = true;
                        apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }
                    break;
                default:
                    System.out.println("Problem with the potential cards to buy !!!");
            }


        }
    }


    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'APPEL DES RENFORTS       *********************/
    /***********************************************************************************************/

    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //ici, il faut privilégier les points de gloire
        int size = bot.getReinforcementCard().size();
        if (size == 3) {
            /*il possède alors les 3 cartes de renforcement, on appliquera en dernier <l'ancien> car
            permet de remporter 4 points de gloire, et pour maximiser les chances de l'avoir,
            on appliquera les effets des 2 premières cartes afin de posséder un nombre de points de gold suffisant.
             */
            int theFormerIndex = 0;
            for (int i = 0; i < size; i++) {
                if (bot.getReinforcementCard().get(i).getName().equals("TheFormer")) theFormerIndex = i;
                else bot.getReinforcementCard().get(i).capacity(temple, bot, numberOfTheBot, listFaces, data);
            }
            bot.getReinforcementCard().get(theFormerIndex).capacity(temple, bot, numberOfTheBot, listFaces, data);
        }
    }

    /**
     * On privilégie le gold pour lui permettre de chopper les points de gloire de <l'ancien>
     *
     * @return 0 pour Gold, 1 pour Moon, 2 pour Sun
     */
    @Override
    public int whichResource() {
        return 0;
    }

    @Override
    public int applyFormerEffect() {
        if (bot.getHerosInventory().getGloryPoints() >= 3) {
            return 0;
        } else {
            System.out.println("Not enough gold to apply TheFormer effect card");
            return 1;
        }
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
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple) {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
           
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        int numbassin = temple.giveMeTheBasin(sanctuary[a].get(i));
                        boolean ok = true;
                        for(int b = 0;b<bassin.size();a++){
                            if(numbassin == bassin.get(b))
                                ok = false;
                        }
                        
                        if(ok!=false)
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

    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'EXPLOIT         ******************************/
    /***********************************************************************************************/

    /**
     * 1-On privilégie en premier lieu le nombre de points de gloire rapportés par la carte
     * En cas d'égalité de points de gloire:
     * 1.1-On privilégie ensuite les cartes à effet renfort car elles s'activent à chaque tour du joueur quand il est actif
     * 1.2-On privilégie ensuite les cartes à effet automatiques qui ne s'exécutent que lorsque les conditions sont réunies
     * 1.3-Ensuite les cartes à effet immédiat
     * 1.4-puis viennent les cartes sans effet
     * 1.5-En cas d'égalité entre deux meilleurs faces, le départage s'éffectue sur les ressources de Sun et de Moon disponibles
     *
     * @param potentialCardsToBuy
     * @return
     */
    private Card bestCardToBuy(ArrayList<Card> potentialCardsToBuy) {
        int maxGloryPoints = potentialCardsToBuy.get(0).getGloryPoints(), bestIndex = 0;
        //Affichage des cartes
        for (int a = 0; a < potentialCardsToBuy.size(); a++) {
            System.out.println(potentialCardsToBuy.get(a).toString());
        }
        for (int a = 1; a < potentialCardsToBuy.size(); a++) {
            if (potentialCardsToBuy.get(a).getGloryPoints() > maxGloryPoints) {
                maxGloryPoints = potentialCardsToBuy.get(a).getGloryPoints();
                bestIndex = a;
            } else {
                if (potentialCardsToBuy.get(a).getGloryPoints() == maxGloryPoints) {//il faut trouver la plus avantageuse
                    if (potentialCardsToBuy.get(a).getTypeCard().equals("R")) {
                        if (potentialCardsToBuy.get(bestIndex).getTypeCard().equals("R")) {
                            /**dans cette situation, les cartes ont deja deux points en commun, le nombre de points de gloire
                             * que la carte retourne et le type de d'effet de la carte, il est donc impossible qu'ils aient
                             * le meme type ressource pour le paiement, on va donc baser le départage dessus!
                             * de sorte à utiliser la ressource la plus disponible.
                             */
                            if (potentialCardsToBuy.get(bestIndex).getType().equals("S")) {
                                //alors celle de la seconde carte est M
                                if (bot.getHerosInventory().getMoonPoints() > bot.getHerosInventory().getSunPoints())
                                    bestIndex = a;
                            }

                        } else bestIndex = a;
                    } else if (potentialCardsToBuy.get(a).getTypeCard().equals("A")) {
                        if (!potentialCardsToBuy.get(bestIndex).getTypeCard().equals("R")) {
                            if (potentialCardsToBuy.get(bestIndex).getTypeCard().equals("A")) {
                                if (potentialCardsToBuy.get(bestIndex).getType().equals("S")) {
                                    //alors celle de la seconde carte est M
                                    if (bot.getHerosInventory().getMoonPoints() > bot.getHerosInventory().getSunPoints())
                                        bestIndex = a;
                                } else {//si c'est Moon
                                    if (bot.getHerosInventory().getSunPoints() > bot.getHerosInventory().getMoonPoints())
                                        bestIndex = a;
                                }

                            } else bestIndex = a;
                        }
                    } else if (potentialCardsToBuy.get(a).getTypeCard().equals("I")) {
                        System.out.println(bestIndex);
                        if (!potentialCardsToBuy.get(bestIndex).getTypeCard().equals("R") && !potentialCardsToBuy.get(bestIndex).getTypeCard().equals("A")) {
                            if (potentialCardsToBuy.get(bestIndex).getTypeCard().equals("I")) {
                                if (potentialCardsToBuy.get(bestIndex).getType().equals("S")) {
                                    //alors celle de la seconde carte est M
                                    if (bot.getHerosInventory().getMoonPoints() > bot.getHerosInventory().getSunPoints())
                                        bestIndex = a;
                                } else {//si c'est Moon
                                    if (bot.getHerosInventory().getSunPoints() > bot.getHerosInventory().getMoonPoints())
                                        bestIndex = a;
                                }
                            } else bestIndex = a;
                        }
                    } else if (potentialCardsToBuy.get(a).getTypeCard().equals("NULL")) {
                        if (!potentialCardsToBuy.get(bestIndex).getTypeCard().equals("R") && !potentialCardsToBuy.get(bestIndex).getTypeCard().equals("A") && !potentialCardsToBuy.get(bestIndex).getTypeCard().equals("I")) {
                            if (potentialCardsToBuy.get(bestIndex).getTypeCard().equals("NULL")) {
                                if (potentialCardsToBuy.get(bestIndex).getType().equals("S")) {
                                    //alors celle de la seconde carte est M
                                    if (bot.getHerosInventory().getMoonPoints() > bot.getHerosInventory().getSunPoints())
                                        bestIndex = a;
                                } else {//si c'est Moon
                                    if (bot.getHerosInventory().getSunPoints() > bot.getHerosInventory().getMoonPoints())
                                        bestIndex = a;
                                }
                            } else bestIndex = a;
                        }
                    }
                }
            }
        }
        return potentialCardsToBuy.get(bestIndex);
    }

    /**
     * 1-En lancé de dé, on privilégie toujours les points de gloire, sinon la ressource la plus faible
     * 2-en exploit, on choisit les points de gloire
     */
    @Override
    public int giveMeYourChoice(ArrayList<SimpleFace> Offered, int whichAction) {
        int size = Offered.size(), choice = -1;
        int goldIndex = 0, sunIndex = 0, moonIndex = 0;
        if (whichAction == 0) {
            for (int i = 0; i < size; i++) {
                if (Offered.get(i).getName().equals("GloryFace")) {
                    return i;
                } else {
                    if (Offered.get(i).getName().equals("GoldenFace")) goldIndex = i;
                    if (Offered.get(i).getName().equals("SunFace")) sunIndex = i;
                    if (Offered.get(i).getName().equals("MoonFace")) moonIndex = i;
                }
            }
            
            int goldPoints = bot.getHerosInventory().getGoldPoints(),
                    sunPoints = bot.getHerosInventory().getSunPoints(),
                    moonPoints = bot.getHerosInventory().getMoonPoints();
            if (goldPoints < sunPoints) {
                if (goldPoints < moonPoints) choice = goldIndex;
                else {
                    if (goldPoints == moonPoints) choice = goldIndex;//pour privilégier l'achat des faces
                    else choice = moonIndex;
                }
            } else {
                if (goldPoints == sunPoints) {
                    if (goldPoints < moonPoints) choice = goldIndex;//pour privilégier l'achat des faces
                    else choice = moonIndex;
                } else {
                    if (sunPoints < moonPoints) choice = sunIndex;
                    else {
                        if (sunPoints == moonPoints) {
                            Random random = new Random();
                            int number = random.nextInt(2);//0 pour sun et 1 pour moon
                            if (number == 0) choice = sunIndex;
                            else choice = moonIndex;
                        } else choice = moonIndex;
                    }
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (Offered.get(i).getName().equals("GloryFace")) choice = i;
            }
        }
        return choice;
    }

    /**
     * Intervient aussi dans l'appel des renforts (carte TheClogs)
     * 1-Il va falloir privilégier le dé avec le plus de points de gloire car c'est l'objectif final
     * 2-Il va falloir privilégier ensuite le dé avec le plus de points de gold afin de maximiser les chances
     * d'avoir des points de gloire
     *
     * @return
     */
    @Override
    public int throwWhichDice() {
        int gold1 = 0, gold2 = 0, glory1 = 0, glory2 = 0;
        for (int i = 0; i < 6; i++) {
            String faceName1 = bot.getFirstDice().getFaces()[i].getName();
            String faceName2 = bot.getSecondDice().getFaces()[i].getName();
            if (faceName1.contains("Glory")) glory1++;
            if (faceName2.contains("Glory")) glory2++;
            if (faceName1.contains("Golden") && !faceName1.contains("Glory")) gold1++;
            if (faceName2.contains("Golden") && !faceName2.contains("Glory")) gold2++;
        }
        if (glory1 == 0 || glory2 == 0) {
            if (gold1 > gold2) return 0;
            else return 1;
        } else {
            if (glory1 > glory2) return 0;
            else return 1;
        }
    }
}
