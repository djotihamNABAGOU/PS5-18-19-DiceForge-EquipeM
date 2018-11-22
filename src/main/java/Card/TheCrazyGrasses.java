package Card;
import Player.Bot;


/* Cette carte "Les herbes folles" fait gagner 3 Or et 3 Moon comme ressource supplementaire 
   Elle procure 2 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheCrazyGrasses extends Card{
    
    public TheCrazyGrasses(int amount)   /* prend en parametre le nombre de joueurs */
    { 
        this.gloryPoints = 2;
        this.type = "S";
        this.TypeCard = "I";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 1;
        this.portail = 7;
        this.needs = 0;
    }
    
    
    @Override
    public void actionCard(Bot bot)
    {
        bot.getHerosInventory().DecreaseGoldPoints(3);
        bot.getHerosInventory().IncreaseMoonPoints(3);
        bot.getHerosInventory().IncreaseGloryPoints(2);
    }
}
