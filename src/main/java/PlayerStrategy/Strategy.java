package PlayerStrategy;

import Faces.Sanctuary.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import Faces.Sanctuary.SimpleFace;
import Player.Bot;
import diceforge.Island;
import diceforge.Temple;

import java.util.ArrayList;

public class Strategy {
    protected final Bot bot;//Bot auquel s'applique la stratégie
    protected boolean supActionDone = false;
    /**
     * Pour éviter de forger consécutivement deux faces venant d'un même bassin, il nous faut
     * garder une trace du bassin dans lequel la dernière forge s'est éffectuée car le bot
     * peut non seulement forger plusieurs faces s'il le désire, mais peut aussi faire une action sup
     * s'il est joueur actif.
     * On initialise donc une variable bassin à -1 pour désigner un bassin inexistant
     */
    protected int bassin = -1;

    /**
     * Constructeur de la classe Strategy
     *
     * @param bot Bot auquel s'applique la stratégie
     */
    public Strategy(Bot bot) {
        this.bot = bot;
    }

    /**
     * méthode qui applique la stratégie du bot
     *
     * @param temple         qui servira à réaliser des exploits
     * @param numberOfTheBot
     * @param listFaces
     * @param data
     */
    public void apply(Temple temple, Island island, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        System.out.println("I am General Strategy, please implement effect in subclasses");
    }

    /**
     * Méthode de forge
     *
     * @param face
     */
    public void ForgeDice(GeneralFace face) {
    }

    /**
     * Méthode d'appel des renforts
     *
     * @param temple
     * @param bot
     * @param numberOfTheBot
     * @param listFaces
     * @param data
     */
    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
    }


    /**
     * Méthode permettant au bot de choisir une face à forger en fonction de ses ressources
     *
     * @param bot
     * @param temple
     * @param bassin
     * @return
     */
    public SanctuarysFaces FaceToBuy(Bot bot, Temple temple, int bassin) {
        SanctuarysFaces face = new SanctuarysFaces();
        return face;
    }

    /**
     * Demande au joueur de choisir une face parmi celles proposées
     * A implementer dans les classes filles
     * ----> return 0 pr l"instant, donc le premier choix
     *
     * @param Offered
     * @param whichAction pour savoir si c'est un lancé de dé (0) ou un exploit (1)
     * @return
     */
    public int giveMeYourChoice(ArrayList<SimpleFace> Offered, int whichAction) {
        return 0;
    }

    /**
     * Demande au joueur de choisir une face gold parmi celles proposées
     * 2 cas: soit en lancé de dé, soit en exploit
     * A implementer dans les classes filles
     * ----> return 0 pr l"instant, donc le premier choix
     *
     * @param Offered
     * @return
     */
    public int giveMeYourGChoice(ArrayList<GeneralFace> Offered) {
        return 0;
    }

    /** Effet Minotaure
     *  A implementer
     *  ----> return 0 pr l"instant, donc le premier choix
     * @param Offered
     * @return
     */
    public int giveMeYourGChoiceDecrease(ArrayList<GeneralFace> Offered) {
        return 0;
    }

    /**Demande au joueur de choisir une face à forger parmi celles disponibles au temple
     *
     * @param temple
     * @return
     */
    public SanctuarysFaces giveMeYourWroughtChoice(Temple temple) {
        SanctuarysFaces myFace = new SanctuarysFaces();

        // Corps de la méthode à implémenter

        return myFace;
    }

    /**Demande au joueur quel dé il veut relancer [ effet de carte ]
     *         // Methode à implementer
     * @return // Retourne le premier par défaut pour le moment
     */
    public int throwWhichDice() {
        return 0;
    }

    /**changer un point d'or par un point de gloire ? , effet de la carte Cyclope
     *         // A implementer
     *@return  // 0 si oui , 1 sinon
     */
    public int changeByGloryPoint() {
        return 0;
    }

    /**
     *         // choisir si gagner 4 points de gloire en depensant 3 points d'or
     *         // A implementer
     * @return // 0 si oui , 1 sinon
     */
    public int applyFormerEffect() {
        return 0;
    }

    /**
     * Méthode appelée par la carte "Les ailes de la gardienne" lors de l'appel des cartes de renfort
     * // choisir quel ressource suppplementaire
     *         // A implementer
     * @return
     */
    public int whichResource() {
        return 0;
    }

    /**Demande au joueur de choisir une face parmi celles proposées , effet negatif
     *         // A implementer
     *         // ----> return 0 pr l"instant, donc le premier choix
     * @param Offered
     * @return
     */
    public int giveMeYourChoicedecrease(ArrayList<SimpleFace> Offered) {
        return 0;
    }

    /**
     * //choisit la ressource voulue pr l'utilisation d'un jeton Triton
     *         // 0 ---> 2 Sun
     *         // 1 ---> 2 Moon
     *         // 2 ---> 6 Gold
     * @return
     */
    public int giveMeTokenResource() {
        return 0;
    }

    /**
     * Cette méthode sera appelé automatiquement si le joueur en question a une carte marteau
     *            et vient de gagner des points d'or
     *            NB : Juste implémenter la méthode selon la stratégie , pas se soucier de l'appel (déja géré)
     *
     *            Elle prend en paramètre le nombre de points d'or que vient d'obtenir  le joueur
     *            Elle doit renvoyer le nombre de points d'or que le joueur souhaite gagner : c à d
     *            ---> ( winGoldPoints - nbre de points utilisés pour le parcours de la carte marteau)
     *            Voir la carte "TheHammer" et la méthode "useHammerToken" dans Bot pr une bonne
     *            implémentation car il faudra appeler la méthode "useHammerToken" avec le nombre de points
     *            choisis pr le parcours [si>0]
     * @param winGoldPoints
     * @return  par défaut pour le moment , retourne winGoldPoints , c a d  le joueur décide de gagner tous ses points
     *            et de n'en consacrer aucun pour le parcours de sa carte marteau
     */
    public int applyHammerStrategy(int winGoldPoints) {
        return winGoldPoints;
    }

    /**
     * Cette méthode sera appelé automatiquement si le joueur en question a un jeton cerbers et vient
     *            d'obtenir des faveurs mineures
     * @param val1 valeur obtenue par le premier dé , voir commentaire dns la clase GENERAL FACE
     * @param val2 valeur obtenue par le second dé , voir commentaire dns la clase GENERAL FACE
     * @return 1 pr utiliser le jeton cerbers, 0 pr ne pas l'utiliser
     */
    public int useTokenCerberus(int val1, int val2) {
        return 0;
    }

    /**
     * Cette méthode sera appelé automatiquement si le joueur en question a un jeton cerbers et vient
     *            d'obtenir une faveur mineur
     * NB : Juste implémenter la méthode selon la stratégie , pas se soucier de l'appel (déja géré)
     * @param val1 valeur obtenue par le dé , voir commentaire dns la clase GENERAL FACE
     * @return 1 pr utiliser le jeton cerbers, 0 pr ne pas l'utiliser
     */
    public int useTokenCerberus(int val1) {
        return 0;
    }

}
