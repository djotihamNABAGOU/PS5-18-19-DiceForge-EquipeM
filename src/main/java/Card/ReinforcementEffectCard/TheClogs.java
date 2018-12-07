package Card.ReinforcementEffectCard;

import Card.Reinforcement;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;


/**
 * Cette carte "Les sabots d'argent" permet de relancer une fois le dé de son choix pdt son tour
 * de joueur actif
 * Elle procure 2 points de gloire à la fin de la partie
 * Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
 * ----> Carte conservé dans la liste des cartes de renforcement du joueur
 * ----> Methode "Capacity" appelé à chaque tour actif du joueur en possesion
 */

public class TheClogs extends Reinforcement {

    public TheClogs() {
        this.name = "TheClogs";
        this.gloryPoints = 2;
        this.type = "M";
        this.TypeCard = "R";
        this.price = 2;
        this.portal = 2;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(2);
    }


    // les faces de sautres adversairees est le dernier argument 
    @Override
    public void capacity(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        int a = bot.getStrategy().throwWhichDice();  // choix du dé à lancer
        GeneralFace myFace = bot.rollOneDice(a);     // face obtenue
        /*Bot[] tabBot = new Bot[listBot.size()];
        for (int b = 0; b < listBot.size(); b++) {
            tabBot[b] = listBot.get(b);
        }*/
        myFace.makeEffect(0, 0, temple, numBot, bot, listFaces, tabBot);   // Effet de la face
    }

}

