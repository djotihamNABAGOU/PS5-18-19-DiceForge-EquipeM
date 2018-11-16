package Card;
import Player.HerosInventory;

/* Cette carte "Le coffre du forgeron" permet d'avoir une extension de 4 places pour chacune de 
   ses ressources.
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheFort extends Card {
    
    public TheFort(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 0;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 1;
        this.portail = 1;
        this.needs = 0;
    }
    
    @Override
    public void actionCard(HerosInventory inventory)
    {
        inventory.goldPointsLimit = inventory.goldPointsLimit + 4;
        inventory.moonPointsLimit = inventory.moonPointsLimit + 4;
        inventory.sunPointsLimit = inventory.sunPointsLimit + 4;
    }
}


