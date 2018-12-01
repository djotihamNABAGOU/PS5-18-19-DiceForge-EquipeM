/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces;

import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

/**
 *
 * @author The Beginners
 */
public class GeneralFace {
    
    protected String name;

    public GeneralFace() {
        this.name = "";
    }

    public GeneralFace(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        GeneralFace other = (GeneralFace) obj;
        return name.equals(other.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    // int action ---> sert pr savoir s"il faut incrementer ou decrementer 
    // Utilisé pour la carte minotaure et pour eviter une duplication de code
    // 0 --> incremente ; 1 --> decremente
    public void makeEffect(int action,Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    //Methode qui est déclenchée si on obtient  une face Multiplier dans son lancer
    public void makeEffectFaceMultiplier(int action,Temple temple,int numBot,Bot bot,int a,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    



    
    //Methode appelée par l'utilisation d'une carte Cyclope
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    
    
    
    
    
    //Methode appelée par l'utilisation d'une carte Sentinel
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    //Methode appelée par l'utilisation d'une carte Sentinel
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,int a,Bot bot,ArrayList<GeneralFace>... data){
        System.out.println("I am General face, please implement effect in subclasses");
    }
       
    
}
