package Card;

import Faces.GeneralFace;
import Player.Bot;
import Player.Dice;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "L'enigme" permet au joueur de relancer 04 fois l'un de ses 02 des
 * [NB : le de à lancer 4 fois est le même]
 * Elle procure 10 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheRiddle extends Card {

    public TheRiddle(int amount) /* prend en parametre le nombre de joueurs */ {
        this.gloryPoints = 10;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 6;
        this.portal = 4;
    }


    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        /* Relancer l'un de ses 02 dés 4 fois et MAJ de l'inventaire */
        int a = bot.getStrategy().throwWhichDice();  // choix du dé à lancer
        for (int b = 0; b < 4; b++) {
            GeneralFace myFace = bot.rollOneDice(a);     // face obtenue
            myFace.makeEffect(0, temple, numBot, bot, listFaces);  // Effet Cyclop de la face
        }
    }

}
