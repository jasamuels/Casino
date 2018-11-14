package io.zipcoder.casino.game.cardgames.individualcardgames;
import io.zipcoder.casino.Console;
import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.cardgames.cardutilities.Card;
import io.zipcoder.casino.game.cardgames.cardutilities.Rank;

import java.util.*;

public class GoFish extends CardGame {

    private Player player;

    private ArrayList<Card> playerHand;

    private ArrayList<Card> opponentHand;

    private Card[] playerBookCheck;
    private Card[] opponentBookCheck;

    private HashMap<String, Integer> allRanks;

    private boolean endGameCheck;

    private int playerBook;
    private int opponentBook;

    private int playerTotalCards;
    private int opponentTotalCards;

    private int turnOrder;

    public GoFish(Player player) {

        this.player = player;

        playerHand = getPlayerHand();
        opponentHand = getHouseHand();

        playerBookCheck = new Card[13];
        opponentBookCheck = new Card[13];

        playerBook = 0;
        opponentBook = 0;
        endGameCheck = false;

        playerTotalCards = 0;
        opponentTotalCards = 0;

        turnOrder = randomTurnOrder();

        allRanks = new HashMap<>();
        for (Rank r: Rank.values()) {
            allRanks.put(r.getThirdvalue(), r.getValue() - 1);
        }
    }

    public void play() {

        startOfGame();
        firstBookCheck();


        turnOrder = playerGoesFirst();

        while (!endGameCheck) {
            gameTurnOrder(turnOrder);
        }

        Console.print("Thanks for playing! \n ... Returning to main menu.");
    }

    public void gameTurnOrder(int turnOrder) {
        if (turnOrder == 0) {
            playerTurn();
            opponentTurn();
            bookStandings();

        } else {
            opponentTurn();
            playerTurn();
            bookStandings();
        }
    }

    public void startOfGame() {
        Console.print("Welcome to go fish, " + player.getName() + "!");
        showRules();

        startDealHands();
    }

    public void firstBookCheck() {
        Console.print("Checking dealt hands for books... ");
        Console.pause(250, " . ", 5);

        updateBookChecker(playerHand, playerBookCheck, true);
        updateBookChecker(opponentHand, opponentBookCheck, false);

        bookStandings();

    }

    public void bookStandings() {
        Console.print("\n\nCurrent book standings!"
                + "\n" + player.getName() + "'s Books: " + getPlayerBook()
                + "\nOpponent's Books: " + getOpponentBook() + "\n\n");
    }

    public void startDealHands() {
        Console.print("Dealing cards...");
        Console.pause(250, " . ", 5);
        dealHand(7);
        setPlayerTotalCards(7);
        setOpponentTotalCards(7);
    }

    private void updateBookChecker(ArrayList<Card> hand, Card[] bookCheck, boolean isPlayersTurn) {
        int cardIndex;
        for(Card c: hand) {
            cardIndex = c.getValue().getValue() - 1;
            if (bookCheck[cardIndex] == null) {
                bookCheck[cardIndex] = c;

            } else {
                String name = player.getName();
                if (!isPlayersTurn) {
                    name = "opponent";
                }
                addBookCountDecreaseCardTotal(isPlayersTurn);
                Console.print("Removing cards " + c.toString() + " and " + bookCheck[cardIndex].toString() + " from " + name + "'s hand.");
                bookCheck[cardIndex] = null;

            }
        }
    }

    private int randomTurnOrder() {
        Random random = new Random();
        return random.nextInt(2);
    }

    private int playerGoesFirst() {

        Console.print("Determining who goes first...");
        Console.pause(250, " . ", 5);

        if (this.turnOrder == 0) {
            Console.print("You go first!");
            return 0;
        } else {
            Console.print("Your opponent goes first...");
        }

        return 1;
    }


    public void removeBook(Card[] hand, int newCardIndex) {
        Console.print("Removing " + hand[newCardIndex].getValue().getValue() + "'s.");
        hand[newCardIndex] = null;
    }

    public void increaseTotalCards(boolean isPlayersTurn) {
        if (isPlayersTurn) {
            incrementPlayersCards();
        } else {
            incrementOpponentCards();
        }
    }

    public void addBookCountDecreaseCardTotal(boolean isPlayersTurn) {
        if (isPlayersTurn) {
            playerTurnTrue();
        } else {
            opponentTurnTrue();
        }
    }

    private void goFish(Card[] hand, boolean isPlayersTurn) {
        // put go fish here
        Card newCard = deck.dealCard();
        int newCardIndex = newCard.getValue().getValue() - 1;

        Console.print("Adding " + newCard.toString() + " to the hand.");

        if (hand[newCardIndex] != null) {
            removeBook(hand, newCardIndex);
            addBookCountDecreaseCardTotal(isPlayersTurn);

        } else {
            hand[newCardIndex] = newCard;
            increaseTotalCards(isPlayersTurn);

        }
    }

    public boolean endGame() {
        return (deck.getDeck().isEmpty() || playerBook >= 13 || opponentBook >= 13);
    }

    private boolean checkRank(String s) {
        return allRanks.get(s) != null;
    }

    private String startPlayerTurn() {

        String userAsk = Console.printAndTakeString("What card do you want to ask for?");

        if (!checkRank(userAsk)) {
            userAsk = Console.printAndTakeString("Invalid entry type,"
                + "try typing out the card number/face.. ie. Ace or two\n");
        }

        return userAsk.toLowerCase();
    }

    private boolean addAsktoHand(Card[] takeFromThisHand, Card[] addToThisHand , String userAsk, boolean isPlayersTurn) {

        Card requestedCard = takeFromThisHand[allRanks.get(userAsk)];

        if (requestedCard != null) {
            Console.print("Found a card! Adding " + requestedCard.toString() + " to hand.");
            takeFromThisHand[allRanks.get(userAsk)] = null;

            if (addToThisHand[allRanks.get(userAsk)] != null) {

                Console.print("Removing cards "
                        + requestedCard.toString()
                        + " and "
                        + addToThisHand[allRanks.get(userAsk)].toString()
                        + ".");

                addToThisHand[allRanks.get(userAsk)] = null;
                addBookCountDecreaseCardTotal(isPlayersTurn);

            } else {

                addToThisHand[allRanks.get(userAsk)] = requestedCard;
                increaseTotalCards(isPlayersTurn);

            }
        }

        return requestedCard != null;
    }


    private void playerTurn() {

        boolean validCardAsked = false;

        printSortedHand();

        if (playerTotalCards == 0) {
            Card newCard = deck.dealCard();
            int newCardIndex = newCard.getValue().getValue() - 1;
            Console.print("Adding " + newCard.toString() + " to the player's hand.");
            playerBookCheck[newCardIndex] = newCard;
            incrementPlayersCards();
        }

        while (!validCardAsked) {
            String cardAskedFor = startPlayerTurn();
            checkingOpponentHand();
            if (checkHandForCard(playerBookCheck, cardAskedFor)) {
                if (!addAsktoHand(opponentBookCheck, playerBookCheck, cardAskedFor, true)) {
                    Console.print("No cards of that number found, sorry!\nGO FISH!");
                    goFish(playerBookCheck, true);
                }
                validCardAsked = true;
            } else {
                Console.print("You don't have that card! Try again...");
            }
        }

        checkPlayerBook();
        endGameCheck = endGame();
        endCheck(true);
    }

    private void checkingOpponentHand() {
        Console.print("Checking opponent's hand...");
        Console.pause(250, " . ", 5);
    }

    private boolean checkHandForCard(Card[] cards, String string) {
        return cards[allRanks.get(string)] != null;
    }

    private void checkPlayerBook() {
        Console.print("\nChecking for books... ");
        Console.pause(350, " . ", 5);
        Console.print("You have " + playerBook + " book(s).\n");
    }

    private void opponentTurn() {
        printSortedHand();
        opponentAsk();
        endGameCheck = endGame();
        endCheck(false);
    }

    private void opponentAsk() {

        Console.print("Opponent's turn...\n");
        Random random = new Random();

        if (opponentTotalCards == 0) {
            Card newCard = deck.dealCard();
            int newCardIndex = newCard.getValue().getValue() - 1;
            Console.print("Adding " + newCard.toString() + " to the owner's hand.");
            opponentBookCheck[newCardIndex] = newCard;
            incrementOpponentCards();
        }

        int result = random.nextInt(opponentTotalCards);

        int counter = 0;
        Card randomCard = null;

        for (int i = 0; i < opponentBookCheck.length; i++) {
            if (opponentBookCheck[i] != null) {
                counter++;
                randomCard = opponentBookCheck[i];

                if (counter >= result) {
                    break;
                }
            }
        }

        String opponentAsk = getRankString(opponentBookCheck[randomCard.getValue().getValue()-1]);

        Console.print("Opponent is asking for " + opponentAsk + "'s. \nSearching your hand...");
        Console.pause(250, " . ", 5);

        if (!addAsktoHand(playerBookCheck, opponentBookCheck, opponentAsk, false)) {
            goFish(opponentBookCheck, false);
        }
    }

    public void notGameOver(Card[] bookCheck) {
        Console.print("The hand is empty! Adding a card...");
        Card newCard = deck.dealCard();
        bookCheck[newCard.getValue().getValue() - 1] = newCard ;

    }

    private void endCheck(boolean isPlayersTurn) {
        if (endGameCheck) {
            printResult();
        } else {
            if (isPlayersTurn && playerTotalCards == 0) {

                notGameOver(playerBookCheck);
                incrementPlayersCards();

            } else if (opponentTotalCards == 0) {

                notGameOver(opponentBookCheck);
                incrementOpponentCards();
            }

            Console.print("+++++ End of Turn +++++\n\n");
        }

        Console.pause(350, " . ", 5);
    }

    private String getRankString(Card c) {
        return c.getValue().getThirdvalue();
    }

    private void showRules() {

        boolean validInput = false;

        Console.print("Do you want to see the rules? Y or N");

        while(!validInput) {
            String userRuleSelect = Console.getStringInput().toLowerCase();

            switch (userRuleSelect) {
                case "y" :
                    rules();
                    validInput = true;
                    break;
                case "n":
                    validInput = true;
                    break;
                default:
                    Console.print("Invalid entry, try \'Y\' or \'N\'");
                    break;
            }
        }
    }

    private void rules() {

        Console.print("Go Fish a game for all ages!"
                + "\nEach player starts with 7 cards and the object of the game "
                + "\nis to collect books, which are pairs of the same card number/rank."
                + "\nFor example, a Three of hearts and a Three of diamonds makes a book!"
                + "\ngame ends when the pile of cards is empty or one player has more than 13 pairs."
                + "\n\nEach round, the player will ask the opponent if they have a card, but the card"
                + "\nthe player asks for must be in their hand!"
                + "\nIf the opponent has the card, they have to give ALL the cards that match that card "
                + "\nto the player asking. If the opponent doesn't have it, the player must \"go fish\"."
                + "\nGo fish means you have to get 1 card from the pile (all the leftover cards)."
                + "\nThe first player to empty their hand wins!"
                + "\nGood luck! :) \n\n");

        pressAnyKeyToContinue();
    }

    private void pressAnyKeyToContinue()
    {
        Console.print("Press Enter key to continue...");
        try {
            System.in.read();
        } catch(Exception e) {

            e.printStackTrace();}
    }

    private void printResult() {
        if (opponentBook > playerBook) {
            Console.print("Your opponent won! :( ");
        } else if (opponentBook == playerBook) {
            Console.print("It was a draw :|");
        } else {
            Console.print("Grats, you won! :)");
        }
    }

    public void printSortedHand() {
        Console.printInSameLine("Your hand: [");

        boolean firstCard = true;

        for (int i = 0; i < playerBookCheck.length; i++) {

            if (playerBookCheck[i] != null) {
                if (!firstCard) {
                    Console.printInSameLine(", ");
                }
                Console.printInSameLine(playerBookCheck[i].toString());
                firstCard = false;
            }

        }
        Console.printInSameLine("]\n");
    }

    public int getPlayerBook() {
        return playerBook;
    }

    public void setPlayerBook(int playerBook) {
        this.playerBook = playerBook;
    }

    public int getOpponentBook() {
        return opponentBook;
    }

    public void setOpponentBook(int opponentBook) {
        this.opponentBook = opponentBook;
    }

    public int getPlayerTotalCards() {
        return playerTotalCards;
    }

    public void setPlayerTotalCards(int playerTotalCards) {
        this.playerTotalCards = playerTotalCards;
    }

    public int getOpponentTotalCards() {
        return opponentTotalCards;
    }

    public void setOpponentTotalCards(int opponentTotalCards) {
        this.opponentTotalCards = opponentTotalCards;
    }

    public void incrementPlayersBook() {
        setPlayerBook(getPlayerBook() + 1);
    }

    public void incrementOpponentBook() {
        setOpponentBook(getOpponentBook() + 1);
    }

    public void decrementPlayersCards() {
        setPlayerTotalCards(getPlayerTotalCards() - 1);
    }

    public void decrementOpponentCards() {
        setOpponentTotalCards(getOpponentTotalCards() - 1);
    }

    public void incrementPlayersCards() {
        setPlayerTotalCards(getPlayerTotalCards() + 1);
    }

    public void incrementOpponentCards() {
        setOpponentTotalCards(getOpponentTotalCards() + 1);
    }


    public void playerTurnTrue() {
        incrementPlayersBook();
        decrementPlayersCards();
        decrementPlayersCards();
    }

    public void opponentTurnTrue(){
        incrementOpponentBook();
        decrementOpponentCards();
        decrementOpponentCards();
    }
}
