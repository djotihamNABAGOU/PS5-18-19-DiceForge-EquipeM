package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GardenFace;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le casque d'invisibilité" permet d'avoir une face "*3"
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis suppression immédiate
 * Elle procure 4 points de gloire à la fin de la partie
 */

public class TheInvisibilityHelmet extends Card {

    public TheInvisibilityHelmet() {
        this.name = "TheInvisibilityHelmet";
        this.gloryPoints = 4;
        this.type = "M";
        this.TypeCard = "I";
        this.price = 5;
        this.portal = 3;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        GardenFace myface = temple.takeGardenFace(this); //Recuperer la face correspondante du temple
        GeneralFace face = myface;   // La mettre dans generalFace

        // Methode d'appel de la forge
        bot.getStrategy().ForgeDice(face);
    }

}


