/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Card.Card;
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
    public void makeEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am Garden face, please implement effect in subclasses");
    }
    
    @Override
    public void makeEffectFaceMultiplier(Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        System.out.println("I am Garden face, please implement effect in subclasses");   
    }
}
