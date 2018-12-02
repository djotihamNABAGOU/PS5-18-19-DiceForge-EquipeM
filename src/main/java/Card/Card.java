package Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public abstract class Card {
    
    String name;
    int gloryPoints;      /* Points de gloire accordés par la carte */
    String type;          /* Gold , Sun , Moon , Sun+Moon  ---> type de ressource pour le payement */
    int price;            /* prix */
    String TypeCard;      /* Immediat I , renfort R, automatique A*/
    int portail;          /* 1,2,3,4,5,6,7 */
    int amount;           /* Quantité */

    
    @Override
    public boolean equals(Object obj)
    {
        Card other = (Card) obj;
        return name.equals(other.name);
    }
    
    public void buyCard()     
    {
        amount = amount - 1;
    }

    // voir commentaire sur les parametres dans la classe "GeneralFaces"
    public void actionCard(Temple temple,Bot bot,int numBot,ArrayList<GeneralFace>[] listFaces,ArrayList<Bot> listBot){
    }
    
    
    public void capacity(Temple temple,Bot bot,int numBot,ArrayList<GeneralFace>[] listFaces,ArrayList<Bot> listBot){}
    /* Methode de renforcement qui sera appelé après chaque lancers de des 
         pour les cartes renforcement que les joueurs a
       Le joueur doit être joueur actif */

    
}
