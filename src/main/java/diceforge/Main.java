
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

/**
 *
 * @author Destroyer
 */
public class Main {
    public static void main(String [] args){
        
        System.out.println("********WELCOME TO DICE FORGE********\n");
        Engine engine = new Engine(1,2);
        Temple temple = new Temple();
        Bot botOne = new Bot();
        Bot botTwo = new Bot();
        
        engine.InitializingBots(botOne, botTwo);
        
        System.out.println("STATE BEFORE DICE SET");
        System.out.println("-->BOT ONE");
        botOne.printBotInventoryState();
        botOne.printDiceState();
        System.out.println("-->BOT TWO");
        botTwo.printBotInventoryState();
        botTwo.printDiceState();
        
        System.out.println("-------------------------------------\n");
        engine.MakeOneSetWithTwoBot(botOne, botTwo, temple);
        System.out.println("\n");
        System.out.println("-------------------------------------\n");
        System.out.println("STATE AFTER SET");
        System.out.println("-->BOT ONE");
        botOne.printBotInventoryState();
        botOne.printDiceState();
        System.out.println("-->BOT TWO");
        botTwo.printBotInventoryState();
        botTwo.printDiceState();
        
        System.out.println("\n");
        System.out.println("DETERMINATING THE WINNER");
        engine.TellMeTheWinner(botOne, botTwo);
        
    }
    
}