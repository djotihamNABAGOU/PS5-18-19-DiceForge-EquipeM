package PlayerStrategy;

import Faces.GeneralFace;
import Faces.SanctuarysFaces;
import Player.Bot;
import diceforge.Temple;

import java.util.ArrayList;
import java.util.Random;

public class Strategy {
    private final Bot bot;//Bot auquel s'applique la stratégie
    private boolean supActionDone = false;

    /**
     * Constructeur de la classe Strategy
     * @param bot Bot auquel s'applique la stratégie
     */
    public Strategy(Bot bot) {
        this.bot = bot;
    }

    /**
     * méthode qui applique la stratégie du bot
     * @param temple qui servira à réaliser des exploits
     */
    public void apply(Temple temple, int numberOfTheBot, int actionNumber) {
        //Choix de l'action à effectuer (forge ou exploit)
        Random random = new Random();
        int choice = random.nextInt(2); // 0 pour forge et 1 pour exploit

        switch (choice){
            case 0://forge
                if (supActionDone == false) System.out.println("*ACTION "+actionNumber+" FOR BOT NUMBER "+numberOfTheBot+": FORGE");
                else System.out.println("**SUP ACTION FOR BOT NUMBER "+numberOfTheBot+": FORGE");

                SanctuarysFaces face = randomFaceToBuy(bot,temple);
                if(face.getPrice()!=0){
                    if(temple.buyFace(face)){
                        randomForgeDice(face);
                        bot.getHerosInventory().DecreaseGoldPoints(face.getPrice());
                    }
                    else{
                        System.out.println("Purchase failed");
                    }
                }
                //Fin forge, Action supplémentaire si joueur actif
                if (bot.getHerosInventory().getSunPoints()>=2 && bot.isActive() && supActionDone == false){//il a les conditions requises pour effectuer une action supplémenatire
                    int choiceSupAction = random.nextInt(2); // 0 pour oui et 1 pour non
                    if (choiceSupAction == 0){//On choisit alors quelle action supplémentaire effectuer
                        supActionDone = true;
                        apply(temple,numberOfTheBot,actionNumber);//On réappelle la fonction pour éviter de la duplication de code
                        supActionDone = false;
                    }
                }
                break;

            case 1://exploit
                if (supActionDone == false) System.out.println("*ACTION "+actionNumber+" FOR BOT NUMBER "+numberOfTheBot+": FEAT(Forge)");
                else System.out.println("**SUP ACTION FOR BOT NUMBER "+numberOfTheBot+": FEAT(Forge)");

                System.out.println("No implemantation for now");
                break;
        }
    }


    /**
     * Permet au bot de remplacer une face de son dé avec une nouvelle face venant du sanctuaire
     * @param face de remplacement
     */
    public void randomForgeDice(GeneralFace face){

        Random randomFace = new Random();
        int numberOfFace = randomFace.nextInt(6); //Random pour prendre la face a enlever
        Random randomDice = new Random();
        int numberOfDice = randomDice.nextInt(2)+1; //Random pour prendre le dé sur lequel il faut forger

        if(numberOfDice==1){ //Premier dé
            System.out.println("FORGE ON FIRST DICE");
            //System.out.println(bot.getFirstDice().getFaces()[numberOfFace].toString());
            bot.getRemovedFaces().add(bot.getFirstDice().getFaces()[numberOfFace]); //Ajout dans la liste des faces enlevées
            bot.getFirstDice().setFaces(face,numberOfFace);
        }else{ // Second dé
            System.out.println("FORGE ON SECOND DICE");
            bot.getRemovedFaces().add(bot.getSecondDice().getFaces()[numberOfFace]);
            bot.getSecondDice().setFaces(face,numberOfFace);
        }
    }

    /**
     * retourne le nom de la face à payer choisie au hasard selon les ressources disponibles pour payer la face
     * @param bot utilisé pour avoir accès à l'inventaire du bot
     * @param temple ustilisé pour rechercher les faces disponibles
     * @return la face à payer choisie au hasard
     * en gros, on stocke les faces du sanctuaire disponibles dans une liste FacesAvailable puis on choisit au hasard la face à retourner
     */
    private SanctuarysFaces randomFaceToBuy(Bot bot, Temple temple){
        int v = bot.getHerosInventory().getGoldPoints();
        ArrayList<SanctuarysFaces> FacesAvailable = new ArrayList<>();
        for (int i=0; i<temple.getSanctuary().size();i++){
            if(!temple.getSanctuary().get(i).isSelected() && !FacesAvailable.contains(temple.getSanctuary().get(i)) && v>=temple.getSanctuary().get(i).getPrice()){
                FacesAvailable.add(temple.getSanctuary().get(i));
            }
        }

        Random randomFace = new Random();

        if(FacesAvailable.size() == 0) return new SanctuarysFaces();
        else{
            int faceToReturn = randomFace.nextInt(FacesAvailable.size()); // initialisation
            //System.out.println("La face payée est "+FacesAvailable.get(caseFace).toString());
            return FacesAvailable.get(faceToReturn);
        }

    }
}
