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
    ArrayList<String> FacesAvailable = new ArrayList<>(); 
    
    public Engine(int set, int number) {
        this.set=set;
        this.numberOfBot=number;
        //Ajout de toutes les differentes faces du JEU suivant [valeur-Type-Prix]
        FacesAvailable.add("3-G-2");  // 2 pieces Or   
        FacesAvailable.add("1-M-2");  // 2 pieces Or
        FacesAvailable.add("4-G-3");  // 3 pieces Or
        FacesAvailable.add("1-S-3");  // 3 pieces Or
        FacesAvailable.add("6-G-4");  // 4 pieces Or
        FacesAvailable.add("1-M/S/G-4"); // 4 pieces Or
        FacesAvailable.add("3-2G+1M-4"); // 4 pieces Or
        FacesAvailable.add("2-1Gl+1S-4"); // 4 pieces Or
        FacesAvailable.add("0-3G/2Gl-5"); // 5 pieces Or
        FacesAvailable.add("2-M-6"); // 6 pieces Or
        FacesAvailable.add("2-S-8"); // 8 pieces Or
        FacesAvailable.add("3-Gl-8"); // 8 pieces Or
        FacesAvailable.add("4-ALL-12"); // 12 pieces Or
        FacesAvailable.add("4-Gl-12"); // 12 pieces Or
        FacesAvailable.add("1-2G/2S/2M-12"); // 12 pieces Or
        FacesAvailable.add("2-2Gl+2M-12"); // 12 pieces Or
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
                                          String name = CaseFace(botOne);  // obtenir la face à payer
                                          if(name!="")
                                          {
                                            String tab[] = name.split("-"); // degager valeur,type et prix
                                            SanctuarysFaces faceToForge = new SanctuarysFaces(Integer.valueOf(tab[0]).intValue(),tab[1],Integer.valueOf(tab[2]).intValue()); //Fixed face to forge
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
                                          String name = CaseFace(botTwo);
                                          
                                          if(name!="")
                                          {
                                          String tab[] = name.split("-");
                                          //System.out.println(tab[0]);
                                          //System.out.println(tab[1]);
                                          //System.out.println(tab[2]);
                                          SanctuarysFaces faceToForge = new SanctuarysFaces(Integer.valueOf(tab[0]).intValue(),tab[1],Integer.valueOf(tab[2]).intValue());
                                          
                                          
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
    
    public String CaseFace(Bot bot)  // retourne le nom de la face à payer choisie au hasard
    {
        Random randomFace = new Random();
        int caseFace = 18;  // initialisation
      
        int v = bot.herosInventory.getGoldPoints(); // Nombre de pieces d'or du Joueur
        System.out.println("or = "+v);
        if(v>=12) caseFace = randomFace.nextInt(16);   // choix entre toutes les faces
        else if (v>=8)  caseFace = randomFace.nextInt(12); // choix entre les 12 premieres faces
        else if (v>=6) caseFace = randomFace.nextInt(10); // choix entre les 10 premieres faces
        else if (v>=5) caseFace = randomFace.nextInt(9);
        else if (v>=4) caseFace = randomFace.nextInt(8);
        else if (v>=3) caseFace = randomFace.nextInt(4);
        else if (v>=2) caseFace = randomFace.nextInt(2);
       
        if (caseFace==18)
            return ""; //pas assez de pieces pour achetr une face
        else
        {
            System.out.println("face choisie = "+caseFace);
            String name = FacesAvailable.get(caseFace); 
            return name;
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
