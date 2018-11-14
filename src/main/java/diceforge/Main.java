
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import Player.Bot;

/**
 *
 * @author Destroyer
 */
public class Main {
    public static void main(String [] args){

        System.out.println("********WELCOME TO DICE FORGE********\n");
        Engine engine = new Engine(9,2);
        Temple temple = new Temple();
        Bot botOne = new Bot();
        Bot botTwo = new Bot();
        
        engine.InitializingBots(botOne, botTwo);
        
        System.out.println("STATE BEFORE DICE SET");
        System.out.println("-->BOT ONE");
        System.out.println(botOne.toString());
        botOne.printDiceState();
        System.out.println("-->BOT TWO");
        System.out.println(botTwo.toString());
        botTwo.printDiceState();
        
        System.out.println("-------------------------------------\n");

        engine.MakeNineSetWithTwoBot(botOne, botTwo, temple);
        System.out.println("\n");
        System.out.println("DETERMINATING THE WINNER");
        engine.TellMeTheWinner(botOne, botTwo);

        
    }
    
}
