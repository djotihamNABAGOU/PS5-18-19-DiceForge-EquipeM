package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void forgeDiceFace() {
        Bot bot = new Bot();
        //1-check if the number of removed faces will increase and if the face is on the dice after calling the forge method
        int initialRemovedFaces = bot.getRemovedFaces().size();
        int initialGold3 = 0;
        for (int i=0; i<bot.getFirstDice().faces.length; i++){
            if ((bot.getFirstDice().faces[i].getType().equals("G") && bot.getFirstDice().faces[i].getValue()==3) || (bot.getSecondDice().faces[i].getType().equals("G") && bot.getSecondDice().faces[i].getValue()==3)) initialGold3++;
        }
        bot.forgeDiceFace(new DiceFaces(3,"G"));
        int finalRemovedFaces = bot.getRemovedFaces().size();
        int finalGold3 = 0;
        for (int i=0; i<bot.getFirstDice().faces.length; i++){
            if ((bot.getFirstDice().faces[i].getType().equals("G") && bot.getFirstDice().faces[i].getValue()==3) || (bot.getSecondDice().faces[i].getType().equals("G") && bot.getSecondDice().faces[i].getValue()==3)) finalGold3++;
        }
        assertFalse(initialRemovedFaces==finalRemovedFaces);
        assertFalse(initialGold3==finalGold3);
    }
}