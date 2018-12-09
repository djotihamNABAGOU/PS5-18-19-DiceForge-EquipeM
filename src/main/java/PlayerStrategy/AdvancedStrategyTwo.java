package PlayerStrategy;


import Card.Card;
import Faces.GeneralFace;
import Faces.Sanctuary.SanctuarysFaces;
import GameStrategy.CrazyPasserStrategy;
import GameStrategy.GoldenSubstitution;
import GameStrategy.MoonSubstitution;
import GameStrategy.SunSubstitution;
import Player.Bot;
import diceforge.Island;
import diceforge.Temple;
import java.util.ArrayList;



public class AdvancedStrategyTwo extends Strategy {  

    int compteur;
    private int numIslandStrategy;   // numero de la stratégy ILE à appliquer
    private int numSanctuaryStrategy; // numero de la stratégy FORGE à appliquer
    
    // STrategy pr le payement des cartes
    private CrazyPasserStrategy firstStrategy;   // 1ERE Strategie de jeu
    
    // Strategy de base pour la forge des dés
    private GoldenSubstitution goldenSubstitution;
    private MoonSubstitution moonSubstitution;
    private SunSubstitution sunSubstitution;
    
         
    public AdvancedStrategyTwo(Bot bot) {
        super(bot);
        goldenSubstitution = new GoldenSubstitution();
        moonSubstitution = new MoonSubstitution();
        sunSubstitution = new SunSubstitution();
        firstStrategy = new CrazyPasserStrategy();
        numIslandStrategy = 0;
        numSanctuaryStrategy = 0;
        compteur = 0;
    }
    
    // Cherche les faces qui procurent seulement un seul type de ressource bien déterminé sur le dé
    private ArrayList<GeneralFace>  searchFaceDice(ArrayList<GeneralFace> list,int a,int b,int c){
        ArrayList<GeneralFace> listFace = new ArrayList<>();
        for(GeneralFace face : list){
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
     * @param FacesAvailable
     * @return la face à payer choisie au hasard
     *
     */
    @Override
    public SanctuarysFaces FaceToBuy(ArrayList<SanctuarysFaces> FacesAvailable) {

        
        if(FacesAvailable.size()!=0){
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

        
        if(((compteur<2) || ((compteur>3) && (compteur<6)) || (compteur>7 )) 
                  && (goldenSubstitution.getRep()!=5) && (!goldenFace.isEmpty())){
            
                compteur = compteur + 1;
                return goldenSubstitution.choiceGoldFace(goldenFace);
            
        }else{
            
            numSanctuaryStrategy = 1;
            if((sunSubstitution.getRep()<=moonSubstitution.getRep()) && (sunSubstitution.getRep()!=2)){
                if(!sunFace.isEmpty()){
                     return sunSubstitution.choiceSunFace(sunFace);
                }              
            }else if(moonSubstitution.getRep()!=2){
                if(!moonFace.isEmpty()){
                     compteur = compteur + 1;
                     return moonSubstitution.choiceMoonFace(moonFace);
                }
            }
        }
        
        if(compteur==9){
            // Arrivé ici , 
            
         
                    
        }
      }
        
        
      return new SanctuarysFaces();
    }
    
    private int actionOrForge(Temple temple,Island island){
        
        int choice = 0; // forge par défaut : 1 pour ILE
        ArrayList<Card> potentialCardsToBuy =  potentialCardsToBuy(bot, island);
   
        
        if(!potentialCardsToBuy.isEmpty()){
            
            // Application de la première stratégie si elle est tjr possible
            if(firstStrategy.getLive()!=-1){
                boolean rep = firstStrategy.isApply();
                if(rep==true){
                    numIslandStrategy = 0;
                    choice = 1; // direction ILE
                }else{
                    rep = firstStrategy.checkAvailableAction(potentialCardsToBuy, bot);
                    if(rep == true){
                        numIslandStrategy = 0;
                        choice = 1;  // direction ILE
                    }
                }            
            }else{
                numIslandStrategy = 1;  // Strategy suivante
            }      
            
            
            // Applications des autres strategies ...... 
        }
       
        return choice;
    }
    
    
    
    @Override
    public int throwWhichDice() {
        int one = 0;
        int two = 0;
        
        for(int a=0;a<6;a++){
           ArrayList<String> oneProperties = bot.getFirstDice().getFaces()[a].getProperties();
           if(Integer.valueOf(oneProperties.get(0)) == 1){
                one = one + 1;
           }
           
           ArrayList<String> twoProperties = bot.getSecondDice().getFaces()[a].getProperties();
           if(Integer.valueOf(twoProperties.get(0)) == 1){
                two = two + 1;
           }
        }
        
        if(one==two){
             return 1;
        }else if(one>two){
            return 1;
        }else 
            return 0;
        
    }
    
    
    
     /**************************************************************************************************/
    /***************************      METHODE PRINCIPALE DE LA CLASSE       **************************/
    /*************************************************************************************************/

    @Override
    public void apply(Temple temple, Island island, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //Seul le joueur actif peut appliquer une stratégie après le lancé des dés
        if (bot.isActive()) {

            //1->Le bot avancé appelle forcément ses renforts
            if (supActionDone == false){ //on ne doit pas appeler des renforts lors d'une action sup
                if (bot.getReinforcementCard().size() != 0) {
                    System.out.println("\t->ENHANCEMENT<-");
                    //il les active dans l'ordre de son choix, et donc ici, il analyse le meilleur ordre
                    callTheReinforcements(temple, bot, numberOfTheBot, listFaces, data);
                }
            }

            //2->LE JOUEUR ACTIF PEUT EFFECTUE UNE ACTION

            //Choix de l'action à effectuer (forge ou exploit), il fait un exploit s'il a assez de ressources, sinon il forge
            int choice = actionOrForge(temple, island);// 0 pour forge et 1 pour exploit


            switch (choice) {
                case 0://forge
                {
                    if (supActionDone == false) {
                        System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FORGE");
                    } else {
                        System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FORGE");
                    }

                    //Tant qu'il a les ressources, il forge plusieurs faces de sanctuaire s"il le veut
                    SanctuarysFaces face;
                    int nbPurchase = 1;//indice de forge
                    forgeHowManyTimes(temple, 0);

                    //Fin forge, Action supplémentaire si joueur actif
                    /*if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        bot.getHerosInventory().IncreaseSunPoints(2);//Il paie
                        supActionDone = true;
                        apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }*/
                }
                break;

                case 1://exploit
                {
                    /**
                     * s'il est ici, c'est qu'il a les ressources pour faire de
                     * l'exploit car l'exploit est privilégié par rapport à la
                     * forge pour maximiser les gains
                     */

                    if (supActionDone == false) {
                        System.out.println("*ACTION OF BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                    } else {
                        System.out.println("**SUP ACTION FOR BOT NUMBER " + numberOfTheBot + ": FEAT(Exploit)");
                    }

                    Card card;
                    ArrayList<Card> potentialCardsToBuy = potentialCardsToBuy(bot, island);
                    if (!(card = bestCardToBuy(potentialCardsToBuy)).getName().equals("")) {
                        feat(card, temple, island, bot, numberOfTheBot, listFaces, data);
                    }

                    //Fin exploit, Action supplémentaire si joueur actif
                    /*if (bot.getHerosInventory().getSunPoints() >= 2 && supActionDone == false) {//il a les conditions requises pour effectuer une action supplémenatire
                        //il l'effectue
                        supActionDone = true;
                        apply(temple, island, numberOfTheBot, listFaces, data);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }*/
                }
                break;
                default:
                    System.out.println("Problem with the potential cards to buy !!!");
            }


        }
    }
    
    
    
    // Cherche les faces qui procurent seulement un seul type de ressource bien déterminé au sanctuaire
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


    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'APPEL DES RENFORTS       *********************/
    /***********************************************************************************************/

    @Override
    public void callTheReinforcements(Temple temple, Bot bot, int numberOfTheBot, ArrayList<GeneralFace>[] listFaces, Bot... data) {
        //ici, il faut privilégier les points de gloire
        int size = bot.getReinforcementCard().size();
        if (size == 3) {
            /*il possède alors les 3 cartes de renforcement, on appliquera en dernier <l'ancien> car
            permet de remporter 4 points de gloire, et pour maximiser les chances de l'avoir,
            on appliquera les effets des 2 premières cartes afin de posséder un nombre de points de gold suffisant.
             */
            int theFormerIndex = 0;
            for (int i = 0; i < size; i++) {
                if (bot.getReinforcementCard().get(i).getName().equals("TheFormer")) theFormerIndex = i;
                else bot.getReinforcementCard().get(i).capacity(temple, bot, numberOfTheBot, listFaces, data);
            }
            bot.getReinforcementCard().get(theFormerIndex).capacity(temple, bot, numberOfTheBot, listFaces, data);
        }
    }
    
    
    
    /************************************************************************************************/
    /*********************       METHODES CONCERNANT LA FORGE         ******************************/
    /***********************************************************************************************/

    /**
     * Permet au bot de remplacer une face de son dé avec une nouvelle face venant du sanctuaire
     * Mais il faudra qu'il enlève les faces les moins avantageuses
     *
     * @param face de remplacement
     */
    @Override
    public void ForgeDice(GeneralFace face) {
        
        int numberOfDice = 1;  // le dé 1 par défaut
        int numberOfFace = 0;  // Face 1 par défaut
        
        // choix du dé et de la face en fonction de la statégie de substitution en cours
        
        if(numSanctuaryStrategy==0){
            numberOfFace = goldenSubstitution.faceRemove(bot.getFirstDice());
        } else if(numSanctuaryStrategy==1){
            numberOfDice = 2;
            numberOfFace = sunSubstitution.faceRemove(bot.getSecondDice());
        }
     
        
        
        
        // Fin 
        
        if (numberOfDice == 1) { //Premier dé
            System.out.println("\tFORGE ON FIRST DICE");
            System.out.println("\tFACE OUT: " + bot.getFirstDice().getFaces()[numberOfFace].toString());
            System.out.println("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getFirstDice().getFaces()[numberOfFace]); //Ajout dans la liste des faces enlevées
            bot.getFirstDice().setFaces(face, numberOfFace);
        } else { // Second dé
            System.out.println("\tFORGE ON SECOND DICE");
            System.out.println("\tFACE OUT: " + bot.getSecondDice().getFaces()[numberOfFace].toString());
            System.out.println("\tFACE IN: " + face.toString());
            bot.getRemovedFaces().add(bot.getSecondDice().getFaces()[numberOfFace]);
            bot.getSecondDice().setFaces(face, numberOfFace);
        }
    }
    
    
    /************************************************************************************************/
    /*********************       METHODES CONCERNANT L'ILE  👌          ******************************/
    /***********************************************************************************************/
    
    
    private Card bestCardToBuy(ArrayList<Card> potentialCardsToBuy) {
        
        Card card = new Card();
        if(numIslandStrategy==0){
            int index = firstStrategy.whichCard(potentialCardsToBuy);
            card = potentialCardsToBuy.get(index);
        }
        return card;
    }
}
