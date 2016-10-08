

// Static Main class
class Main {

    static Player[] players = new Player[6];
    static byte playersCount = 0;
    static boolean lifecycle = true;


    static void showCards() {
        for (int i = 0; i < playersCount; i++) {
            Player p = players[i];
            System.out.println(p.name + ": ");

            for (int card = 0; card < p.cardsCount; card++) {
                Card currentCard = p.hand[card];
                System.out.println(currentCard.getSuit() + " " + currentCard.getFace());

            }
            System.out.println(p.score);
        }
    }

}
