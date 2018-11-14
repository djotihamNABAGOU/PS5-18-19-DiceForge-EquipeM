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
        for (int i=0; i<5; i++){
            this.faces[i] = new DiceFaces(1,"G");
        }
        this.faces[5] = new DiceFaces(1,"S");
    }
    
    public void makeDarkDefaultDice(){
        for (int i=0; i<4; i++){
            this.faces[i] = new DiceFaces(1,"G");
        }
        this.faces[4] = new DiceFaces(1,"M");
        this.faces[5] = new DiceFaces(2,"Gl");
    }
    
    public DiceFaces rollDice(){
       Random randomInt = new Random();
       int number = randomInt.nextInt(6);
       System.out.println(this.faces[number].toString());
       return this.faces[number];
    }

    @Override
    public String toString() {
        String diceFaces = "";
        for (int i=0; i<6; i++){
            diceFaces = diceFaces + faces[i].toString()+"\n";
        }
        return diceFaces;
    }
}
