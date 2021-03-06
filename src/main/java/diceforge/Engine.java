package diceforge;

import Faces.GeneralFace;
import Player.Bot;

import java.util.ArrayList;


public class Engine implements GlobalConstants {
    private final int round;//nombres de tours ou de parties
    private final int set; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    /**
     * Contructeur du moteur de jeu
     *
     * @param round  : nombres de parties
     * @param set    : nombre de manches
     * @param number : nombres de bots
     */
    public Engine(int round, int set, int number) {
        this.round = round;
        this.set = set;
        this.numberOfBot = number;
    }

    /**
     * Méthode d'initialisation des bots avec leurs 2 dés (clair et foncé) et un joueur actif
     *
     * @param data tableau de bots, contient le nombre de bots pour la partie
     */
    public void InitializingBots(Bot... data) {  // prend en paramètres une liste de robots
        int a = 0;
        int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
        for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            if (a == 0) bot.setActive(true);  // choisit comme actif le premier joueur
            a = a + 1;
            goldpoints = goldpoints - 1;  // decremente au fur et Ã  mesure  
        }
    }

    /**
     * Méthode de lancé de dé
     *
     * @param temple
     * @param actionNumber :
     * @param data         : nombres de bots du jeu
     */
    private void RollOneTime(Temple temple, Island island, int actionNumber, Bot... data) {

        ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
        int a = 0; // Compteur pour le parcours des faces 

        for (Bot bot : data) {
            listFaces[a] = new ArrayList<GeneralFace>();
            listFaces[a].add(bot.getFirstDice().rollDice());
            listFaces[a].add(bot.getSecondDice().rollDice());
            a = a + 1;
        }  // Après la boucle, les faces obtenues sont stockÃ©es respectivement dans la liste
        a = 0;

        // Utilisé pour répéter l"action des 2 faces , cas jeton cerbers
        int rep = 0;

        while (a < data.length) {
            Print.PrintMessage("-------->ROLL OF BOT " + (a + 1));
            if (data[a].isActive()) Print.PrintMessage("I'm active!");
            int val1 = listFaces[a].get(0).makeEffect(0, 1, temple, a, data[a], listFaces, data);
            int val2 = listFaces[a].get(1).makeEffect(0, 1, temple, a, data[a], listFaces, data);

            if (data[a].getHerosInventory().tokenCerberus > 0) {

                rep = data[a].getStrategy().useTokenCerberus(val1, val2);
                if (rep == 1) {
                    listFaces[a].get(0).makeEffect(0, 1, temple, a, data[a], listFaces, data);
                    listFaces[a].get(1).makeEffect(0, 1, temple, a, data[a], listFaces, data);
                }
            }

            data[a].getStrategy().apply(temple, island, a, listFaces, data);
            a = a + 1;
        }

        for (Bot bot : data) {
            bot.getFirstDice().resetFace();
            bot.getSecondDice().resetFace();
        }
    }

    /**
     * Méthode permettant de faire des manches
     *
     * @param temple
     * @param data
     */
    private void makeSets(Temple temple, Island island, Bot... data) {
        for (int a = 0; a < this.set; a++) {

            Print.PrintMessage("*************** SET : " + (a + 1)+" ***************");

            for (int e = 0; e < this.numberOfBot; e++) {

                for (int d = 0; d < data.length; d++) {
                    if (data[d].isActive()) {
                        Print.PrintMessage("Bot " + (d + 1) + " is active");
                    }
                }
                

                Print.PrintMessage("---------------ROUND " + (e + 1) + "---------------\n");
                RollOneTime(temple, island, e + 1, data);
                Print.PrintMessage();
                Print.PrintMessage("---------------STATE AFTER " + (e + 1) + " ROUND---------------");
                for (int i = 0; i < data.length; i++) {
                    Print.PrintMessage("-->BOT " + (i + 1));
                    Print.PrintMessage(data[i].toString());
                    //data[i].printDiceState();
                }
                Print.PrintMessage("\n");
                //Changement du joueur actif
                boolean findActiveBot = false;
                for (int i = 0; i < data.length; i++) {
                    if (findActiveBot) {        //le précédent était donc actif, il ne l'est plus
                        data[i].setActive(true);//il devient le nouveau joueur actif
                        break;
                    }
                    if (data[i].isActive()) {
                        findActiveBot = true;//On a trouvé le joueur actif
                        if (i == (data.length - 1))
                            data[0].setActive(true);//si on est sur le dernier joueur, on active le 1er
                        data[i].setActive(false);//il redevient inactif, le bot suivant sera alors actif
                    }
                }

            }
        }
    }

    /**
     * Méthode permettant de déterminer le gagnant d'une partie
     *
     * @param data
     */
    private void tellMeTheWinnerOfRound(Bot... data) {

        int winnerGloryPoints = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].getHerosInventory().getGloryPoints() > winnerGloryPoints) {
                winnerGloryPoints = data[i].getHerosInventory().getGloryPoints();
            }
        }

        //Stocke la liste de tous les bots qui ont le nombre de points gagnants
        ArrayList<Integer> listIndice = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i].getHerosInventory().getGloryPoints() == winnerGloryPoints) {
                listIndice.add(i);
                data[i].addGloryWinnerPoints(winnerGloryPoints);
            }
        }
        
        
        
        Print.PrintMessage("GloryWinPoints : " + data[listIndice.get(0)].getHerosInventory().getGloryPoints());
        if (listIndice.size() == 1) {
            Print.PrintMessage("Winner for the set, Bot " + (listIndice.get(0) + 1));
            Print.PrintMessage("Congratulations Bot " + (listIndice.get(0) + 1) + " !");
            data[listIndice.get(0)].wonRounds++;
        } else if (listIndice.size() == 2) {
            Print.PrintMessage("We have 2 winners for the set, Bot " + (listIndice.get(0) + 1) + " and Bot " + (listIndice.get(1) + 1) + ".");
            data[listIndice.get(0)].wonRounds++;
            data[listIndice.get(1)].wonRounds++;
        } else if (listIndice.size() == 3) {
            Print.PrintMessage("We have 3 winners for the set, Bot " + (listIndice.get(0) + 1) + ", Bot " + (listIndice.get(1) + 1) + " and Bot " + (listIndice.get(2) + 1) + ".");
            data[listIndice.get(0)].wonRounds++;
            data[listIndice.get(1)].wonRounds++;
            data[listIndice.get(2)].wonRounds++;
        } else {
            Print.PrintMessage("It's a tie, No winner for the set!");
        }
    }

    /**
     * Méthode permettant de déterminer le gagnant du jeu
     */
    private void tellMeTheWinnerOfTheGame(Bot... data) {

        int winnerWonRounds = 0;
        System.out.println("**********Results*********");
        for (int i = 0; i < data.length; i++) {
            System.out.println("Bot " + (i + 1) + " : " + (((float) data[i].wonRounds / this.round) * 100) + "% of won rounds.");
            if (data[i].wonRounds > winnerWonRounds) {
                winnerWonRounds = data[i].wonRounds;
            }
        }
        
        int moy = 0;
        //Stocke la liste de tous les bots qui ont le même ratio gagnant
        ArrayList<Integer> listIndice = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i].wonRounds == winnerWonRounds) {
                listIndice.add(i);
            }
        }
            
        
        
        if (listIndice.size() == 1) {
            System.out.println("Congratulations Bot " + (listIndice.get(0) + 1) + " !");
            System.out.println("Average Glory Points "+data[listIndice.get(0)].giveMeYourAverageGloryPoints());
        } else if (listIndice.size() == 2) {
            System.out.println("We have 2 winners.");
            System.out.println("Equality between Bot " + (listIndice.get(0) + 1) + " and Bot " + (listIndice.get(1) + 1) + ". Congratulations!");
        } else if (listIndice.size() == 3) {
            System.out.println("We have 3 winners.");
            System.out.println("Equality between Bot " + (listIndice.get(0) + 1) + ", Bot " + (listIndice.get(1) + 1) + " and Bot " + (listIndice.get(2) + 1) + ". Congratulations!");
        } else {
            System.out.println("It's a tie, No winner !");
        }
    }

    /**
     * Méthode permettant de faire des rounds
     *
     * @param data
     * @param temple
     */
    void makeRound(Temple temple, Island island, Bot... data) {
        for (int i = 0; i < this.round; i++) {

            if (i > 0) {
                //Affichage de l'état des bots
                for(Bot bot : data){
                    bot.initialize();
                }
                
                this.InitializingBots(data);
                temple = new Temple();
                island = new Island();
                temple.initializingTemple(data.length);
                island.initializeIsland(data.length);
                Print.PrintMessage("\nSTATE BEFORE ROUND "+(round+1));
                for(Bot bot : data){
                
                Print.PrintMessage("-->BOT ONE");
                Print.PrintMessage(bot.toString());
                bot.printDiceState();
     
                Print.PrintMessage("-------------------------------------\n");
                }
            }
            
            makeSets(temple, island, data);
            
            Print.PrintMessage("#####\tGAME PART " + (i + 1) + "\t#####");
            
            Print.PrintMessage("\n");
            for (int a = 0; a < data.length; a++) {
                Print.PrintMessage("-->BOT " + (a + 1));
                data[a].printDiceState();
            }
            Print.PrintMessage("DETERMINATING THE WINNER");
            tellMeTheWinnerOfRound(data);
        }
        tellMeTheWinnerOfTheGame(data);
    }

}
