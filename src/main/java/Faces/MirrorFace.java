/*
 * Face "Mirroir"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class MirrorFace extends GardenFace{
    
    public MirrorFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }
  
    
    @Override
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data) {
        
        int c = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        int a = 0;   //Face en présence
        GeneralFace myNewFace = faceMirror(numBot, bot, data);  // face choisie
        GeneralFace mySecondNewFaceEventual;  // 2eme face choisie { ssi le resultat est 2 FACES MIR}
        
        if(data.length!=0){ // Si == 0, en presence de faveur mineure
        
                if(data[numBot].get(0) == this){
                    // Verifier si la seconde face est une face mirroir aussi
                    if(data[numBot].get(1) instanceof MirrorFace){
                       mySecondNewFaceEventual = faceMirror(numBot, bot, data);
                       // Remplacer les deux faces dans la liste
                       data[numBot].set(0,myNewFace);
                       data[numBot].set(1,mySecondNewFaceEventual);
                    }else{
                        // Remplacer juste la premiere face
                       data[numBot].set(0,myNewFace);
                    }
                }else{
                    a = 1;
                    // Verifier si la premiere face est une face mirroir aussi
                    if(data[numBot].get(0) instanceof MirrorFace){
                       mySecondNewFaceEventual = faceMirror(numBot, bot, data);
                       // Remplacer les deux faces dans la liste
                       data[numBot].set(0,myNewFace);
                       data[numBot].set(1,mySecondNewFaceEventual);
                    }else{
                        // Remplacer juste la seconde face
                        data[numBot].set(1,myNewFace);
                    }
                }

                /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
                   Multiplier qui s'activera et fera effet
                */

                for(GeneralFace face : data[numBot]){
                    if(face instanceof MultiplierFace){
                        c = 1;
                    }
                }
        }
        
        if(c==0){
            // Appliquer la méthode de la nouvelle face 
            myNewFace.makeEffect(temple, numBot, bot, data); 
        }        
    }

    
    // retourne la face choisie par le joueur parmi les faces de ses adversaires
    // NB : ne pas oublier à l'implementation de la méthode giveMeYourGChoice
    // que le choix de la face mirroir ne doit point se produire ☺☻
    private GeneralFace faceMirror(int numBot,Bot bot,ArrayList<GeneralFace>... data){
        ArrayList<GeneralFace> myList = new ArrayList<>(); // Stocke la liste des faces des adversaires
        for(int b=0;b<data.length;b++){
            for(GeneralFace face : data[b]){
                if(b!=numBot){
                    myList.add(face);
                }
            }
        }
        int choice = bot.giveMeYourGChoice(myList);
        GeneralFace face = myList.get(choice);
        return face;
    }
    
    
    /* S"activera si et seulement si le joueur lance ses dés et : 
       ---> La premiere face obtenue est une face Multiplier "*3"
       ---> La deuxième face obtenue est une face mirroir
    */
    @Override
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        GeneralFace myNewFace = faceMirror(numBot, bot, data);  // face choisie
        // Remplacer la face mirroir par la face choisie
        if(data[numBot].get(0)== this){
           data[numBot].set(0,myNewFace);   
        }else{
           data[numBot].set(1,myNewFace);
        }
        // Appliquer la méthode Multiplier de la nouvelle face
        myNewFace.makeEffectFaceMultiplier(temple, numBot, bot, a, data);
    }
      
}
