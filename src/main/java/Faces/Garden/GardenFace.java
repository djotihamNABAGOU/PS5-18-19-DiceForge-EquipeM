/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces.Garden;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class GardenFace extends GeneralFace{
    
    private Card card;  // Carte qui permet d'avoir la face
    private boolean selected;
    
    public GardenFace()
    {
   
    }
    
    public GardenFace(String name,Card card)
    {
        super(name);
        this.card = card;
        this.selected = false;
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    public Card getCard()
    {
        return card;
    }
    
    @Override
     public int makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
        return 0;
    }
    
    @Override
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am Garden face, please implement effect in subclasses");   
        return 0;
    }
    
    
    
    //Methode appelée par l'utilisation d'une carte Cyclope
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    
    
    
    
    //Methode appelée par l'utilisation d'une carte Sentinel
    
   
    
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    
    @Override
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        return 0;
    }
 
    @Override
    public void initialize() {}
    
    @Override
    public ArrayList<String> getProperties() {
        ArrayList<String> myList = new ArrayList<>();
        myList.add(name);
        return myList;
    }

    @Override
    public String toString() {
        return getName();
    }
}
