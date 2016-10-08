/**
 * Варианты поля BlackJack добавить как enumerate
 */
interface Players {

    void Bet(int bet);


    // массив Card[] hand;


}

class Player implements Players {

    enum BlackJack  {GOOD_BLACKJACK, SAFE_BLACKJACK, BLACKJACK }

    String name;
    Integer money;
    Integer score;
    Integer bet;


    Card[] hand = new Card[5];
    int cardsCount;

    // Constructor
    public Player(String name) {
        money = 1000;
        score = 0;
        bet = 0;
        this.name = name;
        cardsCount = 0;
    }

    public void Bet(int bet) {
        this.bet = bet;
        money -= bet;
    }

}
