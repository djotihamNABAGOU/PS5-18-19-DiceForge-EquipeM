/*
     Cette strategie sera appelé exactement 5 fois car elle remplace toutes les faces 1G du dé 1
     et indique de payer en gros des faces d"or dans l'ordre  3G , 4G , 3G , 4G , 6G
     le dé sur lequel l'implémenter est le 1 (choisi delibéremment)
     s"adapte selon l'épuisement des faces 
 */

package GameStrategy;

import Faces.Sanctuary.SanctuarysFaces;
import Player.Dice;
import java.util.ArrayList;

/**
 *
 * @author The Beginners
 */
public class GoldenSubstitution{
    
    private int val ; // Valeur de la face suivante à payer
    private int rep;  // Nombre de fois que la stratégie est appelée
    private final int dice ;  
 
    
    public GoldenSubstitution(){
         val = 3;  // Valeur de la 1ere face d"or à payer
         rep = 0;
         dice = 1; // C"est sur le premier dé qu"on mettra les fces de dé
    }
    
    
    public SanctuarysFaces choiceGoldFace(ArrayList<SanctuarysFaces> list){
        rep = rep + 1;
        boolean goldFaceThreeAvailable = false;
        boolean goldFaceFourAvailable = false;
        boolean goldFaceSixAvailable = false;
        int indice = 0 ; // indice de la face à payer
        
        for(int a=0;a<list.size();a++){
            
                switch(list.get(a).getOffered().get(0).getValue()){
                    case 3 : goldFaceThreeAvailable = true;
                             break;
                    case 4 : goldFaceFourAvailable = true;
                             break;
                    case 6 : goldFaceSixAvailable = true;
                             break;
                }
                if(list.get(a).getOffered().get(0).getValue()==(val)){
                   indice = a;
                }
        }
        
        // IL PAIT EN 5EM POSITION LA FACE D'OR 6 SSI elle est tjr disponible
        if(rep==5){
            if(goldFaceSixAvailable){
                for(int a=0;a<list.size();a++){
                    if(list.get(a).getOffered().get(0).getValue()==6){
                       val = 6;
                       return list.get(a);
                    }
                }  
            }
        }
        
        if((val==3) && goldFaceThreeAvailable){
            val = 4;
            return list.get(indice);
        }else if(goldFaceFourAvailable){
            for(int a=0;a<list.size();a++){
                if(list.get(a).getOffered().get(0).getValue()==4){
                   val = 4;
                   return list.get(a);
                }
            }
        }else if(goldFaceSixAvailable){
            for(int a=0;a<list.size();a++){
                if(list.get(a).getOffered().get(0).getValue()==6){
                   val = 6;
                   return list.get(a);
                }
            }        
        }
        
        
        if((val==4) && goldFaceFourAvailable){
            val = 3;
            return list.get(indice);
        }else if(goldFaceThreeAvailable){
            for(int a=0;a<list.size();a++){
                if(list.get(a).getOffered().get(0).getValue()==3){
                   val = 3;
                   return list.get(a);
                }
            }
        }else if(goldFaceSixAvailable){
            for(int a=0;a<list.size();a++){
                if(list.get(a).getOffered().get(0).getValue()==6){
                   val = 6;
                   return list.get(a);
                }
            }        
        }
        
        // Arrive ici s'il ne reste que des faces 6 disponibles pour l'achat
        if((val==6) && goldFaceSixAvailable){
            return list.get(0);
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
