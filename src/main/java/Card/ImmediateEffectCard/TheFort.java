package Card.ImmediateEffectCard;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Le coffre du forgeron" permet d'avoir une extension de 4 places pour chacune de
 * ses ressources.
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheFort extends Card {

    public TheFort() {
        this.name = "TheFort";
        this.gloryPoints = 0;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 1;
        this.portal = 1;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().goldPointsLimit = bot.getHerosInventory().goldPointsLimit + 4;
        bot.getHerosInventory().moonPointsLimit = bot.getHerosInventory().moonPointsLimit + 4;
        bot.getHerosInventory().sunPointsLimit = bot.getHerosInventory().sunPointsLimit + 4;
    }
}