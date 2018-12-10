package Player;

import Card.Card;
import Card.Reinforcement;
import Card.ImmediateEffectCard.TheHammer;
import Faces.GeneralFace;
import Faces.Sanctuary.SimpleFace;
import PlayerStrategy.*;
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
    private Strategy strategy;//stratégie du joueur durant tout le déroulement du jeu
    private String strategyName;
    private boolean active = false;
    private ArrayList<Reinforcement> reinforcementCard = new ArrayList<>();   /* Liste des cartes de renfort en possession du joueur */
    private ArrayList<TheHammer> hammerCard = new ArrayList<>();   /* Liste des cartes marteaux en possession du joueur */
    private ArrayList<Reinforcement> automaticCard = new ArrayList<>();   /* Liste des cartes à  effet automatique en possession du joueur */
    private ArrayList<Card> immediateCard = new ArrayList<>();   /* Liste des cartes à  effet immédiat en possession du joueur */
    private ArrayList<Card> withoutEffectCard = new ArrayList<>();   /* Liste des cartes à  sans effet en possession du joueur */
    public int wonRounds;//nombre de parties gagnées par le bot, public car c'est le moteur de jeu qui lui confère cela
    private int portal;          /* 1,2,3,4,5,6,7  values of the gate in Island
                                   0 is the default value i.e. the bot is on orginal gate
                                    This value must be update when the bot move on a gate */
    private ArrayList<Integer> moy = new ArrayList<>();

    public Bot(String strategyName) {
        boolean active = false;
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();
        this.strategyName = strategyName;
        this.portal = 0; /*Bot is on the default gate at the beginning*/
        switch (strategyName) {

            case "Random":
                this.strategy = new RandomStrategy(this);
                break;

            case "Nothing":
                this.strategy = new NothingStrategy(this);
                break;

            case "Advanced":
                this.strategy = new AdvancedStrategy(this);
                break;

            case "Immediat":
                this.strategy = new ImediaCardStrategy(this);
                break;
                
            case "AdvancedTwo" :
                this.strategy = new AdvancedStrategyTwo(this);
                break;
                
            default:
                this.strategy = new Strategy(this);
                break;
        }
    }
    
    public void addGloryWinnerPoints(int a){
       moy.add(a);    
    }
    
    public float giveMeYourAverageGloryPoints(){
         int a = moy.size();
         int sum = 0;
         for(int b=0;b<moy.size();b++){
             sum = sum + moy.get(b);
         }
         return (sum/a);
    }
    
    public void initialize(){
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList<>();
        this.portal = 0; /*Bot is on the default gate at the beginning*/
        reinforcementCard = new ArrayList<>(); 
        automaticCard = new ArrayList<>(); 
        immediateCard = new ArrayList<>(); 
        hammerCard = new ArrayList<>(); 
        switch (strategyName) {

            case "Random":
                this.strategy = new RandomStrategy(this);
                break;

            case "Nothing":
                this.strategy = new NothingStrategy(this);
                break;

            case "Advanced":
                this.strategy = new AdvancedStrategy(this);
                break;

            case "Immediat":
                this.strategy = new ImediaCardStrategy(this);
                break;
                
            case "AdvancedTwo" :
                this.strategy = new AdvancedStrategyTwo(this);
                break;
                
            default:
                this.strategy = new Strategy(this);
                break;
        }
        
    }

    public ArrayList<TheHammer> getHammer() {
        return this.hammerCard;
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

    public ArrayList<Card> getImmediateCard() {
        return immediateCard;
    }

    public ArrayList<Card> getWithoutEffectCard() {
        return withoutEffectCard;
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

    public ArrayList<Reinforcement> getReinforcementCard() {
        return reinforcementCard;
    }

    public void useNewtToken() {
        int number = this.getStrategy().giveMeTokenResource();  // Ressource choisie par le joueur
        if (this.herosInventory.tokenNewt >= 1) {
            switch (number) {
                case 0:
                    this.herosInventory.IncreaseMoonPoints(2);
                    break;
                case 1:
                    this.herosInventory.IncreaseSunPoints(2);
                    break;
                case 2: {
                    int winnerGoldPoints = 6;
                    if (this.hammerCard.size() > 0) {
                        winnerGoldPoints = this.strategy.applyHammerStrategy(6);
                    }
                    this.herosInventory.IncreaseGoldPoints(winnerGoldPoints);
                }
                break;
            }
            this.herosInventory.tokenNewt = this.herosInventory.tokenNewt - 1;
        }
    }

    //Lancer un dÃ© au choix prÃ©defini
    public Faces.GeneralFace rollOneDice(int a) {

        switch (a) {
            case 0:
                return this.getFirstDice().rollDice();
            case 1:
                return this.getSecondDice().rollDice();
            default:
                return this.getFirstDice().rollDice();
        }
    }


    public void updateMyPortal(int gate) {
        this.portal = gate;
    }

    public int getMyPortal() {
        return this.portal;
    }

    /*Hunted bot must do some action-> they are implements here*/
    public void justHuntedBotAct(Temple temple, int numBot, ArrayList<GeneralFace>[] data, Bot... listBot) {

        this.getFirstDice().rollDice().makeEffect(0, 1, temple, numBot, this, data, listBot);
        this.getSecondDice().rollDice().makeEffect(0, 1, temple, numBot, this, data, listBot);
        this.updateMyPortal(0); //This Bot  must return to the orginal portal

        //Use of fonctional method
        /*
         this.automaticCard.stream().filter((card) -> (card.getName().equals("TheGreatBear"))).forEachOrdered((card) -> {
            card.capacity(temple, this, numBot, data, listBot);
        });
        */
        for (Reinforcement card : this.automaticCard) {
            if (card.getName().equals("TheGreatBear")) {
                card.capacity(temple, this, numBot, data, listBot);
            }
        }
    }

    /*Hunter bot must do some action-> they are implements here*/
    public void hunterBotAct(Temple temple, int numBot, ArrayList<GeneralFace>[] data, Bot... listBot) {

        for (Reinforcement card : this.automaticCard) {
            if (card.getName().equals("TheGreatBear")) {
                card.capacity(temple, this, numBot, data, listBot);
            }
        }
    }
    
    public void addImmediateEffectCard(Card card){
        if (card != null) immediateCard.add(card);
        else System.out.println("Null Immediate Effect Card affected !");
    }

    public void addAutomaticEffectCard(Reinforcement card){
        if (card != null) automaticCard.add(card);
        else System.out.println("Null Automatic Effect Card affected !");
    }

    public void addReinforcementEffectCard(Reinforcement card){
        if (card != null) reinforcementCard.add(card);
        else System.out.println("Null Immediate Effect Card affected !");
    }

    public void addWithoutEffectCard(Card card){
        if (card != null) withoutEffectCard.add(card);
        else System.out.println("Null Immediate Effect Card affected !");
    }
}
