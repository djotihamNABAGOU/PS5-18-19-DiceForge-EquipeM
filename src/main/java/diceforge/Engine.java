package diceforge;
import Faces.SanctuarysFaces;
import Player.Bot;
import java.util.ArrayList;
import java.util.Random;


public class Engine {
    private final int set ; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    
     Engine(int set, int number) {
        this.set=set;
        this.numberOfBot=number;

    }
    
     void InitializingBots(Bot botOne, Bot botTwo){
        botOne.getFirstDice().makeBrightDefaultDice();
        botOne.getSecondDice().makeDarkDefaultDice();
        botOne.getHerosInventory().makeFirstDefaultHerosInventory();

        botTwo.getFirstDice().makeBrightDefaultDice();
        botTwo.getSecondDice().makeDarkDefaultDice();
        botTwo.getHerosInventory().makeSecondDefaultHerosInventory();

    }

    /*
    public void RollAndRollSetTimes(Bot botOne,Bot botTwo){
        for(int i=0;i<this.set;i++){
            botOne.getHerosInventory().increaseInventoryWithDiceFace(botOne.getFirstDice().rollDice());
            botOne.getHerosInventory().increaseInventoryWithDiceFace(botOne.getSecondDice().rollDice());
            botTwo.getHerosInventory().increaseInventoryWithDiceFace(botTwo.getFirstDice().rollDice());
            botTwo.getHerosInventory().increaseInventoryWithDiceFace(botTwo.getSecondDice().rollDice());
        }

    }

    private void RollOneTime(Bot theBot){
         theBot.getHerosInventory().increaseInventoryWithDiceFace(theBot.getFirstDice().rollDice());
         theBot.getHerosInventory().increaseInventoryWithDiceFace(theBot.getSecondDice().rollDice());
    }-*/
     
    public void RollAndRollSetTimes(Bot botOne,Bot botTwo){
        for(int i=0;i<this.set;i++){
            botOne.getFirstDice().rollDice().makeEffect(botOne);
            botOne.getSecondDice().rollDice().makeEffect(botOne);
            botTwo.getFirstDice().rollDice().makeEffect(botTwo);
            botTwo.getSecondDice().rollDice().makeEffect(botTwo);
        }

    }

    private void RollOneTime(Bot theBot){
         theBot.getFirstDice().rollDice().makeEffect(theBot);
         theBot.getSecondDice().rollDice().makeEffect(theBot);
    }

    private void MakeOneSetWithTwoBot(Bot botOne,Bot botTwo, Temple temple){
        for(int i=0; i<2; i++){
            System.out.println("Bot "+(i+1)+", Roll or Roll and Forge ?");
            Random randomInt = new Random();
            int number = randomInt.nextInt(2); // 0 for Roll and 1 for Forge
            if(i==0) number = 0;  // For this time 1st Bot will roll and 2nd Bot will forge


            if(i==0){ //First Bot ---> Actions
                      switch(number){
                                      case 0:
                                          System.out.println("-------->ROLL");
                                          RollOneTime(botOne);
                                          break;
                                      case 1:
                                          System.out.println("-------->ROLL AND FORGE");
                                          RollOneTime(botOne);
                                          SanctuarysFaces face = CaseFace(botOne,temple);
                                          if(face.getPrice()!=0)
                                          {
                                              if(temple.buyFace(face)){
                                              botOne.forgeDiceFace(face);
                                              botOne.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                                               }
                                            else{
                                              System.out.println("Purchase failed");
                                          }
                                          }
                                         break;
                      }
            }else{ // Second Bot ---> Actions (i==1)
                   switch(number){
                                      case 0:
                                          System.out.println("-------->ROLL");
                                          RollOneTime(botTwo);
                                          break;
                                      case 1:
                                          System.out.println("-------->ROLL AND FORGE");
                                          RollOneTime(botTwo);
                                          SanctuarysFaces face = CaseFace(botTwo,temple);
                                          
                                          if(face.getPrice()!=0)
                                          {
                                            if(temple.buyFace(face)){
                                                botTwo.forgeDiceFace(face);
                                                botTwo.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                                            }
                                            else{
                                              System.out.println("Purchase failed");
                                            }
                                          }
                                         break;
                      }      
            }
           
       
            
        }
    }

     void MakeNineSetWithTwoBot(Bot botOne,Bot botTwo, Temple temple){
        for(int a = 0;a<9;a++)
        {
            MakeOneSetWithTwoBot(botOne, botTwo, temple);
            System.out.println("\n");
            System.out.println("-------------------------------------\n");
            System.out.println("STATE AFTER "+(a+1)+" SET");
            System.out.println("-->BOT ONE");
            botOne.toString();
            botOne.printDiceState();
            System.out.println("-->BOT TWO");
            botTwo.toString();
            botTwo.printDiceState();
            System.out.println("\n");
        }
    }
    
    private SanctuarysFaces CaseFace(Bot bot, Temple temple)  // retourne le nom de la face à payer choisie au hasard
    {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        for (int i=0; i<temple.getSanctuary().size();i++){
            if(!temple.getSanctuary().get(i).isSelected() 
               && !FacesAvailable.contains(temple.getSanctuary().get(i)) 
               && v>=temple.getSanctuary().get(i).getPrice()){
               
                FacesAvailable.add(temple.getSanctuary().get(i));
            }
        }

        Random randomFace = new Random();

        if(FacesAvailable.size() == 0) return new SanctuarysFaces();
        else{
        int caseFace = randomFace.nextInt(FacesAvailable.size()); // initialisation
        //System.out.println("La face payée est "+FacesAvailable.get(caseFace).toString());
        return FacesAvailable.get(caseFace);
        }

    }
    
     void TellMeTheWinner(Bot botOne,Bot botTwo){
        if(botOne.getHerosInventory().getGloryPoints()>botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Bot 1 wins the game");
        }
        if(botOne.getHerosInventory().getGloryPoints()<botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Bot 2 wins the game");
        }
        if(botOne.getHerosInventory().getGloryPoints()==botTwo.getHerosInventory().getGloryPoints()){
            System.out.println("Its a tie");
        }
      }
     
}
