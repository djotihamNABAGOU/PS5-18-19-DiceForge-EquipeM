package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "La meduse" permet tout simplement de gagner 14 points de gloire
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 * { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
 */

public class TheJellyFish extends Card {

    public TheJellyFish() {
        this.name = "TheJellyFish";
        this.gloryPoints = 14;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 4;
        this.portal = 5;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(14);
    }
}
