package diceforge;

import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Player.Bot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


public class Engine {
    private final int round;//nombres de tours ou de parties
    private final int set; //Number of handle in the game
    private final int numberOfBot; //Number of Bot playing

    /**
     * Contructeur du moteur de jeu
     *
     * @param round  : nombres de parties
     * @param set    : nombre de manches
     * @param number : nombres de bots
     */
    Engine(int round, int set, int number) {
        this.round = round;
        this.set = set;
        this.numberOfBot = number;
    }

    /**
     * Méthode d'initialisation des bots avec leurs 2 dés (clair et foncé) et un joueur actif
     *
     * @param data tableau de bots, contient le nombre de bots pour la partie
     */
    void InitializingBots(Bot... data) {  // prend en paramÃ¨tres une liste de robots
        int a = 0;
        int goldpoints = 3; // points d'or : 3 pr le premier (-1 par la suite)
        for (Bot bot : data) {
            bot.getFirstDice().makeBrightDefaultDice();
            bot.getSecondDice().makeDarkDefaultDice();
            bot.getHerosInventory().makeDefaultHerosInventory(goldpoints);
            if (a == 0) bot.setActive(true);  // choisit comme actif le premier joueur
            a = a + 1;
            goldpoints = goldpoints - 1;  // decremente au fur et Ã  mesure  
        }
    }

    /**
     * Méthode d'initialisation du temple avec un nombre de faces dépendant du nombre de joueurs
     *
     * @param temple : temple
     */
    void initializingTemple(Temple temple) {
        if (numberOfBot == 2) {
            for (int i = 0; i < 10; i++) {
                ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
                int size = sanctuary[i].size();
                Random randomBassin = new Random();
                int condition = size - 2;//Il faut retirer 2 faces
                while (size != condition) {
                    int result = randomBassin.nextInt(size);
                    sanctuary[i].remove(result);
                    size--;
                }
            }
        }
    }

    /**
     * Méthode de lancé de dé
     *
     * @param temple
     * @param actionNumber :
     * @param data         : nombres de bots du jeu
     */
    private void RollOneTime(Temple temple, int actionNumber, Bot... data) {

        ArrayList<GeneralFace>[] listFaces = new ArrayList[data.length];
        int a = 0; // Compteur pour le parcours des faces 

        for (Bot bot : data) {
            listFaces[a].add(bot.getFirstDice().rollDice());
            listFaces[a].add(bot.getSecondDice().rollDice());
            a = a + 1;
        }  // Après la boucle, les faces obtenues sont stockÃ©es respectivement dans la liste
        a = 0;
        int compteur = 0;
        
        /* le 1er attribut compteur permet de connaitre la position des des du joueur dans la liste
           de dés passés.
        */

        while (a < data.length) {
            System.out.println("-------->ROLL OF BOT " + (compteur + 1));
            listFaces[a].get(0).makeEffect(0, temple, compteur, data[compteur], listFaces);
            listFaces[a].get(1).makeEffect(0, temple, compteur, data[compteur], listFaces);
            data[compteur].getStrategy().apply(temple, compteur, actionNumber, listFaces, data);
            a = a + 1;
            compteur = compteur + 1;
        }

    }

    /**
     * Méthode permettant de faire des manches
     *
     * @param temple
     * @param data
     */
    void makeSets(Temple temple, Bot... data) {
        for (int a = 0; a < this.set; a++) {
            System.out.println("\n");
            System.out.println("-------------------------------------\n");
            RollOneTime(temple, a + 1, data);
            for (int i = 0; i < data.length; i++) {
                System.out.println("STATE AFTER " + (a + 1) + " SET");
                System.out.println("-->BOT " + i);
                System.out.println(data[i].toString());
                data[i].printDiceState();
            }
            System.out.println("\n");
            //Changement du joueur actif
            for (Bot bot : data) {
                bot.setActive(!bot.isActive());
            }
        }
    }

    /**
     * Méthode permettant de déterminer le gagnant d'une partie
     *
     * @param data
     */
    void tellMeTheWinnerOfRound(Bot... data) {
        int winnerIndex = 0, winnerWonSets = 0, equalityIndex = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i].wonRounds > winnerWonSets) {
                winnerIndex = i + 1;
                winnerWonSets = data[i].wonRounds;
            }
            if (data[i].wonRounds == winnerWonSets) {
                equalityIndex = i + 1;
            }
        }
        //Vérifications à la sortie de la boucle
        if (equalityIndex > winnerIndex) {
            if (equalityIndex - winnerIndex == 1) {
                System.out.println("We have 2 winners for the round, Bot " + winnerIndex + " and Bot " + equalityIndex + ".");
                data[winnerIndex].wonRounds++;
                data[equalityIndex].wonRounds++;
            }
            if (equalityIndex - winnerIndex == 2) {
                if (winnerWonSets == data[equalityIndex - 1].wonRounds) {
                    System.out.println("We have 3 winners for the round, Bot " + winnerIndex + ", Bot " + (equalityIndex - 1) + " and Bot " + equalityIndex + ".");
                    data[winnerIndex].wonRounds++;
                    data[equalityIndex - 1].wonRounds++;
                    data[equalityIndex].wonRounds++;
                } else {//l'intermédiaire est forcément inférieur ***code dupliqué mais nécessaire pour la compréhension***
                    System.out.println("We have 2 winners for the round, Bot " + winnerIndex + " and Bot " + equalityIndex + ".");
                    data[winnerIndex].wonRounds++;
                    data[equalityIndex].wonRounds++;
                }
            }
            if (equalityIndex - winnerIndex == 3) {//genre personne n'a gagné, 0 parties gagnées pour tout le monde
                System.out.println("It's a tie, No winner for the round!");
            }
        }
        if (winnerIndex > equalityIndex) {
            System.out.println("Congratulations Bot " + winnerIndex + " !");
        }
        /*if (botOne.getHerosInventory().getGloryPoints() > botTwo.getHerosInventory().getGloryPoints()) {
            botOne.roundsWin++;
            System.out.println("Bot 1 wins the round");
        }
        if (botOne.getHerosInventory().getGloryPoints() < botTwo.getHerosInventory().getGloryPoints()) {
            botTwo.roundsWin++;
            System.out.println("Bot 2 wins the round");
        }
        if (botOne.getHerosInventory().getGloryPoints() == botTwo.getHerosInventory().getGloryPoints()) {
            System.out.println("It's a tie");
        }*/
    }

    /**
     * Méthode permettant de déterminer le gagnant du jeu
     */
    void tellMeTheWinnerOfTheGame(Bot... data) {
        int winnerIndex = 0, winnerWonRounds = 0, equalityIndex = 0;
        System.out.println("**********Results*********");
        for (int i = 0; i < data.length; i++) {
            System.out.println("Bot " + (i + 1) + " : " + (((float) data[i].wonRounds / 1000) * 100) + "% of won rounds.");
            if (data[i].wonRounds > winnerWonRounds) {
                winnerIndex = i + 1;
                winnerWonRounds = data[i].wonRounds;
            }
            if (data[i].wonRounds == winnerWonRounds) {
                equalityIndex = i + 1;
            }
        }
        //Vérifications à la sortie de la boucle
        if (equalityIndex > winnerIndex) {
            if (equalityIndex - winnerIndex == 1) {
                System.out.println("We have 2 winners.");
                System.out.println("Equality between Bot " + winnerIndex + " and Bot " + equalityIndex + ". Congratulations!");
            }
            if (equalityIndex - winnerIndex == 2) {
                if (winnerWonRounds == data[equalityIndex - 1].wonRounds) {
                    System.out.println("We have 3 winners.");
                    System.out.println("Equality between Bot " + winnerIndex + ", Bot " + (equalityIndex - 1) + " and Bot " + equalityIndex + ". Congratulations!");
                } else {//l'intermédiaire est forcément inférieur
                    System.out.println("We have 2 winners.");
                    System.out.println("Equality between Bot " + winnerIndex + " and Bot " + equalityIndex + ". Congratulations");
                }
            }
            if (equalityIndex - winnerIndex == 3) {//genre personne n'a gagné, 0 parties gagnées pour tout le monde
                System.out.println("It's a tie, No winner!");
            }
        }
        if (winnerIndex > equalityIndex) {
            System.out.println("Congratulations Bot " + winnerIndex + " !");
        }
        /*if (botOne.roundsWin > botTwo.roundsWin) {
            System.out.println("Bot 1 wins the game with " + (((float) botOne.roundsWin / 1000) * 100) + "% of won rounds aigainst " + ((float) botTwo.roundsWin / 1000) * 100 + "% won rounds for the Bot 2. Congratulations Bot 1!");
        }
        if (botOne.roundsWin < botTwo.roundsWin) {
            System.out.println("Bot 2 wins the game with " + ((float) botTwo.roundsWin / 1000) * 100 + "% of won rounds aigainst " + ((float) botOne.roundsWin / 1000) * 100 + "% won rounds for the Bot 1. Congratulations Bot 2!");
        }
        if (botOne.roundsWin == botTwo.roundsWin) {
            System.out.println("No winner, It's a tie !");
        }*/
    }

    /**
     * Méthode permettant de faire des rounds
     *
     * @param data
     * @param temple
     */
    void makeRound(Temple temple, Bot... data) {
        for (int i = 0; i < this.round; i++) {
            System.out.println("#####\tROUND " + i + "\t#####");
            makeSets(temple, data);
            System.out.println("\n");
            System.out.println("DETERMINATING THE WINNER");
            tellMeTheWinnerOfRound(data);
        }
        tellMeTheWinnerOfTheGame(data);
    }

}
