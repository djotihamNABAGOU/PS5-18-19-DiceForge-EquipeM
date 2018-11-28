package Card;
import Player.Bot;

/* Cette carte "Le Triton" permet au joueur d'acquerir un jeton Triton
   Elle procure 8 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheNewt extends Card{
    
    public TheNewt(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 8;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 4;
        this.portal = 5;
        this.needs = 0;
    } 
    
    @Override
    public void actionCard(Bot bot)
    {
        bot.getHerosInventory().IncreaseGloryPoints(8);
        bot.getHerosInventory().tokenNewt = bot.getHerosInventory().tokenNewt + 1; /* procure un jeton Triton */
    }
}
