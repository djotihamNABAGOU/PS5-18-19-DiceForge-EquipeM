package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;
/**
 * Cette carte "La Sentinelle" permet au joueur de relancer 02 fois ses 02 des
 * En lancant , s'il tombe sur la face "Sun" ou "Moon", il a le choix entre
 * les garder ou obtenir 02 points de gloire pr chacune de ses faces.
 * Elle procure 6 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */
public class TheSentinel extends Card {

    public TheSentinel() {
        this.name = "TheSentinel";
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 6;
        this.portal = 4;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);

        /* Pour chacun des 2 des du joueur, le lancent 2 fois et MAJ de l'inventaire */
        for (int a = 0; a < 2; a++) {
            GeneralFace one = bot.getFirstDice().rollDice();
            GeneralFace two = bot.getSecondDice().rollDice();
            listFaces[numBot].set(0, one); // changement par la nouvelle face
            listFaces[numBot].set(1, two); // changement par la nouvelle face
            // Appliquer effets des faces
            listFaces[numBot].get(0).makeCardSentinelEffect(temple, numBot, bot, listFaces, tabBot);
            listFaces[numBot].get(1).makeCardSentinelEffect(temple, numBot, bot, listFaces, tabBot);
        }
    }
}
