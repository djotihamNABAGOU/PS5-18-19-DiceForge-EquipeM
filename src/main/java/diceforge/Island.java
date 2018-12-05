package diceforge;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;

import java.util.ArrayList;

public class Island {
    int[] portal ={1,2,3,4,5,6,7};
    ArrayList<Card> cards = new ArrayList<>();
    

    public Island(int portal, ArrayList<Card> cardes) {
        for(int i=0; i<cardes.size(); i++){
            this.cards.add(cardes.get(i));
        }
    }
    
    int portalOfThisBot(Bot bot){
        return bot.getMyPortal();
    }
    
    /*This method is called when a Bot wonna do an achievement/peformance*/
    void moveBotToPortal(int gate,int action,int favMin,Temple temple,int numBot,
                               Bot botActive,ArrayList<GeneralFace>[] data,Bot... bots){
        for (Bot bot : bots) {
            if(bot.isActive() && bot.getMyPortal()!=gate){
               
               for(Bot botHunted : bots){
                   if(!botHunted.isActive() && botHunted.getMyPortal()==gate){
                       botHunted.justHuntedBotAct(action,favMin,temple,numBot,
                               botActive,data, bots);
                       break;
                   }
               }
               bot.updateMyPortal(gate);
               break;
            }
        }
    }
    
    public boolean isThisCardAvailable(Card card){
        return this.cards.contains(card);
    }
    public boolean buyCard(Card card,int action,int favMin,Temple temple,int numBot,
                               Bot botActive,ArrayList<GeneralFace>[] data,Bot... bots){
        if(this.isThisCardAvailable(card)){
            this.moveBotToPortal(card.getPortal(), action, favMin, temple, numBot, botActive, data, bots);
            /*According to the type of the Card we gonna add it in the list
              enhancementCard  || hammerCard || automaticCard
              it smelling INSTANCEOF nevertheless.....
            */
            return true;
        }
        return false; //Purchase failed
    }
    
    

}
