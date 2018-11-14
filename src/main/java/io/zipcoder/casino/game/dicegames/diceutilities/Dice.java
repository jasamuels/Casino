package io.zipcoder.casino.game.dicegames.diceutilities;

import java.util.Random;

public class Dice {

    RandomDice randomDice;
    private int numOfDice;

    public Dice(int numOfDice){
        this.numOfDice = numOfDice;
        randomDice = new RandomDice();
    }

    public void setRandomDice(RandomDice randomDice) {
        this.randomDice = randomDice;
    }

    public int tossAndSum() {

        int sumRoll = 0;

        for (int i = 0; i < numOfDice; i++) {
            sumRoll += randomDice.randDice();
        }
        return sumRoll;
    }

    public int getNumOfDice() {
        return numOfDice;
    }
}
