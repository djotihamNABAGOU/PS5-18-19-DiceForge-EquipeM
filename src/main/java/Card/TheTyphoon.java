package Card;
import Player.Bot;


/* Cette carte "Le typhon" permet au joueur d'avoir un point de gloire par face de dé crafté
   Elle procure 16 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheTyphoon extends Card{
    
    public TheTyphoon(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 16;
        this.type = "M+S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 10;
        this.portail = 4;
        this.needs = 3;
    } 
    
    @Override
    public void actionCard(Bot bot,int nb) /* nb: nombres de faces craftés */
    {
        bot.getHerosInventory().IncreaseGloryPoints(16+(1*nb));      
    } 
}
