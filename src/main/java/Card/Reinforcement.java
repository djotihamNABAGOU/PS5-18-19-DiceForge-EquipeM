package Card;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

public class Reinforcement extends Card {

    public Reinforcement() {
    }

    /**
     * Methode de renforcement qui sera appelé après chaque lancers de des
     * pour les cartes renforcement que les joueurs a
     * Le joueur doit être joueur actif
     *
     * @param temple
     * @param bot
     * @param numBot
     * @param listFaces
     * @param tabBot
     */
    public void capacity(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
    }
}
