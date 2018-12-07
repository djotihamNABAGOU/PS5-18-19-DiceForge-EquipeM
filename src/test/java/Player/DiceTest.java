package Player;

import Faces.Sanctuary.GeneralFace;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void makeBrightDefaultDice() {
        Dice dice = new Dice();
        dice.makeBrightDefaultDice();
        int sun = 0, gold = 0;
        for (int i=0; i<dice.getFaces().length; i++) {
            if (dice.getFaces()[i].getName().equals("GoldenFace")) gold++;
            if (dice.getFaces()[i].getName().equals("SunFace")) sun++;
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
            if (dice.getFaces()[i].getName().equals("GoldenFace")) gold++;
            if (dice.getFaces()[i].getName().equals("MoonFace")) moon++;
            if (dice.getFaces()[i].getName().equals("GloryFace")) glory++;
        }
        assertEquals(4,gold);
        assertEquals(1,moon);
        assertEquals(1,glory);
    }

    @Test
    public void rollDice() {
        Dice dice = new Dice();
        dice.makeBrightDefaultDice();
        GeneralFace face = dice.rollDice();
        boolean result = false;
        for (int i=0; i<dice.getFaces().length; i++) {
            if (dice.getFaces()[i].getName().equals(face.getName())){
                result = true;
                break;
            }
        }
        assertEquals(true, result);
    }
}