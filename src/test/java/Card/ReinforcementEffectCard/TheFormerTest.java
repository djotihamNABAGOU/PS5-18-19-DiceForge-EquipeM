package Card.ReinforcementEffectCard;

import Faces.Sanctuary.GeneralFace;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;
import Player.Bot;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheFormerTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private ArrayList<Bot> listBot = new ArrayList<>();
    private Bot tabbot[] = new Bot[4];
    private TheFormer theFormer = new TheFormer();

    @Test
    public void actionCard() {
    }


    @Test
    public void capacity() {
        engine.InitializingBots(bot);
        theFormer.capacity(temple,bot,1,listFaces,tabbot);
        assertTrue(bot.getHerosInventory().getGloryPoints()==4);
        assertTrue(bot.getHerosInventory().getGoldPoints()==0);


    }
}