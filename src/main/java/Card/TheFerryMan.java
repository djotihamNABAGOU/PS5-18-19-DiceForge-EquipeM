package Card;
import Faces.GeneralFace;
import Player.Bot;
import diceforge.Temple;
import java.util.ArrayList;

/* Cette carte "Le passeur" permet tout simplement de gagner 12 points de gloire
   Type Immediat ----> Methode "ActionCard" appelé à l'achat puis Suppression de la Carte
   { Par défaut cette carte n'appartient à "aucun effet" et ne fait que remporter des GloryPoints }
*/

public class TheFerryMan extends Card{
    
    public TheFerryMan(int amount) /* prend en parametre le nombre de joueurs */
    {
        this.name = "TheFerryMan";
        this.gloryPoints = 12;
        this.type = "M";
        this.TypeCard = "I";   /* I pour désigner immédiat */
        if(amount==2 || amount ==4) /* S'il y'a 3 joueurs , il restera une carte qui ne sera jamais utilisé*/
            this.amount = amount;
        else this.amount = 3;
        this.price = 4;
        this.portail = 3;
    }
    
    @Override
    public void actionCard(Temple temple,Bot bot,int numBot,ArrayList<GeneralFace>[] listFaces,ArrayList<Bot> listBot){
        bot.getHerosInventory().IncreaseGloryPoints(12);
    }
    
}
