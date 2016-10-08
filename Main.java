

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
                System.out.println(currentCard.getSuit() + " " + currentCard.getFace() );
            }
            System.out.print("score: ");

            if (p.aces != 0 && p.score < 21)
                System.out.println(p.score + " (" + (p.score - (p.aces * 10)) + ")");

            else
                System.out.println(p.score);

            System.out.println();
        }



        System.out.println("\nDealer's: ");

        Dealer instance = Dealer.INSTANCE;
        for (int z = 0; z < instance.cardsCount; z++) {
            Card dealerCard = instance.dealerHand[z];
            System.out.println(dealerCard.getSuit() + " " + dealerCard.getFace());
        }
        if (instance.aces != 0 && instance.score < 21)
            System.out.println(instance.score + " (" + (instance.score - (instance.aces * 10)) + ") ");

        else
            System.out.println(instance.score);

        System.out.println("\n");
    }

}
