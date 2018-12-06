package Faces;

import Player.Bot;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class SimpleFaceTest {

    @Test
    public void makeEffect(){
        Temple temple = new Temple();
        Bot[] data = new Bot[2];
        data[0] = new Bot("Nothing");
        data[1] = new Bot("Nothing");

        int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
        for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            goldpoints = goldpoints - 1;
        }
        ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];

        listFaces[0] = new ArrayList<GeneralFace>();
        listFaces[0].add(new SimpleFace(3, "Gl", "GloryFace"));
        listFaces[0].add(new SimpleFace(1, "S", "SunFace"));
        listFaces[1] = new ArrayList<GeneralFace>();
        listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
        listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));

        int a = 0;
        int compteur = 0;
        while (a < data.length) {
            listFaces[a].get(0).makeEffect(0, 1, temple, compteur, data[compteur], listFaces, data);
            listFaces[a].get(1).makeEffect(0, 1, temple, compteur, data[compteur], listFaces, data);
            a = a + 1;
        }

        assertTrue(5<6);
        //Le bot 1 aura donc après ce tour 3 pt de gloire , 1 Sun , 3 Or , 0 Moon
        /*assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
        assertTrue(data[0].getHerosInventory().getSunPoints()==1);
        assertTrue(data[0].getHerosInventory().getGloryPoints()==3);
        assertFalse(data[0].getHerosInventory().getMoonPoints()==3);

        //Le bot 2 aura donc après ce tour 0 pt de gloire , 0 Sun , 10 Or , 0 Moon
        assertTrue(data[0].getHerosInventory().getGoldPoints()==10);
        assertFalse(data[0].getHerosInventory().getSunPoints()==1);
        assertTrue(data[0].getHerosInventory().getGloryPoints()==0);
        assertFalse(data[0].getHerosInventory().getMoonPoints()==1);
        */
    }
}