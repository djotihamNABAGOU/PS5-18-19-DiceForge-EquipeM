package Card.AutomaticEffectCard;

import Card.Reinforcement;
import Faces.Garden.GardenFace;
import Faces.GeneralFace;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;

/**
 * Cette carte "Le sanglier acharné " permet d'avoir une face "Sanglier acharné"
 * Type Immediat ----> Methode "ActionCard" appelé à l'achat
 * Type automatice ---> Voir DOC Methode "Capacity"
 * Elle procure 4 points de gloire à la fin de la partie
 */

public class TheWildBoar extends Reinforcement {

    public TheWildBoar(String Type) {   // prend aussi le type (y'en a 4)
        this.name = "TheWildBoar" + Type;
        this.gloryPoints = 4;
        this.type = "M";
        this.TypeCard = "A";
        this.price = 3;
        this.portal = 2;
    }

    @Override
    public void actionCard(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        bot.getHerosInventory().IncreaseGloryPoints(gloryPoints);
        GardenFace myface = temple.takeGardenFace(this); //Recuperer la face correspondante du temple
        GeneralFace face = myface;   // La mettre dans generalFace

        // Methode d'appel de la forge
        bot.getStrategy().ForgeDice(face);
    }

    @Override
    public void capacity(Temple temple, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {
        ArrayList<SimpleFace> Offered = new ArrayList<>();
        Offered.add(new SimpleFace(1, "S", "SunFace"));
        Offered.add(new SimpleFace(1, "M", "MoonFace"));
        Offered.add(new SimpleFace(3, "Gl", "GloryFace"));
        /*
        Bot[] tabBot = new Bot[listBot.size()];
        for (int b = 0; b < listBot.size(); b++) {
            tabBot[b] = listBot.get(b);
        }
        */

        int choice = bot.getStrategy().giveMeYourChoice(Offered,1);
        Offered.get(choice).makeEffect(0, 1, temple, numBot, bot, listFaces, tabBot);
    }


}


