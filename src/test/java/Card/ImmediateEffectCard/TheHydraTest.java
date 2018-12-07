package Card.ImmediateEffectCard;

import Faces.Sanctuary.GeneralFace;
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
    private ArrayList<Bot> listBot = new ArrayList<>();
    private TheHydra theHydra = new TheHydra();

    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theHydra.actionCard(temple,bot, 1,listFaces,listBot);

        assertEquals(26,bot.getHerosInventory().getGloryPoints());
    }
}