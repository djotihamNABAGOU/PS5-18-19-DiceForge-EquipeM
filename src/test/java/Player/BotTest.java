package Player;

import Faces.DiceFaces;
import org.junit.Test;

import static org.junit.Assert.*;

public class BotTest {

    @Test
    public void forgeDiceFace() {
        Bot bot = new Bot();
        //1-check if the number of removed faces will increase and if the face is on the dice after calling the forge method
        int initialRemovedFaces = bot.getRemovedFaces().size();
        int initialGold3 = 0;
        for (int i=0; i<bot.getFirstDice().getFaces().length; i++){
            if ((bot.getFirstDice().getFaces()[i].getType().equals("G") && bot.getFirstDice().getFaces()[i].getValue()==3) || (bot.getSecondDice().getFaces()[i].getType().equals("G") && bot.getSecondDice().getFaces()[i].getValue()==3)) initialGold3++;
        }
        bot.forgeDiceFace(new DiceFaces(3,"G"));
        int finalRemovedFaces = bot.getRemovedFaces().size();
        int finalGold3 = 0;
        for (int i=0; i<bot.getFirstDice().getFaces().length; i++){
            if ((bot.getFirstDice().getFaces()[i].getType().equals("G") && bot.getFirstDice().getFaces()[i].getValue()==3) || (bot.getSecondDice().getFaces()[i].getType().equals("G") && bot.getSecondDice().getFaces()[i].getValue()==3)) finalGold3++;
        }
        assertNotEquals(initialRemovedFaces,finalRemovedFaces);
        assertNotEquals(initialGold3,finalGold3);
    }
}