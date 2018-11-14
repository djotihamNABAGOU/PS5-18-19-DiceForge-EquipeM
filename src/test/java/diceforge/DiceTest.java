package diceforge;

import Faces.DiceFaces;
import Player.Dice;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void makeBrightDefaultDice() {
        Dice dice = new Dice();
        dice.makeBrightDefaultDice();
        int sun = 0, gold = 0;
        for (int i=0; i<dice.getFaces().length; i++) {
            if (dice.getFaces()[i].getType().equals("G") && dice.getFaces()[i].getValue() == 1) gold++;
            if (dice.getFaces()[i].getType().equals("S") && dice.getFaces()[i].getValue() == 1) sun++;
        }
        assertEquals(gold, 5);
        assertEquals(sun , 1);

    }

    @Test
    public void makeDarkDefaultDice() {
        Dice dice = new Dice();
        dice.makeDarkDefaultDice();
        int glory = 0, moon = 0, gold = 0;
        for (int i=0; i<dice.getFaces().length; i++) {
            if (dice.getFaces()[i].getType().equals("G") && dice.getFaces()[i].getValue() == 1) gold++;
            if (dice.getFaces()[i].getType().equals("M") && dice.getFaces()[i].getValue() == 1) moon++;
            if (dice.getFaces()[i].getType().equals("Gl") && dice.getFaces()[i].getValue() == 2) glory++;
        }
        assertEquals(4,gold);
        assertEquals(1,moon);
        assertEquals(1,glory);
    }

    @Test
    public void rollDice() {
        Dice dice = new Dice();
        DiceFaces face = dice.rollDice();
        boolean result = false;
        for (int i=0; i<dice.getFaces().length; i++) {
            if (dice.getFaces()[i].getType().equals(face.getType()) && dice.getFaces()[i].getValue() == face.getValue()) result = true;
        }
        assertEquals(true, result);
    }

}