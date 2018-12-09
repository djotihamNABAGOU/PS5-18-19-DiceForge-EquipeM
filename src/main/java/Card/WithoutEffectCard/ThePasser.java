package Card.WithoutEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le passeur" permet tout simplement de gagner 12 points de gloire
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 * { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
 */

public class ThePasser extends Card {

    public ThePasser() {
        this.name = "ThePasser";
        this.gloryPoints = 12;
        this.type = "M";
        this.TypeCard = "NULL";   /* I pour désigner immédiat */
        this.price = 4;
        this.portal = 3;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(12);
    }

}