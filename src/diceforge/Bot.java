

package diceforge;

import java.util.Random;

public class Bot {
    final HerosInventory herosInventory;
    final Dice firstDice;
    final Dice secondDice;
    int age;

    public Bot() {
        firstDice = new Dice();
        secondDice = new Dice();
        herosInventory = new HerosInventory();
        age = 0;
   
    }
    
    public void printBotInventoryState(){
        System.out.println("Glory: "+this.herosInventory.getGloryPoints());
        System.out.println("Gold: "+this.herosInventory.getGoldPoints());
        System.out.println("Moon: "+this.herosInventory.getMoonPoints());
        System.out.println("Sun: "+this.herosInventory.getSunPoints());
    }  
    
}
