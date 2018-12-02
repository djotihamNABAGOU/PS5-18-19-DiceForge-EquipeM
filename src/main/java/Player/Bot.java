package Player;

import Card.Card;
import Card.TheHammer;
import Faces.GeneralFace;
import Faces.SimpleFace;
import PlayerStrategy.RandomStrategy;
import PlayerStrategy.Strategy;
import PlayerStrategy.NothingStrategy;

import java.util.ArrayList;

/**
 * @author The Beginners
 */
public class Bot {
    private HerosInventory herosInventory;
    private Dice firstDice;
    private Dice secondDice;
    private ArrayList<GeneralFace> RemovedFaces;
    private final Strategy strategy;//stratégie du joueur durant tout le déroulement du jeu
    private String strategyName;
    private boolean active = false;
    private final ArrayList<Card> enhancementCard = new ArrayList<Card>();   /* Liste des cartes de renfort en possession du joueur */
    private ArrayList<TheHammer> hammerCard;   /* Liste des cartes marteaux en possession du joueur */
    private ArrayList<Card> automaticCard;   /* Liste des cartes à effets automatiques en possession du joueur */
    public int roundsWin;

    /*
    public Bot() {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();
        strategy = new Strategy(this);//Un contructeur par défaut de bot crée un bot avec une stratégie qui dépendra entièrement du joueur(entrée standard)
    }*/

    public Bot(String strategyName) {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();
        this.strategyName = strategyName;
        switch (strategyName) {

            case "Random":
                this.strategy = new RandomStrategy(this);
                break;

            case "Nothing":
                this.strategy = new NothingStrategy(this);
                break;

            default:
                this.strategy = new Strategy(this);
                break;
        }
        this.roundsWin = 0;
    }

    public HerosInventory getHerosInventory() {
        return herosInventory;
    }

    public Dice getFirstDice() {
        return firstDice;
    }

    public Dice getSecondDice() {
        return secondDice;
    }

    public ArrayList<GeneralFace> getRemovedFaces() {
        return RemovedFaces;
    }
    
    public ArrayList<Card> getAutomaticCard() {
        return automaticCard;
    }

    public void printDiceState() {
        System.out.println("------First Dice-------");
        System.out.println(firstDice.toString());
        System.out.println("------Second Dice-------");
        System.out.println(secondDice.toString());
    }


    @Override
    public String toString() {
        return "Glory: " + this.herosInventory.getGloryPoints()
                + "\nGold: " + this.herosInventory.getGoldPoints()
                + "\nMoon: " + this.herosInventory.getMoonPoints()
                + "\nSun: " + this.herosInventory.getSunPoints()
                + "\n";
    }

    /**
     * permet au joueur d'utiliser un de ses jetons Triton
     * <<<<<<< HEAD
     *
     * @param number =======
     * @param number indique la ressource choisie par le joueur
     */
    public void useNewtToken(int number) {  /* Le paramètre indique la ressource choisie par le joueur */

        if (this.herosInventory.tokenNewt >= 1) {
            switch (number) {
                case 0:
                    this.herosInventory.IncreaseMoonPoints(2);
                    break;
                case 1:
                    this.herosInventory.IncreaseSunPoints(2);
                    break;
                case 2:
                    this.herosInventory.DecreaseGoldPoints(6);
                    break;
            }
            this.herosInventory.tokenNewt = this.herosInventory.tokenNewt - 1;
        }
    }

    /* permet au joueur d'utiliser un de ses jetons cerbères */
    /* Les paramètres seront les faces obtenues par le joueur après son lancer */
    public void useCerberusToken(SimpleFace... diceface) {

        for (SimpleFace dicefaceT : diceface) {
            this.herosInventory.increaseInventoryWithDiceFace(dicefaceT,1);
        }
    }

    /* permet d'utiliser un jeton marteau */
    /* ☺♠☻ se referer à la DOC et à la classe "TheHammmer" avant de lire cette methode*/
    public void useHammerToken(int goldPoints) { /*en parametre points d'or pr le parcours */

        hammerCard.get(0).IncreaseGoldPoints(goldPoints, this.herosInventory);
        if (hammerCard.get(0).getUses() == 0) {
            /* La carte marteau a deja ete utilise 2 fois selon les termes du jeu , suppression */
            hammerCard.remove(0);
        }
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public ArrayList<Card> getEnhancementCard() {
        return enhancementCard;
    }
    
    //Lancer un dé au choix prédefini
    public GeneralFace rollOneDice(int a){
        
        switch(a){
            case 0 : return this.getFirstDice().rollDice();
            case 1 : return this.getSecondDice().rollDice();
            default: return this.getFirstDice().rollDice();
        }
    }
                    
        
    
    
}
