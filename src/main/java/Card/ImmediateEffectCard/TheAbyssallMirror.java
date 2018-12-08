package Card.ImmediateEffectCard;

import Card.Card;
import Faces.Garden.GardenFace;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le mirroir abysall" permet d'avoir une face "miroir"
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis suppression immédiate
 * Elle procure 10 points de gloire à la fin de la partie
 */

public class TheAbyssallMirror extends Card {

    public TheAbyssallMirror() {
        this.name = "TheAbyssallMirror";
        this.gloryPoints = 10;
        this.type = "S";
        this.TypeCard = "I";
        this.price = 5;
        this.portal = 5;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        GardenFace myface = temple.takeGardenFace(this); //Recuperer la face correspondante du temple
        GeneralFace face = myface;   // La mettre dans generalFace

        // Methode d'appel de la forge
        bot.getStrategy().ForgeDice(face);
    }

}