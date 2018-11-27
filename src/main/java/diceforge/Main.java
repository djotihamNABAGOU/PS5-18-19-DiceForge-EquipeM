
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diceforge;

import Faces.SanctuarysFaces;
import Player.Bot;
import PlayerStrategy.RandomStrategy;
import PlayerStrategy.Strategy;

import java.util.ArrayList;

/**
 *
 * @author The Beginners
 */
public class Main {
    public static void main(String [] args){

        System.out.println("********WELCOME TO DICE FORGE********\n");
        Engine engine = new Engine(9,2);
        Temple temple = new Temple();
        Bot botOneRandom = new Bot("Random");
        Bot botTwoRandom = new Bot("Random");
        //Bot botTwoRandom = new Bot("Nothing");
        
        //Test permettant de savoir si le nombres de cartes dans les bassins passe éffectivement à 2 pour 2 joueurs
        engine.InitializingBots(botOneRandom, botTwoRandom);
        engine.initializingTemple(temple);
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int i=0; i<10;i++){
            for (int a = 0; a < sanctuary[a].size(); a++) {
                System.out.println(sanctuary[i].get(a).toString());
            }
        }
        /*
        System.out.println("STATE BEFORE DICE SET");
        System.out.println("-->BOT ONE");
        System.out.println(botOneRandom.toString());
        botOneRandom.printDiceState();
        System.out.println("-->BOT TWO");
        System.out.println(botTwoRandom.toString());
        botTwoRandom.printDiceState();
        
        System.out.println("-------------------------------------\n");

        engine.MakeNineSetWithTwoBot(botOneRandom, botTwoRandom, temple);
        //engine.MakeOneSetWithTwoBot(botOneRandom,botTwoRandom,temple,1);
        System.out.println("\n");
        System.out.println("DETERMINATING THE WINNER");
        engine.TellMeTheWinner(botOneRandom, botTwoRandom);
        */
        
    }
    
}
