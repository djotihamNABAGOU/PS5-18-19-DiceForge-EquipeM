/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import Card.Card;
import Card.ReinforcementEffectCard.TheClogs;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Destroyer
 */
public class IslandTest {
    
    
    @Test
    public void testcardIsAivalable() {
        Island island = new Island();
        island.initializeIsland(4);
        
        Card cardClogs =new TheClogs();
        assertTrue(island.cardIsAivalable(cardClogs)==true);
    }    
}
