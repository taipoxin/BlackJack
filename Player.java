/**
 * Варианты поля BlackJack добавить как enumerate
 */
interface Players {

    void Bet(int bet);


    // массив Card[] hand;


}

class Player implements Players {

    enum BlackJack  {GOOD_BLACKJACK, SAFE_BLACKJACK, BLACKJACK, NULL }

    String name;
    double money;
    int score;
    int bet;
    int aces;
    BlackJack bj = BlackJack.NULL;


    Card[] hand = new Card[5];
    int cardsCount;

    // Constructor
    public Player(String name) {
        money = 1000;
        score = 0;
        bet = 0;
        this.name = name;
        cardsCount = 0;
        aces = 0;
    }

    public void Bet(int bet) {
        this.bet = bet;
        money -= bet;
    }

}
