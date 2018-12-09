package Card.ImmediateEffectCard;

import Card.Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;
/**
 * Cette carte "L'enigme" permet au joueur de relancer 04 fois l'un de ses 02 des
 * [NB : le de à lancer 4 fois est le même]
 * Elle procure 10 points de gloire à la fin de la partie
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
 */
public class TheRiddle extends Card {

    public TheRiddle() {
        this.name = "TheRiddle";
        this.gloryPoints = 10;
        this.type = "S";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        this.price = 6;
        this.portal = 4;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        /* Relancer l'un de ses 02 dés 4 fois et MAJ de l'inventaire */

        int a = bot.getStrategy().throwWhichDice();  // choix du dé à lancer
        for (int b = 0; b < 4; b++) {
            GeneralFace myFace = bot.rollOneDice(a);     // face obtenue
            int val = myFace.makeEffect(0, 0, temple, numBot, bot, listFaces, tabBot);
            if(bot.getHerosInventory().tokenCerberus>0){
                
                int rep = bot.getStrategy().useTokenCerberus(val); 
                if(rep==1){
                    myFace.makeEffect(0, 0, temple, numBot, bot, listFaces, tabBot);
                }
            }
        }
    }

}
