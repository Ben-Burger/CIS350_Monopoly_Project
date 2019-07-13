package Monopoly_test;

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
        assertEquals(2, test.getCurrentPlayerVar());
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
        assertEquals(1, test.getBoard()[5].getOwnerNum());
        assertEquals(1300, test.getCurrentPlayer().getMoney());
    }
}
