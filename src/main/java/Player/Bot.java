package Player;
import Card.Card;
import Card.TheHammer;
import Faces.DiceFaces;
import java.util.ArrayList;
import java.util.Random;


public class Bot {
    private HerosInventory herosInventory;
    private Dice firstDice;
    private Dice secondDice;
    private ArrayList<DiceFaces> RemovedFaces;
    
    private ArrayList<Card> enhancementCard;   /* Liste des cartes de renfort en possession du joueur */
    private ArrayList<TheHammer> hammerCard;   /* Liste des cartes marteaux en possession du joueur */
    
    public HerosInventory getHerosInventory() {
        return herosInventory;
    }

    public Dice getFirstDice() {
        return firstDice;
    }

    public Dice getSecondDice() {
        return secondDice;
    }

    public ArrayList<DiceFaces> getRemovedFaces() {
        return RemovedFaces;
    }

    public Bot() {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();

    }

    public void printDiceState(){
        System.out.println("------First Dice-------"); 
        System.out.println(firstDice.toString());
        System.out.println("------Second Dice-------");
        System.out.println(secondDice.toString());
    }
    
    public void forgeDiceFace(DiceFaces face){
        DiceFaces temp = new DiceFaces();
        
        Random randomInt = new Random();
        int numberOfFace = randomInt.nextInt(6); //Random pour prendre la face a enlever
        Random randomSecondInt = new Random();
        int numberOfDice = randomSecondInt.nextInt(2)+1; //Random pour prendre le dee sur lequel il faut forger
        if(numberOfDice==1){ //Premier dee
            
            this.RemovedFaces.add(this.firstDice.faces[numberOfFace]); //Substitution--forge
            this.firstDice.faces[numberOfFace]=face;
            
        }else{ // Second dee
            this.RemovedFaces.add(this.secondDice.faces[numberOfFace]); //Substitution--forge
            this.secondDice.faces[numberOfFace]=face;
        }      
    }


    @Override
    public String toString() {
        return "Glory: "+this.herosInventory.getGloryPoints()+"\nGold: "+this.herosInventory.getGoldPoints()+"\nMoon: "+this.herosInventory.getMoonPoints()+"\nSun: "+this.herosInventory.getSunPoints();
    }
    
    /* permet au joueur d'utiliser un de ses jetons Triton */
    public void useNewtToken(int number)   /* Le paramètre indique la ressource choisie par le joueur */
    {
        if(this.herosInventory.tokenNewt>=1)
        {
            switch(number)
            {
                case 0 : this.herosInventory.IncreaseMoonPoints(2); break;
                case 1 : this.herosInventory.IncreaseMoonPoints(2); break;
                case 2 : this.herosInventory.DecreaseGoldPoints(6); break;
            }
            this.herosInventory.tokenNewt = this.herosInventory.tokenNewt - 1;
        }
    }
    
    /* permet au joueur d'utiliser un de ses jetons cerbères */
    /* Les paramètres seront les faces obtenues par le joueur après son lancer */
    public void useCerberusToken(DiceFaces... diceface) 
    {
        for(DiceFaces dicefaceT : diceface){
              this.herosInventory.increaseInventoryWithDiceRoll(dicefaceT);
        }
    }
    
    /* permet d'utiliser un jeton marteau */
    /* ☺♠☻ se referer à la DOC et à la classe "TheHammmer" avant de lire cette methode*/
    public void useHammerToken(int goldPoints)  /*en parametre points d'or pr le parcours */
    {
        hammerCard.get(0).IncreaseGoldPoints(goldPoints,this.herosInventory);
        if(hammerCard.get(0).getUses()==0)
        {
            /* La carte marteau a deja ete utilise 2 fois selon les termes du jeu , suppression */
            hammerCard.remove(0);
        }
    }
    
}
