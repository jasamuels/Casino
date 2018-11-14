package io.zipcoder.casino;

import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.cardgames.individualcardgames.BlackJack;
import io.zipcoder.casino.game.cardgames.individualcardgames.GoFish;
import io.zipcoder.casino.game.cardgames.individualcardgames.HiLo;
import io.zipcoder.casino.game.chancegames.Roulette;
import io.zipcoder.casino.game.chancegames.Slots;
import io.zipcoder.casino.game.dicegames.individualdicegames.Craps;
import io.zipcoder.casino.game.interfaces.Game;

public class Casino {
    Player player;
    Game game;
    int choice;
    boolean playFlag;

    public Casino() {
        player = new Player();
    }

    public void startCasino() {

        printWelcome();
        askForName();
        askForBank();
        chooseGame();
    }


    public void printWelcome(){
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.print("  /$$$$$$   /$$$$$$   /$$$$$$  /$$$$$$ /$$   /$$  /$$$$$$ \n" +
                " /$$__  $$ /$$__  $$ /$$__  $$|_  $$_/| $$$ | $$ /$$__  $$\n" +
                "| $$  \\__/| $$  \\ $$| $$  \\__/  | $$  | $$$$| $$| $$  \\ $$\n" +
                "| $$      | $$$$$$$$|  $$$$$$   | $$  | $$ $$ $$| $$  | $$\n" +
                "| $$      | $$__  $$ \\____  $$  | $$  | $$  $$$$| $$  | $$\n" +
                "| $$    $$| $$  | $$ /$$  \\ $$  | $$  | $$\\  $$$| $$  | $$\n" +
                "|  $$$$$$/| $$  | $$|  $$$$$$/ /$$$$$$| $$ \\  $$|  $$$$$$/\n" +
                " \\______/ |__/  |__/ \\______/ |______/|__/  \\__/ \\______/");
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.print("Welcome to the Casino");
    }

    public void askForName() {
        player.setName(Console.printAndTakeString("Enter your name:"));
    }

    public void printMenu() {
        Console.print("Choose a game! Enter the option number (ex. 1 for Blackjack)"
                + "\nPlayer Bank: "
                + player.getBank()
                + "\n1: Blackjack\n2: Hi Lo\n3: Roulette"
                + "\n4: Craps\n5: Slots\n6: Go Fish\n7: Quit Casino");
    }

    public void askForBank() {

        int userBank = Console.printAndTakeInt("Enter the amount you want to start playing with (bank) :");
        if (!player.checkInitialBank(userBank)) {
            Console.print("Didn't quite catch that, here's 10k Zimbabwean dollars to play.");
            player.setBank(10000);
        } else {
            player.setBank(userBank);
        }
    }


    private void chooseGame() {
        boolean playGame = true;
        while (playGame) {
            printMenu();
            choice = Console.getIntegerInput();
            switch (choice) {
                case 1:
                    game = new BlackJack(player);
                    game.play();
                    break;

                case 2:
                    game = new HiLo(player);
                    game.play();
                    break;

                case 3:
                    game = new Roulette(player);
                    game.play();
                    break;

                case 4:
                    game = new Craps(player);
                    game.play();
                    break;

                case 5:
                    game = new Slots(player);
                    game.play();
                    break;

                case 6:
                    game = new GoFish(player);
                    game.play();
                    break;

                case 7:
                    System.exit(1);

                default:
                    Console.print("Incorrect choice. Choose the listed options");
                    continue;
            }

            Console.print("\n\n\n\n\n♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣\n"
                    + "Welcome back to the Casino Menu!");
            }
        }
    }


