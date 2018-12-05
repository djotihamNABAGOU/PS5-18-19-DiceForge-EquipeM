
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
    public static void main(String[] args) {

        System.out.println("********WELCOME TO DICE FORGE********\n");
        //Création des Bots
        Bot botOneRandom = new Bot("Random");
        Bot botTwoRandom = new Bot("Random");
        Bot botThreeNothing = new Bot("Nothing");
        Bot botFourNothing = new Bot("Nothing");

        //Création du moteur de jeu, du temple et de l'île
        Engine engine = new Engine(2, 10, 4);
        Temple temple = new Temple();

        //Initialisation des Bots, du temple et de l'île
        engine.InitializingBots(botOneRandom, botTwoRandom, botThreeNothing, botFourNothing);
        engine.initializingTemple(temple);

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
        engine.makeRound(temple, botOneRandom, botTwoRandom, botThreeNothing, botFourNothing);
    }

}
