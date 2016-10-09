

// Static Main class
class Main {

    static Player[] players = new Player[6];
    static byte playersCount = 0;
    static boolean lifecycle = true;
    static byte canPlay = 0;

    static void deletePlayers() {
        for (int i = 0; i < playersCount; i++) {
            if (players[i].money <= 0) {
                System.out.println("Player " + players[i].name + " hasn't money");
                players[i].bkr = true;
            }
            else {
                canPlay++;
            }
        }
    }

    /**
     * This method show fields "money" and current bets
     */
    static void showBank() {
        for (int i = 0; i < playersCount; i++) {
            Player p = players[i];
            System.out.println(p.name + ": ");
            System.out.println("\t current bet: " + p.bet);
            System.out.println("\t current bank: " + p.money + "\n");
        }
    }


    static void showCards() {
        for (int i = 0; i < playersCount; i++) {
            Player p = players[i];
            if (!p.bkr) {
                System.out.println(p.name + ": ");

                for (int card = 0; card < p.cardsCount; card++) {
                    Card currentCard = p.hand[card];
                    System.out.println(currentCard.getSuit() + " " + currentCard.getFace());
                }
                System.out.print("score: ");

                // KOSTILI POLETELI
                if (p.aces != 0 && p.cardsCount < 3)
                    System.out.println(p.score + " (" + (p.score - (p.aces * 10)) + ")");

                else
                    System.out.println(p.score);

                System.out.println();
            }
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
