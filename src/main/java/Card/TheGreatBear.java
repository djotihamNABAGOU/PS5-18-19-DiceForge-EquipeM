package Card;

import Faces.GardenFace;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "La grande ourse" permet d'obtenir 3 points de gloire lorsqu'on est chassé ou qu'on
 * est chassé
 * Elle procure 2 points de gloire à la fin de la partie
 * Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
 * ----> Carte conservé dans la liste des cartes de renforcement du joueur
 * ----> Methode "Capacity" appelé si la condition automatique se produit
 */

public class TheGreatBear extends Card {

    public TheGreatBear(int amount)  /* prend en parametre le nombre de joueurs */ {
        this.name = "TheGreatBear";
        this.gloryPoints = 2;
        this.type = "M";
        this.TypeCard = "R";
        if (amount == 2 || amount == 4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 2;
        this.portal = 2;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
    }

    @Override
    public void capacity(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces) {
        bot.getHerosInventory().IncreaseGloryPoints(3);
    }

}