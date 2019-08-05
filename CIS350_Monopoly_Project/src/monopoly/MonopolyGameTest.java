package monopoly;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * JUnit test class that tests the game class.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	7/20/2019 
 */
public class MonopolyGameTest {
	
	/** Game object to be tested. */
    private Game test;

    /**
     * Makes a new game before each test.
     */
    @Before
    public void buildBoard() {
        test = new Game(4);
    }

    /**
     * Test the next turn feature.
     * Making sure the current player updates.
     */
    @Test
    public void passTurn() {
        test.nextTurn();
        assertEquals(2, test.getCurrentPlayerNum());
    }

    /**
     * Test the move feature.
     * Making sure the player moves the correct number of spaces.
     */
//    @Test
//    public void moving() {
//        int spaces = test.move();
//        assertEquals(spaces, test.getCurrentPlayerPosition());
//    }

    /**
     * Test that a property will report as having no action.
     */
    @Test
    public void propertyActionForNothing() {
        test.setCurrentPlayerPosition(10);
        assertEquals(-1, test.propertyActions());
    }

    /**
     * Test that an owned property will report as being owned by the player.
     */
    @Test
    public void propertyActionForAlreadyOwnedProperty() {
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        assertTrue(test.propertyActions() > 0);
    }

    /**
     * Test that an unowned property will report as being unowned.
     */
    @Test
    public void propertyActionForUnownedProperty() {
        test.setCurrentPlayerPosition(9);
        assertEquals(0, test.propertyActions());
    }

    /**
     * Testing that paying rent works correctly.
     */
    @Test
    public void payRent() {
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(39);
        test.payRent();
        assertEquals(1450, test.getCurrentPlayer().getMoney());
        assertEquals(1150, test.getPlayer(1).getMoney());
    }

    
    /**
     * Testing that a player goes bankrupt when they run out of money.
     */
    @Test
    public void goesBankrupt() {
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        test.setCurrentPlayer(3);
        test.setCurrentPlayerPosition(39);
        test.getCurrentPlayer().setMoney(20);
        assertTrue(test.payRent());
    }

    /**
     * Testing that a player's properties update properly.
     */
    @Test
    public void buyMultipleOfSameColor() {
        test.setCurrentPlayerPosition(37);
        test.buyProperty();
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        assertEquals((Integer) 2, test.getCurrentPlayer().getProperties().get('b'));
    }

    /**
     * Testing that two property colors will charge double when monopolized.
     */
    @Test
    public void chargeDoubleForAllTwoOwned() {
        test.setCurrentPlayerPosition(37);
        test.buyProperty();
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(39);
        test.payRent();
        assertEquals(1400, test.getPlayerMoney(2));
    }

    /**
     * Testing that three property colors will charge double when monopolized.
     */
    @Test
    public void chargeDoubleForAllThreeOwned() {
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.setCurrentPlayerPosition(8);
        test.buyProperty();
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(9);
        test.payRent();
        assertEquals(1484, test.getPlayerMoney(2));
    }

    /**
     * Testing that three property colors will charge double when monopolized.
     */
    @Test
    public void chargeForTwoRailroadsOwned() {
        test.setCurrentPlayerPosition(5);
        test.buyProperty();
        test.setCurrentPlayerPosition(15);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(5);
        test.payRent();
        test.setCurrentPlayerPosition(15);
        test.payRent();
        assertEquals(1400, test.getPlayerMoney(2));
    }

    /**
     * Testing that three property colors will charge double when monopolized.
     */
    @Test
    public void chargeForThreeRailroadsOwned() {
        test.setCurrentPlayerPosition(5);
        test.buyProperty();
        test.setCurrentPlayerPosition(15);
        test.buyProperty();
        test.setCurrentPlayerPosition(25);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(5);
        test.payRent();
        test.setCurrentPlayerPosition(15);
        test.payRent();
        test.setCurrentPlayerPosition(25);
        test.payRent();
        assertEquals(1275, test.getPlayerMoney(2));
    }

    /**
     * Testing that all railroads will charge $100 when monopolized.
     */
    @Test
    public void chargeForFourRailroadsOwned() {
        test.setCurrentPlayerPosition(5);
        test.buyProperty();
        test.setCurrentPlayerPosition(15);
        test.buyProperty();
        test.setCurrentPlayerPosition(25);
        test.buyProperty();
        test.setCurrentPlayerPosition(35);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(5);
        test.payRent();
        test.setCurrentPlayerPosition(15);
        test.payRent();
        test.setCurrentPlayerPosition(25);
        test.payRent();
        test.setCurrentPlayerPosition(35);
        test.payRent();
        assertEquals(1100, test.getPlayerMoney(2));
    }

    /**
     *Tests cards that pay out to everyone.
     */
    @Test
    public void cardThatPaysToEveryone() {
        test.drawSpecificCard('a', 12);
        assertEquals(1350, test.getCurrentPlayerMoney());
        assertEquals(1550, test.getPlayerMoney(2));
        assertEquals(1550, test.getPlayerMoney(3));
        assertEquals(1550, test.getPlayerMoney(4));
    }

    @Test
    public void cardThatReceivesFromEveryone() {
        test.drawSpecificCard('e', 9);
        assertEquals(1530, test.getCurrentPlayerMoney());
        assertEquals(1490, test.getPlayerMoney(2));
        assertEquals(1490, test.getPlayerMoney(3));
        assertEquals(1490, test.getPlayerMoney(4));
    }

    @Test
    public void cardThatMovesBackSpaces() {
        test.moveTo(7);
        test.drawSpecificCard('a', 7);
        assertEquals(4, test.getCurrentPlayerPosition());
        assertEquals(1500, test.getCurrentPlayerMoney());
    }

    @Test
    public void cardThatSendsYouToGo() {
        test.moveTo(7);
        test.drawSpecificCard('a', 0);
        assertEquals(0, test.getCurrentPlayerPosition());
        assertEquals(1700, test.getCurrentPlayerMoney());
    }

    @Test
    public void buyHouse() {
        test.setCurrentPlayerPosition(1);
        test.buyProperty();
        test.setCurrentPlayerPosition(3);
        test.buyProperty();
        test.buyHouse(1);
        assertEquals(1, test.getPropertyHouses(1));
    }

    @Test
    public void chargesRentForHouse() {
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.setCurrentPlayerPosition(8);
        test.buyProperty();
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.buyHouse(6);
        test.nextTurn();
        test.moveTo(6);
        test.payRent();
        assertEquals(1, test.getPropertyHouses(6));
        assertEquals(1470, test.getCurrentPlayerMoney());
    }
}
