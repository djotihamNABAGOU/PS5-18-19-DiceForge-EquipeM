
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import Player.Bot;

/**
 * @author The Beginners
 */
public class Main {
    //nombre de joueurs
    private static final int numberOfBots = 4;
    //Création des Bots
    private static final Bot botOneRandom = new Bot("Nothing");
    private static final Bot botTwoRandom = new Bot("Nothing");
    private static final Bot botThreeNothing = new Bot("Advanced");
    private static final Bot botFourNothing = new Bot("Immediat");
    //Création du moteur de jeu, du temple et de l'île
    private static final Engine engine = new Engine(10, 10, numberOfBots);
    private static final Temple temple = new Temple();
    private static final Island island = new Island();

    public static void main(String[] args) {

        System.out.println("********WELCOME TO DICE FORGE********\n");

        //Initialisation des Bots, du temple et de l'île
        engine.InitializingBots(botOneRandom, botTwoRandom, botThreeNothing, botFourNothing);
        temple.initializingTemple(numberOfBots);
        island.initializeIsland(numberOfBots);

        //Affichage de l'état des bots
        System.out.println("STATE BEFORE DICE SET");
        System.out.println("-->BOT ONE");
        System.out.println(botOneRandom.toString());
        botOneRandom.printDiceState();
        System.out.println("-->BOT TWO");
        System.out.println(botTwoRandom.toString());
        botTwoRandom.printDiceState();
        System.out.println("-->BOT THREE");
        System.out.println(botThreeNothing.toString());
        botThreeNothing.printDiceState();
        System.out.println("-->BOT FOUR");
        System.out.println(botFourNothing.toString());
        botFourNothing.printDiceState();
        System.out.println("-------------------------------------\n");
        //Lancement du jeu
        engine.makeRound(temple, island, botOneRandom, botTwoRandom, botThreeNothing, botFourNothing);

    }

}
