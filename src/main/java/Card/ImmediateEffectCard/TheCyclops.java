package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le cyclope" permet au joueur de relancer 04 fois l'un de ses 02 des
 * [NB : le de à lancer 4 fois est le même]
 * Et pour chaque ressource d'or obtenue , le joueur peut l'échanger contre 1 point de gloire
 * Elle procure 8 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */


public class TheCyclops extends Card {

    public TheCyclops() {
        this.name = "TheCyclops";
        this.gloryPoints = 8;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 6;
        this.portal = 4;
    }


    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        int a = bot.getStrategy().throwWhichDice();  // choix du dé à lancer

        Bot[] tabBot = new Bot[listBot.size()];
        for (int b = 0; b < listBot.size(); b++) {
            tabBot[b] = listBot.get(b);
        }


        for (int b = 0; b < 4; b++) {
            GeneralFace myFace = bot.rollOneDice(a);     // face obtenue
            myFace.makeCardCyclopEffect(temple, numBot, bot, listFaces, tabBot);  // Effet Cyclop de la face
        }
    }

}