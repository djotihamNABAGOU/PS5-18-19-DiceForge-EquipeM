package Card.ImmediateEffectCard;

import Faces.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import Card.ImmediateEffectCard.TheCerberus;

public class TheCerberusTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private ArrayList<Bot> listBot = new ArrayList<>();
    private TheCerberus theCerberus = new TheCerberus();

    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theCerberus.actionCard(temple,bot, 1,listFaces,listBot);

        assertTrue(bot.getHerosInventory().tokenCerberus==1);
    }
}