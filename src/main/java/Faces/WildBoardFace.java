/*
 * Face "Sanglier acharné"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Card.Reinforcement;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;
import java.util.Arrays;

public class WildBoardFace extends GardenFace{
    
    public WildBoardFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        
        
        if(a==0){
            makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,1,data,listBot); // gain choisie * 1 (a=1)
            
        }
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        ArrayList<SimpleFace> Offered = new ArrayList<>();
        Offered.add(new SimpleFace(1, "S", "SunFace"));
        Offered.add(new SimpleFace(1, "M", "MoonFace"));
        int choice = -1;
        if(action==0){
            choice = bot.getStrategy().giveMeYourChoice(Offered);
        }else{
            choice = bot.getStrategy().giveMeYourChoicedecrease(Offered);
        }
        
        for(int b=0;b<a;b++){
            Offered.get(choice).makeEffect(action,favMin,temple,1,bot,data,listBot);
        }
        
        
        // Parcours des joueurs pour l'activation de l"effet automatique de la CARTE SANGLIER
        
        ArrayList<Bot> lt = new ArrayList<>();
        lt.addAll(Arrays.asList(listBot));
        for(int nbfois=0;nbfois<a;nbfois++){
            for(int z=0;z<listBot.length;z++){
                // Parcours des cartes à effets automatiques des autres joueurs
                if(z!=numBot){
                    ArrayList<Reinforcement> automatic = listBot[z].getEnhancementCard();
                    for(int base = 0;base<automatic.size();base++){
                        if(automatic.get(base).equals(this.getCard())){ 
                            listBot[z].getAutomaticCard().get(base).capacity(temple, listBot[z], z, data, lt);
                        }
                    }
                }
            }
        }
            
    }
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                               Bot... listBot){
         makeEffectFaceMultiplier(0,1,temple, numBot, bot, 1,data,listBot);
     }
     
    
    //Effet Sentinel
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
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
            makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,1,bot,data,listBot); // gain choisie * 1 (a=1)
        }   
    }
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        ArrayList<SimpleFace> Offered = new ArrayList<>();
        Offered.add(new SimpleFace(1, "S", "SunFace"));
        Offered.add(new SimpleFace(1, "M", "MoonFace"));
        int choice = bot.getStrategy().giveMeYourChoice(Offered);
        for(int b=0;b<a;b++){
            int apply = bot.getStrategy().changeByGloryPoint(); // choix du bot
            if(apply==1){
                Offered.get(choice).makeEffect(0,1,temple,numBot,bot,data,listBot);
            }else {
                bot.getHerosInventory().IncreaseGloryPoints(2);
            }
        }
        
        // Parcours des joueurs pour l'activation de l"effet automatique de la CARTE SANGLIER
        
        ArrayList<Bot> lt = new ArrayList<>();
        lt.addAll(Arrays.asList(listBot));
        for(int nbfois=0;nbfois<a;nbfois++){
            for(int z=0;z<listBot.length;z++){
                // Parcours des cartes à effets automatiques des autres joueurs
                if(z!=numBot){
                    ArrayList<Reinforcement> automatic = listBot[z].getEnhancementCard();
                    for(int base = 0;base<automatic.size();base++){
                        if(automatic.get(base).equals(this.getCard())){ 
                            listBot[z].getAutomaticCard().get(base).capacity(temple, listBot[z], z, data, lt);
                        }
                    }
                }
            }
        }
    }
    
}
