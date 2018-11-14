package diceforge;

import java.util.ArrayList;

public class Temple {
    
    private ArrayList<SanctuarysFaces> Sanctuary = new ArrayList<>();

    public ArrayList<SanctuarysFaces> getSanctuary() {
        return Sanctuary;
    }

    public Temple()
   {
       int a = 0;
       

       for(a=0;a<4;a++)
       {
           SanctuarysFaces faceG3 = new SanctuarysFaces(3,"G",2);/* 2 Pieces D'or */
           SanctuarysFaces faceM1 = new SanctuarysFaces(1,"M",2);
           SanctuarysFaces faceG4 = new SanctuarysFaces(4,"G",3);
           SanctuarysFaces faceS1 = new SanctuarysFaces(1,"S",3);
           SanctuarysFaces face3G_2Gl = new SanctuarysFaces(0,"3G/2Gl",5);
           SanctuarysFaces faceM2 = new SanctuarysFaces(2,"M",6);
           SanctuarysFaces faceS2 = new SanctuarysFaces(2,"S",8);
           SanctuarysFaces face3Gl = new SanctuarysFaces(3,"Gl",8);
           Sanctuary.add(faceG3);
           Sanctuary.add(faceM1);
           Sanctuary.add(faceG4);
           Sanctuary.add(faceS1);
           Sanctuary.add(face3G_2Gl);
           Sanctuary.add(faceM2);
           Sanctuary.add(faceS2);
           Sanctuary.add(face3Gl);
       }

       
       /* 4 Pieces D'or */
       SanctuarysFaces faceOne = new SanctuarysFaces(1,"M/S/G",4);
       Sanctuary.add(faceOne);
       SanctuarysFaces faceTwo = new SanctuarysFaces(3,"2G+1M",4);
       Sanctuary.add(faceTwo);
       SanctuarysFaces faceThree = new SanctuarysFaces(2,"1Gl+1S",4);
       Sanctuary.add(faceThree);
       SanctuarysFaces faceFour = new SanctuarysFaces(6,"G",4);
       Sanctuary.add(faceFour);

       
       /* 12 Pieces D'or */
       SanctuarysFaces faceSix = new SanctuarysFaces(4,"Gl",12);
       Sanctuary.add(faceSix);
       SanctuarysFaces faceSeven = new SanctuarysFaces(4,"ALL",12);
       Sanctuary.add(faceSeven);
       SanctuarysFaces faceEight = new SanctuarysFaces(1,"2G/2S/2M",12);
       Sanctuary.add(faceEight);
       SanctuarysFaces faceNine = new SanctuarysFaces(2,"2Gl+2M",12);
       Sanctuary.add(faceNine);
   }
   
   public int faceAvailable(SanctuarysFaces sanctuaryFaces)
   {
      int a;
      for(a=0;a<Sanctuary.size();a++)
      {
          if(Sanctuary.get(a).getType().equals(sanctuaryFaces.getType()) && Sanctuary.get(a).getValue() == sanctuaryFaces.getValue() && Sanctuary.get(a).isSelected()==false)
          {
              return a;
          }
      }
      return -1;
   }
   
   public boolean buyFace(SanctuarysFaces sanctuaryFaces)
   {
       int a = faceAvailable(sanctuaryFaces);
       if (a!=-1)
       {
           Sanctuary.get(a).setSelected(true);
           return true;
       }
       else return false;
   }
}
