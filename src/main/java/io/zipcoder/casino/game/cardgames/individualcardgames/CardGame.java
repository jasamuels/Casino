package io.zipcoder.casino.game.cardgames.individualcardgames;
import io.zipcoder.casino.game.cardgames.cardutilities.Card;
import io.zipcoder.casino.game.cardgames.cardutilities.Deck;
import io.zipcoder.casino.game.interfaces.Game;

import java.util.ArrayList;

public abstract class CardGame implements Game {


    Deck deck;
    private ArrayList<Card> playerHand;
    private ArrayList<Card> houseHand;

    public void setPlayerHand(ArrayList<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public void setHouseHand(ArrayList<Card> houseHand) {
        this.houseHand = houseHand;
    }

    public CardGame() {
        deck = new Deck();
        playerHand = new ArrayList<Card>();
        houseHand = new ArrayList<Card>();

    }

    public void dealHand(int howManyCards) {

        for (int i = 0; i < howManyCards; i++) {
            playerHand.add(deck.dealCard());
            houseHand.add(deck.dealCard());
        }

    }


    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<Card> getHouseHand() {
        return houseHand;
    }



}

