package Card.ImmediateEffectCard;

import Faces.GeneralFace;
import Player.Bot;
import diceforge.Engine;
import diceforge.Temple;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class TheCrazyGrassesTest {
    private Temple temple =new Temple();
    private Engine engine = new Engine(0,0,1);
    private Bot bot = new Bot("");
    private ArrayList<GeneralFace>[] listFaces = new ArrayList[6];
    private ArrayList<Bot> listBot = new ArrayList<>();

    @Test
    public void actionCard() {
         TheCrazyGrasses theCrazyGrasses = new TheCrazyGrasses(4);
        engine.InitializingBots(bot);
        theCrazyGrasses.actionCard(temple,bot, 1,listFaces,listBot);

        int gloryinit =0 ;
        int suninit = 1;
        int goldinit = 0;
        int mooninit = 0;

        assertTrue(goldinit+6==bot.getHerosInventory().getGoldPoints());
        assertTrue(mooninit+3==bot.getHerosInventory().getMoonPoints());
        assertTrue(gloryinit+2==bot.getHerosInventory().getGloryPoints());
        assertTrue(suninit-1==bot.getHerosInventory().getSunPoints());
    }

}