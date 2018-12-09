/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces.Garden;

import Card.ImmediateEffectCard.TheAbyssallMirror;
import Card.ImmediateEffectCard.TheInvisibilityHelmet;
import Faces.Garden.MultiplierFace;
import Faces.Garden.ShieldOfTheGuardianFace;
import Faces.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author KOFFI Merveille
 */
public class MirrorFaceTest {
    
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
          listFaces[0].add(new MirrorFace("MirrorFace",new TheAbyssallMirror()));
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
         
          assertTrue(listFaces[0].get(0).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==4);
          assertTrue(listFaces[0].get(1).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==8);
          assertTrue(listFaces[1].get(0).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);
          assertTrue(listFaces[1].get(1).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);  
   

                 
          assertTrue(data[0].getHerosInventory().getGoldPoints()==9);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==3);
          
          
          assertTrue(data[1].getHerosInventory().getGoldPoints()==8);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==1);
 
    }
    
    @Test
    public void testmakeEffectFaceMultiplier() {
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
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[0].add(new MirrorFace("MirrorFace",new TheAbyssallMirror()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
         
          assertTrue(listFaces[0].get(0).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==8);
          assertTrue(listFaces[0].get(1).makeEffect(0, 1, temple, 0, data[0], listFaces, data)==4);
          assertTrue(listFaces[1].get(0).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);
          assertTrue(listFaces[1].get(1).makeEffect(0, 1, temple, 1, data[1], listFaces, data)==4);  
               
          assertTrue(data[0].getHerosInventory().getGoldPoints()==9);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==3);
          
          
          assertTrue(data[1].getHerosInventory().getGoldPoints()==8);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==0);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==1);
 
    }
    
    
    @Test
    public void makeCardCyclopEffect() {
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
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[0].add(new MirrorFace("MirrorFace",new TheAbyssallMirror()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
         
          listFaces[0].get(0).makeCardCyclopEffect(temple, 0, data[0], listFaces, data);
          listFaces[0].get(1).makeCardCyclopEffect(temple, 0, data[0], listFaces, data);
          listFaces[1].get(0).makeCardCyclopEffect(temple, 1, data[1], listFaces, data);
          listFaces[1].get(1).makeCardCyclopEffect(temple, 1, data[1], listFaces, data);  
               
          assertTrue(data[0].getHerosInventory().getGoldPoints()==3);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==2);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==1);
          
          
          assertTrue(data[1].getHerosInventory().getGoldPoints()==2);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==6);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==1);
 
    }
    
    @Test
    public void makeCardSentinelEffect() {
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
          listFaces[0].add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
          listFaces[0].add(new MirrorFace("MirrorFace",new TheAbyssallMirror()));
          listFaces[1] = new ArrayList<GeneralFace>();
          listFaces[1].add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                ));
          listFaces[1].add(new SimpleFace(4, "G", "GoldenFace"));
          
         
          listFaces[0].get(0).makeCardSentinelEffect(temple, 0, data[0], listFaces, data);
          listFaces[0].get(1).makeCardSentinelEffect(temple, 0, data[0], listFaces, data);
          listFaces[1].get(0).makeCardSentinelEffect(temple, 1, data[1], listFaces, data);
          listFaces[1].get(1).makeCardSentinelEffect(temple, 1, data[1], listFaces, data);  
               
          assertTrue(data[0].getHerosInventory().getGoldPoints()==9);
          assertTrue(data[0].getHerosInventory().getSunPoints()==0);
          assertTrue(data[0].getHerosInventory().getGloryPoints()==6);
          assertTrue(data[0].getHerosInventory().getMoonPoints()==0);
          
          
          assertTrue(data[1].getHerosInventory().getGoldPoints()==8);
          assertTrue(data[1].getHerosInventory().getSunPoints()==0);
          assertTrue(data[1].getHerosInventory().getGloryPoints()==2);
          assertTrue(data[1].getHerosInventory().getMoonPoints()==0);
    }
}
