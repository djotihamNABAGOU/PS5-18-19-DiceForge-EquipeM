package Card;
import Player.Bot;
import Player.Dice;
import Player.HerosInventory;

/* Cette carte "L'ancien" permet au joueur dechanger 3 pieces d'or contre 4 point de gloire
   comme action supplementaire à chaque tour du joueur actif qui l'a en sa posssession 
   Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
                 ----> Carte conservé dans la liste des cartes de renforcement du joueur
                 ----> Methode "Capacity" appelé à chaque tour actif du joueur en possesion
*/

public class TheFormer extends Card{
 
    public TheFormer(int amount)   /* prend en parametre le nombre de joueurs */
    { 
        this.gloryPoints = 0;
        this.type = "S";
        this.TypeCard = "R";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 1;
        this.portal = 7;
        this.needs = 0;
        this.capacityNeeds = 0;
    }
    
    @Override
    public void actionCard(Bot bot)
    {
        //Ne donne pas de points de gloire donc methode vide 
    }
    
    @Override
    public void capacity(Bot bot)
    {
        bot.getHerosInventory().IncreaseGloryPoints(4);
        bot.getHerosInventory().DecreaseGoldPoints(3);
    }
}
