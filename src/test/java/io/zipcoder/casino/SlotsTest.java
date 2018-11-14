package io.zipcoder.casino;

import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.chancegames.Slots;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SlotsTest {
    Slots slots;
    Player player;
    int slotsArr[];
    @Before
    public void setup(){
        player = new Player();
        slots = new Slots(player);
    }

    @Test
    public void testPlay(){

    }

    @Test
    public void testSpin(){

        slots.spin();

       //System.out.println(slots.getSlots()[0]);

        Assert.assertTrue(slots.getSlots()[0] > 0 && slots.getSlots()[0] < 8);
    }

    @Test
    public void testCheckWin(){
        slotsArr = new int[3];
        //when all the three slots have same value
        slotsArr[0] = 2;
        slotsArr[1] = 2;
        slotsArr[2] = 2;

        slots.setSlots(slotsArr);
        slots.bet(3);
        int actual = slots.checkMatching(3);
        int expectedAmount = 9;
        Assert.assertEquals(expectedAmount, actual);
    }

    @Test
    public void testCheckTwoMatching(){
        slotsArr = new int[3];
        //when all the three slots have same value
        slotsArr[0] = 1;
        slotsArr[1] = 1;
        slotsArr[2] = 9;
        slots.setSlots(slotsArr);
        int actual = slots.checkMatching(2);
        int expectedAmount = 4;
        Assert.assertEquals(expectedAmount, actual);
    }

    @Test
    public void testCheckNoMatching(){
        slotsArr = new int[3];
        //when all the three slots have same value
        slotsArr[0] = 4;
        slotsArr[1] = 6;
        slotsArr[2] = 9;
        slots.setSlots(slotsArr);
        int actual = slots.checkMatching(2);
        int expectedAmount = 2;
        Assert.assertEquals(expectedAmount, actual);
    }

    @Test
    public void testResult(){

    }

    @Test
    public void testGetSlots(){
        int slotsArr[] = slots.getSlots();
        Assert.assertTrue(slotsArr.length == 3);
    }

    @Test
    public void testEndGame(){

    }

}
