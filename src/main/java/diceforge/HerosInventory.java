/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import java.util.Random;

/**
 *
 * @author Destroyer
 */
public class HerosInventory {
    private int gloryPoints;
    private int sunPoints;
    private int moonPoints;
    private int goldPoints;
    private static int gloryPointsLimit = 100;
    private static int moonPointsLimit = 6;
    private static int goldPointsLimit = 12;
    private static int sunPointsLimit = 6;

    public int getGloryPoints() {
        return gloryPoints;
    }

    public int getSunPoints() {
        return sunPoints;
    }

    public int getMoonPoints() {
        return moonPoints;
    }

    public int getGoldPoints() {
        return goldPoints;
    }
    
    

    public HerosInventory() {
        this.gloryPoints = 0;
        this.sunPoints = 0;
        this.moonPoints = 0;
        this.goldPoints = 0;
    }

    public HerosInventory(int gloryPoints, int sunPoints, int moonPoints, int goldPoints) {
        this.gloryPoints = gloryPoints;
        this.sunPoints = sunPoints;
        this.moonPoints = moonPoints;
        this.goldPoints = goldPoints;
    }
    
    public void IncreaseGloryPoints(int points){
        int maxToAdd = gloryPointsLimit - this.gloryPoints;
        if(maxToAdd>points){
            this.gloryPoints+=points;
        }else{
            this.gloryPoints=points-maxToAdd;
            //On doit lui donner un jeton de point de gloire 100
        }
    }
    public void IncreaseMoonPoints(int points){
        int maxToAdd = moonPointsLimit - this.moonPoints;
        if(maxToAdd>points){
            this.moonPoints+=points;
        }else{
            this.moonPoints=moonPointsLimit;
        }
        
    }
    public void IncreaseGoldPoints(int points){
        int maxToAdd = goldPointsLimit - this.goldPoints;
        if(maxToAdd>points){
            this.goldPoints+=points;
        }else{
            this.goldPoints=goldPointsLimit;
        }
    }
    public void DecreaseGoldPoints(int points){
       this.goldPoints-=points;
    }
    public void IncreaseSunPoints(int points){
        int maxToAdd = sunPointsLimit - this.sunPoints;
        if(maxToAdd>points){
            this.sunPoints+=points;
        }else{
            this.sunPoints=sunPointsLimit;
        }
    }
    
    public void makeFirstDefaultHerosInventory(){  //First Hero's inventory initializing
        this.gloryPoints=0;
        this.goldPoints=0;
        this.moonPoints=0;
        this.goldPoints=3;
    }
    public void makeSecondDefaultHerosInventory(){  //Second Hero's inventory initializing
        this.gloryPoints=0;
        this.goldPoints=0;
        this.moonPoints=0;
        this.goldPoints=2;
    }
    public void makeThirdDefaultHerosInventory(){  //Third Hero's inventory initializing
        this.gloryPoints=0;
        this.goldPoints=0;
        this.moonPoints=0;
        this.goldPoints=1;
    }
    public void makeFourthDefaultHerosInventory(){  //Fourth Hero's inventory initializing
        this.gloryPoints=0;
        this.goldPoints=0;
        this.moonPoints=0;
        this.goldPoints=0;
    }
    
    public void increaseInventoryWithDiceRoll(DiceFaces faceObtained){
        if(faceObtained.getType().equals("S")){
            this.IncreaseSunPoints(faceObtained.getValue());
        }
        if(faceObtained.getType().equals("M")){
            this.IncreaseMoonPoints(faceObtained.getValue());
        }
        if(faceObtained.getType().equals("G")){
            this.IncreaseGoldPoints(faceObtained.getValue());
        }
        if(faceObtained.getType().equals("Gl")){
            this.IncreaseGloryPoints(faceObtained.getValue());
        }
        if(faceObtained.getType().equals("2G+1M")){
            this.IncreaseGoldPoints(2);
            this.IncreaseMoonPoints(1);
        }
        if(faceObtained.getType().equals("1Gl+1S")){
            this.IncreaseGloryPoints(1); 
            this.IncreaseSunPoints(1);
        }
        if(faceObtained.getType().equals("M/S/G")){
            Random randomInt = new Random();
            int number = randomInt.nextInt(3);
            switch(number) {
                case 0:
                    this.IncreaseMoonPoints(1); 
                    break;
                case 1:
                    this.IncreaseSunPoints(1);
                    break;
                case 2:
                    this.IncreaseGoldPoints(1);
                    break;
            }
        }
        if(faceObtained.getType().equals("3G/2Gl")){
            Random randomInt = new Random();
            int number = randomInt.nextInt(2);
            switch(number) {
                case 0:
                    this.IncreaseGoldPoints(3); 
                    break;
                case 1:
                    this.IncreaseGloryPoints(2);
                    break;
            }
        }
        if(faceObtained.getType().equals("ALL")){
            this.IncreaseSunPoints(1);
            this.IncreaseMoonPoints(1);
            this.IncreaseGloryPoints(1);
            this.IncreaseGoldPoints(1);
        }
        if(faceObtained.getType().equals("2G/2S/2M")){
            Random randomInt = new Random();
            int number = randomInt.nextInt(3);
            switch(number) {
                case 0:
                    this.IncreaseMoonPoints(2); 
                    break;
                case 1:
                    this.IncreaseSunPoints(2);
                    break;
                case 2:
                    this.IncreaseGoldPoints(2);
                    break;
            }
        }
        if(faceObtained.getType().equals("2Gl+2M")){
            this.IncreaseGloryPoints(2);
            this.IncreaseMoonPoints(2);
        }
    }
    
    /*On doit avoir des fonctions decrease pour les achats/depenses des points*/  
}
