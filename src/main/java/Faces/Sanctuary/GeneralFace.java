/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faces.Sanctuary;

import Card.ImmediateEffectCard.TheHammer;
import Faces.Garden.ShieldOfTheGuardianFace;
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
    
    /* Le commentaire Suivant concerne les paramètres et vaut pour toutes les methodes ci dessous : 
    
       action ---> sert pr savoir s"il faut incrementer ou decrementer les points obtenues,
                       Utilisé surtt pour la carte minotaure et pour eviter une duplication de code
                       0 --> incremente ; 1 --> decremente
       favMin ---> indique si la face en présence est obtenue lors d'une "faveur Mineure" , 0 Oui / 1 Non
       temple ---> Temple du jeu
       numBot ---> Numero du joueur actuel qui possède la face dont l'effet s'active
       Bot    ---> Le joueur Actuel qui possède la face dont l'effet s'active
       data   ---> Tableau de ArrayList qui contient les faces obtenues par tous les joueurs lors d'un 
                   lancé de dés , Exemple : 
                   data[0].get(0) ---> face obtenue par le lancé du premier dé du premier joueur
                   data[0].get(1) ---> face obtenue par le lancé du deuxièmz dé du premier joueur 
       listBot ---> Liste qui contient tous les joueurs de la partie
       a       ---> Nombres de fois qu'il faut effectuer l'operation { face Multilplier }
    
    
       L'entier renvoyé par les 2 méthodes sert à savoir s"il faut utiliser le jeton cerbers si 
       le joueur en possède [voir l'utilisation du jeton cerbers dans le DOC]
       // pr calcul de l'entier , si la face fait gagner : 
          1gold      +1 
          1sun       +2
          1moon      +2
          1glory     +4
          1face forge  +8
          Ex : Si un joueur obtient la face 6Or : entier renvoyé = 6
       
    
    */
    
    public int makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
        return 0;
    }
    
    //Methode qui est déclenchée si on obtient  une face Multiplier dans son lancer
    public int makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
        return 0;
    }
    

    //Methode appelée par l'utilisation d'une carte Cyclope
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                                                        Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
  
    //Methode appelée par l'utilisation d'une carte Sentinel
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
    
    //Methode appelée par l'utilisation d'une carte Sentinel
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        System.out.println("I am General face, please implement effect in subclasses");
    }
       
    //Donner le type de ressource que procure la face en présence de la face "AILE DE LA GARDIENNE
    //0  ----> pour le GAIN A
    //1  ----> pour le GAIN B
    public int giveMeShieldGain(int action,Bot bot,int numBot,ShieldOfTheGuardianFace face,ArrayList<GeneralFace>[] data,Bot... listBot){
        return 0;
    }

    //initialise la face en question
    public void initialize() {
        
    }
    
    /*
       Retourne les propriétés de la face sous la forme d'une arraylist dans cet ordre :
       indice 0  ---> valeur d'or de la face  (0 S"il elle n"en procure pas)
       indice 1  ---> valeur de Sun    (0 S"il elle n"en procure pas)
       indice 2  ---> valeur de Moon   (0 S"il elle n"en procure pas)
       indice 3  ---> valeur de glory  (0 S"il elle n"en procure pas)
       indice 4  ---> "choice" ou "add" selon la carte
    
       Les faces spéciales eux retourneront une arraylist de taille == 1 qui contiendra le nom de la face
       pr reconnaitre une face S --> verifier donc si la taille == 1
    */
    public ArrayList<String> getProperties() {
        return new ArrayList<String>();
    }
}
