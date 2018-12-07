package Card;

import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

public class Card {

    protected String name;          //Nom de la carte
    protected int gloryPoints;      /* Points de gloire accordés par la carte */
    protected String type;          /* Gold , Sun , Moon , Sun+Moon  ---> type de ressource pour le payement */
    protected int price;            /* prix */
    protected String TypeCard;      /* Immediat I , renfort R, automatique A*/
    protected int portal;          /* 1,2,3,4,5,6,7 */

    public Card() {
        this.name = "";
    }

    @Override
    public boolean equals(Object obj) {
        Card other = (Card) obj;
        return name.equals(other.name);
    }

    // voir commentaire sur les parametres dans la classe "GeneralFaces"
    protected void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
    }

    public int getPortal(){
    	return this.portal;
    }

    public String getName() {
        return name;
    }
}
