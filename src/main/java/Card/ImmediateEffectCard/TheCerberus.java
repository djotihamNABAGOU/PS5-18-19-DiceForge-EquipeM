package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Le Triton" permet au joueur d'acquerir un jeton Cerbère
 * Elle procure 6 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheCerberus extends Card {

    public TheCerberus() {
        this.name = "TheCerberus";
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 5;
        this.portal = 3;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().tokenCerberus = bot.getHerosInventory().tokenCerberus + 1; /* procure un jeton cerbère */
    }

}
