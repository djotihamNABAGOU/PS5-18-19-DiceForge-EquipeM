package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void makeBrightDefaultDice() {
        Dice dice = new Dice();
        dice.makeBrightDefaultDice();
        int sun = 0, gold = 0;
        for (int i=0; i<dice.faces.length; i++) {
            if (dice.faces[i].getType().equals("G") && dice.faces[i].getValue() == 1) gold++;
            if (dice.faces[i].getType().equals("S") && dice.faces[i].getValue() == 1) sun++;
        }
        assertTrue(gold == 5);
        assertTrue(sun == 1);

    }

    @Test
    public void makeDarkDefaultDice() {
        Dice dice = new Dice();
        dice.makeDarkDefaultDice();
        int glory = 0, moon = 0, gold = 0;
        for (int i=0; i<dice.faces.length; i++) {
            if (dice.faces[i].getType().equals("G") && dice.faces[i].getValue() == 1) gold++;
            if (dice.faces[i].getType().equals("M") && dice.faces[i].getValue() == 1) moon++;
            if (dice.faces[i].getType().equals("Gl") && dice.faces[i].getValue() == 2) glory++;
        }
        assertEquals(4,gold);
        assertEquals(1,moon);
        assertEquals(1,glory);
    }

    @Test
    public void rollDice() {
        Dice dice = new Dice();
        DiceFaces face = dice.rollDice();
        Boolean result = false;
        for (int i=0; i<dice.faces.length; i++) {
            if (dice.faces[i].getType().equals(face.getType()) && dice.faces[i].getValue() == face.getValue()) result = true;
        }
        assertEquals(true, result);
    }
}