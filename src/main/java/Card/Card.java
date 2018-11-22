package Card;
import Player.Bot;
import Player.Dice;
import Player.HerosInventory;

public abstract class Card {
    
    int gloryPoints;      /* Points de gloire accordés par la carte */
    String type;          /* Gold , Sun , Moon , Sun+Moon  ---> type de ressource pour le payement */
    int price;            /* prix */
    String TypeCard;      /* Immediat I , renfort R, automatique A*/
    int portail;          /* 1,2,3,4,5,6,7 */
    int amount;           /* Quantité */
    int needs;            /* permet de savoir quoi passer en attribut à la methode action d'une carte,
                             0  ---> Le Joueur
                             1  ---> Tous les joueurs en respenctant "Le joueur actif est le prmier dans la liste
                             2  ---> Le Joueur et ses 02 des 
                             3  ---> Le Joueur et son nombre de faces de dés craftés
                          */
    
    
    public void buyCard()     
    {
        amount = amount - 1;
    }
    //if(post instanceof MessagePost)
    // MessagePost msg = (MessagePost) post
    // demander correction de l'ex^ressio
    public void actionCard(Bot bot){};
    
    public void actionCard(Bot... bot){};    
    
    public void actionCard(Bot bot,Dice... dice){};
    
    public void actionCard(Bot bot,int number){};
    
    public void capacity(Bot bot,Dice dice){};   
     
    public void capacity(Bot bot,int choice){};
    /* Methode de renforcement qui sera appelé après chaque lancers de des 
         pour les cartes renforcement que les joueurs a
       Le joueur doit être joueur actif */
    
 
}
