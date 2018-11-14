package io.zipcoder.casino;



import io.zipcoder.casino.game.Player;
import io.zipcoder.casino.game.cardgames.cardutilities.Card;
import io.zipcoder.casino.game.cardgames.cardutilities.Deck;
import io.zipcoder.casino.game.cardgames.cardutilities.Rank;
import io.zipcoder.casino.game.cardgames.cardutilities.Suit;
import io.zipcoder.casino.game.cardgames.individualcardgames.BlackJack;
import org.junit.*;
public class BlackJackTest {
    private DeckMock deck;
    @Before
    public void initialize(){
        deck = new DeckMock();
    }


@Test
public void clearHandsTest(){
    //Given
    PlayerMock playerMock = new PlayerMock();
    BlackJack blackJack = new BlackJack(playerMock);
    blackJack.playerTotal = 21;
    Integer actual = 0;
    //When
    blackJack.clearHands();
    //Then
    Assert.assertEquals(blackJack.playerTotal, actual);
}
    @Test
    public void endGameTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 21;
        //When
        boolean actual = blackJack.endGame();
        //Then
        Assert.assertFalse(actual);
    }


@Test
public void introTest(){
    //Given
    PlayerMock playerMock = new PlayerMock();
    BlackJack blackJack = new BlackJack(playerMock);
    String expected = "\n\n\n\n\n\n\n\n\n\n\n\n\n♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣\n" +
            "♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦ WELCOME TO BLACKJACK ♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♠♥♦♣\n" +
            "♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣♠♥♦♣\n";
    //When
    String actual = blackJack.intro();
    //Then
    Assert.assertEquals(expected, actual);

}

@Test
public void dealHouseTest(){
    //Given
    PlayerMock playerMock = new PlayerMock();
    BlackJack blackJack = new BlackJack(playerMock);
    boolean expected = true;
    //When
    boolean actual = blackJack.dealHouse();
    //Then
    Assert.assertTrue(actual);

}

@Test
public void handSumTest(){
    //Given
    PlayerMock playerMock = new PlayerMock();
    BlackJack blackJack = new BlackJack(playerMock);
    blackJack.getPlayerHand().add(new CardMock());
    blackJack.getHouseHand().add(new CardMock());
    int expected = 11;
    //When
    blackJack.handSum();
    int actual = blackJack.playerTotal;
    //Then
    Assert.assertEquals(expected,actual);


}
@Test
public void doBlackjackTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        int stakes = 1000;
        int expected = 12500;
        //When
        blackJack.doBlackjack(stakes);
        int actual = playerMock.getBank() + stakes;
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void winConditionsPlayerBustTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 22;
        int stakes = 1000;
        int expected = 9000;
        //When
        blackJack.winConditions(stakes);
        int actual = playerMock.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void winConditionsHouseBustTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 10;
        blackJack.houseTotal = 22;
        int stakes = 1000;
        int expected = 11000;
        //When
        blackJack.winConditions(stakes);
        int actual = playerMock.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void winConditionsPlayerWinsTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 20;
        blackJack.houseTotal = 10;
        int stakes = 1000;
        int expected = 11000;
        //When
        blackJack.winConditions(stakes);
        int actual = playerMock.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void winConditionsHouseWinsTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 10;
        blackJack.houseTotal = 20;
        int stakes = 1000;
        int expected = 9000;
        //When
        blackJack.winConditions(stakes);
        int actual = playerMock.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void winConditionsPushTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.playerTotal = 10;
        blackJack.houseTotal = 10;
        int stakes = 1000;
        int expected = 10000;
        //When
        blackJack.winConditions(stakes);
        int actual = playerMock.getBank();
        //Then
        Assert.assertEquals(expected,actual);

    }
    @Test
    public void dealHouseWith21Test(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.houseTotal = 22;
        boolean expected = true;
        //When
        boolean actual = blackJack.dealHouse();
        //Then
        Assert.assertTrue(actual);

    }
    @Test
    public void dealSumPrintTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.houseTotal = 22;
        boolean expected = true;
        //When
        boolean actual = blackJack.dealSumPrint();
        //Then
        Assert.assertTrue(actual);

    }
    @Test
    public void dealSumFirstShowTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.getHouseHand().add(new CardMock());
        blackJack.houseTotal = 22;
        boolean expected = true;
        //When
        boolean actual = blackJack.dealSumFirstShow();
        //Then
        Assert.assertTrue(actual);

    }
    @Test
    public void firstShownTest(){
        //Given
        PlayerMock playerMock = new PlayerMock();
        BlackJack blackJack = new BlackJack(playerMock);
        blackJack.houseTotal = 22;
        blackJack.getHouseHand().add(new CardMock());
        boolean expected = true;
        //When
        boolean actual = blackJack.firstShow();
        //Then
        Assert.assertTrue(actual);
    }

private class CardMock extends Card{
    private CardMock() {
        super(Suit.Spades, Rank.Ace);
    }


}
    public class DeckMock extends Deck{
        CardMock cardMock = new CardMock();
        public DeckMock() {
        }
        @Override
        public Card dealCard(){
            return cardMock;

        }

    }

    private class PlayerMock extends Player{

        public PlayerMock() {
            super("John", 10000);
        }
    }
}

