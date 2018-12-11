
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
public class Main implements GlobalConstants {
    //nombre de joueurs
    private static final int numberOfBots = 4;
    //Création des Bots
    private static final Bot botOneRandom = new Bot("Nothing");
    private static final Bot botTwoRandom = new Bot("Random");
    private static final Bot botThreeNothing = new Bot("Immediat");
    private static final Bot botFourNothing = new Bot("Advanced");
    //Création du moteur de jeu, du temple et de l'île
    private static final Engine engine = new Engine(1000, 10, numberOfBots);
    private static final Temple temple = new Temple();
    private static final Island island = new Island();

    public static void main(String[] args) {

        Print.PrintMessage("********WELCOME TO DICE FORGE********\n");

        //Initialisation des Bots, du temple et de l'île
        engine.InitializingBots(botOneRandom, botTwoRandom, botThreeNothing,botFourNothing);
        temple.initializingTemple(numberOfBots);
        island.initializeIsland(numberOfBots);

        //Affichage de l'état des bots
        Print.PrintMessage("STATE BEFORE DICE SET");
        Print.PrintMessage("-->BOT ONE");
        Print.PrintMessage(botOneRandom.toString());
        botOneRandom.printDiceState();
        Print.PrintMessage("-->BOT TWO");
        Print.PrintMessage(botTwoRandom.toString());
        botTwoRandom.printDiceState();
        Print.PrintMessage("-->BOT THREE");
        Print.PrintMessage(botThreeNothing.toString());
        botThreeNothing.printDiceState();
        Print.PrintMessage("-->BOT FOUR");
        Print.PrintMessage(botFourNothing.toString());
        botFourNothing.printDiceState();
        Print.PrintMessage("-------------------------------------\n");
        //Lancement du jeu
        engine.makeRound(temple, island, botOneRandom, botTwoRandom,botThreeNothing, botFourNothing);

    }

}
