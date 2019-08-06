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
        assertEquals((Integer) 2, test.getCurrentPlayer()
                .getProperties().get('b'));
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
     * Tests pulling a specific card
     */
    @Test
    public void gettingCardName(){
        assertEquals("Advance to Go (Collect $200)",
                test.getDecks().getCard('a', 0).getName());
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

    /**
     * Tests card that receives money from everyone.
     */
    @Test
    public void cardThatReceivesFromEveryone() {
        test.drawSpecificCard('e', 9);
        assertEquals(1530, test.getCurrentPlayerMoney());
        assertEquals(1490, test.getPlayerMoney(2));
        assertEquals(1490, test.getPlayerMoney(3));
        assertEquals(1490, test.getPlayerMoney(4));
    }

    /**
     * Tests card that moves player back a number of spaces.
     */
    @Test
    public void cardThatMovesBackSpaces() {
        test.moveTo(7);
        test.drawSpecificCard('a', 7);
        assertEquals(4, test.getCurrentPlayerPosition());
        assertEquals(1500, test.getCurrentPlayerMoney());
    }

    /**
     * Tests card that sends you to GO.
     */
    @Test
    public void cardThatSendsYouToGo() {
        test.moveTo(7);
        test.drawSpecificCard('a', 0);
        assertEquals(0, test.getCurrentPlayerPosition());
        assertEquals(1700, test.getCurrentPlayerMoney());
    }

    /**
     * Tests cards that send you to specific locations and past GO.
     */
    @Test
    public void cardsThatSendYouToSpecificSpaces() {
        test.drawSpecificCard('a', 1);
        test.nextTurn();
        test.moveTo(7);
        test.drawSpecificCard('a', 10);
        test.nextTurn();
        test.drawSpecificCard('a', 11);

        assertEquals(24, test.getPlayer(1).getPosition());
        assertEquals(1500, test.getPlayerMoney(1));
        assertEquals(5, test.getPlayer(2).getPosition());
        assertEquals(1700, test.getPlayerMoney(2));
        assertEquals(39, test.getPlayer(3).getPosition());
        assertEquals(1500, test.getPlayerMoney(3));
    }

    /**
     * Tests buying a house for a property.
     */
    @Test
    public void buyHouse() {
        test.setCurrentPlayerPosition(1);
        test.buyProperty();
        test.setCurrentPlayerPosition(3);
        test.buyProperty();
        test.buyHouse(1);
        assertEquals(1, test.getPropertyHouses(1));
    }

    /**
     * Tests that properties with houses charge the appropriate rent.
     */
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

    /**
     * Checks for a single bankrupt player.
     */
    @Test
    public void checkPlayerBankrupt() {
        test.setCurrentPlayerBankrupt(true);
        assertTrue(test.checkCurrentPlayerBankrupt());
    }

    /**
     * Gets all properties after selling one.
     */
    @Test
    public void getTotalProperties() {
        test.setCurrentPlayerPosition(1);
        test.buyProperty();
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.setCurrentPlayerPosition(8);
        test.buyProperty();
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.sellProperty("Oriental Avenue");
        assertEquals(3, test.getProperties(1).size());
    }

    /**
     * Gives money correctly for selling houses.
     */
    @Test
    public void sellHouses(){
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.setCurrentPlayerPosition(8);
        test.buyProperty();
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        test.buyHouse(6);
        test.buyHouse(8);
        test.sellHouse(6);
        test.sellHouse(8);
        assertEquals(1130, test.getCurrentPlayerMoney());
    }

    /**
     * Gives money correctly for selling property
     */
    @Test
    public void sellProperty() {
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.sellProperty(test.getPropertyName(6));
        assertEquals(0, test.getPropertyOwner(6));
        assertEquals(1450, test.getCurrentPlayerMoney());
    }

    /**
     * Ensures game ends correctly.
     */
    @Test
    public void checkGameEnd() {
        assertEquals(0, test.checkForGameEnd());
        test.setCurrentPlayerBankrupt(true);
        test.nextTurn();
        test.setCurrentPlayerBankrupt(true);
        test.nextTurn();
        test.setCurrentPlayerBankrupt(true);
        test.nextTurn();
        test.nextTurn();
        assertEquals(4, test.checkForGameEnd());
    }

    /**
     * Gets properties that can have houses.
     */
    @Test
    public void getPropertiesForHouses() {
        test.setCurrentPlayerPosition(1);
        test.buyProperty();
        test.setCurrentPlayerPosition(6);
        test.buyProperty();
        test.setCurrentPlayerPosition(8);
        test.buyProperty();
        test.setCurrentPlayerPosition(9);
        test.buyProperty();
        assertEquals(3, test.canGetHouse().size());
    }

    @Test
    public void removesCardsFromDecks() {
        test.addMoney(1, 5000);
        for (int i = 0; i < 4; i++) {
            test.drawChance();
            test.drawChest();
        }
        assertEquals(11, test.getDecks().cardsLeftInChance());
        assertEquals(12, test.getDecks().cardsLeftInChest());
    }
}
