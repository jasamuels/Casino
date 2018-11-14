package io.zipcoder.casino.game.cardgames.cardutilities;

public class Card {


        public Card(Suit suit, Rank value) {
            this.suit = suit;
            this.value = value;
        }

        private Suit suit;
        private Rank value;


        public Suit getSuit() {
            return suit;
        }

        public void setSuit(Suit suit) {
            this.suit = suit;
        }

        public Rank getValue() {
            return value;
        }

        public void setValue(Rank value) {
            this.value = value;
        }

        @Override
        public String toString (){
            String s = value + " of " + suit;
            return s;
        }


        public int getSecondaryVal(Card c) {
            return c.getValue().getSecondaryvalue();
        }
    }







