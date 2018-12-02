/*
 * Face "Boucliers de la gardienne "
   ☻ Veuillez prendre le temps de lire la doc concernant cette face dans le pdf avant de lire la classe
 */
package Faces;

import Card.Card;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;



public class ShieldOfTheGuardianFace extends GardenFace{
    
    SimpleFace Type1 = new SimpleFace(5, "Gl", "GloryFace");    // Gain A
    SimpleFace Type2;    // Gain B


    public ShieldOfTheGuardianFace(String name,Card card,SimpleFace face) {
        super(name,card);
        Type2 = face;
    }

    public SimpleFace getType1() {
        return Type1;
    }

    public SimpleFace getType2() {
        return Type2;
    }
    
    @Override
    public String toString() {
        return this.getType1().toString() + "||" + this.getType2().toString();
    }

    @Override
    public void makeEffect(int action,int favMin,Temple temple,int numBot,
                               Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        
        
        if(a==0){
                if(favMin==0) { // Reçu via une faveur mineure
                    Type2.makeEffectFaceMultiplier(action,1,temple,numBot,bot,1,data,listBot);  // GAIN B 
                }
                else {
                    comparaison(action,temple,numBot, bot, 0, 1, data);
                    comparaison(action,temple,numBot, bot, 1, 0, data);
                }
        }
    }
    
    @Override
    public void makeEffectFaceMultiplier(int action,int favMin,Temple temple,int numBot,
                                      Bot bot,int a,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        System.out.println("Face obtained  -> " + Type2.toString());
        Type2.makeEffectFaceMultiplier(action,favMin,temple,numBot,bot,3,data,listBot);   
       // Donne 3* le gain B en présence de la face "*3" 
    }
    
    @Override
    public void makeCardCyclopEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                                                        Bot... listBot){
        
         Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);  // GAIN B 
    }
    
    
    // determine le gain
    public void comparaison(int action,Temple temple,int numBot,Bot bot,int a,int b,ArrayList<GeneralFace>[] data,Bot... listBot)
    {
        if(data[numBot].get(a).getName().equals("ShieldOfTheGuardianFace")){
            // Le traitement s"effectuera sur la seconde face ---> face b
            
            SimpleFace temp = new SimpleFace(); // face simple de recuperation
            int sanctuaryFaceAdd = 0;  // passera à 1 si c'est une face du sanctuaire avec "add"
            
            if(data[numBot].get(b) instanceof SanctuarysFaces){  // Face Multiple au choix
                
                SanctuarysFaces other = (SanctuarysFaces) data[numBot].get(b);
                ArrayList<SimpleFace> Offered = other.getOffered(); // Avoir la liste de choix dispo
                if(other.getMode().equals("Choice")){  // Face multiple au choix , on doit faire le choix avt de continuer
                        
                        int number = bot.getStrategy().giveMeYourChoice(Offered);
                        temp = new SimpleFace(Offered.get(number));
                }else if(Offered.size() ==1 && other.getMode().equals("Add")){
                        temp = new SimpleFace(Offered.get(0));
                }else{  // Face "Add" et nombre >= 1
                         sanctuaryFaceAdd =1;
                }
             }else if(data[numBot].get(b) instanceof SimpleFace){
                    temp = (SimpleFace) data[numBot].get(b);
             }
            
            if(!(data[numBot].get(b) instanceof SanctuarysFaces)&&!(data[numBot].get(b) instanceof SimpleFace) || (sanctuaryFaceAdd == 1))
            {
                
                // les autres Faces du Jardin et les faces du sanctuaires qui rapporte plusieurs gains
                if(action==0){
                    Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B
                }else{
                    Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B   
                }
                 
                
            }else{  // Face simple
                
                if(temp.getName().equals(Type2.getName())){
                    if(action==0){
                        bot.getHerosInventory().IncreaseGloryPoints(5);  // GAIN A car les types concordent
                    }else{
                        bot.getHerosInventory().DecreaseGloryPoints(5);  // GAIN A car les types concordent
                    }
                       
                }else{
                    if(action==0){
                        Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B  
                    }else{
                        Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B  
                    }
                    
                }
            }
      }
    }
    
    
    
    
    // effet Sentinnel
    
    @Override
    public void makeCardSentinelEffect(Temple temple,int numBot,Bot bot,ArrayList<GeneralFace>[] data,
                                                Bot... listBot){
        /* Si le joueur possède une face multiplier : Ne rien faire car c'est la face
           Multiplier qui s'activera et fera effet
        */
        int a = 0; // Pas de face Multiplier obtenue, passe à 1 sinon
        if(data.length!=0){ // si == 0, faveur mineure
           for(GeneralFace face : data[numBot]){
                if(face instanceof MultiplierFace){
                    a = 1;
                }
            } 
        }
        
        if(a==0){
                    comparaisonSentinel(temple,numBot, bot, 0, 1, data);
                    comparaisonSentinel(temple,numBot, bot, 1, 0, data);
        }
        
    }
    
 
    @Override
    public void makeEffectFaceMultiplierCardSentinelEffect(Temple temple,int numBot,
                                          int a,Bot bot,ArrayList<GeneralFace>[] data,Bot... listBot){
        
        System.out.println("Face obtained  -> " + Type2.toString());
        Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B    
       // Donne 3* le gain B en présence de la face "*3" 
    }
    
    
    
    
    // determine le gain version Sentinel
    public void comparaisonSentinel(Temple temple,int numBot,Bot bot,int a,int b,ArrayList<GeneralFace>[] data,Bot... listBot)
    {
        if(data[numBot].get(a).getName().equals("ShieldOfTheGuardianFace")){
            // Le traitement s"effectuera sur la seconde face ---> face b
            
            SimpleFace temp = new SimpleFace(); // face simple de recuperation
            int sanctuaryFaceAdd = 0;  // passera à 1 si c'est une face du sanctuaire avec "add"
            
            if(data[numBot].get(b) instanceof SanctuarysFaces){  // Face Multiple au choix
                
                SanctuarysFaces other = (SanctuarysFaces) data[numBot].get(b);
                ArrayList<SimpleFace> Offered = other.getOffered(); // Avoir la liste de choix dispo
                if(other.getMode().equals("Choice")){  // Face multiple au choix , on doit faire le choix avt de continuer
                        
                        int number = bot.getStrategy().giveMeYourChoice(Offered);
                        temp = new SimpleFace(Offered.get(number));
                }else if(Offered.size() ==1 && other.getMode().equals("Add")){
                        temp = new SimpleFace(Offered.get(0));
                }else{  // Face "Add" et nombre >= 1
                         sanctuaryFaceAdd =1;
                }
             }else if(data[numBot].get(b) instanceof SimpleFace){
                    temp = (SimpleFace) data[numBot].get(b);
             }
            
            if(!(data[numBot].get(b) instanceof SanctuarysFaces)&&!(data[numBot].get(b) instanceof SimpleFace) || (sanctuaryFaceAdd == 1))
            {
                
                // les autres Faces du Jardin et les faces du sanctuaires qui rapporte plusieurs gains
                 Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B  
                
            }else{  // Face simple
                
                if(temp.getName().equals(Type2.getName())){
                    bot.getHerosInventory().IncreaseGloryPoints(5);  // GAIN A car les types concordent   
                }else{
                   Type2.makeEffectFaceMultiplier(0,1,temple,numBot,bot,1,data,listBot);   // GAIN B  
                }
            }
      }
    }
    
    // FIN Sentinel 
    
    
    
}
