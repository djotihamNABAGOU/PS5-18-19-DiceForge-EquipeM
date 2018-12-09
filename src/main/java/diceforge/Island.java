package diceforge;

import Card.AutomaticEffectCard.TheWildBoar;
import Card.AutomaticEffectCard.TheGreatBear;
import Card.Card;
import Card.ImmediateEffectCard.*;
import Card.ReinforcementEffectCard.*;
import Card.WithoutEffectCard.ThePasser;
import Card.WithoutEffectCard.TheHydra;
import Card.WithoutEffectCard.TheJellyFish;
import Faces.GeneralFace;

import Player.Bot;

import java.util.ArrayList;

public class Island {
    ArrayList<Card>[] cards = new ArrayList[15];

    public Island() {
        for (int i = 0; i < 15; i++) {
            this.cards[i] = new ArrayList<>();
        }
    }

    public void initializeIsland(int numberOfBot) {
        if (numberOfBot == 2) numberOfBot = 4;

        /*Here we have the first to seventh gate for the main card*/
        for (int i = 0; i < numberOfBot; i++) {
            this.cards[0].add(new TheHammer()); //1st gate
            this.cards[1].add(new TheFort());

            this.cards[2].add(new TheClogs()); //2nd gate
            this.cards[3].add(new TheSatyrs());

            this.cards[4].add(new ThePasser());  //3rd gate
            this.cards[5].add(new TheInvisibilityHelmet());

            this.cards[6].add(new ThePliers());  //4th gate
            this.cards[7].add(new TheHydra());
            this.cards[8].add(new TheRiddle());

            //this.cards[9].add(new TheAbyssallMirror()); //5th gate
            this.cards[10].add(new TheJellyFish());

            this.cards[11].add(new TheMinotaur()); //6th gate
            this.cards[12].add(new TheWingsOfTheGuardians());

            this.cards[13].add(new TheCrazyGrasses()); //7th gate
            this.cards[14].add(new TheFormer());

        }
        /*Here we have the first to seventh gate for the remplacement card*/
        for (int i = 0; i < numberOfBot; i++) {
            this.cards[2].add(new TheGreatBear()); //2nd gate
            this.cards[4].add(new TheCerberus());  //3rd gate
            this.cards[6].add(new TheSentinel()); //4th gate
            this.cards[7].add(new TheTyphoon());
            this.cards[8].add(new TheCyclops());
            this.cards[9].add(new TheNewt()); //5th gate
            this.cards[11].add(new TheShieldOfTheGuardian()); //6th gate
            this.cards[12].add(new TheCelestialSail());


        }
        this.cards[3].add(new TheWildBoar("green")); //2nd gate
        this.cards[3].add(new TheWildBoar("yellow"));
        this.cards[3].add(new TheWildBoar("brown"));
        if (numberOfBot == 4) this.cards[3].add(new TheWildBoar("blue"));

    }


    int portalOfThisBot(Bot bot) {
        return bot.getMyPortal();
    }

    /*This method is called when a Bot wonna do an achievement/peformance*/
    void moveBotToPortal(int gate, Temple temple, int numBot, ArrayList<GeneralFace>[] data, Bot... bots) {
        for (Bot bot : bots) {
            if (bot.isActive() && bot.getMyPortal() != gate) {

                for (Bot botHunted : bots) {
                    if (!botHunted.isActive() && botHunted.getMyPortal() == gate) {
                        botHunted.justHuntedBotAct(temple, numBot, data, bots);
                        bot.hunterBotAct(temple, numBot, data, bots);
                        break;
                    }
                }
                bot.updateMyPortal(gate);
                break;
            }
        }
    }
    /*This Method returns all the available cards, the bot has the choice if he want to do an achievement*/

    public ArrayList<Card> availableCards() {
        ArrayList<Card> result = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            if (!this.cards[i].isEmpty()) {
                result.add(this.cards[i].get(0));
            }

        }
        return result;
    }

    /*This Method tell us if a card*/
    public boolean cardIsAivalable(Card card) {
        for (int i = 0; i < 15; i++) {
            if (!this.cards[i].isEmpty()) {
                if (this.cards[i].get(0).getName().equals(card.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteCard(Card card) {
        for (int i = 0; i < 15; i++) {
            if (!this.cards[i].isEmpty()) {
                if (this.cards[i].get(0).getName().equals(card.getName())) {
                    this.cards[i].remove(this.cards[i].get(0));
                }
            }
        }
    }


    public boolean buyCard(Card card, Temple temple, int numBot, ArrayList<GeneralFace>[] data, Bot... bots) {
        if (this.cardIsAivalable(card)) {
            this.moveBotToPortal(card.getPortal(), temple, numBot, data, bots);
            /*According to the type of the Card we gonna add it in the list
                enhancementCard  || hammerCard || automaticCard
              It smelling INSTANCEOF nevertheless.....
              It seems that we can use the type of the card to identify the which listOfCards of the 
                Bot is concerned
            */
            /*Whatever this traitement is mooved to The Bot Strategy Class*/

            this.deleteCard(card);
            return true;
        }
        return false; //Purchase failed
    }
}
