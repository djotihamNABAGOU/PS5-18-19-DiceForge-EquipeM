package diceforge;

import Card.Card;
import Card.ImmediateEffectCard.TheHammer;
import Card.Reinforcement;
import Faces.GeneralFace;
import Player.Bot;

import java.util.ArrayList;

public class Island {
    ArrayList<Card>[] cards;

    public Island() {
        for(int i=0; i<7; i++){
            this.cards[i] = new ArrayList<>();
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
    /*This Method returns all the available cards, the bot has the choice if he want to do an achievement*/
    
    public ArrayList<Card> availableCards(Card card){
        ArrayList<Card> result = new ArrayList<>();
        for(int i=0; i<7; i++){
            result.add(this.cards[i].get(0));
        }
        return result;
    }
    /*This Method tell us if a card*/
    public boolean cardIsAivalable(Card card){
        for(int i=0; i<7; i++){
           return this.cards[i].get(0).equals(card);
        }
        return false;
    }
    public boolean buyCard(Card card,int action,int favMin,Temple temple,int numBot,
                               Bot botActive,ArrayList<GeneralFace>[] data,Bot... bots){
        if(this.cardIsAivalable(card)){
            this.moveBotToPortal(card.getPortal(), action, favMin, temple, numBot, botActive, data, bots);
            /*According to the type of the Card we gonna add it in the list
                enhancementCard  || hammerCard || automaticCard
              It smelling INSTANCEOF nevertheless.....
              It seems that we can use the type of the card to identify the which listOfCards of the 
                Bot is concerned
            */
                            /*if(card instanceof TheHammer){
                                 botActive.getHammer().add( (TheHammer)card);
                            }else{
                                  // botActive.getEnhancement().add( (Reinforcement)card);
                                  // botActive.getHammer().add( (TheHammer)card);
                            }*/
           
            return true;
        }
        return false; //Purchase failed
    }
    
}
