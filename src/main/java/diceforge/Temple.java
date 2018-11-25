package diceforge;

import Faces.SanctuarysFaces;
import Faces.SimpleFace;

import java.util.ArrayList;

 public class Temple {
    
    private ArrayList<SanctuarysFaces> Sanctuary = new ArrayList<>();

    Temple(){
       for(int a=0;a<4;a++){
           Sanctuary.add(new SanctuarysFaces(2,"GoldenFace","Add",new SimpleFace(3,"G","GoldenFace")));
           Sanctuary.add(new SanctuarysFaces(2,"MoonFace","Add",new SimpleFace(1,"M","MoonFace")));
           Sanctuary.add(new SanctuarysFaces(3,"GoldenFace","Add",new SimpleFace(4,"G","GoldenFace")));
           Sanctuary.add( new SanctuarysFaces(3,"Sunface","Add",new SimpleFace(1,"S","SunFace")));
           Sanctuary.add( new SanctuarysFaces(5,"GoldenGloryFace","Choice",new SimpleFace(3,"G","GoldenFace"),new SimpleFace(2,"Gl","GloryFace")));
           Sanctuary.add(new SanctuarysFaces(6,"MoonFace","Add",new SimpleFace(2,"M","MoonFace")));
           Sanctuary.add( new SanctuarysFaces(8,"Sunface","Add",new SimpleFace(2,"S","SunFace")));
           Sanctuary.add( new SanctuarysFaces(8,"Gloryface","Add",new SimpleFace(3,"Gl","GloryFace")));
         
       }
       /* 4 Pieces D'or */
       Sanctuary.add( new SanctuarysFaces(4,"MoonSunGoldenFace",
                                          "Choice",
                                          new SimpleFace(1,"M","MoonFace"),
                                          new SimpleFace(1,"S","SunFace"),
                                          new SimpleFace(1,"G","GoldenFace")
                                          )
                    );
       Sanctuary.add( new SanctuarysFaces(4,"GoldenMoonFace",
                                          "Add",
                                          new SimpleFace(2,"G","GoldenFace"),
                                          new SimpleFace(1,"M","MoonFace")
                                          )
                    );
       Sanctuary.add( new SanctuarysFaces(4,"GlorySunFace",
                                          "Add",
                                          new SimpleFace(1,"Gl","GloryFace"),
                                          new SimpleFace(1,"S","SunFace")
                                          )
                    );
       Sanctuary.add(new SanctuarysFaces(4,"GoldenFace","Add",new SimpleFace(6,"G","GoldenFace")));
      
       /* 12 Pieces D'or */
       Sanctuary.add(new SanctuarysFaces(12,"GloryFace","Add",new SimpleFace(4,"Gl","GloryFace")));
       Sanctuary.add( new SanctuarysFaces(12,"MoonSunGoldenGloryFace",
                                          "Add",
                                          new SimpleFace(1,"M","MoonFace"),
                                          new SimpleFace(1,"S","SunFace"),
                                          new SimpleFace(1,"G","GoldenFace"),
                                          new SimpleFace(1,"Gl","GloryFace")
                                          )
                    );
       Sanctuary.add( new SanctuarysFaces(12,"MoonGoldenSunFace",
                                          "Choice",
                                          new SimpleFace(2,"M","MoonFace"),
                                          new SimpleFace(2,"S","SunFace"),
                                          new SimpleFace(2,"G","GoldenFace")
                                          )
                    );
       Sanctuary.add( new SanctuarysFaces(12,"MoonGloryFace",
                                          "Add",
                                          new SimpleFace(2,"M","MoonFace"),
                                          new SimpleFace(2,"Gl","GloryFace")
                                          )
                    );
    }

     public ArrayList<SanctuarysFaces> getSanctuary() {
         return Sanctuary;
     }
   
    int faceAvailable(SanctuarysFaces sanctuaryFaces){
      for(int a=0;a<Sanctuary.size();a++){
          if( Sanctuary.get(a).getName().equals(sanctuaryFaces.getName()) 
              && Sanctuary.get(a).getPrice() == sanctuaryFaces.getPrice()
              && !Sanctuary.get(a).isSelected()){
              return a;
          }
      }
      return -1;
    }
   
    public boolean buyFace(SanctuarysFaces sanctuaryFaces){
       int a = faceAvailable(sanctuaryFaces);
       if (a!=-1){
           Sanctuary.get(a).setSelected(true);
           return true;
       }
       else return false;
    }
 }
