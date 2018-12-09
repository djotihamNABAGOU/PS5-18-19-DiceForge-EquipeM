package PlayerStrategy;

import Card.Card;
import Card.Reinforcement;
import Faces.GeneralFace;
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
    protected ArrayList<Integer> bassin;
    

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
     * l'exploit consitera à appliquer en premier lieu l'action de la carte, celle-ci consiste tout simplement à augmenter
     * les points de gloire du joueur conformément aux points de gloire qu'offrent la carte
     * @param card
     * @param temple
     * @param bot
     * @param numBot
     * @param listFaces
     * @param tabBot
     */
    public void feat(Card card, Temple temple, Island island, Bot bot, int numBot, ArrayList<GeneralFace>[] listFaces, Bot... tabBot) {

        if (island.buyCard(card, temple, numBot, listFaces, tabBot)) {
            //on effectue alors l'exploit
            switch (card.getType()) {
                case "R":
                    Reinforcement reinforcement = (Reinforcement) card;
                    reinforcement.actionCard(temple, bot, numBot, listFaces, tabBot);
                    bot.addReinforcementEffectCard(reinforcement);
                    break;
                case "A":
                    Reinforcement automatic = (Reinforcement) card;
                    automatic.actionCard(temple, bot, numBot, listFaces, tabBot);
                    bot.addAutomaticEffectCard(automatic);
                    break;
                case "I":
                    card.actionCard(temple, bot, numBot, listFaces, tabBot);
                    bot.addImmediateEffectCard(card);
                    break;
                case "NULL":
                    card.actionCard(temple, bot, numBot, listFaces, tabBot);
                    bot.addWithoutEffectCard(card);
                    break;
                default:
                    System.out.println("Unknown type of card !!!");
            }
            //on procède au paiement
            if (card.getType().equals("M")) bot.getHerosInventory().DecreaseMoonPoints(card.getPrice());
            if (card.getType().equals("S")) bot.getHerosInventory().DecreaseSunPoints(card.getPrice());
            if (card.getType().equals("M+S")) {
                bot.getHerosInventory().DecreaseMoonPoints(5);
                bot.getHerosInventory().DecreaseSunPoints(5);
            }
        } else {
            System.out.println("Purchase failed");
        }
    }

    /**
     * Méthode qui détermine les cartes accessibles au bot en fontion de ses ressources
     * @param bot
     * @param island
     * @return
     */
    public ArrayList<Card> potentialCardsToBuy(Bot bot, Island island) {
        int sun = bot.getHerosInventory().getSunPoints();
        int moon = bot.getHerosInventory().getMoonPoints();
        ArrayList<Card> potentialCardsToBuy = new ArrayList<>();
        ArrayList<Card> availableCards = island.availableCards();
        for (int a = 0; a < availableCards.size(); a++) {
            switch (availableCards.get(a).getType()) {
                case "S":
                    if (sun >= availableCards.get(a).getPrice()) {
                        potentialCardsToBuy.add(availableCards.get(a));
                    }
                    break;
                case "M":
                    if (moon >= availableCards.get(a).getPrice()) {
                        potentialCardsToBuy.add(availableCards.get(a));
                    }
                    break;
                case "M+S"://il n'ya qu'un seul type de carte dont le type du prix est à la fois M et S
                    if (sun >= 5 && moon >= 5) {
                        potentialCardsToBuy.add(availableCards.get(a));
                    }
                    break;
                default:
                    System.out.println("Unknown type of price's card");
            }
        }
        return potentialCardsToBuy;
    }

    /**
     * Méthode permettant de faire une ou plusieurs forges selon les ressources du joueur
     * @param temple
     * @param choice 0 pour plusieurs forges, 1 pour une seule forge
     */
    public void forgeHowManyTimes(Temple temple, int choice) {
        ArrayList<SanctuarysFaces> potentialFaces = potentialFacesToBuy(bot, temple);
        if (choice == 0) {//forge de plusieurs faces
            SanctuarysFaces face;
            int nbPurchase = 1;//indice de forge
            while (!(face = FaceToBuy(potentialFaces)).getName().equals("null")) {
                if (temple.buyFace(face)) {
                    System.out.println("PURCHASE " + nbPurchase);
                    ForgeDice(face);
                    bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                    nbPurchase++;
                    bassin.add(temple.giveMeTheBasin(face));//enregistrement du bassin de la nouvelle face
                } else {
                    System.out.println("Purchase failed");
                }
            }
        } else {//forge d'une seule face
            SanctuarysFaces face;
            if (!(face = FaceToBuy(potentialFaces)).getName().equals("null")) {
                if (temple.buyFace(face)) {
                    ForgeDice(face);
                    bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                    bassin.add(temple.giveMeTheBasin(face));//enregistrement du bassin de la nouvelle face
                } else {
                    System.out.println("Purchase failed");
                }
            }
        }
    }

    /**
     * Méthode permettant de retourner au bot les faces qu'ils peuvent payer
     * en gros, on stocke les faces du sanctuaire disponibles dans une liste FacesAvailable puis on choisit au hasard la face à retourner
     * @param bot
     * @param temple
     * @param bassin
     * @return
     */
    public ArrayList<SanctuarysFaces> potentialFacesToBuy(Bot bot, Temple temple){
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        ArrayList<SanctuarysFaces>[] sanctuary = temple.getSanctuary();
        for (int a = 0; a < 10; a++) {
            
                for (int i = 0; i < sanctuary[a].size(); i++) {
                    if (!sanctuary[a].get(i).isSelected() && !FacesAvailable.contains(sanctuary[a].get(i)) && v >= sanctuary[a].get(i).getPrice()) {
                        int numbassin = temple.giveMeTheBasin(sanctuary[a].get(i));
                        boolean ok = true;
                        for(int b = 0;b<bassin.size();a++){
                            if(numbassin == bassin.get(b))
                                ok = false;
                        }
                        
                        if(ok!=false)
                            FacesAvailable.add(sanctuary[a].get(i));
                    }
                }
            
        }
        return FacesAvailable;
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
     * @param facesAvailable
     * @return
     */

    public SanctuarysFaces FaceToBuy(ArrayList<SanctuarysFaces> facesAvailable) {
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
