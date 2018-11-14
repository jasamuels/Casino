package io.zipcoder.casino.game.dicegames.individualdicegames;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.game.dicegames.diceutilities.Dice;
import io.zipcoder.casino.game.interfaces.Gamble;
import io.zipcoder.casino.game.Player;

public class Craps extends DiceGame implements Gamble {

    private Player player;
    private Dice pairOfDice;
    private int numOfRolls;
    public boolean win;
    public boolean continueGame;
    private int amount;
    public int rollOne;
    public int rollTwo;


    public Craps(Player player){
        this.player = player;
        numOfRolls = 0;
        pairOfDice = createDice(2);
        rollOne = 0;
        rollTwo = 0;
    }

    public int getNumOfRolls(){
        return numOfRolls;
    }

    public Dice getPairOfDice() {
        return pairOfDice;
    }

    public int roll(){
        int sum = pairOfDice.tossAndSum();
        return sum;
    }

    @Override
    public Dice createDice(int numberOfDice) {
        Dice crapsDice = new Dice(numberOfDice);
        return crapsDice;
    }

    @Override
    public int bet(int initialAmount) {
        int returnBet = initialAmount;

        if (win){
            returnBet *= 2;
            win(player, returnBet);
        } else if (!win) {
            loss(player, returnBet);
        }
        return -1;
    }

    @Override
    public void play() {

        continueGame = true;

        Console.print("  _______________________________________________________________ ___________________    _____ __________  _________\n" +
                " /   _____/\\__    ___/\\______   \\_   _____/\\_   _____/\\__    ___/ \\_   ___ \\______   \\  /  _  \\\\______   \\/   _____/\n" +
                " \\_____  \\   |    |    |       _/|    __)_  |    __)_   |    |    /    \\  \\/|       _/ /  /_\\  \\|     ___/\\_____  \\ \n" +
                " /        \\  |    |    |    |   \\|        \\ |        \\  |    |    \\     \\___|    |   \\/    |    \\    |    /        \\\n" +
                "/_______  /  |____|    |____|_  /_______  //_______  /  |____|     \\______  /____|_  /\\____|__  /____|   /_______  /\n" +
                "        \\/                    \\/        \\/         \\/                     \\/       \\/         \\/                 \\/");

        Console.print("               (( _______\n" +
                "     _______     /\\O    O\\\n" +
                "    /O     /\\   /  \\      \\\n" +
                "   /   O  /O \\ / O  \\O____O\\ ))\n" +
                "((/_____O/    \\\\    /O     /\n" +
                "  \\O    O\\    / \\  /   O  /\n" +
                "   \\O    O\\ O/   \\/_____O/\n" +
                "    \\O____O\\/ ))          ))\n" +
                "  ((");

        while(continueGame) {
            Console.print("Player Bank: " + "$" + player.getBank());
            Console.print("Enter the amount you want to bet");
            amount = Console.getIntegerInput();
            Console.print("\n");

            if (player.checkbet(amount)) {
                runGame();
            } else {
                Console.print("You have insufficient funds.\nEnter \"yes\" to continue with a new bet or \"no\" to exit the game");
                String choice = Console.getStringInput();
                if (choice.equals("yes")) {
                    continue;
                } else if (choice.equals("no")) {
                    continueGame = false;
                    break;
                }
            }
            continueGame = endGame();
        }
    }

    public void runGame() {
        firstRoll();
        bet(amount);
    }

    public boolean isWin() {
        return win;
    }

    public void firstRoll() {

        rollOne = roll();
        if (rollOne == 7 || rollOne == 11) {
            Console.print("Your first roll is: " + rollOne);
            Console.print("You Win!! (⌐■_■)");
            win = true;

        } else if (rollOne == 2 || rollOne == 3 || rollOne == 12) {
            Console.print("Your first roll is: " + rollOne);
            Console.print("You Lose ಠ_ಠ");
            win = false;
        } else{
            Console.print("Your first roll is: " + rollOne + ", Rolling again.");
            Console.pause(500/2, " ⚀ ");
            Console.pause(500/2, " ⚁ ");
            Console.pause(500/2, " ⚂ ");
            Console.pause(500/2, " ⚃ ");
            Console.pause(500/2, " ⚄ ");
            Console.pause(500/2, " ⚅ ");
            secondRoll();}
    }

        public void secondRoll() {
            rollTwo = roll();

            if (rollTwo == rollOne) {
                Console.print("Your second roll is your point number");
                Console.print("You Win!! (⌐■_■)");
                win = true;
            } else  {
                Console.print("\nYour second roll is: " + rollTwo);
                Console.print("You Lose ಠ_ಠ");
                win = false;
            }
        }

    @Override
    public boolean endGame() {
        Console.print("Do you want to continue Street Craps, Yes or No?");
        String input = Console.getStringInput();

        if (input.equalsIgnoreCase("yes")) {
            return true;
        } else {
            return false;
        }
    }

}



