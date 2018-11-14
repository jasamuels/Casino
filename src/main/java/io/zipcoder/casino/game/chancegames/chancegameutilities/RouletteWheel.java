package io.zipcoder.casino.game.chancegames.chancegameutilities;

import java.util.HashMap;
import java.util.Map;

public class RouletteWheel {
    private final Map<Integer, String> wheel;



    public RouletteWheel() {
        wheel = new HashMap<>();
        wheel.put(0, "Green");
        wheel.put(1, "Red");
        wheel.put(2, "Black");
        wheel.put(3, "Red");
        wheel.put(4, "Black");
        wheel.put(5, "Red");
        wheel.put(6, "Black");
        wheel.put(7, "Red");
        wheel.put(8, "Black");
        wheel.put(9, "Red");
        wheel.put(10, "Black");
        wheel.put(11, "Red");
        wheel.put(12, "Black");
        wheel.put(13, "Red");
        wheel.put(14, "Black");
        wheel.put(15, "Red");
        wheel.put(16, "Black");
        wheel.put(17, "Red");
        wheel.put(18, "Black");
        wheel.put(19, "Red");
        wheel.put(20, "Black");
        wheel.put(21, "Red");
        wheel.put(22, "Black");
        wheel.put(23, "Red");
        wheel.put(24, "Black");
        wheel.put(25, "Red");
        wheel.put(26, "Black");
        wheel.put(27, "Red");
        wheel.put(28, "Black");
        wheel.put(29, "Black");
        wheel.put(30, "Red");
        wheel.put(31, "Black");
        wheel.put(32, "Red");
        wheel.put(33, "Black");
        wheel.put(34, "Black");
        wheel.put(35, "Black");
        wheel.put(36, "Red");

    }

    public RouletteWheel(Map<Integer, String> wheel) {
        this.wheel = wheel;
    }

    public Map<Integer, String> getWheel() {
        return wheel;
    }

    public int getWheelSize() {
       return wheel.size();
    }
}
