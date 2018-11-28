/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Player.Bot;

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
    public void makeEffect(int numBot,Bot bot,GeneralFace... data) {
        System.out.println("Face obtained -> " + toString());
        bot.getHerosInventory().increaseInventoryWithDiceFace(this);
    }

}
