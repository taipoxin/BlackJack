import java.util.Scanner;

public class BlackJackMain {
    public static void main(String[] args) {
        Game();
    }

    // Start Game
    private static void Game() {

        while (Main.lifecycle) {

            // Dealer (Singleton)
            Dealer inst = Dealer.INSTANCE;
            Main.deletePlayers();

            if (Main.canPlay == 0 & Main.players[0] != null) {
                System.out.println("Nobody can play");
                break;
            }

            // Console input
            Scanner sc = new Scanner(System.in);
            int playersCount = Main.playersCount;

            if (Main.playersCount == 0) {

                System.out.println("Enter  count of players");
                Main.playersCount = sc.nextByte();
                // чтобы все время не обращаться в поле Main.playersCount
                playersCount = Main.playersCount;
                sc.skip("\n");


                String name;

                // Enter players
                for (int i = 0; i < playersCount; i++) {
                    System.out.println("Enter player name");
                    name = sc.nextLine();
                    // create new Player
                    Main.players[i] = new Player(name);
                }
            }
            Main.showBank();

            // Bets
            System.out.println("\nBets:");
            for (int i = 0; i < playersCount; i++) {
                if (!Main.players[i].bkr) {

                    do {
                        System.out.println("Hello, " + Main.players[i].name + ". How much money you would like to bet?");
                        Main.players[i].Bet(sc.nextInt());
                    }
                    while (Main.players[i].bet > Main.players[i].money | Main.players[i].bet <= 0);
                    Main.players[i].money -= Main.players[i].bet;

                    System.out.println("Nice, you bet " + Main.players[i].bet + "$");
                }

            }
            System.out.println("All bets are accepted\n");
            // 1 card for dealer
            // ATTENTION! It's important to .takeCard() go before .giveCard()
            inst.takeCard();

            // Dealer give all 2 cards, and 1 for himself
            for (int i = 0; i < playersCount; i++) {
                inst.giveCard(Main.players[i]);
                inst.giveCard(Main.players[i]);
            }


            // show all statistics.
            // protect double showStatistics
            if (Main.players[0].score > 19)
                Main.showCards();

            // // TODO: 08.10.16 there all need to test
            for (int i = 0; i < playersCount; i++) {
                if (Main.players[i].score < 20 & !Main.players[i].bkr) {

                    System.out.println("statistic: ");
                    Main.showCards();
                    System.out.println("Hello, " + Main.players[i].name + " , now you can take card");
                    System.out.println("Now you can take one more card(Y), or no(N)");
                    String s = "";
                    while ((!s.equals("N") | Main.players[i].score < 20) & (Main.players[i].cardsCount < 5)) {
                        while (!s.equals("Y") & !s.equals("N")) {
                            s = sc.nextLine();
                            s = s.toUpperCase();
                        }
                        if (s.equals("N")) {
                            System.out.println("OK");
                            s = "";
                            break;
                        }
                        if (s.equals("Y")) {
                            inst.giveCard(Main.players[i]);
                            Main.showCards();
                            if (!(Main.players[i].score < 20)) {
                                s = "";
                                break;
                            }

                            System.out.println("Take another card?");
                            s = "";
                        }
                    }
                } else {
                    System.out.println("Hi, " + Main.players[i].name + " , you already have enough score");
                }
            }

            // Dealer
            while (inst.score < 17 & inst.cardsCount < 5) {
                inst.takeCard();
                System.out.println("Dealer take card: ");
                System.out.println("Dealer score is: " + inst.score);
            }
            Main.showCards();


            // Results
            for (int i = 0; i < playersCount; i++) {
                Player p = Main.players[i];

                switch (p.bj) {
                    case GOOD_BLACKJACK: {
                        p.money += 2 * p.bet;
                        p.bet = 0;
                        continue;
                    }
                    case SAFE_BLACKJACK: {
                        p.money += 2 * p.bet;
                        p.bet = 0;
                        continue;
                    }
                    case BLACKJACK: {
                        if (inst.score != 21) {
                            p.money += 2.5 * p.bet;
                            p.bet = 0;
                            continue;
                        } else {
                            p.money += p.bet;
                            p.bet = 0;
                            continue;
                        }
                    }
                    case NULL: {
                        if (p.score < 22 && inst.score > 21) {
                            p.money += 2 * p.bet;
                            p.bet = 0;
                            continue;
                        }


                        if (p.score < 22 && inst.score < 22) {
                            if (p.score > inst.score) {
                                p.money += 2 * p.bet;
                                p.bet = 0;
                                continue;
                            }
                            if (p.score == inst.score) {
                                p.money += p.bet;
                                p.bet = 0;
                                continue;
                            } else {
                                p.bet = 0;
                                continue;
                            }
                        } else if (p.score > 21 && inst.score < 22) {
                            p.bet = 0;
                            continue;
                        }
                    }   // end case
                }   // end switch
                p.bet = 0;
            }   // end for

            System.out.println("GG");

            System.out.println();
            // метод вывода банков, ставок всех игроков.
            Main.showBank();

            //
            System.out.println("Now we can: ");
            System.out.println("1:\t End game");
            System.out.println("2:\t Restart game with old players");
            System.out.println("3:\t Change players and play");
            System.out.println("What are you choose?");
            int choice = 0;
            while (choice != 1 & choice != 2 & choice != 3)
                choice = sc.nextByte();

            switch (choice) {
                case 1: {
                    Main.lifecycle = false;
                    break;
                }
                case 2: {
                    for (int i = 0; i < playersCount; i++) {
                        Player p = Main.players[i];
                        p.hand = new Card[5];
                        p.score = 0;
                        p.bet = 0;
                        p.aces = 0;
                        p.cardsCount = 0;
                        p.bj = Player.BlackJack.NULL;

                    }
                    Dealer.INSTANCE.dealerHand = new Card[5];
                    Dealer.INSTANCE.cardsCount = 0;
                    Dealer.INSTANCE.score = 0;
                    Dealer.INSTANCE.aces = 0;
                    break;
                }
                case 3: {
                    Main.players = new Player[6];
                    Main.playersCount = 0;
                    Dealer.INSTANCE.dealerHand = new Card[5];
                    Dealer.INSTANCE.cardsCount = 0;
                    Dealer.INSTANCE.score = 0;
                    Dealer.INSTANCE.aces = 0;
                    break;
                }
            }
        }

    }
}
