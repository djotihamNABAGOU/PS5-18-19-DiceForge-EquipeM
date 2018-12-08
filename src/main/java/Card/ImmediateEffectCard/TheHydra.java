package Card.ImmediateEffectCard;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "L'hydre" permet tout simplement de gagner 26 points de gloire
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 * { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
 */

public class TheHydra extends Card {

    public TheHydra() {
        this.name = "TheHydra";
        this.gloryPoints = 26;
        this.type = "M+S";
        this.TypeCard = "NULL";   /* I pour désigner immédiat */
        this.price = 10;
        this.portal = 4;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(26);
    }
}
