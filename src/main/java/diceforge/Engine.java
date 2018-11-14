/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Random;

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
        botOne.getFirstDice().makeBrightDefaultDice();
        botOne.getSecondDice().makeDarkDefaultDice();
        botOne.getHerosInventory().makeFirstDefaultHerosInventory();

        botTwo.getFirstDice().makeBrightDefaultDice();
        botTwo.getSecondDice().makeDarkDefaultDice();
        botTwo.getHerosInventory().makeSecondDefaultHerosInventory();

    }

    public void RollAndRollSetTimes(Bot botOne,Bot botTwo){
        for(int i=0;i<this.set;i++){
            botOne.getHerosInventory().increaseInventoryWithDiceRoll(botOne.getFirstDice().rollDice());
            botOne.getHerosInventory().increaseInventoryWithDiceRoll(botOne.getSecondDice().rollDice());
            botTwo.getHerosInventory().increaseInventoryWithDiceRoll(botTwo.getFirstDice().rollDice());
            botTwo.getHerosInventory().increaseInventoryWithDiceRoll(botTwo.getSecondDice().rollDice());
        }

    }

    public void RollOneTime(Bot theBot){
         theBot.getHerosInventory().increaseInventoryWithDiceRoll(theBot.getFirstDice().rollDice());
         theBot.getHerosInventory().increaseInventoryWithDiceRoll(theBot.getSecondDice().rollDice());
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
                                          SanctuarysFaces face = CaseFace(botOne,temple);
                                          if(face.getPrice()!=0)
                                          {
                                              if(temple.buyFace(face)){
                                              botOne.forgeDiceFace(face);
                                              botOne.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                                               }
                                            else{
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
                                          SanctuarysFaces face = CaseFace(botTwo,temple);
                                          
                                          if(face.getPrice()!=0)
                                          {
                                            if(temple.buyFace(face)){
                                                botTwo.forgeDiceFace(face);
                                                botTwo.getHerosInventory().DecreaseGoldPoints(face.getPrice());
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

    public void MakeNineSetWithTwoBot(Bot botOne,Bot botTwo, Temple temple){
        for(int a = 0;a<9;a++)
        {
            MakeOneSetWithTwoBot(botOne, botTwo, temple);
            System.out.println("\n");
            System.out.println("-------------------------------------\n");
            System.out.println("STATE AFTER "+(a+1)+" SET");
            System.out.println("-->BOT ONE");
            botOne.toString();
            botOne.printDiceState();
            System.out.println("-->BOT TWO");
            botTwo.toString();
            botTwo.printDiceState();
            System.out.println("\n");
        }
    }
    
    public SanctuarysFaces CaseFace(Bot bot, Temple temple)  // retourne le nom de la face à payer choisie au hasard
    {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        for (int i=0; i<temple.getSanctuary().size();i++){
            if(temple.getSanctuary().get(i).isSelected()==false && !FacesAvailable.contains(temple.getSanctuary().get(i)) && v>=temple.getSanctuary().get(i).getPrice()){
                FacesAvailable.add(temple.getSanctuary().get(i));
            }
        }

        Random randomFace = new Random();

        if(FacesAvailable.size() == 0) return new SanctuarysFaces(0,"",0);
        else{
        int caseFace = randomFace.nextInt(FacesAvailable.size()); // initialisation
        return FacesAvailable.get(caseFace);
        }

    }
    
    public void TellMeTheWinner(Bot botOne,Bot botTwo){
        if(botOne.getHerosInventory().getGloryPoints()>botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Bot 1 wins the game");
        }
        if(botOne.getHerosInventory().getGloryPoints()<botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Bot 2 wins the game");
        }
        if(botOne.getHerosInventory().getGloryPoints()==botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Its a tie");
        }
      }
   
}
