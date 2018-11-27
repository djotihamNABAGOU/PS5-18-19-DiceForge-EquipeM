package diceforge;

import Faces.SanctuarysFaces;
import Player.Bot;

import java.util.ArrayList;
import java.util.Random;


public class Engine {
    private final int set; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    /**
     * Contructeur du moteur de jeu
     *
     * @param set    : nombre de manches
     * @param number : nombres de bots
     */
    Engine(int set, int number) {
        this.set = set;
        this.numberOfBot = number;

    }

    /**
     * Méthode d'initialisation des bots avec leurs 2 dés (clair et foncé) et un joueur actif
     * @param botOne : premier joueur
     * @param botTwo : deuxième joueur
     */
    void InitializingBots(Bot botOne, Bot botTwo) {
        botOne.getFirstDice().makeBrightDefaultDice();
        botOne.getSecondDice().makeDarkDefaultDice();
        botOne.getHerosInventory().makeFirstDefaultHerosInventory();
        botOne.setActive(true);

        botTwo.getFirstDice().makeBrightDefaultDice();
        botTwo.getSecondDice().makeDarkDefaultDice();
        botTwo.getHerosInventory().makeSecondDefaultHerosInventory();

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

    void MakeNineSetWithTwoBot(Bot botOne, Bot botTwo, Temple temple) {
        for (int a = 0; a < 9; a++) {
            MakeOneSetWithTwoBot(botOne, botTwo, temple, a + 1);
            //Changement du joueur actif
            botOne.setActive(!botOne.isActive());
            botTwo.setActive(!botTwo.isActive());
        }
    }

    void TellMeTheWinner(Bot botOne, Bot botTwo) {
        if (botOne.getHerosInventory().getGloryPoints() > botTwo.getHerosInventory().getGloryPoints()) {
            System.out.println("Bot 1 wins the game");
        }
        if (botOne.getHerosInventory().getGloryPoints() < botTwo.getHerosInventory().getGloryPoints()) {
            System.out.println("Bot 2 wins the game");
        }
        if (botOne.getHerosInventory().getGloryPoints() == botTwo.getHerosInventory().getGloryPoints()) {
            System.out.println("It's a tie");
        }
    }

}
