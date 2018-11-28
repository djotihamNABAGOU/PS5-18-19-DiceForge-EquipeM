package Card;
import Player.Bot;
import Player.Dice;

/* Cette carte "L'enigme" permet au joueur de relancer 04 fois l'un de ses 02 des
   [NB : le de à lancer 4 fois est le même]
   Elle procure 10 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheRiddle extends Card{
    
    public TheRiddle(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 10;
        this.type = "S";
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
        bot.getHerosInventory().IncreaseGloryPoints(10);
        /* Relancer l'un de ses 02 dés 4 fois et MAJ de l'inventaire , par defaut premier de pr le moment*/
        int a = 0;
        for(a=0;a<4;a++){
            dice[1].rollDice().makeEffect(bot);
        }          
    } 
}
