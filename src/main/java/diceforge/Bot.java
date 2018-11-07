/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author PS5
 */
public class Bot {
    final HerosInventory herosInventory;
    final Dice firstDice;
    final Dice secondDice;
    final ArrayList<DiceFaces> RemovedFaces;

    public Bot() {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        RemovedFaces = new ArrayList();
   
    }
    
    public void printBotInventoryState(){
        System.out.println("Glory: "+this.herosInventory.getGloryPoints());
        System.out.println("Gold: "+this.herosInventory.getGoldPoints());
        System.out.println("Moon: "+this.herosInventory.getMoonPoints());
        System.out.println("Sun: "+this.herosInventory.getSunPoints());
    }
    
    public void printDiceState(){
        System.out.println("------First Dice-------"); 
        for(int i=0; i<6; i++){
            firstDice.faces[i].printDiceFaces();    
        }
        System.out.println("------Second Dice-------"); 
        for(int i=0; i<6; i++){
            secondDice.faces[i].printDiceFaces();    
        }
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
    
    
    
    
    
}
