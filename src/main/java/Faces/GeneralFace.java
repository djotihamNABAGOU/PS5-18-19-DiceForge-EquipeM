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
 *
 * @author The Beginners
 */
public class GeneralFace {
    
    protected String name;

    public GeneralFace() {
        this.name = "";
    }

    public GeneralFace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        GeneralFace other = (GeneralFace) obj;
        return name.equals(other.name);
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
        
    
}
