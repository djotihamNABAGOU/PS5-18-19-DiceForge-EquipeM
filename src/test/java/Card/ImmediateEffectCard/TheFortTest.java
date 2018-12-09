package Card.ImmediateEffectCard;

import Faces.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheFortTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private Bot tabBot[] = new Bot[1];
    private TheFort theFort = new TheFort();

    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theFort.actionCard(temple,bot, 1,listFaces,tabBot);
        int goldlimitinit =12;
        int moonlimitinit = 6;
        int sunlimitinit = 6;

        assertEquals(goldlimitinit+4,bot.getHerosInventory().goldPointsLimit);
        assertEquals(moonlimitinit+4,bot.getHerosInventory().moonPointsLimit);
        assertEquals(sunlimitinit+4,bot.getHerosInventory().sunPointsLimit);
    }
}