package Card;

/* Cette carte "La Sentinelle" permet au joueur de relancer 02 fois ses 02 des
   En lancant , s'il tombe sur la face "Sun" ou "Moon", il a le choix entre 
   les garder ou obtenir 02 points de gloire pr chacune de ses faces.
   Elle procure 6 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheSentinel extends Card{
    
    public TheSentinel(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 6;
        this.portal = 4;
        this.needs = 2;
    } 
    
    /*
    @Override
    public void actionCard(HerosInventory inventory,Dice... dice)
    {
        inventory.IncreaseGloryPoints(6);  
        Random randomInt = new Random();
        int number = randomInt.nextInt(2); // Nombre choisi au hasard
       
        // Pour chacun des 2 des du joueur, le lancent 2 fois et MAJ de l'inventaire
        for (Dice dice1 : dice){
            for(int a = 0;a<2;a++)
            {
                DiceFaces face = dice1.rollDice();
                
                if(face.getType().equals("S") || face.getType().equals("M") || 
                   face.getType().equals("M/S/G") ){           
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(2*face.getValue());break;
                     }
                }
                else if(face.getType().equals("2G+1M"))
                {
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : {
                                    inventory.IncreaseGloryPoints(2);
                                    inventory.IncreaseGoldPoints(2);
                         }break;
                     }
                }
                else if(face.getType().equals("1Gl+1S"))
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
                                    inventory.IncreaseGloryPoints(5);
                                    inventory.IncreaseGoldPoints(1);
                         }break;
                     }
                }
                else if(face.getType().equals("2G/2S/2M"))
                {
                     switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(4);break;
                     }   
                }
                else if(face.getType().equals("2Gl+2M"))
                {
                    switch(number){
                         case 0 : inventory.increaseInventoryWithDiceRoll(face);break;
                         case 1 : inventory.IncreaseGloryPoints(6);break;
                     } 
                }
                else inventory.increaseInventoryWithDiceRoll(face);
            }
        }        
    }*/
    
}
