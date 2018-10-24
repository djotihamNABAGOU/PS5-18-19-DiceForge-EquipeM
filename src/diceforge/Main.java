
package diceforge;


public class Main {
    public static void main(String [] args){
        
        System.out.println("********WELCOME TO DICE FORGE********");
        Engine engine = new Engine(9,2);
        Bot botOne = new Bot();
        Bot botTwo = new Bot();
        
        engine.InitializingBots(botOne, botTwo);
        
        System.out.println("STATE BEFORE DICE ROLLING");
        System.out.println("BOT ONE");
        botOne.printBotInventoryState();
        System.out.println("BOT TWO");
        botTwo.printBotInventoryState();
        
        System.out.println("-------------------------------------");
        engine.RollAndRollSetTimes(botOne, botTwo);  // Every bots roll set(int) times his both dices
        System.out.println("STATE AFTER DICE ROLLING");
        System.out.println("BOT ONE");
        botOne.printBotInventoryState();
        System.out.println("BOT TWO");
        botTwo.printBotInventoryState();
        
        System.out.println("DETERMINATING THE WINNER");
        engine.TellMeTheWinner(botOne, botTwo);
        
    }
    
}
