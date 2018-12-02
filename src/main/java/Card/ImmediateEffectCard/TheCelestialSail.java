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

    public TheCelestialSail(int amount)  /* prend en parametre le nombre de joueurs */ {
        this.name = "TheCelestialSail";
        this.gloryPoints = 4;
        this.type = "S";
        this.TypeCard = "I";
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
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