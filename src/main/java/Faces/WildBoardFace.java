/*
 * Face "Sanglier acharné"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class WildBoardFace extends GardenFace{
    
    public WildBoardFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data) {
        
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
            makeEffectFaceMultiplier(temple,numBot,bot,1); // gain choisie * 1 (a=1)
        }
    }
    
    @Override
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        ArrayList<SimpleFace> Offered = new ArrayList<>();
        Offered.add(new SimpleFace(1, "S", "SunFace"));
        Offered.add(new SimpleFace(1, "M", "MoonFace"));
        int choice = bot.giveMeYourChoice(Offered);
        for(int b=0;b<a;b++){
            Offered.get(choice).makeEffect(temple,1,bot);
        }
    }
      
}
