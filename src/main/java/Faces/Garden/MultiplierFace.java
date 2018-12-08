/*
 * Face "*3"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces.Garden;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class MultiplierFace extends GardenFace{
    

    public MultiplierFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    
   
    @Override
    public int makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        // Active l'effet multiplicateur de l'autre face à laquelle elle est associé avec multiple = 2
        int val = 0;
        if (favMin != 0){   // == 0 Faveur Mineure, n'obtient rien
             if(data[numBot].get(0)==this){  
                   val =  data[numBot].get(1).makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,2,data,listBot);
             }else{
                   val =  data[numBot].get(0).makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,2,data,listBot);
             }
        }
        return val;
    }
    
    // Ne s"activera que si et seulement si le joueur est tomber sur 2 faces "*3", le joueur ne gagne rien
    
    @Override
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I do not provide anything");   
        return 0;
    }
      
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        // Cette face ne procure rien [ Lire la doc ]
    }
    
    
    // Sentinelle effet
    
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot) {
        // Active l'effet multiplicateur de l'autre face à laquelle elle est associé
        
      
             if(data[numBot].get(0)==this){  
                   data[numBot].get(1).makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,2,bot,data);
             }else{
                   data[numBot].get(0).makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,2,bot,data);
             }
        
    }
    
    // Ne s"activera que si et seulement si le joueur est tomber sur 2 faces "*3", le joueur ne gagne rien
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I do not provide anything"); 
    }
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        return 1;
    }
    
    @Override
    public void initialize() {}
    
}
