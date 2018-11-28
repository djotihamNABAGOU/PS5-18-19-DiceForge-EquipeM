package Card;
import Player.Bot;
import Player.HerosInventory;

/* Cette carte "Le marteau du forgeron" permet d'avoir un jeton marteau 
   Type Immediat ----> Methode "ActionCard" appelé à l'achat mais pas de suppression immédiate
   Utilisation  ---> ☻☻☻ [se referer au DOC de la carte marteau avant de lire la classe ] 
                ---> sera rangé spécialement dans la liste des cartes marteaux pour faciliter la gstion
                ---> methode "Increase GoldPoints" pour gerer le parcours de la carte "Marteau"
*/

public class TheHammer extends Card{
      
    int GoldPoints = 0;       /* represente les 15 emplacements d'or pour le parcours */
    int uses = 2;             /* Nombres d'utilisation restantes */
    
    public TheHammer(int amount)  /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 0;
        this.type = "M";
        this.TypeCard = "I";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 1;
        this.portal = 1;
        this.needs = 0;
    }
    
    @Override
    public void actionCard(Bot bot)
    {
       bot.getHerosInventory().tokenHammer = bot.getHerosInventory().tokenHammer + 1;   /* procure un jeton marteau */
    }
    
    public void IncreaseGoldPoints(int points,HerosInventory inventory)
    {
      GoldPoints = GoldPoints + points;
      if(GoldPoints == 15)
      {  
          if(uses==2)   /* 1ere utilisation */
          {
              inventory.IncreaseGloryPoints(10);
              uses = 1;
          }
          else          /* 2eme utilisation */
          {
              inventory.IncreaseGloryPoints(15);
              uses = 0;
              inventory.tokenHammer = 0;     /* Remettre le jeton à 0 */
          }
           
      }
    }
    
    public int getGoldPoints()
    {
        return GoldPoints;
    }
    
    public int getUses()
    {
        return uses;
    }
}


