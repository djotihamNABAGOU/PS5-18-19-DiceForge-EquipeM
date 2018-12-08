package Card.ReinforcementEffectCard;

import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheWingsOfTheGuardiansTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private Bot tabBot[] = new Bot[1];
    private TheWingsOfTheGuardians theWingsOfTheGuardians = new TheWingsOfTheGuardians();


    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theWingsOfTheGuardians.actionCard(temple,bot, 1,listFaces,tabBot);

        assertTrue(bot.getHerosInventory().getGloryPoints()==4);

    }

    @Test
    public void capacity() {
        engine.InitializingBots(bot);
        theWingsOfTheGuardians.capacity(temple,bot, 1,listFaces,tabBot);

        assertTrue(bot.getHerosInventory().getGoldPoints()==4);
    }
}