package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GardenFace;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Les ailes de la gardienne" permet d'avoir une face "aile de la gardienne"
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis suppression immédiate
 * Elle procure 6 points de gloire à la fin de la partie
 */

public class TheShieldOfTheGuardian extends Card {

    public TheShieldOfTheGuardian(int amount)  /* prend en parametre le nombre de joueurs */ {
        this.name = "TheShieldOfTheGuardian";
        this.gloryPoints = 6;
        this.type = "S";
        this.TypeCard = "I";
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 3;
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

