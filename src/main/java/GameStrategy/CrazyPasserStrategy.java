/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameStrategy;

import Card.Card;
import Player.Bot;
import java.util.ArrayList;

/**
 *
 * @author KOFFI Merveille
 */
public class CrazyPasserStrategy {
    
    private ArrayList<String> cardName = new ArrayList<>(); //Contient la liste des cartes primordiales 
                                        //que le joueur doit acquérir
    
    public int nbCard;   // Contient le nombre de cartes actuellement payée par le joueur
    boolean apply;    // Si la strategy doit être appliquée
    boolean applyNext; // Si la strategy doit être appliquée au prochain tour
    int live;  // indique si la stratégie peut être continué à être utilisé
    boolean applyJelly;  // au cas ou le joueur a la possibilité de payer une Carte Méduse
    boolean applyFormer;  // indique si le joueur possède déja une carte 
    
    public CrazyPasserStrategy(){
        cardName.add("TheCrazyGrasses");  
        cardName.add("ThePasser");
        cardName.add("TheJellyFish");   
        cardName.add("TheFormer");
        apply = false;
        applyNext = false;
        applyJelly = false;
        applyFormer = false;
        nbCard = 0;
        live = 0;
    }
    
    
    public boolean checkAvailableAction(ArrayList<Card> list,Bot bot){
         
         if((bot.getHerosInventory().getMoonPoints()>=1) && (bot.getHerosInventory().getMoonPoints()<4)){
                  apply = false;
                  System.out.println("J'ai entre 1 et 3 MOON ");
         }else{
             if(bot.getHerosInventory().getMoonPoints()==0){ 
                 System.out.println("J'ai 0 MOON ");
                 apply = false;   
             }
             if(bot.getHerosInventory().getMoonPoints()>=4){
                 System.out.println("J'ai plus de 4 MOON ");
                 apply = true;   
                 applyNext = true;
             }
         }
         
         if((bot.getHerosInventory().getSunPoints()>=1) && applyNext!=true){
                 apply = true;
                 System.out.println("J'ai au moins 1 SUN ");
         }
         
         if((bot.getHerosInventory().getSunPoints()>=4)){
                 apply = true;
                 applyJelly = true;
                 System.out.println("J'ai au moins 4 SUN ");
         }
         
         return apply;
    }
    
    // Recherche une carte précisement parmi celles 
    private int searchCard(ArrayList<Card> list,String name){
        for(int a=0;a<list.size();a++){
            System.out.println("Nom de la carte "+list.get(a).getName());
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
                System.out.println("Je dois payer l'ancien");
                indice = searchCard(list,cardName.get(3));
                applyFormer = true;
                if(indice!=-1)
                    return indice;
            }
        }
        
        if(applyJelly==true){
           System.out.println("Je dois payer une Méduse");
           indice = searchCard(list,cardName.get(2));
           apply = false ;
           applyJelly = false;
           return indice;
        }
        
        if(applyNext==true){
           System.out.println("Je dois payer un passeur");
           indice = searchCard(list,cardName.get(1));
           apply = false ;
           applyNext = false ;
           if(indice==-1)
               indice = searchCard(list,cardName.get(3));
           return indice;
        }
        
        if(applyNext==false){
           System.out.println("Je dois payer les herbes");
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
