package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Le typhon" permet au joueur d'avoir un point de gloire par face de dé crafté
 * Elle procure 16 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheTyphoon extends Card {

    public TheTyphoon() {
        this.name = "TheTyphoon";
        this.gloryPoints = 16;
        this.type = "M+S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 10;
        this.portal = 4;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        int nb = bot.getRemovedFaces().size();
        bot.getHerosInventory().IncreaseGloryPoints(16 + (1 * nb));
    }
}
