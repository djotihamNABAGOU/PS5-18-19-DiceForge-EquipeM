package Card;

/* Cette carte "Le cyclope" permet au joueur de relancer 04 fois l'un de ses 02 des
   [NB : le de à lancer 4 fois est le même]
   Et pour chaque ressource d'or obtenue , le joueur peut l'échanger contre 1 point de gloire
   Elle procure 8 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/


public class TheCyclops extends Card{
    
    public TheCyclops(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 8;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 6;
        this.portail = 4;
        this.needs = 2;
    } 
    
    /*
    @Override
    public void actionCard(HerosInventory inventory,Dice... dice)
    {
        inventory.IncreaseGloryPoints(8);
        Random randomInt = new Random();
        int number = randomInt.nextInt(2);
        // Relancer l'un de ses 02 dés 4 fois et MAJ de l'inventaire , par defaut premier de pr le moment
        int a = 0;
        for(a=0;a<4;a++){
            DiceFaces face = dice[1].rollDice();
               
                if(face.getType().equals("G") || face.getType().equals("M/S/G")){           
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(1*face.getValue());break;
                     }
                }
                else if(face.getType().equals("2G+1M"))
                {
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : {
                                    inventory.IncreaseGloryPoints(2);
                                    inventory.IncreaseMoonPoints(1);
                         }break;
                     }
                }
                else if(face.getType().equals("3G/2Gl"))
                {    
                    switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(3);break;
                     }           
                }
                else if(face.getType().equals("ALL"))
                {    
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : {
                                    inventory.IncreaseMoonPoints(1);
                                    inventory.IncreaseSunPoints(1);
                                    inventory.IncreaseGoldPoints(2);
                         }break;
                     }
                }
                else if(face.getType().equals("2G/2S/2M"))
                {
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(2);break;
                     }   
                }
                else inventory.increaseInventoryWithDiceRoll(face);
        }
    }
     */
}