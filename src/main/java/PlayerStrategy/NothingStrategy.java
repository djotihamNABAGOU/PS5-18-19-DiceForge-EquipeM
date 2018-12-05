package PlayerStrategy;

import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

/**
 * ici la stratégie est très simple le bot ne fait aucune action, donc ne fait que récolter les ressources
 */
public class NothingStrategy extends Strategy {

    public NothingStrategy(Bot bot) {
        super(bot);
    }

    @Override
    public void apply(Temple temple, int numberOfTheBot, int actionNumber,ArrayList<GeneralFace>[] listFaces,Bot... data){
    }
}