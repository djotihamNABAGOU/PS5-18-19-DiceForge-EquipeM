package Card.ImmediateEffectCard;

import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheTyphoonTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,2);
    private Bot bot1 = new Bot("");
    private Bot bot2 = new Bot ("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private ArrayList<Bot> listBot = new ArrayList<>();
    private TheTyphoon theTyphoon = new TheTyphoon();
    private Bot tabbot[] = new Bot[4];

    @Test
    public void actionCard() {
        engine.InitializingBots(bot1,bot2);
        int nb1 = bot1.getRemovedFaces().size();
        theTyphoon.actionCard(temple,bot1, 1,listFaces,listBot);
        bot2.getRemovedFaces().add(new GeneralFace());
        int nb2 = bot2.getRemovedFaces().size();
        theTyphoon.actionCard(temple,bot2,1,listFaces,listBot);


        //test1
        assertTrue(bot1.getHerosInventory().getGloryPoints()==16+nb1);
        //test2
        assertTrue(bot2.getHerosInventory().getGloryPoints()==16+nb2);

    }
}