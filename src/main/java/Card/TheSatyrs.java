package Card;

import Faces.DiceFaces;
import Player.Bot;
import Player.HerosInventory;

/* Cette carte "Les satyres" permet au joueur qui l'achete de choisir 2 faces parmi les faces de des des
   autres joueurs après que ceux ci aient relancer tous leurs deux dés
   Elle procure 6 points de gloire à la fin de la partie
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
*/

public class TheSatyrs extends Card{
    
    public TheSatyrs(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.gloryPoints = 6;
        this.type = "M";
        this.TypeCard = "I";
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 3;
        this.portail = 2;
        this.needs = 1;
    }
    
    @Override
    public void actionCard(HerosInventory inventory,Bot... nb) /* prend en param les autres joueurs */
    {
        inventory.IncreaseGloryPoints(6);
        /* Tableau de face qui contiendra les faces des lancers des autres joueurs */
        DiceFaces tabFace[] = new DiceFaces[nb.length*2];
        int a = 0;
        int compteur = 0;
        for(a=0;a<nb.length;a++) /* Tous les joueurs relancent leurs faces */
        {
            tabFace[compteur] = nb[a].getFirstDice().rollDice();
            compteur = compteur + 1;
            tabFace[compteur] = nb[a].getSecondDice().rollDice();
            compteur = compteur + 1;
        }
        
        /* choisit pour l'instant par defaut la 1ere et la derniere face */
        inventory.increaseInventoryWithDiceRoll(tabFace[0]);
        inventory.increaseInventoryWithDiceRoll(tabFace[(nb.length*2)-1]);      
    } 
} 
