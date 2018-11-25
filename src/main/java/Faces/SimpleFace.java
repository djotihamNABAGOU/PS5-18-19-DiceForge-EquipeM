/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Player.Bot;

/**
 *
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

    /*public SimpleFace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

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
        return "Type: "+this.getType()+" Value: "+this.getValue();
    }
    @Override
    public void makeEffect(Bot bot){
        bot.getHerosInventory().increaseInventoryWithDiceFace(this);
    }

}
