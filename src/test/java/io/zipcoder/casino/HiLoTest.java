package io.zipcoder.casino;

import io.zipcoder.casino.game.cardgames.individualcardgames.HiLo;
import org.junit.Assert;
import org.junit.Test;

public class HiLoTest {

    @Test
    public void highLogic() {

        //Given
        int expected = 600;

        //Assert
        Assert.assertEquals(expected, HiLo.highLogic(50, 12), 0);
    }

    @Test
    public void lowLogic() {

        //Given
        int expected = 10;

        //Assert
        Assert.assertEquals(expected, HiLo.lowLogic(5, 6), 0);

    }
}