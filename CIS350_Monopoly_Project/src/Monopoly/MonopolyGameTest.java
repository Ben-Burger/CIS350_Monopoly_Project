//package Monopoly;
//
//import Monopoly.Game;
//import org.junit.Before;
//import org.junit.Test;
//
//import static junit.framework.TestCase.assertEquals;
//import static junit.framework.TestCase.assertTrue;
//
//public class MonopolyGameTest {
//    private Game test;
//
//    @Before
//    public void buildBoard() {
//        test = new Game(4);
//    }
//
//    @Test
//    public void passTurn() {
//        test.nextTurn();
//        assertEquals(2, test.getCurrentPlayerNum());
//    }
//
//    @Test
//    public void moving() {
//        int spaces = test.move();
//        assertEquals(spaces, test.getCurrentPlayerPosition());
//    }
//
//    @Test
//    public void buyProperty() {
//        test.getCurrentPlayer().boardPosition = 5;
//        test.buyProperty();
//        assertEquals(1, test.getPropertyOwner(5));
//        assertEquals(1300, test.getCurrentPlayer().money);
//    }
//
//    @Test
//    public void propertyActionForNothing() {
//        test.getCurrentPlayer().boardPosition = 10;
//        assertEquals(0, test.propertyActions());
//    }
//
//    @Test
//    public void propertyActionForAlreadyOwnedProperty() {
//        test.getCurrentPlayer().boardPosition = 9;
//        test.buyProperty();
//        assertEquals(0, test.propertyActions());
//    }
//
//    @Test
//    public void propertyActionForUnownedProperty() {
//        test.getCurrentPlayer().boardPosition = 9;
//        assertEquals(1, test.propertyActions());
//    }
//
//    @Test
//    public void propertyActionForOtherPlayerOwnedProperty() {
//        test.getCurrentPlayer().boardPosition = 9;
//        test.buyProperty();
//        test.nextTurn();
//        test.getCurrentPlayer().boardPosition = 9;
//        assertEquals(2, test.propertyActions());
//    }
//
//    @Test
//    public void payRent() {
//        test.getCurrentPlayer().boardPosition = 39;
//        test.buyProperty();
//        test.nextTurn();
//        test.getCurrentPlayer().boardPosition = 39;
//        test.payRent();
//        assertEquals(1450, test.getCurrentPlayerMoney());
//        assertEquals(1150, test.getPlayers().get(0).money);
//    }
//
//    @Test
//    public void goesBankrupt() {
//        test.getCurrentPlayer().boardPosition = 39;
//        test.buyProperty();
//        test.setCurrentPlayer(3);
//        test.getCurrentPlayer().boardPosition = 39;
//        test.getCurrentPlayer().money = 20;
//        assertTrue(test.payRent());
//    }
//
//    @Test
//    public void butMultipleOfSameColor() {
//        test.getCurrentPlayer().boardPosition = 37;
//        test.buyProperty();
//        test.getCurrentPlayer().boardPosition = 39;
//        test.buyProperty();
//        assertEquals((Integer)2, test.getCurrentPlayer().properties.get('b'));
//    }
//}
