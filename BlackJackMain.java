import java.util.Scanner;

public class BlackJackMain {
    public static void main(String[] args) {
        Game();
    }

    // Start Game
    private static void Game() {
        // Dealer (Singleton)
        Dealer inst = Dealer.INSTANCE;

        // Console input
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter  count of players");
        Main.playersCount =  sc.nextByte();
        // чтобы все время не обращаться в поле Main.playersCount
        int playersCount = Main.playersCount;
        sc.skip("\n");




        String name;

        // Enter players
        for (int i = 0; i < playersCount; i++) {
            System.out.println("Enter player name");
            name = sc.nextLine();
            // create new Player
            Main.players[i] = new Player(name);
        }

        // Bets
        System.out.println("\nBets:");
        for (int i = 0; i < playersCount; i++) {
            System.out.println("Hello, " + Main.players[i].name + ". How much money you would like to bet?");
            Main.players[i].bet = sc.nextInt();
            System.out.println("Nice, you bet " + Main.players[i].bet + "$");

        }
        System.out.println("All bets are accepted");

        // Dealer give all 2 cards, and 1 for himself
        for (int i = 0; i < playersCount; i++) {
            inst.giveCard(Main.players[i]);
            inst.giveCard(Main.players[i]);
        }

        inst.takeCard();

        Main.showCards();








    }
}
