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
                             0  ---> son inventaire
                             1  ---> son inventaire et les autres Bots
                             2  ---> son inventaire et ses 02 des 
                             3  ---> son inventaire et son nombre de faces de dés craftés
                          */
    
    
    public void buyCard()     
    {
        amount = amount - 1;
    }
    
    public void actionCard(HerosInventory inventory){};    
    
    public void actionCard(HerosInventory inventory,Bot... nb){};
    
    public void actionCard(HerosInventory inventory,Dice... dice){};
    
    public void actionCard(HerosInventory inventory,int number){};
    
    public void capacity(HerosInventory inventory,Dice dice){};   
     
    public void capacity(HerosInventory inventory,int choice){};
    /* Methode de renforcement qui sera appelé après chaque lancers de des 
         pour les cartes renforcement que les joueurs a
       Le joueur doit être joueur actif */
    
 
}
