/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Player.Bot;

/**
 *
 * @author Destroyer
 */
public class GeneralFace {
    
    String name;
     public GeneralFace() {
        this.name = "";
    }
    public GeneralFace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void makeEffect(Bot bot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
}
