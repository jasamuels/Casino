package io.zipcoder.casino.game.cardgames.individualcardgames;

import io.zipcoder.casino.game.Player;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import org.junit.contrib.java.lang.system.SystemOutRule;

public class GoFishTest {
    Player player;
    GoFish goFishTest;
    public GoFishTest() {
        player = new Player();
        goFishTest = new GoFish(player);
    }

    /* Test Section : Setters and Getters */
    @Test
    public void testSetPlayerBookTrue() {

        int expectedBookCount = 1;

        goFishTest.setPlayerBook(1);

        int actualBookCount = goFishTest.getPlayerBook();

        Assert.assertEquals(expectedBookCount, actualBookCount);
    }

    @Test
    public void testSetPlayerBookFalse() {
        int expectedBookCount = 1;

        goFishTest.setPlayerBook(18);

        int actualBookCount = goFishTest.getPlayerBook();

        Assert.assertNotEquals(expectedBookCount, actualBookCount);

    }

    @Test
    public void testSetOpponentBookTrue() {

        int expectedBookCount = 1;

        goFishTest.setOpponentBook(1);

        int actualBookCount = goFishTest.getOpponentBook();

        Assert.assertEquals(expectedBookCount, actualBookCount);

    }

    @Test
    public void testSetOpponentBookFalse() {

        int expectedBookCount = 1;

        goFishTest.setPlayerBook(18);

        int actualBookCount = goFishTest.getPlayerBook();

        Assert.assertNotEquals(expectedBookCount, actualBookCount);

    }

    @Test
    public void testSetPlayerTotalCards() {
        int expectedTotalCards = 7;

        goFishTest.setPlayerTotalCards(7);

        int actualCardCount = goFishTest.getPlayerTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCardCount);

    }

    @Test
    public void testSetOpponentTotalCards() {
        int expectedTotalCards = 7;

        goFishTest.setOpponentTotalCards(7);

        int actualCardCount = goFishTest.getOpponentTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCardCount);

    }

    @Test
    public void testIncrementPlayersBookOnce() {

        int expectedTotalBooks = 2;

        goFishTest.setPlayerBook(1);
        goFishTest.incrementPlayersBook();

        int actualCardCount = goFishTest.getPlayerBook();

        Assert.assertEquals(expectedTotalBooks, actualCardCount);

    }

    @Test
    public void testIncrementPlayersBook10Times() {

        int expectedTotalBooks = 10;

        for (int i = 0; i < 10; i++) {
            goFishTest.incrementPlayersBook();
        }

        int actualCardCount = goFishTest.getPlayerBook();

        Assert.assertEquals(expectedTotalBooks, actualCardCount);

    }

    @Test
    public void testIncrementOpponentBookOnce() {
        int expectedTotalBooks = 2;

        goFishTest.setOpponentBook(1);
        goFishTest.incrementOpponentBook();

        int actualCardCount = goFishTest.getOpponentBook();

        Assert.assertEquals(expectedTotalBooks, actualCardCount);

    }

    @Test
    public void testIncrementOpponentBook7Times() {

        int expectedTotalBooks = 7;

        for (int i = 0; i < 7; i++) {
            goFishTest.incrementOpponentBook();
        }

        int actualCardCount = goFishTest.getOpponentBook();

        Assert.assertEquals(expectedTotalBooks, actualCardCount);

    }

    @Test
    public void testDecrementPlayerCards() {
        int expectedTotalCards = 6;

        goFishTest.setPlayerTotalCards(7);
        goFishTest.decrementPlayersCards();

        int actualCards = goFishTest.getPlayerTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCards);

    }

    @Test
    public void testDecrementOpponentCards() {
        int expectedTotalCards = 3;

        goFishTest.setOpponentTotalCards(4);
        goFishTest.decrementOpponentCards();

        int actualCards = goFishTest.getOpponentTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCards);

    }

    @Test
    public void testIncrementPlayersCards() {
        int expectedTotalCards = 8;

        goFishTest.setPlayerTotalCards(7);
        goFishTest.incrementPlayersCards();

        int actualCards = goFishTest.getPlayerTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCards);

    }

    @Test
    public void testIncrementOpponentsCards() {
        int expectedTotalCards = 5;

        goFishTest.setOpponentTotalCards(4);
        goFishTest.incrementOpponentCards();

        int actualCards = goFishTest.getOpponentTotalCards();

        Assert.assertEquals(expectedTotalCards, actualCards);

    }

    /* Combination Setters */

    @Test
    public void testPlayerTurnTrue() {
        // Expect to increment players book by 1
        // expect to decrement players cards by 2

        int expectedCards = 3;
        int expectedBooks = 1;

        goFishTest.setPlayerTotalCards(5);

        goFishTest.playerTurnTrue();

        Assert.assertEquals(expectedCards, goFishTest.getPlayerTotalCards());
        Assert.assertEquals(expectedBooks, goFishTest.getPlayerBook());

    }

    @Test
    public void testOpponentTurnTrue() {

        int expectedCards = 1;
        int expectedBooks = 3;

        goFishTest.setOpponentTotalCards(7);

        for (int i = 0; i < 3; i++) {
            goFishTest.opponentTurnTrue();
        }

        Assert.assertEquals(expectedCards, goFishTest.getOpponentTotalCards());
        Assert.assertEquals(expectedBooks, goFishTest.getOpponentBook());

    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void overrideProperty() {
        System.out.print("hello world");
        Assert.assertEquals("hello world", systemOutRule.getLog());
    }

    @Test
    public void testPrintSortedHand() {

        goFishTest.printSortedHand();


    }
}