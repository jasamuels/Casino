package io.zipcoder.casino.game.interfaces;

import io.zipcoder.casino.game.Player;

public interface Gamble {

    int bet(int initialAmount);

    default void win(Player player, int winnings) {
        player.setBank(player.getBank() + winnings);
    }

    default void loss(Player player, int losses) {
        player.setBank(player.getBank() - losses);
    }

}
