package Card.ImmediateEffectCard;

import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheHammerTest {
    private Temple temple = new Temple();
    private Engine engine = new Engine(0, 0, 1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private Bot[] tabBot = new Bot[1];
    private TheHammer theHammer = new TheHammer();

    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theHammer.actionCard(temple, bot, 1, listFaces, tabBot);

        assertEquals(1,bot.getHerosInventory().tokenHammer );
    }

    @Test
    public void increaseGoldPoints() {
        int use = theHammer.getUses();
        theHammer.IncreaseGoldPoints(15, bot.getHerosInventory());
        assertEquals(10, bot.getHerosInventory().getGloryPoints());
        assertEquals(1, theHammer.getUses());
        if (use == 1) {
            theHammer.IncreaseGoldPoints(15, bot.getHerosInventory());
            assertEquals(15, bot.getHerosInventory().getGloryPoints());
            assertEquals(0, theHammer.getUses());
            assertEquals(0,bot.getHerosInventory().tokenHammer);
        }
    }
}