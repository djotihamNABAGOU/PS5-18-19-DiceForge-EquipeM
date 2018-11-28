package Card;
import Player.Bot;

/* Cette carte "L'hydre" permet tout simplement de gagner 26 points de gloire
  Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
  { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
*/

public class TheHydra extends Card{
    
    public TheHydra(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 26;
        this.type = "M+S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 10;
        this.portal = 4;
        this.needs = 0;
    } 
     
    @Override
    public void actionCard(Bot bot)
    {
        bot.getHerosInventory().IncreaseGloryPoints(26);
    }
}
