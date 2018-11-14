
package io.zipcoder.casino.game.chancegames;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.chancegames.chancegameutilities.RandomNumGen;
import io.zipcoder.casino.game.chancegames.chancegameutilities.RouletteWheel;
import io.zipcoder.casino.game.interfaces.Gamble;
import io.zipcoder.casino.game.interfaces.Game;

import java.util.*;

public class Roulette implements Gamble, Game {

    Player player;
    private int numResult;
    private String clrResult;
    private int numGuess;
    private String clrGuess;
    private int betAmount;
    private RouletteWheel wheel = new RouletteWheel();
    private RandomNumGen random = new RandomNumGen();
    boolean runner = true;



    public Roulette(Player player) {
        this.player = player;

    }

    public void play() {

        while (runner == true) {

            Console.print("Welcome to Zip Roulette: please enter a wager");
            Console.print("Player Bank: " + player.getBank());
            betAmount = Console.getIntegerInput();
            int input = Console.printAndTakeInt("Great job! Enter 1 for to bet on a number; enter 2 to bet on a color");
            playEntry(input);
            win(betAmount);
            runner = endGame();
        }
    }

    @Override
    public boolean endGame() {

        String input = Console.printAndTakeString("Do you want to end the game or play again? Yes or No?");
        if (input.equalsIgnoreCase("yes")) {
            return runner = true;
        } else {
            return runner = false;
        }
    }


    public void getResult(RandomNumGen random) {
        Object[] keys = wheel.getWheel().keySet().toArray();
        Object tempKey = keys[random.randomRoulette()];
        numResult = (int) tempKey;
        clrResult = wheel.getWheel().get(tempKey);
        Console.print("\n****** " + numResult + " " + clrResult + " ******");
    }

    public void playEntry(int input) {
        if (input == 1) {
            numberEntry();
        } else if (input == 2) {
            colorEntry();
        } else {
            Console.print("Please try again");
        }
    }

    public void numberEntry() {
        numGuess = Console.printAndTakeInt("Please enter number between 0 and 36");
        Console.pause(500, ". ",4);
        getResult(random);
        compareNumbers(numGuess);
    }

    public void colorEntry() {
        clrGuess = Console.printAndTakeString("Please enter black, red or green");
        Console.pause(500, ". ",4);
        getResult(random);
        compareBlckRd(clrGuess);


    }

    public boolean compareNumbers(int numGuess) {

        if (numGuess == numResult) {
            bet(5);
            return true;
        } else {
            loss(betAmount);
            betAmount = 0;
            return false;}

    }

    public boolean compareBlckRd(String clrGuess) {

        if(clrGuess.equalsIgnoreCase(clrResult)){
            bet(2);
            return true;
        } else {
            loss(betAmount);
            betAmount = 0;
            return false;}

    }


    @Override
    public int bet(int betMultiplier) {

        return betAmount *= betMultiplier;

    }

    public void win(int betAmount) {
        player.setBank(player.getBank() + betAmount);
    }


    public void loss(int betAmount) {
        player.setBank(player.getBank() - betAmount);

    }


    public void checkBet() {
        if (betAmount > player.getBank()) {
            Console.print("Not enough funds: enter another wager");

        }
    }

    public int getNumResult() {
        return numResult;
    }

    public String getClrResult() {
        return clrResult;
    }

    public void setNumResult(int numResult) {
        this.numResult = numResult;
    }

    public void setClrResult(String clrResult) {
        this.clrResult = clrResult;
    }

    public int getNumGuess() {
        return numGuess;
    }

    public String getClrGuess() {
        return clrGuess;
    }

    public int getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public void setNumGuess(int numGuess) {
        this.numGuess = numGuess;
    }

    public void setClrGuess(String clrGuess) {
        this.clrGuess = clrGuess;
    }

    public boolean isRunner() { return runner; }
}

