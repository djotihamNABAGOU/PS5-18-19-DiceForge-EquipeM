package Card;
import Player.Bot;
import Player.Dice;


/* Cette carte "La pince" permet au joueur de relancer 02 fois ses 02 des et de collecter les ress
   Elle procure 8 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class ThePliers extends Card{
 
    public ThePliers(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 8;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 6;
        this.portal = 4;
        this.needs = 2;
    } 
    
    @Override
    public void actionCard(Bot bot,Dice... dice)
    {
        bot.getHerosInventory().IncreaseGloryPoints(8);
        /* Pour chacun des 2 des du joueur, le lancent 2 fois et MAJ de l'inventaire */
        for (Dice dice1 : dice){
            dice1.rollDice().makeEffect(bot);    
            dice1.rollDice().makeEffect(bot);
        }
            
    } 
}
