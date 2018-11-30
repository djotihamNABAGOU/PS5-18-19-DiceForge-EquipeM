/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

/**
 * @author The Beginners
 */
public class SimpleFace extends GeneralFace {
    private int value;
    private String type; /*Sun S, Moon M, Gold G, Glory Gl*/


    public SimpleFace(int value, String type, String name) {
        super(name);
        this.value = value;
        this.type = type;
    }
    
    public SimpleFace(SimpleFace face) {
        super(face.name);
        this.value = face.value;
        this.type = face.type;
    }

    SimpleFace() {
        
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type: " + this.getType() + " Value: " + this.getValue();
    }

    @Override
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        if(data.length!=0){ // si == 0, faveur mineure
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        }
            
        if(a==0){
            makeEffectFaceMultiplier(temple,numBot,bot,1);
        }
    }
    
    @Override
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data)
    {
        System.out.println("Face obtained  -> " + toString());
        bot.getHerosInventory().increaseInventoryWithDiceFace(this,a);
    }
}
