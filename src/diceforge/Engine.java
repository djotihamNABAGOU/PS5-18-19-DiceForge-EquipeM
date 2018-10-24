
package diceforge;


public class Engine {
    private final int set ; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    public Engine(int set, int number) {
        this.set=set;
        this.numberOfBot=number;
    }
    
    public void InitializingBots(Bot botOne,Bot botTwo){
        botOne.firstDice.makeBrightDefaultDice();
        botOne.secondDice.makeDarkDefaultDice();
        botOne.herosInventory.makeFirstDefaultHerosInventory();
        
        botTwo.firstDice.makeBrightDefaultDice();
        botTwo.secondDice.makeDarkDefaultDice();
        botTwo.herosInventory.makeSecondDefaultHerosInventory();
             
    }
    
    public void RollAndRollSetTimes(Bot botOne,Bot botTwo){
        for(int i=0;i<this.set;i++){
            botOne.herosInventory.increaseInventoryWithDiceRoll(botOne.firstDice.rollDice());
            botOne.herosInventory.increaseInventoryWithDiceRoll(botOne.secondDice.rollDice());
            botTwo.herosInventory.increaseInventoryWithDiceRoll(botTwo.firstDice.rollDice());
            botTwo.herosInventory.increaseInventoryWithDiceRoll(botTwo.secondDice.rollDice());
        }
        
    }
    
    public void TellMeTheWinner(Bot botOne,Bot botTwo){
        if(botOne.herosInventory.getGloryPoints()>botTwo.herosInventory.getGloryPoints()){
            System.out.println("Bot 1 wins the game");
        }
        if(botOne.herosInventory.getGloryPoints()<botTwo.herosInventory.getGloryPoints()){
            System.out.println("Bot 2 wins the game");
        }
        if(botOne.herosInventory.getGloryPoints()==botTwo.herosInventory.getGloryPoints()){
            System.out.println("Its a tie");
        }
      }


   
}
