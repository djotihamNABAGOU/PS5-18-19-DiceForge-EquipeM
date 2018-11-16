package Card;
import Player.HerosInventory;

/* Cette carte "La meduse" permet tout simplement de gagner 14 points de gloire
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
   { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
*/

public class TheJellyFish extends Card{
    
    public TheJellyFish(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 14;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 4;
        this.portail = 5;
        this.needs = 0;
    }
     
    @Override
    public void actionCard(HerosInventory inventory)
    {
        inventory.IncreaseGloryPoints(14);
    }
}
