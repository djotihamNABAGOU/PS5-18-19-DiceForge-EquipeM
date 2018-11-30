/*
 * Face "*3"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
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
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data) {
        // Active l'effet multiplicateur de l'autre face à laquelle elle est associé
        
        if (data.length != 0){   // == 0 Faveur Mineure, n'obtient rien
             if(data[numBot].get(0)==this){  
                   data[numBot].get(1).makeEffectFaceMultiplier(temple,numBot,bot,3);
             }else{
                   data[numBot].get(0).makeEffectFaceMultiplier(temple,numBot,bot,3);
             }
        }
    }
    
    // Ne s"activera que si et seulement si le joueur est tomber sur 2 faces "*3", le joueur ne gagne rien
    @Override
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        System.out.println("I do not provide anything");   
    }
      
}
