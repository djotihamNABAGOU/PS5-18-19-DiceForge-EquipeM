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
    
    public void printDiceFaces(){
        if(this.type.equals("S")){
          System.out.println("Type: Sun ; Value: "+this.value);  
        }
        if(this.type.equals("G")){
          System.out.println("Type: Gold ; Value: "+this.value);  
        }
        if(this.type.equals("Gl")){
          System.out.println("Type: Glory ; Value: "+this.value);  
        }
        if(this.type.equals("M")){
          System.out.println("Type: Moon ; Value: "+this.value);  
        }
        if(this.type.equals("2G+1M")){
          System.out.println("Type: 2G+1M ; Value: "+this.value); 
        }
        if(this.type.equals("1Gl+1S")){
          System.out.println("Type: 1Gl+1S ; Value: "+this.value); 
        }
        if(this.type.equals("M/S/G")){
          System.out.println("Type: M/S/G ; Value: "+this.value); 
        }
        if(this.type.equals("3G/2Gl")){
          System.out.println("Type: 3G/2Gl ; Value: "+this.value); 
        }
        if(this.type.equals("ALL")){
          System.out.println("Type: ALL ; Value: "+this.value); 
        }
        if(this.type.equals("2G/2S/2M")){
          System.out.println("Type: 2G/2S/2M ; Value: "+this.value); 
        }
        if(this.type.equals("2Gl+2M")){
          System.out.println("Type: 2Gl+2M ; Value: "+this.value); 
        }
    }
    
}
