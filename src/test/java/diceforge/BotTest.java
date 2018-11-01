package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void forgeDiceFace() {
        Bot bot = new Bot();
        //1-check if the number of removed faces will increase and if the face is on the dice after calling the forge method
        int initialRemovedFaces = bot.RemovedFaces.size();
        int initialGold3 = 0;
        for (int i=0; i<bot.firstDice.faces.length; i++){
            if ((bot.firstDice.faces[i].getType().equals("G") && bot.firstDice.faces[i].getValue()==3) || (bot.secondDice.faces[i].getType().equals("G") && bot.secondDice.faces[i].getValue()==3)) initialGold3++;
        }
        bot.forgeDiceFace(new DiceFaces(3,"G"));
        int finalRemovedFaces = bot.RemovedFaces.size();
        int finalGold3 = 0;
        for (int i=0; i<bot.firstDice.faces.length; i++){
            if ((bot.firstDice.faces[i].getType().equals("G") && bot.firstDice.faces[i].getValue()==3) || (bot.secondDice.faces[i].getType().equals("G") && bot.secondDice.faces[i].getValue()==3)) finalGold3++;
        }
        assertFalse(initialRemovedFaces==finalRemovedFaces);
        assertFalse(initialGold3==finalGold3);
    }
}