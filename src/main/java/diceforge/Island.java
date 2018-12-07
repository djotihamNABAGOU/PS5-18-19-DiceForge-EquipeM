package diceforge;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;

import java.util.ArrayList;

public class Island {
    //int[] portal ={1,2,3,4,5,6,7};
    //ArrayList<Card> cards = new ArrayList<>();
    //le plateau d'îles est un tableau de 7 îles représentés par des arraylists et dont les indices du tableau sont les portails
    private ArrayList<Card>[] islandTray = new ArrayList[7];

    public Island() {
        for(int i=0; i<cards.size(); i++){
            this.cards.add(cards.get(i));
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

    public ArrayList<Card> getCards() {
        return cards;
    }
}
