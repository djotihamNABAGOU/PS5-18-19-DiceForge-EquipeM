package Card.ImmediateEffectCard;

import Card.WithoutEffectCard.TheJellyFish;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TheJellyFishTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private Bot tabBot[] = new Bot[1];
    private TheJellyFish theJellyFish = new TheJellyFish();


    @Test
    public void actionCard() {
        engine.InitializingBots(bot);
        theJellyFish.actionCard(temple,bot, 1,listFaces,tabBot);

        assertTrue(bot.getHerosInventory().getGloryPoints()==14);
    }
}