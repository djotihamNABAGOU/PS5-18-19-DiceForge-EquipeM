package diceforge;

import Card.AutomaticEffectCard.TheWildBoar;
import Card.Card;
import Card.ImmediateEffectCard.TheAbyssallMirror;
import Card.ImmediateEffectCard.TheCelestialSail;
import Card.ImmediateEffectCard.TheInvisibilityHelmet;
import Faces.Garden.GardenFace;
import Faces.Garden.MirrorFace;
import Faces.Garden.MultiplierFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Garden.ShieldOfTheGuardianFace;
import Faces.Sanctuary.SimpleFace;
import Faces.Garden.WildBoardFace;
import Faces.Garden.WroughtFace;

import java.util.ArrayList;

public class Temple {

    //le sanctuaire est un tableau de 10 bassins représentés par des arraylists
    private ArrayList<SanctuarysFaces>[] Sanctuary = new ArrayList[10];
    //On aura aussi un tableau de bassins pour les faces de jardin
    private ArrayList<GardenFace>[] Garden = new ArrayList[5];

    public Temple() {

        ArrayList<SanctuarysFaces> bassinG3 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinM1 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinG4 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinS1 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinG3_Gl2 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinM2 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinS2 = new ArrayList<>();
        ArrayList<SanctuarysFaces> bassinGl3 = new ArrayList<>();
        
        ArrayList<GardenFace> bassinMirror = new ArrayList<>();
        ArrayList<GardenFace> bassinWrought = new ArrayList<>();
        ArrayList<GardenFace> bassinMultiplier = new ArrayList<>();


        for (int a = 0; a < 4; a++) {

            bassinG3.add(new SanctuarysFaces(2, "GoldenFace", "Add", new SimpleFace(3, "G", "GoldenFace")));
            bassinM1.add(new SanctuarysFaces(2, "MoonFace", "Add", new SimpleFace(1, "M", "MoonFace")));
            bassinG4.add(new SanctuarysFaces(3, "GoldenFace", "Add", new SimpleFace(4, "G", "GoldenFace")));
            bassinS1.add(new SanctuarysFaces(3, "Sunface", "Add", new SimpleFace(1, "S", "SunFace")));
            bassinG3_Gl2.add(new SanctuarysFaces(5, "GoldenGloryFace", "Choice", new SimpleFace(3, "G", "GoldenFace"), new SimpleFace(2, "Gl", "GloryFace")));
            bassinM2.add(new SanctuarysFaces(6, "MoonFace", "Add", new SimpleFace(2, "M", "MoonFace")));
            bassinS2.add(new SanctuarysFaces(8, "Sunface", "Add", new SimpleFace(2, "S", "SunFace")));
            bassinGl3.add(new SanctuarysFaces(8, "Gloryface", "Add", new SimpleFace(3, "Gl", "GloryFace")));
        
            // face du bassin
            bassinMirror.add(new MirrorFace("MirrorFace",new TheAbyssallMirror()));
            bassinWrought.add(new WroughtFace("WroughtFace",new TheCelestialSail()));
            bassinMultiplier.add(new MultiplierFace("MultiplierFace",new TheInvisibilityHelmet()));
        }

        /* 4 Pieces D'or */
        ArrayList<SanctuarysFaces> bassinHybride_Cost4 = new ArrayList<>();
        bassinHybride_Cost4.add(new SanctuarysFaces(4, "MoonSunGoldenFace",
                        "Choice",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace")
                )
        );
        bassinHybride_Cost4.add(new SanctuarysFaces(4, "GoldenMoonFace",
                        "Add",
                        new SimpleFace(2, "G", "GoldenFace"),
                        new SimpleFace(1, "M", "MoonFace")
                )
        );
        bassinHybride_Cost4.add(new SanctuarysFaces(4, "GlorySunFace",
                        "Add",
                        new SimpleFace(1, "Gl", "GloryFace"),
                        new SimpleFace(1, "S", "SunFace")
                )
        );
        bassinHybride_Cost4.add(new SanctuarysFaces(4, "GoldenFace", "Add", new SimpleFace(6, "G", "GoldenFace")));

        /* 12 Pieces D'or */
        ArrayList<SanctuarysFaces> bassinHybride_Cost12 = new ArrayList<>();
        bassinHybride_Cost12.add(new SanctuarysFaces(12, "GloryFace", "Add", new SimpleFace(4, "Gl", "GloryFace")));
        bassinHybride_Cost12.add(new SanctuarysFaces(12, "MoonSunGoldenGloryFace",
                        "Add",
                        new SimpleFace(1, "M", "MoonFace"),
                        new SimpleFace(1, "S", "SunFace"),
                        new SimpleFace(1, "G", "GoldenFace"),
                        new SimpleFace(1, "Gl", "GloryFace")
                )
        );
        bassinHybride_Cost12.add(new SanctuarysFaces(12, "MoonGoldenSunFace",
                        "Choice",
                        new SimpleFace(2, "M", "MoonFace"),
                        new SimpleFace(2, "S", "SunFace"),
                        new SimpleFace(2, "G", "GoldenFace")
                )
        );
        bassinHybride_Cost12.add(new SanctuarysFaces(12, "MoonGloryFace",
                        "Add",
                        new SimpleFace(2, "M", "MoonFace"),
                        new SimpleFace(2, "Gl", "GloryFace")
                )
        );
        
        // Face AILE DE LA GARDIENNE 
        
        ArrayList<GardenFace> bassinShield = new ArrayList<>();
        bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(3, "G", "GoldenFace")));
        bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "S", "SunFace")));
        bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "M", "MoonFace")));
        bassinShield.add(new ShieldOfTheGuardianFace("ShieldOfTheGuardianFace",new TheInvisibilityHelmet(),new SimpleFace(2, "Gl", "GloryFace")));
        
        // Face SANGLIER ACHARNE
        
        ArrayList<GardenFace> bassinWild = new ArrayList<>();
        bassinWild.add(new WildBoardFace("TheWildBoargreen",new TheWildBoar("green")));
        bassinWild.add(new WildBoardFace("TheWildBoarblue",new TheWildBoar("blue")));
        bassinWild.add(new WildBoardFace("TheWildBoaryellow",new TheWildBoar("yellow")));
        bassinWild.add(new WildBoardFace("TheWildBoarbrown",new TheWildBoar("brown")));
        
        
        //Rangement des bassins dans le tableau sanctuaire
        Sanctuary[0] = bassinG3;
        Sanctuary[1] = bassinM1;
        Sanctuary[2] = bassinG4;
        Sanctuary[3] = bassinS1;
        Sanctuary[4] = bassinG3_Gl2;
        Sanctuary[5] = bassinM2;
        Sanctuary[6] = bassinS2;
        Sanctuary[7] = bassinGl3;
        Sanctuary[8] = bassinHybride_Cost4;
        Sanctuary[9] = bassinHybride_Cost12;
        
        //Rangement des bassins dans le tableau garden
        Garden[0] = bassinMirror;
        Garden[1] = bassinShield;
        Garden[2] = bassinWrought;
        Garden[3] = bassinWild;
        Garden[4] = bassinMultiplier;
        
      
    }

    public ArrayList<SanctuarysFaces>[] getSanctuary() {
        return Sanctuary;
    }

    String faceAvailable(SanctuarysFaces sanctuaryFaces) {
        for (int a = 0; a < 10; a++) {
            for (int i = 0; i < Sanctuary[a].size(); i++) {
                if (Sanctuary[a].get(i).getName().equals(sanctuaryFaces.getName())
                        && Sanctuary[a].get(i).getPrice() == sanctuaryFaces.getPrice()
                        && !Sanctuary[a].get(i).isSelected()) {
                    return "" + a + "-" + i;//indice du tableau puis indice du arraylist
                }
            }
        }
        return "";
    }

    public boolean buyFace(SanctuarysFaces sanctuaryFaces) {
        String a = faceAvailable(sanctuaryFaces);
        if (!a.equals("")) {
            String[] tab = a.split("-");
            int tabIndex = Integer.parseInt(tab[0]);
            int arrayListIndex = Integer.parseInt(tab[1]);
            Sanctuary[tabIndex].get(arrayListIndex).setSelected(true);
            return true;
        } else return false;
    }

    // Methode pour recupérer une face du jardin grâce à la carte correspondante
    public GardenFace takeGardenFace(Card card) {
        GardenFace returnFace = new GardenFace();
        for (int a = 0; a < 5; a++) {
            for (int i = 0; i < Garden[a].size(); i++) {
                if (Garden[a].get(i).getCard().equals(card) && !Garden[a].get(i).isSelected()) {
                    Garden[a].get(i).setSelected(true);
                    returnFace = Garden[a].get(i);
                }
            }
        }
        return returnFace;
    }
    
    // Methode pour retourner le bassin dans lequel se trouve une face
    public int giveMeTheBasin(SanctuarysFaces face){
         int basin = 0;
         for (int a = 0; a < 10; a++) {
            for (int i = 0; i < Sanctuary[a].size(); i++) {
                if (Sanctuary[a].get(i).getName().equals(face.getName())
                        && Sanctuary[a].get(i).getPrice() == face.getPrice()){
                     basin = a;
                }
            }
        }
        return basin;
    }
}
