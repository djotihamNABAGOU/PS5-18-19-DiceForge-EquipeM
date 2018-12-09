package Card.ImmediateEffectCard;

import Card.WithoutEffectCard.TheHydra;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheHydraTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private Bot[] tabBot = new Bot[1];
    private TheHydra theHydra = new TheHydra();

    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theHydra.actionCard(temple,bot, 1,listFaces,tabBot);

        assertEquals(26,bot.getHerosInventory().getGloryPoints());
    }
}