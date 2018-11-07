/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import java.time.Clock;
import java.util.Random;

/**
 *
 * @author PS5
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
    
    public void RollOneTime(Bot theBot){
         theBot.herosInventory.increaseInventoryWithDiceRoll(theBot.firstDice.rollDice());
         theBot.herosInventory.increaseInventoryWithDiceRoll(theBot.secondDice.rollDice());
    }
    
    public void MakeOneSetWithTwoBot(Bot botOne,Bot botTwo, Temple temple){
        for(int i=0; i<2; i++){
            System.out.println("Bot "+(i+1)+", Roll or Roll and Forge ?");
            Random randomInt = new Random();
            int number = randomInt.nextInt(2); // 0 for Roll and 1 for Forge
            if(i==0) number = 0;  // For this time 1st Bot will roll and 2nd Bot will forge
            
            
            if(i==0){ //First Bot ---> Actions
                      switch(number){
                                      case 0: 
                                          System.out.println("-------->ROLL");
                                          RollOneTime(botOne);
                                          break;
                                      case 1:
                                          System.out.println("-------->ROLL AND FORGE");
                                          RollOneTime(botOne);
                                          if(botOne.herosInventory.getGoldPoints()>=2)
                                          {
                                            SanctuarysFaces faceToForge = new SanctuarysFaces(3,"G",2); //Fixed face to forge
                                            if(temple.buyFace(faceToForge)){
                                                botOne.herosInventory.DecreaseGoldPoints(faceToForge.getPrice());
                                                botOne.forgeDiceFace(faceToForge);
                                            }else{
                                              System.out.println("Purchase failed");
                                          }
                                          }                                    
                                         break;
                      }
            }else{ // Second Bot ---> Actions (i==1)
                   switch(number){
                                      case 0:
                                          System.out.println("-------->ROLL");
                                          RollOneTime(botTwo);
                                          break;
                                      case 1:
                                          System.out.println("-------->ROLL AND FORGE");
                                          RollOneTime(botTwo);
                                          if(botTwo.herosInventory.getGoldPoints()>=2)
                                          {
                                            SanctuarysFaces faceToForge = new SanctuarysFaces(3,"G",2); //Fixed face to forge
                                            if(temple.buyFace(faceToForge)){
                                                botTwo.forgeDiceFace(faceToForge);
                                                botTwo.herosInventory.DecreaseGoldPoints(faceToForge.getPrice());
                                            }
                                            else{
                                              System.out.println("Purchase failed");
                                           }
                                          }
                                         break;
                      }      
            }
           
       
            
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
