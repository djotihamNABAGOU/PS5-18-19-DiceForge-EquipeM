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
        
        System.out.println("********WELCOME TO DICE FORGE********");
        Engine engine = new Engine(1,2);
        Bot botOne = new Bot();
        Bot botTwo = new Bot();
        
        engine.InitializingBots(botOne, botTwo);
        System.out.println("STATE BEFORE DICE ROLLING");
        System.out.println("BOT ONE");
        botOne.printBotInventoryState();
        System.out.println("BOT TWO");
        botTwo.printBotInventoryState();
       
        
        System.out.println("--------------------------------");
        
        botOne.herosInventory.increaseInventoryWithDiceRoll(botOne.firstDice.rollDice());
        botTwo.herosInventory.increaseInventoryWithDiceRoll(botTwo.firstDice.rollDice());
        System.out.println("STATE AFTER DICE ROLLING");
        System.out.println("BOT ONE");
        botOne.printBotInventoryState();
        System.out.println("BOT TWO");
        botTwo.printBotInventoryState();
        
        System.out.println("DETERMINATING THE WINNER");
        engine.TellMeTheWinner(botOne, botTwo);
        
    }
    
}
