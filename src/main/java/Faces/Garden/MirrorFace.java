/*
 * Face "Mirroir"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces.Garden;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
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
    public void makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
            GeneralFace myNewFace = new GeneralFace();// face choisie
            if(action==0){
                myNewFace = faceMirror(numBot, bot, data);
            }else{
                myNewFace = faceMirrorDecrease(numBot, bot, data);
            }

            if(data[numBot].get(0)==this){
                data[numBot].set(0,myNewFace); 
            }else{
                data[numBot].set(1,myNewFace); 
            }
            myNewFace.makeEffect(action,favMin,temple, numBot, bot, data,listBot);
        
    }
    
    // retourne la face choisie par le joueur parmi les faces de ses adversaires
    // NB : ne pas oublier à l'implementation de la méthode giveMeYourGChoice
    // que le choix de la face mirroir ne doit point se produire ☺☻
    private GeneralFace faceMirror(int numBot,Bot bot,ArrayList<GeneralFace>... data){
        ArrayList<GeneralFace> myList = new ArrayList<>(); // Stocke la liste des faces des adversaires
        for(int b=0;b<data.length;b++){
            if(b!=numBot){
            for(GeneralFace face : data[b]){        
                    myList.add(face);
              }
            }
        }
        int choice = bot.getStrategy().giveMeYourGChoice(myList);
        GeneralFace face = myList.get(choice);
        return face;
    }
    
    
    /* S"activera si et seulement si le joueur lance ses dés et : 
       ---> La premiere face obtenue est une face Multiplier "*3"
       ---> La deuxième face obtenue est une face mirroir
    */
    @Override
    public void makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        GeneralFace myNewFace = new GeneralFace();// face choisie   
        if(action==0){
            myNewFace = faceMirror(numBot, bot, data);
        }else{
            myNewFace = faceMirrorDecrease(numBot, bot, data);
        }
        
        if(data[numBot].get(0)==this){
            data[numBot].set(0,myNewFace); 
        }else{
            data[numBot].set(1,myNewFace); 
        }
          
        myNewFace.makeEffectFaceMultiplier(action,favMin,temple, numBot, bot, 2,data,listBot);
        
    }
    
    //Effet Cyclope
    
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        GeneralFace myNewFace = faceMirror(numBot, bot, data);  // face choisie
        myNewFace.makeCardCyclopEffect(temple, numBot, bot, data); 
    }
    
    
    //Effet sentinel
    
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        GeneralFace myNewFace = new GeneralFace();// face choisie
        myNewFace = faceMirror(numBot, bot, data);
   
        if(data[numBot].get(0)==this){
            data[numBot].set(0,myNewFace); 
        }else{
            data[numBot].set(1,myNewFace); 
        }
         
        myNewFace.makeCardSentinelEffect(temple, numBot, bot, data,listBot);
        
    }
    
    
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        GeneralFace myNewFace = new GeneralFace();// face choisie
        myNewFace = faceMirror(numBot, bot, data);
   
        if(data[numBot].get(0)==this){
            data[numBot].set(0,myNewFace); 
        }else{
            data[numBot].set(1,myNewFace); 
        }
      
        myNewFace.makeEffectFaceMultiplierCardSentinelEffect(temple, numBot,2, bot, data,listBot);
    }
    
      
    
    //Effet Minotaure
    
    // retourne la face choisie par le joueur parmi les faces de ses adversaires
    // NB : ne pas oublier à l'implementation de la méthode giveMeYourGChoice
    // que le choix de la face mirroir ne doit point se produire ☺☻
    private GeneralFace faceMirrorDecrease(int numBot,Bot bot,ArrayList<GeneralFace>... data){
        ArrayList<GeneralFace> myList = new ArrayList<>(); // Stocke la liste des faces des adversaires
        for(int b=0;b<data.length;b++){
            if(b!=numBot){
                for(GeneralFace face : data[b]){
                    myList.add(face);
                }
            }
        }
        int choice = bot.getStrategy().giveMeYourGChoiceDecrease(myList);
        GeneralFace face = myList.get(choice);
        return face;
    }
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        GeneralFace newFace  = new GeneralFace();
        if(action==0){
            newFace = faceMirror(numBot, bot, data);
        }else{
            newFace = faceMirrorDecrease(numBot, bot, data);
        }
        
        if(data[numBot].get(0)==this){
            data[numBot].set(0,newFace); 
        }else{
            data[numBot].set(1,newFace); 
        }
        
        return newFace.giveMeShieldGain(action, bot, numBot, face, data, listBot);
    }
    
    @Override
    public void initialize() {}
}
