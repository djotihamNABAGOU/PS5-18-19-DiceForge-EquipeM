package Card.ImmediateEffectCard;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "La pince" permet au joueur de relancer 02 fois ses 02 des et de collecter les ress
 * Elle procure 8 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class ThePliers extends Card {

    public ThePliers() {
        this.gloryPoints = 8;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 6;
        this.portal = 4;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        /* Pour chacun des 2 des du joueur, le lancent 2 fois et MAJ de l'inventaire */
        Bot[] tabBot = new Bot[listBot.size()];
        for (int b = 0; b < listBot.size(); b++) {
            tabBot[b] = listBot.get(b);
        }

        for (int a = 0; a < 2; a++) {
            GeneralFace one = bot.getFirstDice().rollDice();
            GeneralFace two = bot.getSecondDice().rollDice();
            listFaces[numBot].set(0, one); // changement par la nouvelle face
            listFaces[numBot].set(1, two); // changement par la nouvelle face
            // Appliquer effets des faces


            listFaces[numBot].get(0).makeEffect(0, 1, temple, numBot, bot, listFaces, tabBot);
            listFaces[numBot].get(1).makeEffect(0, 1, temple, numBot, bot, listFaces, tabBot);
        }
    }
}
