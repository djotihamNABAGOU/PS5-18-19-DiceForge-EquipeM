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
/**
 *
 * @author KOFFI Merveille
 */
public class SanctuarysFacesTest {
    
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
          listFaces[0].add(new SanctuarysFaces(2, "GoldenFace", "Add", new SimpleFace(3, "G", "GoldenFace")));
          listFaces[0].add(new SanctuarysFaces(5, "GoldenGloryFace", "Choice", new SimpleFace(3, "G", "GoldenFace"), new SimpleFace(2, "Gl", "GloryFace")));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          
         
          assertTrue(listFaces[0].get(0).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==3);
          assertTrue(listFaces[0].get(1).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==3);
          assertTrue(listFaces[1].get(0).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);
          assertTrue(listFaces[1].get(1).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==8);  
   

          //Le bot 1 aura donc après ce tour 0 pt de gloire , 0 Sun , 9 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==9);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==0);
          
          //Le bot 2 aura donc après ce tour 0 pt de gloire , 3 Sun , 8 Or , 0 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==8);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==3);
 
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
          listFaces[0].add(new SanctuarysFaces(2, "GoldenFace", "Add", new SimpleFace(3, "G", "GoldenFace")));
          listFaces[0].add(new SanctuarysFaces(5, "GoldenGloryFace", "Choice", new SimpleFace(3, "G", "GoldenFace"), new SimpleFace(2, "Gl", "GloryFace")));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          
         
          listFaces[0].get(0).makeCardCyclopEffect(temple, 0, data[0], listFaces, data);
          listFaces[0].get(1).makeCardCyclopEffect(temple, 0, data[0], listFaces, data);
          listFaces[1].get(0).makeCardCyclopEffect(temple, 1, data[1], listFaces, data);
          listFaces[1].get(1).makeCardCyclopEffect(temple, 1, data[1], listFaces, data); 
   

          //Le bot 1 aura donc après ce tour 5 pt de gloire , 0 Sun , 3 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==6);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==0);
          
          //Le bot 2 aura donc après ce tour 2 pt de gloire , 0 Sun , 2 Or , 1 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==2);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==2);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==1);
 
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
          listFaces[0].add(new SanctuarysFaces(2, "GoldenFace", "Add", new SimpleFace(3, "G", "GoldenFace")));
          listFaces[0].add(new SanctuarysFaces(5, "GoldenGloryFace", "Choice", new SimpleFace(3, "G", "GoldenFace"), new SimpleFace(2, "Gl", "GloryFace")));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          
          listFaces[0].get(0).makeCardSentinelEffect(temple, 0, data[0], listFaces, data);
          listFaces[0].get(1).makeCardSentinelEffect(temple, 0, data[0], listFaces, data);
          listFaces[1].get(0).makeCardSentinelEffect(temple, 1, data[1], listFaces, data);
          listFaces[1].get(1).makeCardSentinelEffect(temple, 1, data[1], listFaces, data); 
   

          //Le bot 1 aura donc après ce tour 0 pt de gloire , 0 Sun , 9 Or , 0 Moon        
          assertTrue(data[0].getHerosInventory().getGoldPoints()==9);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==0);
          
          //Le bot 2 aura donc après ce tour 6 pt de gloire , 0 Sun , 8 Or , 0 Moon
          assertTrue(data[1].getHerosInventory().getGoldPoints()==8);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==6);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
 
    }
    
    
    
    @Test
    public void giveMeShieldGain(){
          
          int[] rep = new int[4];
          int[] repTwo = new int[4];
          int[] repThree = new int[4];
          
          //Juste pr le test
          Bot bot = new Bot("Nothing");
          ArrayList<GeneralFace>[] listFaces = new ArrayList[2];
          /**/
          
          ArrayList<ShieldOfTheGuardianFace> bassinShield = new ArrayList<>();
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(3, "G", "GoldenFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "S", "SunFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "M", "MoonFace")));
          bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "Gl", "GloryFace")));
          
          
          SanctuarysFaces One  = new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                );
          
          SanctuarysFaces two = new SanctuarysFaces(4, "MoonSunGoldenFace",
                        "Choice",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace")
                );
          
          SanctuarysFaces three = new SanctuarysFaces(12, "MoonSunGoldenGloryFace",
                        "Add",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace"),
                        new SimpleFace(1, "Gl", "GloryFace")
                );
          
          for(int a=0;a<4;a++){
              rep[a] = One.giveMeShieldGain(0,bot,1,bassinShield.get(a),listFaces);
              repTwo[a] = two.giveMeShieldGain(0,bot,1,bassinShield.get(a),listFaces);
              repThree[a] = three.giveMeShieldGain(0,bot,1,bassinShield.get(a),listFaces);
              
          }
          
          assertTrue(rep[0]==0);
          assertTrue(rep[1]==1);
          assertTrue(rep[2]==0); 
          assertTrue(rep[3]==1);
          
          assertTrue(repTwo[0]==1);
          assertTrue(repTwo[1]==1);
          assertTrue(repTwo[2]==0); 
          assertTrue(repTwo[3]==1);
          
          assertTrue(repThree[0]==0);
          assertTrue(repThree[1]==0);
          assertTrue(repThree[2]==0); 
          assertTrue(repThree[3]==0);
    }
    
    
    @Test
    public void getProperties(){
                
        
        SanctuarysFaces One  = new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                );
          
          SanctuarysFaces two = new SanctuarysFaces(4, "MoonSunGoldenFace",
                        "Choice",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace")
                );
          
          SanctuarysFaces three = new SanctuarysFaces(12, "MoonSunGoldenGloryFace",
                        "Add",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace"),
                        new SimpleFace(1, "Gl", "GloryFace")
                );
          
          ArrayList<String> oneFace = new ArrayList<>();
          ArrayList<String> twoFace = new ArrayList<>();
          ArrayList<String> threeFace = new ArrayList<>();
          oneFace = One.getProperties();
          twoFace = two.getProperties();
          threeFace = three.getProperties();
          
          // Face One
          
          assertTrue(oneFace.get(0).equals("2"));
          assertTrue(oneFace.get(1).equals("0"));
          assertTrue(oneFace.get(2).equals("1"));
          assertTrue(oneFace.get(3).equals("0"));
          assertTrue(oneFace.get(4).equals("Add"));
          
          
          // Face Two
          
          assertTrue(twoFace.get(0).equals("1"));
          assertTrue(twoFace.get(1).equals("1"));
          assertTrue(twoFace.get(2).equals("1"));
          assertTrue(twoFace.get(3).equals("0"));
          assertTrue(twoFace.get(4).equals("Choice"));
          
          
          // Face Three
          
          assertTrue(threeFace.get(0).equals("1"));
          assertTrue(threeFace.get(1).equals("1"));
          assertTrue(threeFace.get(2).equals("1"));
          assertTrue(threeFace.get(3).equals("1"));
          assertTrue(threeFace.get(4).equals("Add"));
          
          
          
         
    }
    
}
