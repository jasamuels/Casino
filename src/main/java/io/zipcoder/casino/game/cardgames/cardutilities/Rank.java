package io.zipcoder.casino.game.cardgames.cardutilities;

public enum Rank {
    Ace(1, 11, "ace"),
    Two(2, 2, "two"),
    Three(3, 3, "three"),
    Four(4, 4, "four"),
    Five(5, 5, "five"),
    Six(6, 6, "six"),
    Seven(7, 7, "seven"),
    Eight(8, 8, "eight"),
    Nine(9, 9, "nine"),
    Ten(10, 10, "ten"),
    Jack(11, 10, "jack"),
    Queen(12, 10, "queen"),
    King(13, 10, "king");

    Rank(int value, int secondaryvalue, String thirdvalue) {
        this.value = value;
        this.secondaryvalue = secondaryvalue;
        this.thirdvalue = thirdvalue;
    }


    Rank(int value, int secondaryvalue) {
        this.value = value;
        this.secondaryvalue = secondaryvalue;
    }

    private int value;

    private int secondaryvalue;

    public int getSecondaryvalue() {
        return secondaryvalue;
    }

    public String getThirdvalue() {
        return thirdvalue;
    }

    public String thirdvalue;

    public int getValue() {
        return value;
    }

}
