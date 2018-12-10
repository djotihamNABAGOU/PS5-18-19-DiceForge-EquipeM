package PlayerStrategy;

import Faces.GeneralFace;
import Card.WithoutEffectCard.ThePasser;
import Card.WithoutEffectCard.TheHydra;
import Card.WithoutEffectCard.TheJellyFish;
import Faces.Sanctuary.*;
import Player.*;
import diceforge.Temple;
import diceforge.*;


import java.util.ArrayList;
import java.util.Random;

public class ImediaCardStrategy extends Strategy {

    private int sunpoints = bot.getHerosInventory().getSunPoints();
    private int moonpoints = bot.getHerosInventory().getMoonPoints();
    private TheHydra hydra = new TheHydra();
    private ThePasser passer = new ThePasser();
    private TheJellyFish jellyFish = new TheJellyFish();


    public ImediaCardStrategy(Bot bot) {
        super(bot);
    }

    public void apply(Temple temple, Island island, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {


        //Seul le joueur actif peut appliquer une stratégie après le lancé des dés
        if (bot.isActive()) {
            //1->Choix d'appel des renforts, LE JOUEUR ACTIF PEUT APPELER DES RENFORTS
            //il ne possède pas de renforts

            Random random = new Random();

            //2->choix de faire une action, LE JOUEUR ACTIF PEUT EFFECTUER UNE ACTION

                //Choix de l'action à effectuer (forge ou exploit)
                int choice1 = random.nextInt(2); // 0 pour forge et 1 pour exploit
                switch (choice1){
                    case 0:
                        SanctuarysFaces face = GloryMoonSunFaceToBuy(bot, temple);
                        if (face.getPrice() != 0) {
                            if (temple.buyFace(face)) {
                                ForgeDice(face);
                                bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                            }
                        }
                    case 1:
                        switch (sunpoints){
                            case 5:
                                if(moonpoints==5) {island.buyCard(hydra,temple,numberOfTheBot,listFaces); hydra.actionCard(temple,bot,numberOfTheBot,listFaces,data);}
                                island.buyCard(jellyFish,temple,numberOfTheBot,listFaces);jellyFish.actionCard(temple,this.bot,numberOfTheBot,listFaces,data);break;
                            case 4:
                                island.buyCard(jellyFish,temple,numberOfTheBot,listFaces);jellyFish.actionCard(temple,this.bot,numberOfTheBot,listFaces,data);break;
                        }
                        switch (moonpoints){
                            case 5:
                            {island.buyCard(hydra,temple,numberOfTheBot,listFaces); hydra.actionCard(temple,this.bot,numberOfTheBot,listFaces,data);}
                            case 4:
                                island.buyCard(passer,temple,numberOfTheBot,listFaces);passer.actionCard(temple,this.bot,numberOfTheBot,listFaces,data);break;
                        }
                }
            }
        }




    private void ForgeDice(SanctuarysFaces face){
        Random randomFace = new Random();
        Random randomDice = new Random();
        int numberOfDice = randomDice.nextInt(2) + 1;//Random pour prendre le dé sur lequel il faut forger
        int numberOfFace ;
        if (numberOfDice == 1) {
             numberOfFace = randomFace.nextInt(5); //Random pour prendre la face a enlever
        } else {
             numberOfFace = randomFace.nextInt(4);}

        this.bot.getRemovedFaces().add(this.bot.getFirstDice().getFaces()[numberOfFace]); //Ajout dans la liste des faces enlevées
        this.bot.getFirstDice().setFaces(face, numberOfFace);
        }



    private SanctuarysFaces GloryMoonSunFaceToBuy(Bot bot, Temple temple) {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
            switch (a) {
                case 1: case 3: case 6: case 7: case 8:
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        FacesAvailable.add(sanctuary[a].get(i));
                    }
                }
            }
        }
        Random randomFace = new Random();

        if (FacesAvailable.size() == 0) return new SanctuarysFaces();
        else {
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //Print.PrintMessage("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }

    }
}