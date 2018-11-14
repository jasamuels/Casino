
package io.zipcoder.casino;

import io.zipcoder.casino.game.chancegames.chancegameutilities.RandomNumGen;
import org.junit.Assert;
import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.chancegames.Roulette;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;


public class RouletteTest {
    Player player;
    MockRandom random;
    Roulette test;

    private class MockRandom extends RandomNumGen {
        @Override
        public int randomRoulette() {
            return 1;
        }
    }
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    @Before
    public void initialize() {
        random = new MockRandom();
        player = new Player();
        test = new Roulette(player);
        player.setBank(1000);
    }

    @Test
    public void setGetNumResultTest() {

        Roulette test = new Roulette(player);
        test.setNumResult(1);

        int expected = 1;
        int actual = test.getNumResult();

        Assert.assertEquals(expected, actual);


    }

    @Test
    public void setGetColorResultTest() {
        String expected = "Red";
        test.setClrResult("Red");
        String actual = test.getClrResult();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void setGetNumGuessTest() {
        int expected = 10;
        test.setNumGuess(10);

        int actual = test.getNumGuess();


        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setGetClrGuessTest() {
        String expected = "Black";
        test.setClrGuess("Black");

        String actual = test.getClrGuess();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void setGetBetAmountTest() {
        int expected = 100;
        test.setBetAmount(100);

        int actual = test.getBetAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareNumbersTest01() {
        test.setNumResult(10);

        boolean actual = test.compareNumbers(10);

        Assert.assertTrue(actual);

    }

    @Test
    public void compareNumbersTest02() {
        test.setNumResult(100);

        boolean actual = test.compareNumbers(10);

        Assert.assertFalse(actual);

    }

    @Test
    public void compareBlckRd01() {
        test.setClrResult("Black");

        boolean actual = test.compareBlckRd("Black");

        Assert.assertTrue(actual);
    }

    @Test
    public void compareBlckRd02() {
        test.setClrResult("Red");

        boolean actual = test.compareBlckRd("Black");

        Assert.assertFalse(actual);
    }

    @Test
    public void betMultiplierTest() {
        test.setBetAmount(5);
        int expected = 500;
        int actual = test.bet(100);

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void lossTest() {
       int expected = 900;
       test.loss(100);

       int actual = player.getBank();

       Assert.assertEquals(expected, actual);

    }

    @Test
    public void winTest() {
        int expected = 1100;

        test.win(100);
        int actual = player.getBank();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getResultNumTest() {
        test.getResult(random);
        int expected = 1;

        int actual = test.getNumResult();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getResultClrTest02() {
        test.getResult(random);
        String expected = "Red";

        String actual = test.getClrResult();

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void checkBetTest() {
        test.setBetAmount(10000);
        String expected = "Not enough funds: enter another wager\n";
        test.checkBet();
        Assert.assertEquals(expected, systemOutRule.getLog());
    }
    
}
