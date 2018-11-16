package Card;
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
        this.gloryPoints = 4;
        this.type = "S";
        this.TypeCard = "R";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 2;
        this.portail = 6;
        this.needs = 0;
    }
    
    @Override
    public void actionCard(HerosInventory inventory)
    {
       inventory.IncreaseGloryPoints(4);
    }
   
    @Override
    public void capacity(HerosInventory inventory,int choice) /* prend en param l'un des 02 des du joueur*/
    {
       switch(choice)
       {
           case 0 : inventory.IncreaseGoldPoints(1);break;
           case 1 : inventory.IncreaseMoonPoints(1);break;
           case 2 : inventory.IncreaseSunPoints(1);break;
       }
    }
}
