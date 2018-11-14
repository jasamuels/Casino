package io.zipcoder.casino.game.dicegames.individualdicegames;

import io.zipcoder.casino.game.dicegames.diceutilities.Dice;
import io.zipcoder.casino.game.interfaces.Gamble;
import io.zipcoder.casino.game.interfaces.Game;

public abstract class DiceGame implements Game, Gamble {

    public abstract Dice createDice(int numberOfDice);
}
