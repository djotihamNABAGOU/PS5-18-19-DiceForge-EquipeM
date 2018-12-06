package diceforge;

import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Player.Bot;

import java.util.ArrayList;
import java.util.Random;


public class Engine {
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
     * Méthode d'initialisation du temple avec un nombre de faces dépendant du nombre de joueurs
     *
     * @param temple : temple
     */
    void initializingTemple(Temple temple) {
        if (numberOfBot == 2) {
            for (int i = 0; i < 10; i++) {
                ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
                int size = sanctuary[i].size();
                Random randomBassin = new Random();
                int condition = size - 2;//Il faut retirer 2 faces
                while (size != condition) {
                    int result = randomBassin.nextInt(size);
                    sanctuary[i].remove(result);
                    size--;
                }
            }
        }
    }

    /**
     * Méthode de lancé de dé
     *
     * @param temple
     * @param actionNumber :
     * @param data         : nombres de bots du jeu
     */
    private void RollOneTime(Temple temple, int actionNumber, Bot... data) {

        ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
        int a = 0; // Compteur pour le parcours des faces 

        for (Bot bot : data) {
            listFaces[a] = new ArrayList<GeneralFace>();
            listFaces[a].add(bot.getFirstDice().rollDice());
            listFaces[a].add(bot.getSecondDice().rollDice());
            a = a + 1;
        }  // Après la boucle, les faces obtenues sont stockÃ©es respectivement dans la liste
        a = 0;
        int compteur = 0;
        
        /* le 1er attribut compteur permet de connaitre la position des des du joueur dans la liste
           de dés passés.
        */

        while (a < data.length) {
            System.out.println("-------->ROLL OF BOT " + (compteur + 1));
            if (data[compteur].isActive()) System.out.println("I'm active!");
            listFaces[a].get(0).makeEffect(0, 1, temple, compteur, data[compteur], listFaces, data);
            listFaces[a].get(1).makeEffect(0, 1, temple, compteur, data[compteur], listFaces, data);
            data[compteur].getStrategy().apply(temple, compteur+1, listFaces, data);
            a = a + 1;
            compteur = compteur + 1;
        }
        
        for(Bot bot : data){
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
    private void makeSets(Temple temple, Bot... data) {
        for (int a = 0; a < this.set; a++) {
            System.out.println("---------------SET " + (a + 1) + "---------------\n");
            RollOneTime(temple, a + 1, data);
            System.out.println();
            System.out.println("---------------STATE AFTER " + (a + 1) + " SET---------------");
            for (int i = 0; i < data.length; i++) {
                System.out.println("-->BOT " + (i + 1));
                System.out.println(data[i].toString());
                //data[i].printDiceState();
            }
            System.out.println("\n");
            //Changement du joueur actif
            boolean findActiveBot = false;
            for (int i=0;i<data.length;i++) {
                if (findActiveBot ){//le précédent était donc actif, il ne l'est plus
                    data[i].setActive(true);//il devient le nouveau joueur actif
                    break;
                }
                if (data[i].isActive()){
                    findActiveBot = true;//On a trouvé le joueur actif
                    if (i == (data.length-1)) data[0].setActive(true);//si on est sur le dernier joueur, on active le 1er
                    data[i].setActive(false);//il redevient inactif, le bot suivant sera alors actif
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
        for(int i=0;i<data.length;i++){
            if(data[i].getHerosInventory().getGloryPoints()>winnerGloryPoints){
               winnerGloryPoints = data[i].getHerosInventory().getGloryPoints();
            }
        }

        //Stocke la liste de tous les bots qui ont le nombre de points gagnants
        ArrayList<Integer> listIndice = new ArrayList<>();  
        for(int i=0;i<data.length;i++){
            if(data[i].getHerosInventory().getGloryPoints()==winnerGloryPoints){
               listIndice.add(i);
            }
        }

        if (listIndice.size() == 1){    
           System.out.println("Winner for the round, Bot " + (listIndice.get(0)+1)); 
           System.out.println("Congratulations Bot " + (listIndice.get(0)+1) + " !");
           data[listIndice.get(0)].wonRounds++;     
        }else if(listIndice.size()==2){     
           System.out.println("We have 2 winners for the round, Bot " + (listIndice.get(0)+1) + " and Bot " + (listIndice.get(1)+1) + ".");
           data[listIndice.get(0)].wonRounds++;
           data[listIndice.get(1)].wonRounds++;   
        }else if(listIndice.size() == 3){  
           System.out.println("We have 3 winners for the round, Bot " + (listIndice.get(0)+1) + ", Bot " + (listIndice.get(1)+1) + " and Bot " + (listIndice.get(2)+1) + ".");
           data[listIndice.get(0)].wonRounds++;
           data[listIndice.get(1)].wonRounds++;
           data[listIndice.get(2)].wonRounds++;  
        }else {
           System.out.println("It's a tie, No winner for the round!");
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
        //Stocke la liste de tous les bots qui ont le même ratio gagnant
        ArrayList<Integer> listIndice = new ArrayList<>();  
        for(int i=0;i<data.length;i++){
            if(data[i].wonRounds == winnerWonRounds){
               listIndice.add(i);
            }
        }  
        if (listIndice.size() == 1) {      
           System.out.println("Congratulations Bot " + (listIndice.get(0)+1) + " !");
        }else if(listIndice.size()==2){     
           System.out.println("We have 2 winners.");
           System.out.println("Equality between Bot " + (listIndice.get(0)+1) + " and Bot " + (listIndice.get(1)+1) + ". Congratulations!");
        }else if(listIndice.size() == 3){       
           System.out.println("We have 3 winners.");
           System.out.println("Equality between Bot " + (listIndice.get(0)+1) + ", Bot " + (listIndice.get(1)+1) + " and Bot " + (listIndice.get(2)+1) + ". Congratulations!");
        }else {
           System.out.println("It's a tie, No winner !");
        }
    }

    /**
     * Méthode permettant de faire des rounds
     *
     * @param data
     * @param temple
     */
    void makeRound(Temple temple, Bot... data) {
        for (int i = 0; i < this.round; i++) {
            System.out.println("#####\tROUND " + (i + 1) + "\t#####");
            makeSets(temple, data);
            System.out.println("\n");
            System.out.println("DETERMINATING THE WINNER");
            tellMeTheWinnerOfRound(data);
        }
        tellMeTheWinnerOfTheGame(data);
    }

}
