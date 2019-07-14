package Monopoly;

import Monopoly.Game;
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
        assertEquals(spaces, test.getCurrentPlayer().getBoardPosition());
    }

    @Test
    public void buyProperty() {
        test.getCurrentPlayer().setBoardPosition(5);
        test.buyProperty();
        assertEquals(1, test.board[5].getOwnerNum());
        assertEquals(1300, test.getCurrentPlayer().getMoney());
    }

    @Test
    public void propertyActionForNothing() {
        test.getCurrentPlayer().setBoardPosition(10);
        assertEquals(0, test.propertyActions());
    }

    @Test
    public void propertyActionForAlreadyOwnedProperty() {
        test.getCurrentPlayer().setBoardPosition(9);
        test.buyProperty();
        assertEquals(0, test.propertyActions());
    }

    @Test
    public void propertyActionForUnownedProperty() {
        test.getCurrentPlayer().setBoardPosition(9);
        assertEquals(1, test.propertyActions());
    }

    @Test
    public void propertyActionForOtherPlayerOwnedProperty() {
        test.getCurrentPlayer().setBoardPosition(9);
        test.buyProperty();
        test.nextTurn();
        test.getCurrentPlayer().setBoardPosition(9);
        assertEquals(2, test.propertyActions());
    }

    @Test
    public void payRent() {
        test.getCurrentPlayer().setBoardPosition(39);
        test.buyProperty();
        test.nextTurn();
        test.getCurrentPlayer().setBoardPosition(39);
        test.payRent();
        assertEquals(1450, test.getCurrentPlayer().getMoney());
        assertEquals(1150, test.getPlayers().get(0).getMoney());
    }

    @Test
    public void goesBankrupt() {
        test.getCurrentPlayer().setBoardPosition(39);
        test.buyProperty();
        test.setCurrentPlayer(3);
        test.getCurrentPlayer().setBoardPosition(39);
        test.getCurrentPlayer().setMoney(20);
        assertTrue(test.payRent());
    }


}

