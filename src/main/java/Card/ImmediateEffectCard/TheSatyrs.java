package Card.ImmediateEffectCard;

import Card.Card;
import Faces.Sanctuary.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Les satyres" permet au joueur qui l'achete de choisir 2 faces parmi les faces de des des
 * autres joueurs après que ceux ci aient relancer tous leurs deux dés
 * Elle procure 6 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */

public class TheSatyrs extends Card {

    public TheSatyrs() {
        this.name = "TheSatyrs";
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";
        this.price = 3;
        this.portal = 2;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        bot.getHerosInventory().IncreaseGloryPoints(6);

        //Lancer des dés par les autres joueurs et remplacements des faces dans la liste
        for (int a = 0; a < listBot.size(); a++) {
            if (a != numBot) {  // le joueur lui ne relance plus
                GeneralFace one = listBot.get(a).getFirstDice().rollDice();
                GeneralFace two = listBot.get(a).getSecondDice().rollDice();
                listFaces[a].set(0, one); // changement par la nouvelle face
                listFaces[a].set(1, two); // changement par la nouvelle face
            }
        }

        // Ranger les faces dans une liste pour le choix du joueur 
        ArrayList<GeneralFace> list = new ArrayList<>();
        for (int b = 0; b < listFaces.length; b++) {
            for (GeneralFace face : listFaces[b]) {
                if (b != numBot) {
                    list.add(face);
                }
            }
        }

        GeneralFace one = list.get(bot.getStrategy().giveMeYourGChoice(list));  // 1er choix
        list.remove(one); // Supprimer la face choisie de la liste avt le second choix
        GeneralFace two = list.get(bot.getStrategy().giveMeYourGChoice(list));  // 2em choix

        //Ranger les nouvelles faces du joueur
        listFaces[numBot].set(0, one); // changement par la nouvelle face
        listFaces[numBot].set(1, two); // changement par la nouvelle face

        Bot[] tabBot = new Bot[listBot.size()];
        for (int b = 0; b < listBot.size(); b++) {
            tabBot[b] = listBot.get(b);
        }

        //Appliquer enfin l'effet des faces


        listFaces[numBot].get(0).makeEffect(0, 1, temple, numBot, bot, listFaces, tabBot);
        listFaces[numBot].get(1).makeEffect(0, 1, temple, numBot, bot, listFaces, tabBot);

    }
} 
