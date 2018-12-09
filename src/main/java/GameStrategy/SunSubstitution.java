/*
     Cette strategie sera appelé exactement 2 fois car elle remplace 2 faces 1G du dé 2
     et indique de payer en gros des faces de Sun dans l'orde 1S 1S  
     le dé sur lequel l'implémenter est le 2 (choisi delibéremment)
     s"adapte selon l'épuisement des faces  (recherche une 2S )
 */

package GameStrategy;

import Faces.Sanctuary.SanctuarysFaces;
import Player.Dice;
import java.util.ArrayList;

/**
 *
 * @author KOFFI Merveille
 */
public class SunSubstitution {
    
    
public class GoldenSubstitution{
    
    private int val ; // Valeur de la face suivante à payer
    private int rep;  // Nombre de fois que la stratégie est appelée
    private final int dice ;  
 
    
    public GoldenSubstitution(){
         val = 1;  // Valeur de la 1ere face de SUn à payer
         rep = 0;
         dice = 2; // C"est sur le second dé qu"on mettra les fces de dé
    }
    
    
    public SanctuarysFaces choiceGoldFace(ArrayList<SanctuarysFaces> list){
        rep = rep + 1;
        boolean sunFaceOneAvailable = false;
        boolean sunFaceTwoAvailable = false;
        int indice = 0 ; // indice de la face à payer
        
        for(int a=0;a<list.size();a++){
            
                switch(list.get(a).getOffered().get(0).getValue()){
                    case 1 : sunFaceOneAvailable = true;
                             break;
                    case 2 : sunFaceTwoAvailable = true;
                             break;
                }
                if(list.get(a).getOffered().get(0).getValue()==(val)){
                   indice = a;
                }
        }
        
       
        if((val==1) && sunFaceOneAvailable){
            val = 1;
            return list.get(indice);
        }else if(sunFaceTwoAvailable){
            for(int a=0;a<list.size();a++){
                if(list.get(a).getOffered().get(0).getValue()==2){
                   val = 2;
                   return list.get(a);
                }
            }
        }
        
        return new SanctuarysFaces();
    }
    
    // Toutes les faces qui seront remplacées seront ceux de 1G
    public int faceRemove(Dice dice){
        int indice = 0;
        for(int a=0;a<6;a++){
           ArrayList<String> properties = dice.getFaces()[a].getProperties();
           if(Integer.valueOf(properties.get(0)) == 1){
               indice = a;
           }
        }
        return indice;
    }
    
}

}
