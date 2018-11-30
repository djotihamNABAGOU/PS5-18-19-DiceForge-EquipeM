package Card;
import Player.Bot;
import Player.HerosInventory;

/* Cette carte "Les ailes de la gardienne" permet au joueur de choisir entre 1G/1S/1M 
   comme ressource supplementaire 
   Elle procure 4 points de gloire à la fin de la partie
   Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
                 ----> Carte conservé dans la liste des cartes de renforcement du joueur
                 ----> Methode "Capacity" appelé à chaque tour actif du joueur en possesion
*/


public class TheWingsOfTheGuardians extends Card{
    
    public TheWingsOfTheGuardians(int amount)   /* prend en parametre le nombre de joueurs */
    { 
        this.name = "TheWingsOfTheGuardians";
        this.gloryPoints = 4;
        this.type = "S";
        this.TypeCard = "R";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 2;
        this.portail = 6;
        this.needs = 0;
        this.capacityNeeds = 2;
    }
    
    @Override
    public void actionCard(Bot bot)
    {
       bot.getHerosInventory().IncreaseGloryPoints(4);
    }
   
    @Override
    public void capacity(Bot bot,int choice) /* prend en param l'un des 02 des du joueur*/
    {
       switch(choice)
       {
           case 0 : bot.getHerosInventory().IncreaseGoldPoints(1);break;
           case 1 : bot.getHerosInventory().IncreaseMoonPoints(1);break;
           case 2 : bot.getHerosInventory().IncreaseSunPoints(1);break;
       }
    }
}
