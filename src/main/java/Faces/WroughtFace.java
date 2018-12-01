/*
 * Face "Forge"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class WroughtFace extends GardenFace{
    
    public WroughtFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void makeEffect(int action,Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data) {
        
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        if(data.length!=0){ // si == 0, faveur mineure
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        }
        
        if(a==0){
            if(action==0){
                makeEffectFaceMultiplier(action,temple,numBot,bot,1); // // Forge avec côut réduit de 2 car [a==1]
            }
       }
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        int b =  a*2; // côut réduit de 6 car [a==3]
        if(action==0){
            SanctuarysFaces myFace = bot.getStrategy().giveMeYourWroughtChoice(temple);
            if(myFace.getPrice() != 0) {    // face non vide
                    if (temple.buyFace(myFace)) {
                           bot.getStrategy().ForgeDice(myFace);
                           bot.getHerosInventory().DecreaseGoldPoints(myFace.getPrice()-b);
                    }else {
                           System.out.println("Purchase failed");
                   }
            } 
        }   
    }
     
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        
        int b =  2; // côut réduit de 2
        SanctuarysFaces myFace = bot.getStrategy().giveMeYourWroughtChoice(temple);
         if(myFace.getPrice() != 0) {    // face non vide
                 if (temple.buyFace(myFace)) {
                        bot.getStrategy().ForgeDice(myFace);
                        bot.getHerosInventory().DecreaseGoldPoints(myFace.getPrice()-b);
                 }else {
                        System.out.println("Purchase failed");
                }
         } 
    }
    
    //Effet sentinel
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        makeEffect(0,temple, numBot, bot, data); 
    }
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int a,Bot bot,ArrayList<GeneralFace>... data){
        makeEffectFaceMultiplier(0,temple, numBot, bot, a, data);
    }
    
    
}
