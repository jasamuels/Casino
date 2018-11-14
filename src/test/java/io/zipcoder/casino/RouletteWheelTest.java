package io.zipcoder.casino;

import io.zipcoder.casino.game.chancegames.chancegameutilities.RouletteWheel;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RouletteWheelTest {

    @Test
    public void getWheelSizeTest() {

    RouletteWheel wheelTest = new RouletteWheel();

    int expected = 37;
    int actual = wheelTest.getWheelSize();

    Assert.assertEquals(expected, actual);
}

    @Test
    public void getWheelTest() {
        Map<Integer, String> test = new HashMap<>();
        test.put(1, "black");
        test.put(2, "red");
        RouletteWheel wheelTest = new RouletteWheel(test);

        int expected = test.size();
        int actual = wheelTest.getWheelSize();

        Assert.assertEquals(expected, actual);

    }
}
