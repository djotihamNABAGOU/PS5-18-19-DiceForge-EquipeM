/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

/**
 *
 * @author Destroyer
 */
public class DiceFaces {
    private int value;
    private String type; /*Sun S, Moon M, Gold G, Glory Gl*/

    public DiceFaces() {
        this.value = 0;
        this.type = "";
    }

    public DiceFaces(int value, String type) {
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
        return "Type: "+this.getType()+" Value: "+this.getValue();
    }

}
