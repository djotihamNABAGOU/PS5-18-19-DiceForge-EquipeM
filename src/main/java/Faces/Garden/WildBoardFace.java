/*
 * Face "Sanglier acharné"
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces.Garden;

import Card.Card;
import Card.Reinforcement;
import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class WildBoardFace extends GardenFace{
    
    SimpleFace faceSelected; // face choisi
    
    public WildBoardFace(String name,Card card) {
        super(name,card);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    // choix entre les 2 faces proposees par la carte sanglier
    public void caseFace(int action,Bot bot){
  
            ArrayList<SimpleFace> Offered = new ArrayList<>();
            Offered.add(new SimpleFace(1, "S", "SunFace"));
            Offered.add(new SimpleFace(1, "M", "MoonFace"));
            int choice = -1;
            if(action==0){
                choice = bot.getStrategy().giveMeYourChoice(Offered,0);
            }else{
                choice = bot.getStrategy().giveMeYourChoicedecrease(Offered);
            }
            this.faceSelected = Offered.get(choice);
       
    }
    
    @Override
    public int makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        if(this.faceSelected==null){
                caseFace(action, bot);
        }
        return this.faceSelected.makeEffect(action,favMin,temple,1,bot,data,listBot);
    }
    
    
    @Override
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        int val = 0;
        
        if(this.faceSelected==null){
                caseFace(action, bot);
        }
        
        for(int b=0;b<a;b++){
                 val = val + this.faceSelected.makeEffect(action,favMin,temple,1,bot,data,listBot);
        }

        
        // Parcours des joueurs pour l'activation de l"effet automatique de la CARTE SANGLIER
        
        for(int nbfois=0;nbfois<a;nbfois++){
            for(int z=0;z<listBot.length;z++){
                // Parcours des cartes à effets automatiques des autres joueurs
                if(z!=numBot){
                    ArrayList<Reinforcement> automatic = listBot[z].getReinforcementCard();
                    for(int base = 0;base<automatic.size();base++){
                        if(automatic.get(base).equals(this.getCard())){ 
                            listBot[z].getAutomaticCard().get(base).capacity(temple, listBot[z], z, data, listBot);
                        }
                    }
                }
            }
        }
        return val;
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
       
            makeEffectFaceMultiplierCardSentinelEffect(temple,numBot,1,bot,data,listBot); // gain choisie * 1 (a=1)
           
    }
    
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        ArrayList<SimpleFace> Offered = new ArrayList<>();
        Offered.add(new SimpleFace(1, "S", "SunFace"));
        Offered.add(new SimpleFace(1, "M", "MoonFace"));
        int choice = bot.getStrategy().giveMeYourChoice(Offered,0);
        for(int b=0;b<a;b++){
            int apply = bot.getStrategy().changeByGloryPoint(); // choix du bot
            if(apply==1){
                Offered.get(choice).makeEffect(0,1,temple,numBot,bot,data,listBot);
            }else {
                bot.getHerosInventory().IncreaseGloryPoints(2);
            }
        }
        
        // Parcours des joueurs pour l'activation de l"effet automatique de la CARTE SANGLIER
        
        for(int nbfois=0;nbfois<a;nbfois++){
            for(int z=0;z<listBot.length;z++){
                // Parcours des cartes à effets automatiques des autres joueurs
                if(z!=numBot){
                    ArrayList<Reinforcement> automatic = listBot[z].getReinforcementCard();
                    for(int base = 0;base<automatic.size();base++){
                        if(automatic.get(base).equals(this.getCard())){ 
                            listBot[z].getAutomaticCard().get(base).capacity(temple, listBot[z], z, data, listBot);
                        }
                    }
                }
            }
        }
    }
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        int a = 1;
        if(face.getType2().getType().equals(this.faceSelected.getType())){
           a = 0;    
        }
        return a;
    }
    
    @Override
    public void initialize() {
        this.faceSelected = null;
    }
}
