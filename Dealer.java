/**
 * Реализовать Dealer как Singleton
 */
interface DealerInt {
    // максимально 5 карт
    //Card[] dealerHand = new Card[5];

    void giveCard(Player player);

    void takeCard();

}


// Singleton
class Dealer implements  DealerInt {
    private Dealer() {}

    // simple singleton
    public static final Dealer INSTANCE = new Dealer();

    static Card[] dealerHand = new Card[5];
    static int cardsCount = 0;

    public void giveCard(Player player) {
        // add Card in hand
        player.hand[player.cardsCount] = new Card();
        // score++
        player.score += player.hand[player.cardsCount].getScore();
        player.cardsCount++;
    }

    public void takeCard() {
        dealerHand[cardsCount++] = new Card();
    }





}
