package diceforge;

import java.util.ArrayList;

public class Temple {
    
    ArrayList<SanctuarysFaces> Sanctuary = new ArrayList<>(); 
   
   public Temple()
   {
       int a = 0;
       
       /* 2 Pieces D'or */
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(3,"G",2);
          Sanctuary.add(face);
       }
       
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(1,"M",2);
          Sanctuary.add(face);
       }
       
       /* 3 Pieces D'or */
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(4,"G",3);
          Sanctuary.add(face);
       }
       
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(1,"S",3);
          Sanctuary.add(face);
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
       
       /* 5 Pieces D'or */
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(0,"3G/2Gl",5);
          Sanctuary.add(face);
       }
       
       /* 6 Pieces D'or */
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(2,"M",6);
          Sanctuary.add(face);
       }
       
       /* 8 Pieces D'or */
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(2,"S",8);
          Sanctuary.add(face);
       }
       
       for(a=0;a<4;a++)
       {
          SanctuarysFaces face = new SanctuarysFaces(3,"Gl",8);
          Sanctuary.add(face);
       }
       
       /* 12 Pieces D'or */
       SanctuarysFaces faceSix = new SanctuarysFaces(4,"Gl",12);
       Sanctuary.add(faceSix);
       SanctuarysFaces faceSeven = new SanctuarysFaces(4,"ALL",12);
       Sanctuary.add(faceSeven);
       SanctuarysFaces faceEight = new SanctuarysFaces(1,"1G/1S/1M",12);
       Sanctuary.add(faceEight);
       SanctuarysFaces faceNine = new SanctuarysFaces(2,"1G+1M",12);
       Sanctuary.add(faceNine);
   }
   
   public int faceAvailable(SanctuarysFaces sanctuaryFaces)
   {
      SanctuarysFaces face;
      int a;
      for(a=0;a<Sanctuary.size();a++)
      {
          if(Sanctuary.get(a).getType() == sanctuaryFaces.getType() && Sanctuary.get(a).isSelected()==false)
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
           Sanctuary.get(a).isSelected();
           return true;
       }
       else return false;
   }
}
