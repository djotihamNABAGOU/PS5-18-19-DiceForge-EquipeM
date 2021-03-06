/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import Faces.GeneralFace;
import Faces.Sanctuary.SimpleFace;

import java.util.Random;

/**
 * @author The Beginners
 */
public class Dice {
    private GeneralFace faces[];

    public Dice() {
        this.faces = new GeneralFace[6];
    }

    public GeneralFace[] getFaces() {
        return faces;
    }

    public void setFaces(GeneralFace face, int faceIndex) {
        faces[faceIndex] = face;
    }

    public void makeBrightDefaultDice() {
        for (int i = 0; i < 5; i++) {
            this.faces[i] = new SimpleFace(1, "G", "GoldenFace");
        }
        this.faces[5] = new SimpleFace(1, "S", "SunFace");
    }

    public void makeDarkDefaultDice() {
        for (int i = 0; i < 4; i++) {
            this.faces[i] = new SimpleFace(1, "G", "GoldenFace");
        }
        this.faces[4] = new SimpleFace(1, "M", "MoonFace");
        this.faces[5] = new SimpleFace(2, "Gl", "GloryFace");
    }

    public GeneralFace rollDice() {
        Random randomInt = new Random();
        int number = randomInt.nextInt(6);
        return this.faces[number];
    }
    
    public boolean equalityFace(GeneralFace face,int position)
    {
       if(this.faces[position].equals(face)){
           return true;
       }
       else return false;
    }
    
   
    
    @Override
    public String toString() {
        String diceFaces = "";
        for (int i = 0; i < 6; i++) {
            diceFaces += faces[i].toString() + "\n";
        }
        return diceFaces;
    }
    
    public void resetFace(){
     for (int i = 0; i < 6; i++) {
            this.faces[i].initialize();
        }   
    }
}
