/*
      Cette stratégie est basé uniquement sur un combo de 4 Cartes, que sont : 
      - Les herbes folles
      - Le passeur
      - La méduse
      - L'ancien

*/


package GameStrategy;

import Card.Card;
import Player.Bot;
import diceforge.GlobalConstants;
import java.util.ArrayList;

/**
 *
 * @author KOFFI Merveille
 */
public class FourCardComboStrategy implements GlobalConstants {
    
    private ArrayList<String> cardName = new ArrayList<>(); //Contient la liste des cartes primordiales 
                                        //que le joueur doit acquérir
    
    public int nbCard;   // Contient le nombre de cartes actuellement payée par le joueur
    boolean apply;    // Si la strategy doit être appliquée
    boolean applyNext; // Si la strategy doit être appliquée au prochain tour
    int live;  // indique si la stratégie peut être continué à être utilisé
    boolean applyJelly;  // au cas ou le joueur a la possibilité de payer une Carte Méduse
    boolean applyFormer;  // indique si le joueur possède déja une carte L'ancien
    boolean applyFormerTwo;  // indique si le joueur a déja payer la 2ème carte Ancien
    boolean applyHydra;     // indique qu'il faut payer une hydre
    
    public FourCardComboStrategy(){
        cardName.add("TheCrazyGrasses");  
        cardName.add("ThePasser");
        cardName.add("TheJellyFish");   
        cardName.add("TheFormer");
        cardName.add("TheHydra");
        apply = false;
        applyNext = false;
        applyJelly = false;
        applyFormer = false;
        applyFormerTwo = false;
        applyHydra = false;
        nbCard = 0;
        live = 0;
    }
    
    
    public boolean checkAvailableAction(ArrayList<Card> list,Bot bot){
         
         int Occ = 0;
         for(Card card : list){
            for(int a = 0;a<cardName.size();a++){
                if(card.getName().equals(cardName.get(a))){
                    Occ = Occ +1;
                }
            }    
         }
         
         if(Occ==0)
             return false;
         
         if((bot.getHerosInventory().getMoonPoints()>=1) && (bot.getHerosInventory().getMoonPoints()<4)){
                  apply = false;
                  Print.PrintMessage("J'ai entre 1 et 3 MOON ");
         }else{
             if(bot.getHerosInventory().getMoonPoints()==0){ 
                 Print.PrintMessage("J'ai 0 MOON ");
                 apply = false;   
             }
             if(bot.getHerosInventory().getMoonPoints()>=4){
                 Print.PrintMessage("J'ai plus de 4 MOON ");
                 apply = true;   
                 applyNext = true;
             }
         }
         
         if((bot.getHerosInventory().getSunPoints()>=1) && applyNext!=true){
                 apply = true;
                 Print.PrintMessage("J'ai au moins 1 SUN ");
         }
         
         if((bot.getHerosInventory().getSunPoints()>=4)){
                 apply = true;
                 applyJelly = true;
                 Print.PrintMessage("J'ai au moins 4 SUN ");
         }
         
         if((bot.getHerosInventory().getSunPoints()>=5) && (bot.getHerosInventory().getMoonPoints()>=5)){
                 apply = true;
                 applyHydra = true;
                 Print.PrintMessage("Je peux payer une hydra ");   
         }
         
         return apply;
    }
    
    // Recherche une carte précisement parmi celles à disposition
    private int searchCard(ArrayList<Card> list,String name){
        for(int a=0;a<list.size();a++){
            Print.PrintMessage("Nom de la carte "+list.get(a).getName());
            if(list.get(a).getName().equals(name)){
                 return a;
            }          
        }
        return -1;
    }
    
    
    public int whichCard(ArrayList<Card> list,Bot bot){
        
        int indice = 0;
        
        
        
        if((nbCard>=2) && (bot.getHerosInventory().getSunPoints()>=1)){
            if(applyFormer==false){
                Print.PrintMessage("Je dois payer l'ancien");
                indice = searchCard(list,cardName.get(3));
                applyFormer = true;
                if(indice!=-1)
                    return indice;
            }
        }
        
        if(applyJelly==true){
           Print.PrintMessage("Je dois payer une Méduse");
           indice = searchCard(list,cardName.get(2));
           if(indice!=-1){              
            apply = false ;
            applyJelly = false;
            return indice;
           }
        }
        
        if(applyHydra==true){
           Print.PrintMessage("Je dois payer une Hydre");
           indice = searchCard(list,cardName.get(4));
           apply = false ;
           applyHydra = false;
           return indice;
        }
        
        if(applyNext==true){
           Print.PrintMessage("Je dois payer un passeur");
           indice = searchCard(list,cardName.get(1));
           apply = false ;
           applyNext = false ;
           if(indice==-1)
               indice = searchCard(list,cardName.get(3));
           return indice;
        }
        
        if(applyNext==false){
           Print.PrintMessage("Je dois payer les herbes");
           indice = searchCard(list,cardName.get(0));
           apply = false ;
           if(indice==-1)
               indice = searchCard(list,cardName.get(3));
           //applyNext = true;
        }
        
       // if(indice == -1)
         //   live = -1;   // L'une des deux cartes a été épuisé
        
        return indice;
    }
        
       
////    // permet d'avoir la liste des cartes du même type
////    private ArrayList<Card> getTypeCard(ArrayList<Card> list,String type){
////        ArrayList<Card> listCard = new ArrayList<>();
////        for(int a=0;a<list.size();a++){
////            if(list.get(a).getType().equals(type)){
////                listCard.add(list.get(a));
////            }
////        }
////        return listCard;
////    }

    public int getNbCard() {
        return nbCard;
    }

    public boolean isApply() {
        return apply;
    }
    
    public int getLive() {
        return live;
    }
    
    
    
}
