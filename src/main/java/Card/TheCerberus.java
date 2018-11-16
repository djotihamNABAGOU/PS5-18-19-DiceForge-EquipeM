package Card;
import Player.HerosInventory;

/* Cette carte "Le Triton" permet au joueur d'acquerir un jeton Cerbère
   Elle procure 6 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheCerberus extends Card{
    
    public TheCerberus(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 5;
        this.portail = 3;
        this.needs = 0;
    } 
    
    @Override
    public void actionCard(HerosInventory inventory)
    {
        inventory.IncreaseGloryPoints(6);
        inventory.tokenCerberus = inventory.tokenCerberus + 1; /* procure un jeton cerbère */
    }
    
}
