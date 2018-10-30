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
public class Dice {
    final DiceFaces faces[];
    
    public Dice() {
        this.faces = new DiceFaces[6];
        for(int i=0; i<6;i++){
            DiceFaces diceFace = new DiceFaces();
            faces[i] = diceFace;
        }
    }
    
    public void makeBrightDefaultDice(){
        
        DiceFaces firstFace = new DiceFaces(1,"G");
        DiceFaces secondFace = new DiceFaces(1,"G");
        DiceFaces thirdFace = new DiceFaces(1,"G");
        DiceFaces fourthFace = new DiceFaces(1,"G");
        DiceFaces fivethFace = new DiceFaces(1,"G");
        DiceFaces sixthFace = new DiceFaces(1,"S");
        
        this.faces[0]=firstFace;
        this.faces[1]=secondFace;
        this.faces[2]=thirdFace;
        this.faces[3]=fourthFace;
        this.faces[4]=fivethFace;
        this.faces[5]=sixthFace;
        
    }
    
    public void makeDarkDefaultDice(){
        
        DiceFaces firstFace = new DiceFaces(1,"G");
        DiceFaces secondFace = new DiceFaces(1,"G");
        DiceFaces thirdFace = new DiceFaces(1,"G");
        DiceFaces fourthFace = new DiceFaces(1,"G");
        DiceFaces fivethFace = new DiceFaces(1,"M");
        DiceFaces sixthFace = new DiceFaces(2,"Gl");
        
        this.faces[0]=firstFace;
        this.faces[1]=secondFace;
        this.faces[2]=thirdFace;
        this.faces[3]=fourthFace;
        this.faces[4]=fivethFace;
        this.faces[5]=sixthFace;
        
    }
    
    public DiceFaces rollDice(){
       Random randomInt = new Random();
       int number = randomInt.nextInt(6);
       return this.faces[number];
       
    }
    
    
    
    
    
}
