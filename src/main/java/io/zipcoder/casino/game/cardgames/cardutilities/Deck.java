package io.zipcoder.casino.game.cardgames.cardutilities;

import java.util.Collections;
import java.util.Stack;

public class Deck {

    private Stack<Card> deck;

    public Deck() {
        refreshDeck();
        deck = shuffleDeck();
    }

    public Stack<Card> getDeck() {
        return deck;
    }

    public Stack<Card> shuffleDeck() {
        Collections.shuffle(deck);
        return deck;
    }

    public Card dealCard() {
        return deck.pop();
    }

    public void refreshDeck() {

        Stack<Card> newDeck = new Stack<>();
        for (Rank r : Rank.values()) {
            for (Suit s : Suit.values()) {
                Card temp = new Card(s, r);
                newDeck.push(temp);
            }
        }

        this.deck = newDeck;

    }
}
