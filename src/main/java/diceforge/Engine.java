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
     * @param set    : nombre de manches
     * @param number : nombres de bots
     */
    Engine(int round, int set, int number) {
        this.round = round;
        this.set = set;
        this.numberOfBot = number;
    }

    /**
     * Méthode d'initialisation des bots avec leurs 2 dés (clair et foncé) et un joueur actif
     * @param tabBots tableau de bots (compris entre 2 et 4 bots)
     */
    void InitializingBots(Bot... data) {  // prend en paramÃ¨tres une liste de robots
        int a = 0;
        int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
        for(Bot bot : data)
        {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            if(a == 0) bot.setActive(true);  // choisit comme actif le premier joueur
            a = a + 1;
            goldpoints = goldpoints - 1;  // decremente au fur et Ã  mesure  
        }
    }
    

    void initializingTemple(Temple temple){
        if (numberOfBot == 2){
            for (int i=0; i<10; i++){
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
     * méthode de lancé de dé
     * @param Les bots du jeu
     */
    private void RollOneTime(Temple temple,int actionNumber,Bot... data){
        
        ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
        int a = 0; // Compteur pour le parcours des faces 
        
        for(Bot bot : data)
        {
          listFaces[a].add(bot.getFirstDice().rollDice());
          listFaces[a].add(bot.getSecondDice().rollDice());
          a = a + 1;
        }  // AprÃ¨s la boucle, les faces obtenues sont stockÃ©es respectivement dans la liste 
        a = 0;
        int compteur = 0;
        
        /* le 1er attribut compteur permet de connaitre la position des des du joueur dans la liste
           de dess passees.
        */
        
        while(a<data.length) 
        {
            System.out.println("-------->ROLL OF BOT "+(compteur+1));
            listFaces[a].get(0).makeEffect(temple,compteur,data[compteur],listFaces);
            listFaces[a].get(1).makeEffect(temple,compteur,data[compteur],listFaces);
            data[compteur].getStrategy().apply(temple,(compteur+1),actionNumber);
            a = a + 1;
            compteur = compteur + 1;
        }

    }
    
     /**
     * mÃ©thode permettant de faire une manche
     * @param les bots
     * @param temple
     * @param actionNumber
     */
    void MakeOneSetWithBot(Temple temple, int actionNumber,Bot... data) {
        
        System.out.println("\n");
        System.out.println("-------------------------------------\n");
        RollOneTime(temple, actionNumber, data);
        for(int a=0;a<data.length;a++)
        {
             System.out.println("STATE AFTER " + (actionNumber) + " SET");
             System.out.println("-->BOT "+a);
             System.out.println(data[a].toString());
             data[a].printDiceState();
        }
        System.out.println("\n");
    }
    

    void makeSets(Temple temple,Bot... data) {
        for (int a = 0; a < this.set; a++) {
            MakeOneSetWithBot(temple, a + 1,data);
            //Changement du joueur actif
            for(Bot bot : data)
            {
                bot.setActive(!bot.isActive());
            }
        }
    }

    void makeRound(Bot botOne, Bot botTwo, Temple temple){
        for (int i=0; i<this.round;i++){
            makeSets(temple,botOne,botTwo);
            System.out.println("\n");
            System.out.println("DETERMINATING THE WINNER");
            TellMeTheWinner(botOne, botTwo);
        }
        System.out.println("**********Results*********");
        if (botOne.roundsWin > botTwo.roundsWin){
            System.out.println("Bot 1 wins the game with "+(((float)botOne.roundsWin/1000)*100)+"% of won rounds aigainst "+((float)botTwo.roundsWin/1000)*100+"% won rounds for the Bot 2. Congratulations Bot 1!");
        }
        if (botOne.roundsWin < botTwo.roundsWin){
            System.out.println("Bot 2 wins the game with "+((float)botTwo.roundsWin/1000)*100+"% of won rounds aigainst "+((float)botOne.roundsWin/1000)*100+"% won rounds for the Bot 1. Congratulations Bot 2!");
        }
        if (botOne.roundsWin == botTwo.roundsWin){
            System.out.println("No winner, It's a tie !");
        }
    }

    void TellMeTheWinner(Bot botOne, Bot botTwo) {
        if (botOne.getHerosInventory().getGloryPoints() > botTwo.getHerosInventory().getGloryPoints()) {
            botOne.roundsWin++;
            System.out.println("Bot 1 wins the round");
        }
        if (botOne.getHerosInventory().getGloryPoints() < botTwo.getHerosInventory().getGloryPoints()) {
            botTwo.roundsWin++;
            System.out.println("Bot 2 wins the round");
        }
        if (botOne.getHerosInventory().getGloryPoints() == botTwo.getHerosInventory().getGloryPoints()) {
            System.out.println("It's a tie");
        }
    }

}
