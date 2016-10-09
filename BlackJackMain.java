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

        // Bets
        System.out.println("\nBets:");
        for (int i = 0; i < playersCount; i++) {
            System.out.println("Hello, " + Main.players[i].name + ". How much money you would like to bet?");
            Main.players[i].Bet(sc.nextInt());
            System.out.println("Nice, you bet " + Main.players[i].bet + "$");

        }
        System.out.println("All bets are accepted");
        // 1 card for dealer
        // ATTENTION! It's important to .takeCard() go before .giveCard()
        inst.takeCard();

        // Dealer give all 2 cards, and 1 for himself
        for (int i = 0; i < playersCount; i++) {
            inst.giveCard(Main.players[i]);
            inst.giveCard(Main.players[i]);
        }


        // show all statistics.
        Main.showCards();

        // // TODO: 08.10.16 there all need to test
        for (int i = 0; i < playersCount; i++) {
            if (Main.players[i].score < 20) {

                System.out.println("statistic: ");
                // может иногда случаться лишний вывод
                Main.showCards();
                System.out.println("Hello, " + Main.players[i].name + " , now you can take card");
                System.out.println("Now you can take one more card(Y), or no(N)");
                String s = "";
                while ((!s.equals("N") | Main.players[i].score < 20) & (Main.players[i].cardsCount < 6)) {
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
        while (inst.score < 17 & inst.cardsCount < 6) {
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
        }   // end for

        System.out.println("GG");

        System.out.println();
        // метод вывода банков, ставок всех игроков.

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
                break;
            }
            case 3: {
                Main.players = new Player[6];
                Main.playersCount = 0;
                break;
            }
        }

    }

    }
}
