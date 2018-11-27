package PlayerStrategy;

import Player.Bot;
import diceforge.Temple;

public class Strategy {
    protected final Bot bot;//Bot auquel s'applique la stratégie

    /**
     * Constructeur de la classe Strategy
     * @param bot Bot auquel s'applique la stratégie
     */
    public Strategy(Bot bot) {
        this.bot = bot;
    }

    public void apply(Temple temple, int numberOfTheBot, int actionNumber){
        System.out.println("I am General Strategy, please implement effect in subclasses");
    }
}