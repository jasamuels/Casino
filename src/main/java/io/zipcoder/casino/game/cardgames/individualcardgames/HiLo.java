
        package io.zipcoder.casino.game.cardgames.individualcardgames;

        import io.zipcoder.casino.game.Player;
        import io.zipcoder.casino.game.cardgames.cardutilities.Card;
        import io.zipcoder.casino.game.interfaces.Gamble;
        import io.zipcoder.casino.Console;

        import java.util.ArrayList;
        import java.util.Scanner;

        public class HiLo extends CardGame implements Gamble {

    //Variables
    Scanner in = new Scanner(System.in);

    boolean gameon;

    boolean cont;

    boolean enoughmoney;

    private Player player;

    //-----------Some Verbiage--------------

    String intro = "Welcome to HiLo!" + System.lineSeparator() + "Please place a bet.";

    //In Next Steps Matrix

    String nextsteps = "Feeling lucky today? Try your luck again then! (Yes or No)";

    String outro = "Thanks for playing!";

    String invalid = "That is not a valid response. Try again.";

    String assout = "You're out of cash! Come back with deeper pockets!!";

    //In Bet method

    String notenough = "You do not have the amount specified. Would you like to place another bet or return to the main menu? (Type \"Bet\" or \"Quit\"";

    String ohwell = "Better Luck Next Time!";


    //Constructors
    public HiLo(Player player) {
        this.player = player;
        gameon = true;
        cont = false;
    }

//Main game Method

    @Override
    public void play() {

        while (gameon) {
            // place a bet
            Console.print(intro);
            int betbet = in.nextInt();

            while (!enoughmoney) {
                bet(betbet); }

            //check to make sure deck has 52 cards
            if (deck.getDeck().isEmpty()) {
                deck.refreshDeck();
            }

            //deal a card to the player and house
            getPlayerHand().add(deck.dealCard());
            getHouseHand().add(deck.dealCard());

            //prompt for high or low input
            System.out.println("You have drawn a " + getPlayerHand().get(0).toString() + " would you like to bet High or Low?");
            String input = in.next();

            //evaluate for Win or Loss
            int playerCard = (getPlayerHand().get(0).getValue().ordinal());
            int dealerCard = (getHouseHand().get(0).getValue().ordinal());
            winlossmatrix(input, playerCard, dealerCard, betbet);

            //Next steps matrix
            nextstepsmatrix();
        }
    }

//Logic and Extra Methods


    @Override
    public boolean endGame() {

        return true;
    }


    @Override
    public int bet(int bet) {
        if (!player.checkbet(bet)) {
            Console.print(notenough);
            String response = in.next();
            if (response.equalsIgnoreCase("Bet")) { play(); }
            else if (response.equalsIgnoreCase("Quit")) {
                Console.print(ohwell);
                enoughmoney = false;
                gameon = false;
            }
        } else {
            enoughmoney = true;}
        return bet;
    }




    public void winlossmatrix(String nput, int theplayer, int dealer, int hedge) {
        int winnings = 0;

        if (nput.equalsIgnoreCase("high")){
            if(theplayer > dealer) {
                winnings = (int) Math.floor(highLogic(hedge, dealer));
                win(player, winnings);
                System.out.println("You've won! The dealer's card was " + getHouseHand().get(0).toString() + " and your new bank is " + player.getBank() + "!");
            } else if (theplayer == dealer ){
                System.out.println("Bad luck! You and the dealer drew the same card. Dealer had a " + getHouseHand().get(0).toString());
            } else {
                loss(player, hedge);
                System.out.println("You've lost! The dealer's card was " + getHouseHand().get(0).toString() + ". Your new bank is " + player.getBank());
                in.nextLine();
            }
        } else if (nput.equalsIgnoreCase("low")){
            if(theplayer < dealer){
                winnings = (int)Math.floor(lowLogic(hedge, dealer));
                win(player, winnings);
                System.out.println("You've won! The dealer's card was " + getHouseHand().get(0).toString() + " and your new bank is " + player.getBank() + "!");
            } else if (theplayer == dealer ){
                System.out.println("Bad luck! You and the dealer drew the same card. Dealer had a " + getHouseHand().get(0).toString());
            } else {
                loss(player, hedge);
                System.out.println("You've lost! The dealer's card was " + getHouseHand().get(0).toString() + ". Your new bank is " + player.getBank());
                in.nextLine();
            }
        }

    }



    public void  nextstepsmatrix () {
        while (!cont) {
            if (player.getBank() > 0) {
                Console.print(nextsteps);
                String again = in.next();
                if (again.equalsIgnoreCase("yes")) {
                    enoughmoney = false;
                    ArrayList<Card> newPlayerHand = new ArrayList<>();
                    ArrayList<Card> newHouseHand = new ArrayList<>();
                    setPlayerHand(newPlayerHand);
                    setHouseHand(newHouseHand);
                    play();
                } else if (again.equalsIgnoreCase("no")) {
                    Console.printInSameLine(outro);
                    enoughmoney = false;
                    gameon = false;
                    cont = true;
                    break;
                } else {
                    Console.print(invalid);
                }
            } else {
                Console.print(assout);
                enoughmoney = false;
                gameon = false;
                break;
            }
        }
    }




    public static double highLogic(int bet, int dealerCard){
        double sum = 0;

        switch (dealerCard) {
            case 1:
                sum = bet;
                return sum;
            case 2:
                sum = bet * 1.1;
                return sum;
            case 3:
                sum = bet * 1.2;
                return sum;
            case 4:
                sum = bet * 1.4;
                return sum;
            case 5:
                sum = bet * 1.4;
                return sum;
            case 6:
                sum = bet * 1.5;
                return sum;
            case 7:
                sum = bet * 1.8;
                return sum;
            case 8:
                sum = bet * 2;
                return sum;
            case 9:
                sum = bet * 3;
                return sum;
            case 10:
                sum = bet * 4;
                return sum;
            case 11:
                sum = bet * 5;
                return sum;
            case 12:
                sum = bet * 12;
                return sum;
            case 13:
                sum = bet;
                return sum;
        }

        return sum;
    }

    public static double lowLogic(int bet, int dealerCard){
        double sum = 0;

        switch (dealerCard) {
            case 1:
                sum = bet;
                return sum;
            case 2:
                sum = bet * 12;
                return sum;
            case 3:
                sum = bet * 5;
                return sum;
            case 4:
                sum = bet * 3;
                return sum;
            case 5:
                sum = bet * 3;
                return sum;
            case 6:
                sum = bet * 2;
                return sum;
            case 7:
                sum = bet * 1.8;
                return sum;
            case 8:
                sum = bet * 1.5;
                return sum;
            case 9:
                sum = bet * 1.4;
                return sum;
            case 10:
                sum = bet * 1.3;
                return sum;
            case 11:
                sum = bet * 1.2;
                return sum;
            case 12:
                sum = bet * 1.1;
                return sum;
            case 13:
                sum = bet;
                return sum;
        }

        return sum;
    }


}

