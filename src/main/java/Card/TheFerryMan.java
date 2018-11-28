package Card;
import Player.Bot;

/* Cette carte "Le passeur" permet tout simplement de gagner 12 points de gloire
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
   { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
*/

public class TheFerryMan extends Card{
    
    public TheFerryMan(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 12;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 4;
        this.portal = 3;
        this.needs = 0;
    }
    
    @Override
    public void actionCard(Bot bot)
    {
        bot.getHerosInventory().IncreaseGloryPoints(12);
    }
}
