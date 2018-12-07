/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces.Sanctuary;

import Faces.Garden.ShieldOfTheGuardianFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

/**
 * @author The Beginners
 */
public class SimpleFace extends GeneralFace {
    private int value;
    private String type; /*Sun S, Moon M, Gold G, Glory Gl*/


    public SimpleFace(int value, String type, String name) {
        super(name);
        this.value = value;
        this.type = type;
    }
    
    public SimpleFace(SimpleFace face) {
        super(face.name);
        this.value = face.value;
        this.type = face.type;
    }

    SimpleFace() {
        
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type: " + this.getType() + " Value: " + this.getValue();
    }

    @Override
    public int makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
            return makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,1,data);
        
    }
    
    @Override
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        // Calcul de l'entier renvoyé par la face 
           int val = 0;
           if(this.type.equals("G"))        val = value*a;
           else if(this.type.equals("S"))   val = (value*2)*a;
           else if(this.type.equals("M"))   val = (value*2)*a;
           else if(this.type.equals("Gl"))  val = (value*4)*a;
           
        // fin
        
        System.out.println("Face obtained  -> " + toString());
        if(action==0){
            
            if(this.type.equals("G")){  // pour une face simple d'or
                int winGoldPoint = this.value*a;
                // Verifier si le joueur possède une carte marteau
                if(bot.getHammerCard().size()>0){
                   winGoldPoint = bot.getStrategy().applyHammerStrategy(this.value*a);
                }
                bot.getHerosInventory().IncreaseGoldPoints(winGoldPoint);
            }else{
                bot.getHerosInventory().increaseInventoryWithDiceFace(this,a);
            }
          
        }else{
            bot.getHerosInventory().decreaseInventoryWithDiceFace(this,a);
        }
         
        return val;
    }
    
   
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                                                        Bot... listBot){
        
        System.out.println("Face obtained  -> " + toString());
        if(this.type.equals("G")){ // Si la face procure de l'or
            int val = this.value;  // Recuperer la valeur
            for(int a=0;a<val;a++){ 
                int choice = bot.getStrategy().changeByGloryPoint(); //0 si oui , 1 sinon
                if(choice==0){   // OuI
                    bot.getHerosInventory().IncreaseGloryPoints(1);
                }else {
                    int winGoldPoint = 1;
                    // Verifier si le joueur possède une carte marteau
                    if(bot.getHammerCard().size()>0){
                       winGoldPoint = bot.getStrategy().applyHammerStrategy(1);
                    }
                    bot.getHerosInventory().IncreaseGoldPoints(winGoldPoint);
                }
            }
        }
        else bot.getHerosInventory().increaseInventoryWithDiceFace(this,1);
    }
    
    
    // Effets Sentinel 
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
        
        
         makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,1,bot,data,listBot);
        
    }
    
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int d,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        for(int b=0;b<d;b++){
            System.out.println("Face obtained  -> " + toString());
            if(this.type.equals("S")){ // Si la face est de type Sun 
                int val = this.value;  // Recuperer la valeur
                for(int a=0;a<val;a++){ 
                    int choice = bot.getStrategy().changeByGloryPoint(); //0 si oui , 1 sinon
                    if(choice==0){   // OuI
                        bot.getHerosInventory().IncreaseGloryPoints(2);
                    }else {
                        bot.getHerosInventory().IncreaseSunPoints(1);
                    }
                }
            }else if(this.type.equals("M")){ // Si la face est de type Moon
                int val = this.value;  // Recuperer la valeur
                for(int a=0;a<val;a++){ 
                    int choice = bot.getStrategy().changeByGloryPoint(); //0 si oui , 1 sinon
                    if(choice==0){   // OuI
                        bot.getHerosInventory().IncreaseGloryPoints(2);
                    }else {
                        bot.getHerosInventory().IncreaseMoonPoints(1);
                    }
              }
            }else{
                if(this.type.equals("G")){  // pour une face simple d'or
                    int winGoldPoint = this.value;
                    // Verifier si le joueur possède une carte marteau
                    if(bot.getHammerCard().size()>0){
                       winGoldPoint = bot.getStrategy().applyHammerStrategy(this.value);
                    }
                    bot.getHerosInventory().IncreaseGoldPoints(winGoldPoint);
                }else{
                    bot.getHerosInventory().increaseInventoryWithDiceFace(this,1);
                }    
            }
        }
    }
    
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        int a = 1;
        if(face.getType2().getType().equals(this.getType())){
           a = 0;    
        }
        return a;
    }
    
    @Override
    public void initialize() { }
    
  
}
