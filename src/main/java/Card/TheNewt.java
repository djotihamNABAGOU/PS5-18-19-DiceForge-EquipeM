package Card;

import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le Triton" permet au joueur d'acquerir un jeton Triton
 * Elle procure 8 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheNewt extends Card {

    public TheNewt(int amount) /* prend en parametre le nombre de joueurs */ {
        this.name = "TheNewt";
        this.gloryPoints = 8;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 4;
        this.portal = 5;
    }


    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(8);
        bot.getHerosInventory().tokenNewt = bot.getHerosInventory().tokenNewt + 1; /* procure un jeton Triton */
    }
}
