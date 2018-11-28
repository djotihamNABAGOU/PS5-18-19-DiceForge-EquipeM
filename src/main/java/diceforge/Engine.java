package diceforge;

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
    void InitializingBots(Bot... tabBots) {
        switch (tabBots.length) {
            case 2:
                tabBots[0].getFirstDice().makeBrightDefaultDice();
                tabBots[0].getSecondDice().makeDarkDefaultDice();
                tabBots[0].getHerosInventory().makeFirstDefaultHerosInventory();
                tabBots[0].setActive(true);

                tabBots[1].getFirstDice().makeBrightDefaultDice();
                tabBots[1].getSecondDice().makeDarkDefaultDice();
                tabBots[1].getHerosInventory().makeSecondDefaultHerosInventory();
                break;

            case 3:
                tabBots[0].getFirstDice().makeBrightDefaultDice();
                tabBots[0].getSecondDice().makeDarkDefaultDice();
                tabBots[0].getHerosInventory().makeFirstDefaultHerosInventory();
                tabBots[0].setActive(true);

                tabBots[1].getFirstDice().makeBrightDefaultDice();
                tabBots[1].getSecondDice().makeDarkDefaultDice();
                tabBots[1].getHerosInventory().makeSecondDefaultHerosInventory();

                tabBots[2].getFirstDice().makeBrightDefaultDice();
                tabBots[2].getSecondDice().makeDarkDefaultDice();
                tabBots[2].getHerosInventory().makeThirdDefaultHerosInventory();
                break;

            case 4:
                tabBots[0].getFirstDice().makeBrightDefaultDice();
                tabBots[0].getSecondDice().makeDarkDefaultDice();
                tabBots[0].getHerosInventory().makeFirstDefaultHerosInventory();
                tabBots[0].setActive(true);

                tabBots[1].getFirstDice().makeBrightDefaultDice();
                tabBots[1].getSecondDice().makeDarkDefaultDice();
                tabBots[1].getHerosInventory().makeSecondDefaultHerosInventory();

                tabBots[2].getFirstDice().makeBrightDefaultDice();
                tabBots[2].getSecondDice().makeDarkDefaultDice();
                tabBots[2].getHerosInventory().makeThirdDefaultHerosInventory();

                tabBots[3].getFirstDice().makeBrightDefaultDice();
                tabBots[3].getSecondDice().makeDarkDefaultDice();
                tabBots[3].getHerosInventory().makeFourthDefaultHerosInventory();
                break;
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
     * ?????????
     * @param botOne
     * @param botTwo
     */
    public void RollAndRollSetTimes(Bot botOne, Bot botTwo) {
        for (int i = 0; i < this.set; i++) {
            botOne.getFirstDice().rollDice().makeEffect(botOne);
            botOne.getSecondDice().rollDice().makeEffect(botOne);
            botTwo.getFirstDice().rollDice().makeEffect(botTwo);
            botTwo.getSecondDice().rollDice().makeEffect(botTwo);
        }

    }

    /**
     * méthode de lancé de dé
     * @param theBot : joueur qui lance le dé
     */
    private void RollOneTime(Bot theBot) {
        theBot.getFirstDice().rollDice().makeEffect(theBot);
        theBot.getSecondDice().rollDice().makeEffect(theBot);
    }

    /**
     * méthode permettant de faire une manche
     * @param botOne
     * @param botTwo
     * @param temple
     * @param actionNumber
     */
    void MakeOneSetWithTwoBot(Bot botOne, Bot botTwo, Temple temple, int actionNumber) {

        System.out.println("-------->ROLL OF BOT 1");
        RollOneTime(botOne);//Lancé du dé et incrémentation des points d'inventaire
        botOne.getStrategy().apply(temple, 1, actionNumber);//Application de la stratégie du bot
        System.out.println("-------->ROLL OF BOT 2");
        RollOneTime(botTwo);
        botTwo.getStrategy().apply(temple, 2, actionNumber);

        System.out.println("\n");
        System.out.println("-------------------------------------\n");
        System.out.println("STATE AFTER " + (actionNumber) + " SET");
        System.out.println("-->BOT ONE");
        System.out.println(botOne.toString());
        botOne.printDiceState();
        System.out.println("-->BOT TWO");
        System.out.println(botTwo.toString());
        botTwo.printDiceState();
        System.out.println("\n");
    }

    void makeSets(Bot botOne, Bot botTwo, Temple temple) {
        for (int a = 0; a < this.set; a++) {
            MakeOneSetWithTwoBot(botOne, botTwo, temple, a + 1);
            //Changement du joueur actif
            botOne.setActive(!botOne.isActive());
            botTwo.setActive(!botTwo.isActive());
        }
    }

    void makeRound(Bot botOne, Bot botTwo, Temple temple){
        for (int i=0; i<this.round;i++){
            makeSets(botOne,botTwo,temple);
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
