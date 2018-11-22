/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Faces.GeneralFace;
import Faces.SimpleFace;

import java.util.Random;

/**
 *
 * @author Destroyer
 */
public class Dice {
    final GeneralFace faces[];
    
    public Dice() {
        this.faces = new SimpleFace[6];
        for(int i=0; i<6;i++){
            SimpleFace diceFace = new SimpleFace();
            faces[i] = diceFace;
        }
    }

    public GeneralFace[] getFaces() {
        return faces;
    }

    public void makeBrightDefaultDice(){
        for (int i=0; i<5; i++){
            this.faces[i] = new SimpleFace(1,"G","GoldenFace");
        }
        this.faces[5] = new SimpleFace(1,"S","SunFace");
    }
    
    public void makeDarkDefaultDice(){
        for (int i=0; i<4; i++){
            this.faces[i] = new SimpleFace(1,"G","GoldenFace");
        }
        this.faces[4] = new SimpleFace(1,"M","MoonFace");
        this.faces[5] = new SimpleFace(2,"Gl","GloryFace");
    }
    
    public GeneralFace rollDice(){
       Random randomInt = new Random();
       int number = randomInt.nextInt(6);
       //System.out.println(this.faces[number].toString());
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
