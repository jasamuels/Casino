package io.zipcoder.casino;

import io.zipcoder.casino.game.Player;
import org.junit.*;

public class PlayerTest {

    @Test
    public  void playerNameTest(){
        //Given
        Player player = new Player("Bob", 5000);
        String expected = "John";
        //When
        player.setName("John");
        String actual = player.getName();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public  void playerBankTest(){
        //Given
        Player player = new Player("Bob", 5000);
        Integer expected = 10000;
        //When
        player.setBank(10000);
        Integer actual = player.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public  void playerStateTest(){
        //Given
        Player player = new Player("Bob", 5000);
        String expected = "Highroller Bob has $5,000";
        //When
        String actual = player.getState();
        //Then
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void playerDefaultTest(){
        //Given
        Player player = new Player();
        String expected = "Highroller Default has $10,000";
        //When
        String actual = player.getState();
        //Then
        Assert.assertEquals(expected,actual);
    }
}
