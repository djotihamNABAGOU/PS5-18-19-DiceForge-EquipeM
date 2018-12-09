/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces.Sanctuary;


import Card.ImmediateEffectCard.TheInvisibilityHelmet;
import Faces.Garden.MultiplierFace;
import Faces.Garden.ShieldOfTheGuardianFace;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

public class SimpleFaceTest {
    

    /**
     * Test of makeEffect method, of class SimpleFace.
     */
    @Test
    public void testMakeEffect() {
          Temple temple = new Temple();
          Bot[] data = new Bot[2];
          data[0] = new Bot("Nothing");
          data[1] = new Bot("Nothing"); 
          
          int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
          for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            goldpoints = goldpoints - 1;
          }
          ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
          
          listFaces[0] = new ArrayList<GeneralFace>();
          listFaces[0].add(new SimpleFace(3, "Gl", "GloryFace"));
          listFaces[0].add(new SimpleFace(1, "S", "SunFace"));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
         
          assertTrue(listFaces[0].get(0).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==12);
          assertTrue(listFaces[0].get(1).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==2);
          assertTrue(listFaces[1].get(0).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);
          assertTrue(listFaces[1].get(1).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);  
   

          //Le bot 1 aura donc après ce tour 3 pt de gloire , 1 Sun , 3 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==1);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==3);
          assertFalse(data[0].getHerosInventory().getMoonPoints()==3);
          
          //Le bot 2 aura donc après ce tour 0 pt de gloire , 0 Sun , 10 Or , 0 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==10);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
 
    }
    
    @Test
    public void makeEffectFaceMultiplier(){
          Temple temple = new Temple();
          Bot[] data = new Bot[2];
          data[0] = new Bot("Nothing");
          data[1] = new Bot("Nothing"); 
          
          int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
          for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            goldpoints = goldpoints - 1;
          }
          ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
          
          listFaces[0] = new ArrayList<GeneralFace>();
          listFaces[0].add(new SimpleFace(3, "Gl", "GloryFace"));
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
          assertTrue(listFaces[0].get(0).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==12);
          assertTrue(listFaces[0].get(1).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==24);
          assertTrue(listFaces[1].get(0).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);
          assertTrue(listFaces[1].get(1).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);

          //Le bot 1 aura donc après ce tour 9 pt de gloire , 0 Sun , 3 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==9);
          assertFalse(data[0].getHerosInventory().getMoonPoints()==1);
          
          //Le bot 2 aura donc après ce tour 0 pt de gloire , 0 Sun , 10 Or , 0 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==10);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
    }

    @Test
    public void makeCardCyclopEffect(){
        Temple temple = new Temple();
          Bot[] data = new Bot[2];
          data[0] = new Bot("Nothing");
          data[1] = new Bot("Nothing"); 
          
          int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
          for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            goldpoints = goldpoints - 1;
          }
          ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
          
          listFaces[0] = new ArrayList<GeneralFace>();
          listFaces[0].add(new SimpleFace(3, "Gl", "GloryFace"));
          listFaces[0].add(new SimpleFace(1, "S", "SunFace"));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
          int a = 0;
          while (a < data.length) {
             listFaces[a].get(0).makeCardCyclopEffect(temple, a, data[a], listFaces, data);
             listFaces[a].get(1).makeEffect(0, 1, temple, a, data[a], listFaces, data);
             a = a + 1;
         }

          //Le bot 1 aura donc après ce tour 3 pt de gloire , 1 Sun , 3 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==1);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==3);
          assertFalse(data[0].getHerosInventory().getMoonPoints()==3);
          
          /* Le bot 2 aura donc après ce tour :
                - 6 pt de gloire ( 4 + 2) , 0 Moon , 0 Sun , 4 Gold
          */
          assertTrue(data[1].getHerosInventory().getGoldPoints()==6);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==4);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
    }
    
    
    
    @Test
    public void makeCardSentinelEffect(){
          Temple temple = new Temple();
          Bot[] data = new Bot[2];
          data[0] = new Bot("Nothing");
          data[1] = new Bot("Nothing"); 
          
          int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
          for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            goldpoints = goldpoints - 1;
          }
          ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
          
          listFaces[0] = new ArrayList<GeneralFace>();
          listFaces[0].add(new SimpleFace(2, "M", "MoonFace"));
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SimpleFace(2, "S", "SunFace"));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
          int a = 0;
          while (a < data.length) {
             listFaces[a].get(0).makeCardSentinelEffect(temple, a, data[a], listFaces, data);
             listFaces[a].get(1).makeCardSentinelEffect(temple, a, data[a], listFaces, data);
             a = a + 1;
         }

          //Le bot 1 aura donc après ce tour 12 pt de gloire , 0 Sun , 3 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==12);
          assertFalse(data[0].getHerosInventory().getMoonPoints()==1);
          
          //Le bot 2 aura donc après ce tour 4 pt de gloire , 0 Sun , 6 Or , 0 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==6);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==4);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
    }
    
    @Test
    public void giveMeShieldGain(){
          
          int[] rep = new int[4];
          
          //Juste pr le test
          Bot bot = new Bot("Nothing");
          ArrayList<GeneralFace>[] listFaces = new ArrayList[2];
          /**/
          
          ArrayList<ShieldOfTheGuardianFace> bassinShield = new ArrayList<>();
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(3, "G", "GoldenFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "S", "SunFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "M", "MoonFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "Gl", "GloryFace")));
          
          
          SimpleFace One  = new SimpleFace(2, "M", "MoonFace");
          
          for(int a=0;a<4;a++){
              rep[a] = One.giveMeShieldGain(0,bot,1,bassinShield.get(a),listFaces);
          }
          
          assertTrue(rep[0]==1);
          assertTrue(rep[1]==1);
          assertTrue(rep[2]==0); 
          assertTrue(rep[3]==1);
    }
    
}
