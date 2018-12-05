package PlayerStrategy;

import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Faces.SimpleFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

public class Strategy {
    protected final Bot bot;//Bot auquel s'applique la stratégie

    /**
     * Constructeur de la classe Strategy
     * @param bot Bot auquel s'applique la stratégie
     */
    public Strategy(Bot bot) {
        this.bot = bot;
    }

    /**
     * méthode qui applique la stratégie du bot
     *
     * @param temple qui servira à réaliser des exploits
     * @param numberOfTheBot
     * @param actionNumber
     * @param listFaces
     * @param data
     */
    public void apply(Temple temple, int numberOfTheBot, int actionNumber,ArrayList<GeneralFace>[] listFaces,Bot... data){
        System.out.println("I am General Strategy, please implement effect in subclasses");
    }
    
    public void ForgeDice(GeneralFace face) {}
    
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple) {
        SanctuarysFaces face = new SanctuarysFaces();
        return face;
    }
    
    //Demande au joueur de choisir une face parmi celles proposées
    public int giveMeYourChoice(ArrayList<SimpleFace> Offered) {
        
        // A implementer 
        // ----> return 0 pr l"instant, donc le premier choix
        return 0;
    }
    
    public int giveMeYourGChoice(ArrayList<GeneralFace> Offered) {
        
        // A implementer 
        // ----> return 0 pr l"instant, donc le premier choix
        return 0;
    }
    
    // Effet Minotaure
     public int giveMeYourGChoiceDecrease(ArrayList<GeneralFace> Offered) {
        
        // A implementer 
        // ----> return 0 pr l"instant, donc le premier choix
        return 0;
    }
    
    //Demande au joueur de choisir une face à forger parmi celles disponibles au temple
    public SanctuarysFaces giveMeYourWroughtChoice(Temple temple) {
        SanctuarysFaces myFace = new SanctuarysFaces();
        
        // Corps de la méthode à implémenter
      
        return myFace;
    }

    //Demande au joueur quel dé il veut relancer [ effet de carte ]
    public int throwWhichDice() {
        // Methode à implementer
        
        // Retourne le premier par défaut pour le moment
        return 0;
    }

    // changer un point d'or par un point de gloire ? , effet de la carte Cyclop
    public int changeByGloryPoint() {
        // A implementer 
        
        // 0 si oui , 1 sinon
        return 0;
    }

    public int applyFormerEffect() {
        // choisir si gagner 4 points de gloire en depensant 3 points d'or
        // A implementer
        // 0 si oui , 1 sinon
        return 0;
    }

    public int whichResource() {
        // choisir quel ressource suppplementaire
        // A implementer
        return 0;
    }
    
    //Demande au joueur de choisir une face parmi celles proposées , effet negatif
    public int giveMeYourChoicedecrease(ArrayList<SimpleFace> Offered) {
        
        // A implementer 
        // ----> return 0 pr l"instant, donc le premier choix
        return 0;
    }
    
}
