package Player;

import Card.Reinforcement;
import Card.ImmediateEffectCard.TheHammer;
import Faces.GeneralFace;
import Faces.SimpleFace;
//import PlayerStrategy.ImediaCardStrategy;
import PlayerStrategy.RandomStrategy;
import PlayerStrategy.Strategy;
import PlayerStrategy.NothingStrategy;
import diceforge.Temple;

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
    private final ArrayList<Reinforcement> enhancementCard = new ArrayList<>();   /* Liste des cartes de renfort en possession du joueur */
    private ArrayList<TheHammer> hammerCard;   /* Liste des cartes marteaux en possession du joueur */
    private ArrayList<Reinforcement> automaticCard;   /* Liste des cartes à effets automatiques en possession du joueur */
    public int wonRounds;
    private int portal;          /* 1,2,3,4,5,6,7  values of the gate in Island
                                   0 is the default value i.e. the bot is on orginal gate
                                    This value must be update when the bot move on a gate */

    public Bot(String strategyName) {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();
        this.strategyName = strategyName;
        this.portal =0; /*Bot is on the default gate at the beginning*/
        switch (strategyName) {

            case "Random":
                this.strategy = new RandomStrategy(this);
                break;

            case "Nothing":
                this.strategy = new NothingStrategy(this);
                break;

           /* case "Immediat":
                this.strategy = new ImediaCardStrategy(this);break;*/

            default:
                this.strategy = new Strategy(this);
                break;
        }
        this.wonRounds = 0;
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

    public ArrayList<Reinforcement> getAutomaticCard() {
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
            this.herosInventory.increaseInventoryWithDiceFace(dicefaceT, 1);
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

    public ArrayList<Reinforcement> getEnhancementCard() {
        return enhancementCard;
    }

    //Lancer un dé au choix prédefini
    public GeneralFace rollOneDice(int a) {

        switch (a) {
            case 0:
                return this.getFirstDice().rollDice();
            case 1:
                return this.getSecondDice().rollDice();
            default:
                return this.getFirstDice().rollDice();
        }
    }
    
    public void updateMyPortal(int gate){
        this.portal=gate;
    }
    public int getMyPortal(){
        return this.portal;
    }
    
    /*Hunted bot must do some action-> they are implements here*/
    public void justHuntedBotAct(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
         
        this.getFirstDice().rollDice().makeEffect(action, favMin, temple, numBot, this, data, listBot);
        this.getSecondDice().rollDice().makeEffect(action, favMin, temple, numBot, this, data, listBot);
    }


}
