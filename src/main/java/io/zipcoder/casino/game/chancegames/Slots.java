package io.zipcoder.casino.game.chancegames;

import io.zipcoder.casino.Console;
import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.interfaces.Gamble;
import io.zipcoder.casino.game.interfaces.Game;

public class Slots implements Game, Gamble {

    Player player;
    private int slots[];
    private int amount;

    public Slots(Player player){
        this.player = player;
        slots = new int[3];
    }

    public void play(){
        amount = Console.printAndTakeInt("Enter the bet amount you want to bet");

        if(player.checkbet(amount)){
            spin();
        }
        else{
            String choice = Console.printAndTakeString("You have insufficient balance.\nEnter \"yes\" to continue with some new amount or \"no\" to exit the game");
            if(choice.equals("yes"))
                play();
        }

    }

    @Override
    public boolean endGame() {
        return false;
    }

    public void result(){
        int endAmount = bet(amount);

        if(endAmount > amount)
            win(player, endAmount);
        else
            loss(player, endAmount);
        Console.print("Amount in your Bank : " + player.getBank());
    }


    public void spin() {

            for (int i = 0; i < this.slots.length; i++)
                this.slots[i] = (int)(Math.random() * 7 + 1);
            Console.print(slots[0],slots[1], slots[2]);

        result();

    }



    @Override
    public int bet(int initialAmount) {
        int returnBet = checkMatching(initialAmount);

        if(returnBet > initialAmount)
            Console.print("Congrats!! You won");
        else
            Console.print("YOu Lost!!");
        return returnBet;
    }

    public int checkMatching(int initialAmount){
        if(slots[0] == slots[1] && slots[1] == slots[2])
            return initialAmount * 3;
        else if(slots[0] == slots[1] || slots[1] == slots[2] || slots[2] == slots[0])
            return initialAmount * 2;
        else
            return initialAmount;
    }

    public int[] getSlots() {
        return this.slots;
    }

    public void setSlots(int[] slots) {
        this.slots = slots;
    }
}
