package PlayerStrategy;

import Player.Bot;
import diceforge.Temple;

/**
 * ici la stratégie est très simple le bot ne fait aucune action, donc ne fait que récolter les ressources
 */
public class NothingStrategy extends Strategy {

    public NothingStrategy(Bot bot) {
        super(bot);
    }

    @Override
    public void apply(Temple temple, int numberOfTheBot, int actionNumber) {
        if (bot.isActive()) {
            //1->Choix d'appel des renforts, LE JOUEUR ACTIF PEUT APPELER DES RENFORTS
            int choice = 1; // 0 pour oui et 1 pour non
            switch (choice) {
                case 1:
                    break;
            }

            //2->choix de faire une action, LE JOUEUR ACTIF PEUT EFFECTUER UNE ACTION
            choice = 1; // 0 pour oui et 1 pour non
            switch (choice) {
                case 1:
                    break;

            }

        }
    }
}