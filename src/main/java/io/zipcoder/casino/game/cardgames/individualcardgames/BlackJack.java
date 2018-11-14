package io.zipcoder.casino.game.cardgames.individualcardgames;


import io.zipcoder.casino.Console;
import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.cardgames.cardutilities.Card;
import io.zipcoder.casino.game.interfaces.Gamble;

import java.util.*;

public class BlackJack extends CardGame implements Gamble {
    private Player player;
    public Integer playerTotal = 0;
    public Integer houseTotal = 0;
    public boolean nothaRound = true;
    public boolean hitCheck = true;
    public BlackJack(Player player) {
        this.player = player;
    }

    public int bet(int betVal){
        return betVal;
    }

    @Override
    public void play() {
        while (nothaRound) {
            Console.print(intro());
            int stakes = Console.printAndTakeInt("How much money would you like to bet " + player.getName());

            dealHand(2);
            handSum();
            firstShow();
            if (playerTotal == 21) {
                doBlackjack(stakes);
            } else {
                hitOrStand(Console.printAndTakeString("Hit or Stand?").toLowerCase(), stakes);
            }
            String round = Console.printAndTakeString("Wanna play another round? ( y / n )");
            if(round.equals("n")) {
                clearHands();
                nothaRound = false;
            }else{
                hitCheck = true;
                clearHands();
            }
        }
    }
    public void clearHands(){
        getPlayerHand().clear();
        getHouseHand().clear();
        playerTotal = 0;
        houseTotal = 0;
    }

    public String intro(){
        return "\n\n\n\n\n\n\n\n\n\n\n\n\n♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣\n" +
                "♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦ WELCOME TO BLACKJACK ♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♠♥♦♣\n" +
                "♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣\n";
    }

    public void hitOrStand(String s, int stakes){
        switch (s.toLowerCase()) {
            case "hit": {
                while(hitCheck) {
                    dealSumFirstShow();
                    if(playerTotal >= 21){
                        break;
                    }
                    String answer = Console.printAndTakeString("Hit or Stand?").toLowerCase();
                    if(answer.equals("stand")){
                        break;
                    }
                    hitOrStand(answer,stakes);
                }
            }
            case "stand": {
                if (playerTotal <= 21) {
                    printState();
                    if (houseTotal < 17) {
                        dealHouse();
                        winConditions(stakes);
                        break;
                    }
                }
                winConditions(stakes);
                break;
            }
        }
    }
    public boolean dealHouse(){
        boolean dealtHouse = false;
        int count = 0;
        while (count < 4 && houseTotal < 17) {
            dealSumPrint();
            if (houseTotal > 21) {
                break;
            }
            count++;
        }
        dealtHouse = true;
        return dealtHouse;
    }
    public boolean dealSumPrint(){
        boolean isSumDealt = false;
        getHouseHand().add(deck.dealCard());
        houseTotal = sum(getHouseHand());
        printState();
        isSumDealt = true;
        return isSumDealt;
    }

    public boolean dealSumFirstShow(){
        boolean isSumDealt = false;
        getPlayerHand().add(deck.dealCard());
        playerTotal = sum(getPlayerHand());
        firstShow();
        isSumDealt = true;
        return isSumDealt;
    }

    public void handSum(){
        playerTotal += sum(getPlayerHand());
        houseTotal += sum(getHouseHand());
    }
    public void doBlackjack(int stakes){
        Console.print("Hot Dog the PLAYER got Blackjack");
        stakes = (int) (1.5 * stakes);
        win(player, stakes);
        Console.print(player.getState());
    }
    public void winConditions(int stakes){
        if(playerTotal > 21){
            Console.print("HOUSE WINS");
            Console.print("PLAYER BUSTS");
            printState();
            loss(player, stakes);
            Console.print(player.getState());
            hitCheck = false;
        }
        else if(houseTotal > 21){
            Console.print("PLAYER WINS");
            Console.print("HOUSE BUSTS");
            printState();
            win(player,stakes);
            Console.print(player.getState());
            hitCheck = false;
        }
        else if(houseTotal > playerTotal){
            Console.print("HOUSE WINS");
            printState();
            loss(player, stakes);
            Console.print(player.getState());
            hitCheck = false;
        }
        else if(houseTotal < playerTotal){
            Console.print("PLAYER WINS");
            printState();
            win(player,stakes);
            Console.print(player.getState());
            hitCheck = false;
        }
        else if(houseTotal.equals(playerTotal)){
            Console.print("PUSH");
            printState();
            win(player,0);
            Console.print(player.getState());
            hitCheck = false;
        }
    }

    public boolean firstShow(){
        boolean firstShown = true;
        Console.pause(500);
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.pause(500);
        Console.print("PLAYER: " + getPlayerHand());
        Console.pause(500);
        Console.print("PLAYER: " + playerTotal + "\n");
        Console.pause(500);
        Console.print("HOUSE: [" +  getHouseHand().get(0) + ", ***** of *****]");
        Console.pause(500);
        Console.print("HOUSE: " +  getHouseHand().get(0).getSecondaryVal(getHouseHand().get(0)));
        Console.pause(500);
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.pause(1000);
        return firstShown;
    }

    public void printState(){
        Console.pause(500);
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.pause(500);
        Console.print("PLAYER: " + getPlayerHand());
        Console.pause(500);
        Console.print("PLAYER: " + playerTotal + "\n");
        Console.pause(500);
        Console.print("HOUSE: " + getHouseHand());
        Console.pause(500);
        Console.print("HOUSE: " + houseTotal);
        Console.pause(500);
        Console.print("♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣");
        Console.pause(1000);
    }
    public int sum(ArrayList<Card> hand) {
        Integer count = 0;
        boolean aceFound = false;

        for(Card crd : hand){
            count += crd.getValue().getSecondaryvalue();
            if(crd.getValue().getSecondaryvalue() == 11 && !aceFound){
                if(count > 21){
                    count -= 10;
                    aceFound = true;
                }
            }
        }
        return count;
    }

    @Override
    public boolean endGame(){
        return false;
    }

}