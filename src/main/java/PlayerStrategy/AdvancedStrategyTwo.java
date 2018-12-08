package PlayerStrategy;

import Card.Card;
import Card.Reinforcement;
import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Island;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Random; 


public class AdvancedStrategyTwo extends Strategy {  

    public AdvancedStrategyTwo(Bot bot) {
        super(bot);
    }
    
    // Cherche les faces qui procurent seulement un seul type de ressource bien déterminé
    private ArrayList<SanctuarysFaces>  searchFace(ArrayList<SanctuarysFaces> list,int a,int b,int c){
        ArrayList<SanctuarysFaces> listFace = new ArrayList<>();
        for(SanctuarysFaces face : list){
            ArrayList<String> properties = face.getProperties();
            if((Integer.valueOf(properties.get(a))==0) && (Integer.valueOf(properties.get(b))==0)
                         &&  (Integer.valueOf(properties.get(c))==0) ){
                     listFace.add(face);
            }
        }
        return listFace;
    }
    
    // Cherche les faces qui offrent soit un choix seulement / soit plusieurs ressources
    private ArrayList<SanctuarysFaces>  searchAddOrChoiceFace(ArrayList<SanctuarysFaces> list,String mode){
        ArrayList<SanctuarysFaces> listFace = new ArrayList<>();
        for(SanctuarysFaces face : list){
            ArrayList<String> properties = face.getProperties();
            if(properties.get(4).equals(mode)){
                 //pour éviter d"avoir les faces qui procurent un seul gain seulemnt pr mode Add
                 int compteur = 0;
                 for(int a=0;a<3;a++){
                     if(Integer.valueOf(properties.get(a))!=0){
                         compteur = compteur + 1;
                     }
                 }
                 if(compteur>=2)   // procure au moins 2 ressources
                       listFace.add(face);
            }
        }
        return listFace;
    }

    
    
    /**
     * retourne le nom de la face à payer choisie 
     * retourne NULL si le bot ne désire plus acheter une face 👍
     *
     * @param bot    utilisé pour avoir accès à l'inventaire du bot
     * @param temple ustilisé pour rechercher les faces disponibles
     * @return la face à payer choisie au hasard
     * en gros, on stocke les faces du sanctuaire disponibles dans une liste FacesAvailable puis on choisit au hasard la face à retourner
     */
    @Override
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple, int bassin) {
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
            if (a != bassin) {//car il ne peut retirer de faces d'un même bassin consécutivement
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        FacesAvailable.add(sanctuary[a].get(i));
                    }
                }
            }
        }
        
        
                      // -- -Traitement minicieux des faces à disposition- -- // 
        
        // Cette liste contiendra les faces qui ne procure que de l'or parmi les faces à acheter
        ArrayList<SanctuarysFaces> goldenFace = searchFace(FacesAvailable,1,2,3);
        
        // Cette liste contiendra les faces qui ne procure que Sun parmi les faces à acheter
        ArrayList<SanctuarysFaces> sunFace = searchFace(FacesAvailable,0,2,3);
        
        // Cette liste contiendra les faces qui ne procure que Moon parmi les faces à acheter
        ArrayList<SanctuarysFaces> moonFace = searchFace(FacesAvailable,0,1,3);
        
        // Cette liste contiendra les faces qui ne procure que Glory parmi les faces à acheter
        ArrayList<SanctuarysFaces> gloryFace = searchFace(FacesAvailable,0,1,2);
        
        //Cette liste contiendra les faces qui procurent un choix seulement parmi les faces à acheter
        ArrayList<SanctuarysFaces> choiceFace = searchAddOrChoiceFace(FacesAvailable,"Choice");
        
        //Cette liste contiendra les faces qui procurent plusieurs gains seulement parmi les faces à acheter
        ArrayList<SanctuarysFaces> addFace = searchAddOrChoiceFace(FacesAvailable,"Add");

        
        
        
        
        
        Random randomFace = new Random();

        if (FacesAvailable.size() == 0) return new SanctuarysFaces();
        else {
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //System.out.println("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }

    }
}
