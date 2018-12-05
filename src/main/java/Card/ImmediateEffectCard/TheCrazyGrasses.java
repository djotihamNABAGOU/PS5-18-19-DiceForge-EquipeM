package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Les herbes folles" fait gagner 3 Or et 3 Moon comme ressource supplementaire
 * Elle procure 2 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheCrazyGrasses extends Card {

    public TheCrazyGrasses() {
        this.name = "TheCrazyGrasses";
        this.gloryPoints = 2;
        this.type = "S";
        this.TypeCard = "I";
        this.price = 1;
        this.portal = 7;
    }


    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGoldPoints(3);
        bot.getHerosInventory().IncreaseMoonPoints(3);
        bot.getHerosInventory().IncreaseGloryPoints(2);
    }
}