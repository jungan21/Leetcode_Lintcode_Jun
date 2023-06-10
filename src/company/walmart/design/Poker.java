package company.walmart.design;

class PokerCard{
    public PokerCard(int val, int i, int j){

    }
}

public class Poker {
    private static Poker instance;
    private PokerCard[] cards;

    private Poker() {
    }

    private Poker(PokerCard[] cards) {
        this.cards = cards;
    }

    static Poker getInstance() {
        if (instance == null) {
            instance = new Poker(new PokerCard[54]);
            int index = 0;
            for (int val = 0; val < 15; val++) {
                if (val == 0) {
                    instance.cards[index++] = new PokerCard(val, 0, 0);
                } else if (val < 14) {
                    for (int type = 1; type <= 4; type++) {
                        instance.cards[index++] = new PokerCard(val, type % 2, type);
                    }
                } else { // val == 14
                    instance.cards[index++] = new PokerCard(val, 1, 0);
                }
            }
        }
        return instance;
    }

    public PokerCard[] getCards() {
        return this.cards;
    }
}
