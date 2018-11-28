package Card;
import Player.Bot;
import Player.Dice;
import Player.HerosInventory;

/* Cette carte "Les sabots d'argent" permet de relancer une fois le dé de son choix pdt son tour
   de joueur actif
   Elle procure 2 points de gloire à la fin de la partie
   Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
                 ----> Carte conservé dans la liste des cartes de renforcement du joueur
                 ----> Methode "Capacity" appelé à chaque tour actif du joueur en possesion
*/

public class TheClogs extends Card{
    
    public TheClogs(int amount)   /* prend en parametre le nombre de joueurs */
    { 
        this.gloryPoints = 2;
        this.type = "M";
        this.TypeCard = "R";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 2;
        this.portal = 2;
        this.needs = 0;
    }
    
    @Override
    public void actionCard(Bot bot)
    {
       bot.getHerosInventory().IncreaseGloryPoints(2);
    }
    
    @Override
    public void capacity(Bot bot,Dice dice) /* prend en param l'un des 02 des du joueur*/
    {
        dice.rollDice().makeEffect(bot);
    }
}

