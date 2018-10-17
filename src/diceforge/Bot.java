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
public class Bot {
    final HerosInventory herosInventory;
    final Dice firstDice;

    public Bot() {
        firstDice = new Dice();
        herosInventory = new HerosInventory();
    }
    
    public void printBotInventoryState(){
        System.out.println("Glory: "+this.herosInventory.getGloryPoints());
        System.out.println("Gold: "+this.herosInventory.getGoldPoints());
        System.out.println("Moon: "+this.herosInventory.getMoonPoints());
        System.out.println("Sun: "+this.herosInventory.getSunPoints());
    }
    
    
    
    
    
}
