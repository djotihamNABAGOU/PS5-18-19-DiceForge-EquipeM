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
public class Engine {
    private final int set ; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    public Engine(int set, int number) {
        this.set=set;
        this.numberOfBot=number;
    }
    
    public void InitializingBots(Bot botOne,Bot botTwo){
        botOne.firstDice.makeBrightDefaultDice();
        botOne.herosInventory.makeFirstDefaultHerosInventory();
        
        botTwo.firstDice.makeBrightDefaultDice();
        botTwo.herosInventory.makeSecondDefaultHerosInventory();
             
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
