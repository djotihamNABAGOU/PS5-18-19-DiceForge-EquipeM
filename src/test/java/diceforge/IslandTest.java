/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import Card.AutomaticEffectCard.TheGreatBear;
import Card.AutomaticEffectCard.TheWildBoar;
import Card.Card;
import Card.ReinforcementEffectCard.TheClogs;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Destroyer
 */
public class IslandTest {
    
    @Test
    public void testDeleteCard(){
        Island island = new Island();
        island.initializeIsland(4);
        
        Card cardClogs =new TheClogs();
        TheGreatBear cardBear = new TheGreatBear();
        
        assertTrue(island.cardIsAivalable(cardClogs)==true); //True because at the beginning this card is on top in island
        assertTrue(island.cardIsAivalable(cardBear)==false); //False because at the beginning no remplecement card
        
          island.deleteCard(cardClogs); //Removing all the Clogs Card By The Appropriated Method
          island.deleteCard(cardClogs);
          island.deleteCard(cardClogs);
          island.deleteCard(cardClogs);
                                                         
         assertTrue(island.cardIsAivalable(cardBear)==true); //True because they are now in the Game! Welcomeee Bears!
          
          island.deleteCard(cardBear); //Now let delete all the cards (both the main the remplacement)
          island.deleteCard(cardBear);
          island.deleteCard(cardBear);
          island.deleteCard(cardBear);
          
         assertTrue(island.cards[2].isEmpty()==true); // Because no clogs card and no Bear's also
    }
    @Test
    public void testcardIsAivalable() {
        Island island = new Island();
        island.initializeIsland(4);
        
        Card cardClogs =new TheClogs();
        assertTrue(island.cardIsAivalable(cardClogs)==true); //True because at the beginning this card is on top in island
        
          island.deleteCard(cardClogs); //Removing all the Clogs Card By The Appropriated Method
          island.deleteCard(cardClogs);
          island.deleteCard(cardClogs);
          island.deleteCard(cardClogs);
        
        assertTrue(island.cardIsAivalable(cardClogs)==false); // Because no clogs card
        
    } 
    
    @Test 
    public void testinitializeIsland(){
        Island island = new Island();
        island.initializeIsland(3); // ---> Its means that no WildBord With name=="blue"
        
        TheWildBoar boar =new TheWildBoar("blue");
        
        for(Card card: island.cards[3]){
            assertTrue((card.equalss(boar))==false); 
        }
          
    }
    
    public void buyCard(){
        Island island = new Island();
        island.initializeIsland(4);
        
        TheWildBoar boar =new TheWildBoar("blue");
        
        Temple temple = new Temple();
        Bot botActive = new Bot("nothing");
        ArrayList<Bot> bots = new ArrayList<>();
        ArrayList<GeneralFace>[] data = new ArrayList[8] ;
        
        assertTrue((island.buyCard(boar, 0, 0, temple, 0, botActive, data, botActive))==false); //No Bear
        
        Card cardClogs =new TheClogs();
        assertTrue((island.buyCard(cardClogs, 0, 0, temple, 0, botActive, data, botActive))==true); //Yes Yes yes ! We have four clogs at the beginning
        assertTrue((island.buyCard(cardClogs, 0, 0, temple, 0, botActive, data, botActive))==true); 
        assertTrue((island.buyCard(cardClogs, 0, 0, temple, 0, botActive, data, botActive))==true); 
        assertTrue((island.buyCard(cardClogs, 0, 0, temple, 0, botActive, data, botActive))==true); 
        
        assertTrue((island.buyCard(cardClogs, 0, 0, temple, 0, botActive, data, botActive))==false); //No !No more clog's
        
        
        
        
          
    }
    
}
