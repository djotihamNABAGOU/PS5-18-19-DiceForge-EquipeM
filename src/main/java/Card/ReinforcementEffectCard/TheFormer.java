package Card.ReinforcementEffectCard;

import Card.Reinforcement;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "L'ancien" permet au joueur dechanger 3 pieces d'or contre 4 point de gloire
 * comme action supplementaire à chaque tour du joueur actif qui l'a en sa posssession
 * Type Renfort  ----> Methode "ActionCard" appelé à l'achat de la carte
 * ----> Carte conservé dans la liste des cartes de renforcement du joueur
 * ----> Methode "Capacity" appelé à chaque tour actif du joueur en possesion
 */

public class TheFormer extends Reinforcement {

    public TheFormer() {
        this.name = "TheFormer";
        this.gloryPoints = 0;
        this.type = "S";
        this.TypeCard = "R";
        this.price = 1;
        this.portal = 7;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, ArrayList<Bot> listBot) {
        //Ne donne pas de points de gloire donc methode vide 
    }

    @Override
    public void capacity(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        int choice = bot.getStrategy().applyFormerEffect(); // 0 si oui , 1 sinon
        if (choice == 0) {
            bot.getHerosInventory().IncreaseGloryPoints(4);
            bot.getHerosInventory().DecreaseGoldPoints(3);
        }
    }
}