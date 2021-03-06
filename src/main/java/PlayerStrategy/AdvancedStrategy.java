package PlayerStrategy;

import Card.Card;
import Card.Reinforcement;
import Faces.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.GlobalConstants;
import diceforge.Island;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * 1-On décide de privilégier les points de gloire par rapport aux autres types de points pour cette stratégie
 * 2-On décide également de privilégier l'exploit à la forge pour maximiser les gains
 */
public class AdvancedStrategy extends Strategy implements GlobalConstants {

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
                    Print.PrintMessage("\t->ENHANCEMENT<-");
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
                        Print.PrintMessage("*ACTION OF BOT NUMBER " + (numberOfTheBot+1) + ": FORGE");
                    else Print.PrintMessage("**SUP ACTION FOR BOT NUMBER " + (numberOfTheBot+1) + ": FORGE");

                    //Tant qu'il a les ressources, il forge plusieurs faces de sanctuaire
                    SanctuarysFaces face;
                    int nbPurchase = 1;//indice de forge

                    forgeHowManyTimes(temple, 0);

                    //Fin forge, Action supplémentaire si joueur actif
                    if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        bot.getHerosInventory().DecreaseSunPoints(2);//Il paie
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
                        Print.PrintMessage("*ACTION OF BOT NUMBER " + (numberOfTheBot+1) + ": FEAT(Exploit)");
                    else Print.PrintMessage("**SUP ACTION FOR BOT NUMBER " + (numberOfTheBot+1) + ": FEAT(Exploit)");

                    Card card;
                    if (!(card = bestCardToBuy(potentialCardsToBuy)).getName().equals("")) {
                        feat(card, temple, island, bot, numberOfTheBot, listFaces, data);
                    }

                    //Fin exploit, Action supplémentaire si joueur actif
                    if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        //il l'effectue
                        bot.getHerosInventory().DecreaseSunPoints(2);//Mais d'abord, Il paie comme dab
                        supActionDone = true;
                        apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }
                    break;
                default:
                    Print.PrintMessage("Problem with the potential cards to buy !!!");
            }


        }
    }


    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'APPEL DES RENFORTS       *********************/
    /***********************************************************************************************/

    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //ici, il faut privilégier les points de gloire
        int size = bot.getReinforcementCard().size();
        //if (size == 3) {
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
        //}
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
            Print.PrintMessage("Not enough gold to apply TheFormer effect card");
            return 1;
        }
    }


    /************************************************************************************************/
    /*********************       METHODES CONCERNANT LA FORGE         ******************************/
    /***********************************************************************************************/

    /**
     * Permet au bot de remplacer une face de son dé avec une nouvelle face venant du sanctuaire
     * Mais il faudra qu'il enlève les faces les moins avantageuses
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
     * retourne le nom de la face à payer choisie après analyse de la meilleure option selon les ressources disponibles pour payer la face
     *
     * @param facesAvailable
     * @return la face à payer
     */
    @Override
    public SanctuarysFaces FaceToBuy(ArrayList<SanctuarysFaces> facesAvailable) {
        Print.PrintMessage("coucou while");
        for (int a = 0; a < facesAvailable.size(); a++) {
            Print.PrintMessage("je peux payer "+facesAvailable.get(a).toString());
        }
        int price = 0;
        //Recherche du meilleur prix
        for (int a = 0; a < facesAvailable.size(); a++) {
            if (facesAvailable.get(a).getPrice() > price) {
                price = facesAvailable.get(a).getPrice();
            }
        }
        //Recherche de toutes les occurences de meilleur prix
        ArrayList<SanctuarysFaces> bestFaces = new ArrayList<>();
        for (int a = 0; a < facesAvailable.size(); a++) {
            if (facesAvailable.get(a).getPrice() == price) {
                bestFaces.add(facesAvailable.get(a));
            }
        }
        /*Recherche de la meilleure face en présence de deux faces d'un même prix mais de bassins différents.
        cas typique des faces simples*/
        if (bestFaces.size() == 0) return new SanctuarysFaces();
        else {
            int index = -1;
            ArrayList<GeneralFace> bestGeneralFaces = new ArrayList<>();
            for (SanctuarysFaces face: bestFaces) {
                bestGeneralFaces.add(face);
            }
            index = giveMeYourChoice(bestGeneralFaces,2);

            Print.PrintMessage("La face à payer est " + bestFaces.get(index).toString());
            return bestFaces.get(index);
        }

    }

    /**
     * Cherche les faces qui procurent seulement un seul type de ressource bien déterminé
     *
     * @param list
     * @param a
     * @param b
     * @param c
     * @return
     */
    private ArrayList<GeneralFace> searchFace(ArrayList<GeneralFace> list, int a, int b, int c) {
        ArrayList<GeneralFace> listFace = new ArrayList<>();
        for (GeneralFace face : list) {
            ArrayList<String> properties = face.getProperties();
            if ((properties.size() != 1) && (Integer.valueOf(properties.get(a)) == 0) && (Integer.valueOf(properties.get(b)) == 0)
                    && (Integer.valueOf(properties.get(c)) == 0)) {
                listFace.add(face);
            }
        }
        return listFace;
    }

    // Cherche les faces qui offrent soit un choix seulement / soit plusieurs ressources
    private ArrayList<SanctuarysFaces> searchAddOrChoiceFace(ArrayList<SanctuarysFaces> list, String mode) {
        ArrayList<SanctuarysFaces> listFace = new ArrayList<>();
        for (SanctuarysFaces face : list) {
            ArrayList<String> properties = face.getProperties();
            if (properties.get(4).equals(mode)) {
                //pour éviter d"avoir les faces qui procurent un seul gain seulemnt pr mode Add
                int compteur = 0;
                for (int a = 0; a < 3; a++) {
                    if (Integer.valueOf(properties.get(a)) != 0) {
                        compteur = compteur + 1;
                    }
                }
                if (compteur >= 2)   // procure au moins 2 ressources
                    listFace.add(face);
            }
        }
        return listFace;
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
            Print.PrintMessage(potentialCardsToBuy.get(a).toString());
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
                        Print.PrintMessage(bestIndex);
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
     *
     * 2-en exploit, on choisit de privilégier aussi les points de gloire. en cas d'égalité entre deux faces
     * on départage en privilégiant le gold pour l'achat des faces
     *
     * 3- en cas de forge, on privilégie aussi d'abord les faces contenant des points de gloire,
     * en cas d'égalité entre deux faces, on privilégie la face la moins présente sur le dé (cas typique des faces simples)
     *
     * @param Offered     liste de faces
     * @param whichAction 0 pour le lancé de dés, 1 pour exploit, 2 pour forge
     */
    @Override
    public int giveMeYourChoice(ArrayList<GeneralFace> Offered, int whichAction) {
        int size = Offered.size(), choice = -1;
        int goldIndex = 0, sunIndex = 0, moonIndex = 0;

        switch (whichAction) {
            case 0:
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
                break;
            case 1:
                for (int i = 0; i < size; i++) {
                    if (Offered.get(i).getName().equals("GloryFace")) choice = i;
                }
                break;
            case 2:
                for (int i = 0; i < size; i++) {
                    if (Offered.get(i).getName().equals("GloryFace")) {
                        return i;
                    } else {
                        if (Offered.get(i).getName().equals("GoldenFace")) goldIndex = i;
                        if (Offered.get(i).getName().equals("SunFace")) sunIndex = i;
                        if (Offered.get(i).getName().equals("MoonFace")) moonIndex = i;
                    }
                }

                //Ici on est sûr à 100% que ce sont des faces simples de sanctuaire, on peut donc caster
                ArrayList<SanctuarysFaces> offeredSimpleFaces = new ArrayList<>();
                for (GeneralFace face: Offered) {
                    offeredSimpleFaces.add((SanctuarysFaces) face);
                }


                //Premier dé du bot, recherche du nombre d'occurences d'une face que possède un dé
                int goldOccur, sunOccur, moonOccur;
                ArrayList<GeneralFace> diceFaces1 = new ArrayList<>();
                for (GeneralFace face: bot.getFirstDice().getFaces()) {
                    diceFaces1.add(face);
                }

                //Deuxième dé du bot, recherche du nombre d'occurences d'une face que possède un dé
                int goldOccur2, sunOccur2, moonOccur2;
                ArrayList<GeneralFace> diceFaces2 = new ArrayList<>();
                for (GeneralFace face: bot.getSecondDice().getFaces()) {
                    diceFaces2.add(face);
                }

                //On cherche le nombre d'occurences de chaque face sur le dé 1
                goldOccur = searchFace(diceFaces1,1,2,3).size();
                sunOccur = searchFace(diceFaces1,0, 2,3).size();
                moonOccur = searchFace(diceFaces1,0,1,3).size();

                //On cherche le nombre d'occurences de chaque face sur le dé 1
                goldOccur2 = searchFace(diceFaces2,1,2,3).size();
                sunOccur2 = searchFace(diceFaces2,0, 2,3).size();
                moonOccur2 = searchFace(diceFaces2,0,1,3).size();

                //On choisit le dé sur lequel forger
                int dice1 = 0, dice2 =0;
                if (goldOccur < goldOccur2) dice1++;
                else dice2++;
                if (sunOccur < sunOccur2) dice1++;
                else dice2++;
                if (moonOccur < moonOccur2) dice1++;
                else dice2++;
                int finalGoldOccur, finalSunOccur, finalMoonOccur;
                if (dice1 < dice2) {
                    finalGoldOccur = goldOccur;
                    finalSunOccur = sunOccur;
                    finalMoonOccur = moonOccur;
                }else {
                    finalGoldOccur = goldOccur2;
                    finalSunOccur = sunOccur2;
                    finalMoonOccur = moonOccur2;
                }

                //On choisit ensuite quelle face forger
                if (finalGoldOccur < finalSunOccur) {
                    if (finalGoldOccur < finalMoonOccur) choice = goldIndex;
                    else {
                        if (finalGoldOccur == finalMoonOccur) choice = goldIndex;//pour privilégier l'achat des faces
                        else choice = moonIndex;
                    }
                } else {
                    if (finalGoldOccur == finalSunOccur) {
                        if (finalGoldOccur < finalMoonOccur) choice = goldIndex;//pour privilégier l'achat des faces
                        else choice = moonIndex;
                    } else {
                        if (finalSunOccur < finalMoonOccur) choice = sunIndex;
                        else {
                            if (finalSunOccur == finalMoonOccur) {
                                Random random = new Random();
                                int number = random.nextInt(2);//0 pour sun et 1 pour moon
                                if (number == 0) choice = sunIndex;
                                else choice = moonIndex;
                            } else choice = moonIndex;
                        }
                    }
                }
                break;
            default:
                Print.PrintMessage("Unknown the situation !!!");
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
