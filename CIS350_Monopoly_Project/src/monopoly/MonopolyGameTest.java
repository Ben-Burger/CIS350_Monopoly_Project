package monopoly;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class MonopolyGameTest {
    private Game test;

    @Before
    public void buildBoard() {
        test = new Game(4);
    }

    @Test
    public void passTurn() {
        test.nextTurn();
        assertEquals(2, test.getCurrentPlayerNum());
    }

    @Test
    public void moving() {
        int spaces = test.move();
        assertEquals(spaces, test.getCurrentPlayerPosition());
    }



    @Test
    public void propertyActionForNothing() {
        test.setCurrentPlayerPosition(10);
        assertEquals(0, test.propertyActions());
    }

    @Test
    public void propertyActionForAlreadyOwnedProperty() {
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        assertEquals(0, test.propertyActions());
    }

    @Test
    public void propertyActionForUnownedProperty() {
        test.setCurrentPlayerPosition(9);
        assertEquals(1, test.propertyActions());
    }

    @Test
    public void propertyActionForOtherPlayerOwnedProperty() {
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(9);
        assertEquals(2, test.propertyActions());
    }

    @Test
    public void payRent() {
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        test.nextTurn();
        test.setCurrentPlayerPosition(39);
        test.payRent();
        assertEquals(1450, test.getCurrentPlayer().getMoney());
        assertEquals(1150, test.getPlayers().get(0).getMoney());
    }

    @Test
    public void goesBankrupt() {
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        test.setCurrentPlayer(3);
        test.setCurrentPlayerPosition(39);
        test.getCurrentPlayer().setMoney(20);
        assertTrue(test.payRent());
    }

    @Test
    public void butMultipleOfSameColor() {
        test.setCurrentPlayerPosition(37);
        test.buyProperty();
        test.setCurrentPlayerPosition(39);
        test.buyProperty();
        assertEquals((Integer)2, test.getCurrentPlayer().getProperties().get('b'));
    }
}
