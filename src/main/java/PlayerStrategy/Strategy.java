package PlayerStrategy;

import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
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
     * @param listFaces
     * @param data
     */
    public void apply(Temple temple, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces,Bot... data){
        System.out.println("I am General Strategy, please implement effect in subclasses");
    }
    
    public void ForgeDice(GeneralFace face) {}
    
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple, int bassin) {
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

    // changer un point d'or par un point de gloire ? , effet de la carte Cyclope
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

    public int giveMeTokenResource() {
        //choisit la ressource voulue pr l'utilisation d'un jeton Triton 
        // 0 ---> 2 Sun
        // 1 ---> 2 Moon
        // 2 ---> 6 Gold
        return 0;
    }
    
    public int applyHammerStrategy(int winGoldPoints){    
        /* Cette méthode sera appelé automatiquement si le joueur en question a une carte marteau 
           et vient de gagner des points d'or 
           NB : Juste implémenter la méthode selon la stratégie , pas se soucier de l'appel (déja géré)
        
           Elle prend en paramètre le nombre de points d'or que vient d'obtenir  le joueur
           Elle doit renvoyer le nombre de points d'or que le joueur souhaite gagner : c à d
           ---> ( winGoldPoints - nbre de points utilisés pour le parcours de la carte marteau)
           Voir la carte "TheHammer" et la méthode "useHammerToken" dans Bot pr une bonne 
           implémentation car il faudra appeler la méthode "useHammerToken" avec le nombre de points 
           choisis pr le parcours [si>0]
        */        
        return winGoldPoints;   
        /* par défaut pour le moment , retourne winGoldPoints , c a d  le joueur décide de gagner tous ses points 
           et de n'en consacrer aucun pour le parcours de sa carte marteau*/  
    } 

    public int useTokenCerberus(int val1, int val2) {
        /*
           Cette méthode sera appelé automatiquement si le joueur en question a un jeton cerbers et vient
           d'obtenir des faveurs mineures
           val1 -> valeur obtenue par le premier dé , voir commentaire dns la clase GENERAL FACE
           val2 -> valeur obtenue par le second dé , voir commentaire dns la clase GENERAL FACE
        
           1 pr utiliser le jeton cerbers
           0 pr ne pas l'utiliser
        */
        return 0;
    }
    
    public int useTokenCerberus(int val1){
        /*
           Cette méthode sera appelé automatiquement si le joueur en question a un jeton cerbers et vient
           d'obtenir une faveur mineur 
           val1 -> valeur obtenue par le dé , voir commentaire dns la clase GENERAL FACE
           NB : Juste implémenter la méthode selon la stratégie , pas se soucier de l'appel (déja géré)
           1 pr utiliser le jeton cerbers
           0 pr ne pas l'utiliser
        */
        return 0;
    }
    
}
