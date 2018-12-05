/*
 * Face "Boucliers de la gardienne "
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;



public class ShieldOfTheGuardianFace extends GardenFace{
    
    SimpleFace Type1 = new SimpleFace(5, "Gl", "GloryFace");    // Gain A
    SimpleFace Type2;    // Gain B


    public ShieldOfTheGuardianFace(String name,Card card,SimpleFace face) {
        super(name,card);
        Type2 = face;
    }

    public SimpleFace getType1() {
        return Type1;
    }

    public SimpleFace getType2() {
        return Type2;
    }
    
    @Override
    public String toString() {
        return this.getType1().toString() + "||" + this.getType2().toString();
    }

    @Override
    public void makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){

        
                if(favMin==0) { // Reçu via une faveur mineure
                    Type2.makeEffectFaceMultiplier(action,1,temple,numBot,bot,1,data,listBot);  // GAIN B 
                }
                else {
                    comparaison(action,temple,numBot, bot, 0, 1, data);
                    comparaison(action,temple,numBot, bot, 1, 0, data);
                }
        
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        System.out.println("Face obtained  -> " + Type2.toString());
        Type2.makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,2,data,listBot);   
       // Donne 3* le gain B en présence de la face "*3" 
    }
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                                                        Bot... listBot){
        
         Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,2,data,listBot);  // GAIN B 
    }
    
    
    // determine le gain
    public void comparaison(int action,Temple temple,int numBot,Bot bot,int a,int b,ArrayList<GeneralFace>[] data,Bot... listBot)
    {
        if(data[numBot].get(a).getName().equals("ShieldOfTheGuardianFace")){
            // Le traitement s"effectuera sur la seconde face ---> face b
            
           int numberGain = data[numBot].get(b).giveMeShieldGain(action, bot, numBot, this, data, listBot);
           if(numberGain==0){
               bot.getHerosInventory().IncreaseGloryPoints(5);
           }else{
               Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot); 
           }
        }
    }
    
    
    
    // effet Sentinnel
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
       
                    comparaisonSentinel(temple,numBot, bot, 0, 1, data);
                    comparaisonSentinel(temple,numBot, bot, 1, 0, data);
       
    }
    
 
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        System.out.println("Face obtained  -> " + Type2.toString());
        Type2.makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,2,bot,data,listBot);   // GAIN B    
       // Donne 3* le gain B en présence de la face "*3" 
    }
    
    
    
    
    // determine le gain version Sentinel
    public void comparaisonSentinel(Temple temple,int numBot,Bot bot,int a,int b,ArrayList<GeneralFace>[] data,Bot... listBot)
    {
        if(data[numBot].get(a).getName().equals("ShieldOfTheGuardianFace")){
            // Le traitement s"effectuera sur la seconde face ---> face b
            
           int numberGain = data[numBot].get(b).giveMeShieldGain(0, bot, numBot, this, data, listBot);
           if(numberGain==0){
               bot.getHerosInventory().IncreaseGloryPoints(5);
           }else{
               Type2.makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,1,bot,data,listBot); 
           }
        }
    }
    
    // FIN Sentinel 
    
    
    
}
