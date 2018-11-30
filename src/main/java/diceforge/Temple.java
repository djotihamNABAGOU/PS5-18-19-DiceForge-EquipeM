package diceforge;

import Card.Card;
import Faces.GardenFace;
import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Faces.SimpleFace;

import java.util.ArrayList;

public class Temple {

    //private ArrayList<SanctuarysFaces> Sanctuary = new ArrayList<>();
    private ArrayList<SanctuarysFaces>[] Sanctuary = new ArrayList[10];//le sanctuaire est un tableau de 10 bassins représentés par des arraylists
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
        
        
        for (int a = 0; a < 4; a++) {
            
            bassinG3.add(new SanctuarysFaces(2, "GoldenFace", "Add", new SimpleFace(3, "G", "GoldenFace")));
            bassinM1.add(new SanctuarysFaces(2, "MoonFace", "Add", new SimpleFace(1, "M", "MoonFace")));
            bassinG4.add(new SanctuarysFaces(3, "GoldenFace", "Add", new SimpleFace(4, "G", "GoldenFace")));
            bassinS1.add(new SanctuarysFaces(3, "Sunface", "Add", new SimpleFace(1, "S", "SunFace")));
            bassinG3_Gl2.add(new SanctuarysFaces(5, "GoldenGloryFace", "Choice", new SimpleFace(3, "G", "GoldenFace"), new SimpleFace(2, "Gl", "GloryFace")));
            bassinM2.add(new SanctuarysFaces(6, "MoonFace", "Add", new SimpleFace(2, "M", "MoonFace")));
            bassinS2.add(new SanctuarysFaces(8, "Sunface", "Add", new SimpleFace(2, "S", "SunFace")));
            bassinGl3.add(new SanctuarysFaces(8, "Gloryface", "Add", new SimpleFace(3, "Gl", "GloryFace")));
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
                    return ""+a+"-"+i;//indice du tableau puis indice du arraylist
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
                if (Garden[a].get(i).getCard().equals(card) && !Garden[a].get(i).isSelected())
                {
                    Garden[a].get(i).setSelected(true);
                    returnFace = Garden[a].get(i);
                }
             }
            }
        return returnFace;
    }
}
