package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Le coffre du forgeron" permet d'avoir une extension de 4 places pour chacune de
 * ses ressources.
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheFort extends Card {

    public TheFort(int amount) /* prend en parametre le nombre de joueurs */ {
        this.name = "TheFort";
        this.gloryPoints = 0;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
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