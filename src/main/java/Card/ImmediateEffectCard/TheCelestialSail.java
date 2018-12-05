package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GardenFace;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "La voute celeste" permet d'avoir une face "forge"
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis suppression immédiate
 * Elle procure 4 points de gloire à la fin de la partie
 */

public class TheCelestialSail extends Card {

    public TheCelestialSail() {
        this.name = "TheCelestialSail";
        this.gloryPoints = 4;
        this.type = "S";
        this.TypeCard = "I";
        this.price = 2;
        this.portal = 6;
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