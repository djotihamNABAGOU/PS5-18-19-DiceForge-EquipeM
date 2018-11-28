package diceforge;

import Card.Card;

import java.util.ArrayList;

public class Island {
    private int portal;
    ArrayList<Card> pile1 = new ArrayList<Card>();
    ArrayList<Card> pile2 = new ArrayList<Card>();

    public Island(int portal, ArrayList<Card> pile1, ArrayList<Card> pile2) {
        this.portal = portal;
        this.pile1 = pile1;
        this.pile2 = pile2;
    }
}
