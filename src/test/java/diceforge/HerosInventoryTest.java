package diceforge;

import org.junit.Test;

import static org.junit.Assert.*;

public class HerosInventoryTest {

    @Test
    public void increaseGloryPoints() {
        HerosInventory herosInventory = new HerosInventory();
        //test with 0
        int initialGlory = herosInventory.getGloryPoints();
        herosInventory.IncreaseGloryPoints(0);
        int finalGlory = herosInventory.getGloryPoints();
        assertEquals(initialGlory,finalGlory);
        //test with 2
        initialGlory = herosInventory.getGloryPoints();
        herosInventory.IncreaseGloryPoints(2);
        finalGlory = herosInventory.getGloryPoints();
        assertEquals(initialGlory+2,finalGlory);
    }

    @Test
    public void increaseMoonPoints() {
        HerosInventory herosInventory = new HerosInventory();
        //test with 0
        int initialMoon = herosInventory.getMoonPoints();
        herosInventory.IncreaseMoonPoints(0);
        int finalMoon = herosInventory.getMoonPoints();
        assertEquals(initialMoon,finalMoon);
        //test with 2
        initialMoon = herosInventory.getMoonPoints();
        herosInventory.IncreaseMoonPoints(2);
        finalMoon = herosInventory.getMoonPoints();
        assertEquals(initialMoon+2,finalMoon);
    }

    @Test
    public void increaseGoldPoints() {
        HerosInventory herosInventory = new HerosInventory();
        //test with 0
        int initialGold = herosInventory.getGoldPoints();
        herosInventory.IncreaseGoldPoints(0);
        int finalGold = herosInventory.getGoldPoints();
        assertEquals(initialGold,finalGold);
        //test with 2
        initialGold = herosInventory.getGoldPoints();
        herosInventory.IncreaseGoldPoints(2);
        finalGold = herosInventory.getGoldPoints();
        assertEquals(initialGold+2,finalGold);
    }

    @Test
    public void decreaseGoldPoints() {
        HerosInventory herosInventory = new HerosInventory();
        herosInventory.IncreaseGoldPoints(5);
        //test with 0
        int initialGold = herosInventory.getGoldPoints();
        herosInventory.DecreaseGoldPoints(0);
        int finalGold = herosInventory.getGoldPoints();
        assertEquals(initialGold,finalGold);
        //test with 2
        initialGold = herosInventory.getGoldPoints();
        herosInventory.DecreaseGoldPoints(2);
        finalGold = herosInventory.getGoldPoints();
        assertEquals(initialGold-2,finalGold);

    }

    @Test
    public void increaseSunPoints() {
        HerosInventory herosInventory = new HerosInventory();
        //test with 0
        int initialSun = herosInventory.getSunPoints();
        herosInventory.IncreaseSunPoints(0);
        int finalSun = herosInventory.getSunPoints();
        assertEquals(initialSun,finalSun);
        //test with 2
        initialSun = herosInventory.getSunPoints();
        herosInventory.IncreaseSunPoints(2);
        finalSun = herosInventory.getSunPoints();
        assertEquals(initialSun+2,finalSun);

    }

    @Test
    public void makeFirstDefaultHerosInventory() {
        HerosInventory herosInventory = new HerosInventory();
        herosInventory.makeFirstDefaultHerosInventory();
        assertTrue(herosInventory.getGloryPoints()==0 && herosInventory.getMoonPoints()==0 && herosInventory.getSunPoints()==0 && herosInventory.getGoldPoints()==3);
    }

    @Test
    public void makeSecondDefaultHerosInventory() {
        HerosInventory herosInventory = new HerosInventory();
        herosInventory.makeSecondDefaultHerosInventory();
        assertTrue(herosInventory.getGloryPoints()==0 && herosInventory.getMoonPoints()==0 && herosInventory.getSunPoints()==0 && herosInventory.getGoldPoints()==2);
    }

    @Test
    public void makeThirdDefaultHerosInventory() {
        HerosInventory herosInventory = new HerosInventory();
        herosInventory.makeThirdDefaultHerosInventory();
        assertTrue(herosInventory.getGloryPoints()==0 && herosInventory.getMoonPoints()==0 && herosInventory.getSunPoints()==0 && herosInventory.getGoldPoints()==1);
    }

    @Test
    public void makeFourthDefaultHerosInventory() {
        HerosInventory herosInventory = new HerosInventory();
        herosInventory.makeFourthDefaultHerosInventory();
        assertTrue(herosInventory.getGloryPoints()==0 && herosInventory.getMoonPoints()==0 && herosInventory.getSunPoints()==0 && herosInventory.getGoldPoints()==0);
    }
}