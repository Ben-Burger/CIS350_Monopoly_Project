package Monopoly;

import java.util.ArrayList;
import java.util.Random;

/**
 * Covers game logic and turn order.
 */

public class Game {
    public Player getCurrentPlayer() {
        return players.get(currentPlayer);
    }

    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Property[] getBoard() {
        return board;
    }

    public void setBoard(Property[] board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    private int currentPlayer;
    private Property[] board;
    private ArrayList<Player> players;

    public Game() {

    }


    /**
     * Initializes game with given number of players
     * @param numPlayers 2 to 4 players in a game.
     */
    public Game(int numPlayers) {
        players = new ArrayList<Player>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player(i, 1500));
        }
        createProperties();
        currentPlayer = 0;
    }

    /**
     * Rolls two dice and gets the sum.
     * @return Total of two six sided dice rolls
     */
    public int rollDice() {
        Random d6 = new Random();
        return d6.nextInt(6) + d6.nextInt(6) + 2;
    }

    /**
     * Moves a player 2d6 spaces on the board
     * @return Number of spaces moved
     */
    public int move() {
        int movement = rollDice();
        players.get(currentPlayer).boardPosition += movement;
        if (players.get(currentPlayer).boardPosition > 39) {
            players.get(currentPlayer).boardPosition -= 39;
            // Plus $200 for passing GO
            players.get(currentPlayer).money += 200;
        }
        // For future implementation of utilities costs
        return movement;
    }

    /**
     * The current player becomes the owner of the property that he is
     * currently on. That properties price is taken out of the player's money.
     */
    public void buyProperty() {
        players.get(currentPlayer).money -=
                board[players.get(currentPlayer).boardPosition].price;
        board[players.get(currentPlayer).boardPosition].ownerNum =
                currentPlayer;
        players.get(currentPlayer).addProperty(
                board[players.get(currentPlayer).boardPosition].color);
    }

    /**
     * Changes the current player to the next one in order.
     */
    public void nextTurn() {
        currentPlayer++;
        if (currentPlayer >= players.size() - 1) {
            currentPlayer = 1;
        }
    }

    /**
     * Gets the proper possible action for the space landed on by a player.
     * @return 0: Nothing to buy and no rent to pay. Do nothing
     *         1: Unowned property. Prompt to buy.
     *         2: Owned property or other payment to be made. Prompt for
     *         payment.
     */
    public int propertyActions() {
        if ((board[players.get(currentPlayer).boardPosition].price == 0
                && board[players.get(currentPlayer).boardPosition].rent == 0)
                || board[players.get(currentPlayer).boardPosition].ownerNum
                        == currentPlayer) {
            return 0;
        } else if (board[players.get(currentPlayer).boardPosition].price != 0
                && board[players.get(currentPlayer).boardPosition].ownerNum
                == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    /**
     * Current player pays rent of landed property.
     * @return True if player goes bankrupt. False otherwise.
     */
    public boolean payRent() {
        int rent = calculateRent();
        int owner =
                board[players.get(currentPlayer).boardPosition].ownerNum;
        players.get(currentPlayer).money -= rent;
        if (players.get(currentPlayer).money < 0) {
            if (board[players.get(currentPlayer).boardPosition].ownerNum != -1) {
                players.get(owner).money += rent
                        + players.get(currentPlayer).money;
            }
            return true;
        }
        if (board[players.get(currentPlayer).boardPosition].ownerNum != -1) {
            players.get(owner).money +=
                    board[players.get(currentPlayer).boardPosition].rent;
        }
        return false;
    }

    /**
     * Calculates amount of rent owed, taking into account owning all
     * properties of one color or owning multiple railroads.
     * @return Total rent owed.
     */
    private int calculateRent() {
        int owner = board[players.get(currentPlayer).boardPosition].ownerNum;
        char color = board[players.get(currentPlayer).boardPosition].color;
        int rent = board[players.get(currentPlayer).boardPosition].rent;
        if ((color == 'b' || color == 'n' || color == 'u') &&
                players.get(owner).properties.get(color) == 2) {
            rent *= 2;
        } else if (color == 'r') {
            rent *= players.get(owner).properties.get(color);
        } else if (players.get(owner).properties.get(color) == 3) {
            rent *= 2;
        }
        return rent;
    }

    /**
     * Populates board with properties as in Monopoly.
     * Owner being -1 indicates a rent payment that goes to none of
     * the players.
     * Utilities and railroads currently set to a flat rate of $25 for rent.
     */
    private void createProperties() {
        board = new Property[40];

        // Manually create properties on the board.
        board[0] = new Property("GO", 0, 0, 0);
        board[1] = new Property("Mediterranean Avenue", 60, 2, 0, 'n');
        board[2] = new Property("Blank", 0, 0, 0);
        board[3] = new Property("Baltic Avenue", 60, 4, 0, 'n');
        board[4] = new Property("Income Tax", 0, 200, -1);
        board[5] = new Property("Reading Railroad", 200, 25, 0);
        board[6] = new Property("Oriental Avenue", 100, 6, 0, 't');
        board[7] = new Property("Blank", 0, 0, 0);
        board[8] = new Property("Vermont Avenue", 100, 6, 0, 't');
        board[9] = new Property("Connecticut Avenue", 120, 8, 0, 't');
        board[10] = new Property("Jail", 0, 0, 0);
        board[11] = new Property("St. Charles Place", 140, 10, 0, 'p');
        board[12] = new Property("Electric Company", 150, 25, 0, 'u');
        board[13] = new Property("States Avenue", 140, 10, 0, 'p');
        board[14] = new Property("Virginia Avenue", 160, 12, 0, 'p');
        board[15] = new Property("Pennsylvania Railroad", 200, 25, 0);
        board[16] = new Property("St. James Place", 180, 14, 0, 'o');
        board[17] = new Property("Blank", 0, 0, 0);
        board[18] = new Property("Tennessee Avenue", 180, 14, 0, 'o');
        board[19] = new Property("New York Avenue", 200, 16, 0, 'o');
        board[20] = new Property("Free Parking", 0, 0, 0);
        board[21] = new Property("Kentucky Avenue", 220, 18, 0, 'r');
        board[22] = new Property("Blank", 0, 0, 0);
        board[23] = new Property("Indiana Avenue", 220, 18, 0, 'r');
        board[24] = new Property("Illinois Avenue", 240, 20, 0, 'r');
        board[25] = new Property("B & O Railroad", 200, 25, 0);
        board[26] = new Property("Atlantic Avenue", 260, 22, 0, 'y');
        board[27] = new Property("Ventor Avenue", 260, 22, 0, 'y');
        board[28] = new Property("Water Works", 150, 25, 0, 'u');
        board[29] = new Property("Marvin Gardens", 280, 24, 0, 'y');
        board[30] = new Property("Blank", 0, 0, 0);
        board[31] = new Property("Pacific Avenue", 300, 26, 0, 'g');
        board[32] = new Property("North Carolina Avenue", 300, 26, 0, 'g');
        board[33] = new Property("Blank", 0, 0, 0);
        board[34] = new Property("Pennsylvania Avenue", 320, 28, 0, 'g');
        board[35] = new Property("Short Line", 200, 25, 0);
        board[36] = new Property("Blank", 0, 0, 0);
        board[37] = new Property("Park Place", 350, 35, 0, 'b');
        board[38] = new Property("Luxury Tax", 0, 100, -1);
        board[39] = new Property("Boardwalk", 400, 50, 0, 'b');
    }
}
