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
    @Test
    public void moving() {
        int spaces = test.move();
        assertEquals(spaces, test.getCurrentPlayerPosition());
    }

    /**
     * Test that a property will report as having no action.
     */
    @Test
    public void propertyActionForNothing() {
        test.setCurrentPlayerPosition(10);
        assertEquals(0, test.propertyActions());
    }

    /**
     * Test that an owned property will report as being owned by the player.
     */
    @Test
    public void propertyActionForAlreadyOwnedProperty() {
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        assertEquals(0, test.propertyActions());
    }

    /**
     * Test that an unowned property will report as being unowned.
     */
    @Test
    public void propertyActionForUnownedProperty() {
        test.setCurrentPlayerPosition(9);
        assertEquals(1, test.propertyActions());
    }

    /**
     * Test that an owned property will report as being owned by a different player.
     */
    @Test
    public void propertyActionForOtherPlayerOwnedProperty() {
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(9);
        assertEquals(2, test.propertyActions());
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
}
