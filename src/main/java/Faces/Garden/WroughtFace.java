/*
 * Face "Forge"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces.Garden;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
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
    public int makeEffect(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        if(action==0){
            SanctuarysFaces myFace = bot.getStrategy().giveMeYourWroughtChoice(temple);
            if(myFace.getPrice() != 0) {    // face non vide
                    if (temple.buyFace(myFace)) {
                           bot.getStrategy().ForgeDice(myFace);
                           bot.getHerosInventory().DecreaseGoldPoints(myFace.getPrice()-2);
                    }else {
                           System.out.println("Purchase failed");
                   }
            } 
        } 
        return 8;
    }
    
    @Override
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
           bot.getHerosInventory().IncreaseGoldPoints(4);
           return 2;
    }
    
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        makeEffect(0,1,temple, numBot, bot, data,listBot); 
    }
    
    //Effet sentinel
    
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        makeEffect(0,1,temple, numBot, bot, data,listBot); 
    }
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        makeEffectFaceMultiplier(0,1,temple, numBot, bot, a, data,listBot);
    }
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        return 1;
    }
    
    @Override
    public void initialize() {}
}
