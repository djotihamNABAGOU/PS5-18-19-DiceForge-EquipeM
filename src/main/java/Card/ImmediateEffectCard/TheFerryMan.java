package Card.ImmediateEffectCard;

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

public class TheFerryMan extends Card {

    public TheFerryMan(int amount) {
        this.name = "TheFerryMan";
        this.gloryPoints = 12;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 4;
        this.portal = 3;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(12);
    }

}