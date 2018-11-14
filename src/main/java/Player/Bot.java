/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Faces.DiceFaces;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Destroyer
 */
public class Bot {
    private HerosInventory herosInventory;
    private Dice firstDice;
    private Dice secondDice;
    private ArrayList<DiceFaces> RemovedFaces;

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
    
    
    
}
