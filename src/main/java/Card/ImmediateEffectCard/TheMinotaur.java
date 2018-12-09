package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;
/**
 * Cette carte "Le minautaure"
 * voir DOC ☺☻
 */
public class TheMinotaur extends Card {

    public TheMinotaur() {
        this.name = "TheTheMinotaur";
        this.gloryPoints = 8;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 3;
        this.portal = 6;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(6);

        //Lancer des dés par les autres joueurs et remplacements des faces dans la liste
        for (int a = 0; a < tabBot.length; a++) {
            if (a != numBot) {  // le joueur lui ne relance plus
                GeneralFace one = tabBot[a].getFirstDice().rollDice();
                GeneralFace two = tabBot[a].getSecondDice().rollDice();
                listFaces[a].set(0, one); // changement par la nouvelle face
                listFaces[a].set(1, two); // changement par la nouvelle face
            }
        }

        //Appliquer enfin l'effet des faces pour les autres joeurs seulement
        // Seul cas oû on decremente ---> int action = 1
        for (int a = 0; a < tabBot.length; a++) {
            if (a != numBot) {
                listFaces[a].get(0).makeEffect(1, 1, temple, numBot, bot, listFaces, tabBot);
                listFaces[a].get(1).makeEffect(1, 1, temple, numBot, bot, listFaces, tabBot);
            }
        }
    }
}
