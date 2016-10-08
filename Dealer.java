import java.util.Scanner;

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

    Card[] dealerHand = new Card[5];
    int cardsCount = 0;
    int score = 0;
    int aces = 0;

    public void giveCard(Player player) {
        // add Card in hand
        // there was java.lang.ArrayIndexOutOfBoundsException: 5
        player.hand[player.cardsCount] = new Card();
        // checking ace
        if (player.hand[player.cardsCount].getACE()) {
            player.aces++;
        }

        // score++
        player.score += player.hand[player.cardsCount].getScore();
        player.cardsCount++;

        if (player.score > 21 && player.aces != 0)
            player.score -= player.aces * 10;

        // Checking for BlackJack

        if (player.score == 21 & player.cardsCount == 2) {
            if (dealerHand[0].getScore() > 9) {
                System.out.println("Congratulations! " + player.name + " have BlackJack, but dealer may have 21 too");
                System.out.println("you can choose: \n take 1:1 (press N)  or 3:2 (press Y) ");
                String s = "";
                Scanner sc = new Scanner(System.in);
                while (!s.equals("Y") & !s.equals("N")) {
                    s = sc.nextLine();
                    s = s.toUpperCase();
                }
                if (s.equals("Y"))
                    player.bj = Player.BlackJack.BLACKJACK;

                if (s.equals("N"))
                    player.bj = Player.BlackJack.SAFE_BLACKJACK;
            }
            if (dealerHand[0].getScore() < 10) {
                System.out.println("Congratulations! " +  player.name +  " have BlackJack, and dealer cant have it");
                player.bj = Player.BlackJack.GOOD_BLACKJACK;
            }
        }

    }

    public void takeCard() {
        dealerHand[cardsCount] = new Card();
        score+= dealerHand[cardsCount].getScore();
        if (dealerHand[cardsCount].getACE())
            aces++;
        cardsCount++;
        if (score > 21 && aces != 0)
            score -= aces * 10;
    }





}
