package monopoly;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Covers game logic and turn order.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis 
 * @version	7/20/2019 
 */
public class Game {
	
	/** Player whose turn it currently is. */
	private Player currentPlayer;
	
	/** Player number of the current player. */
	private int currentPlayerNum;
	
	/** Current position of the current player. */
	private int currentPosition;
	
	/** The game board consisting of all the properties. */
	private Property[] board;
	
	/** All of the players in the game. */
	private ArrayList<Player> players;

	/**
	 * Returns the current player.
	 * @return Player - the current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Returns the current player.
	 * @param playerNum - the player number
	 * @return Player - the desired player
	 */
	public Player getPlayer(final int playerNum) {
		return players.get(playerNum - 1);
	}
	
	/**
	 * Sets the current player.
	 * @param playerNum - the player who will be the new current player
	 */
	public void setCurrentPlayer(final int playerNum) {
		currentPlayer = players.get(playerNum - 1);
		currentPlayerNum = playerNum - 1;
	}
	
	/**
	 * Returns the player number of the current player.
	 * @return playerNum - the player number of the current player
	 */
	public int getCurrentPlayerNum() {
		return currentPlayer.getPlayerNum();
	}

	/**
	 * Returns the current position of the current player.
	 * @return position - the current position of the current player
	 */
	public int getCurrentPlayerPosition() {
		return currentPlayer.getPosition();
	}

	/**
	 * Sets the current position of the current player.
	 * @param position - the new position of the current player
	 */
	public void setCurrentPlayerPosition(final int position) {
		currentPlayer.setPosition(position);
		currentPosition = position;
	}

	/**
	 * Returns the amount of money owned by a player.
	 * @param playerNum - a player's number 
	 * @return money - the amount of money owned by a player
	 */
	public int getPlayerMoney(final int playerNum) {
		return players.get(playerNum - 1).getMoney();
	}

	/**
	 * Returns the name of a property.
	 * @param propertyNum - the number of the property
	 * @return name - the name of the property
	 */
	public String getPropertyName(final int propertyNum) {
		return board[propertyNum].getName();
	}

	/**
	 * Returns the owner of a property.
	 * @param propertyNum - the number of the property
	 * @return owenrNum - the owner number of the property
	 */
	public int getPropertyOwner(final int propertyNum) {
		return board[propertyNum].getOwnerNum();
	}

	/**
	 * Returns the price of a property.
	 * @param propertyNum - the number of the property
	 * @return price - the price of the property
	 */
	public int getPropertyPrice(final int propertyNum) {
		return board[propertyNum].getPrice();
	}

	/**
	 * Returns the rent for a property.
	 * @param propertyNum - the number of the property
	 * @return rent - the rent for the property
	 */
	public int getPropertyRent(final int propertyNum) {
		return board[propertyNum].getRent();
	}

	/**
	 * Returns the image for a property.
	 * @param propertyNum - the number of the property
	 * @return image - the image of the property
	 */
	public ImageIcon getPropertyImage(final int propertyNum) {
		return board[propertyNum].getImage();
	}

	/**
	 * Initializes game with given number of players.
	 * @param numPlayers - Total number of players in the game.
	 */
	public Game(final int numPlayers) {
		players = new ArrayList<Player>();
		for (int i = 1; i <= numPlayers; i++) {
			players.add(new Player(i, 1500));
		}
		createProperties();
		currentPlayerNum = 0;
		currentPlayer = players.get(0);
		currentPosition = 0;
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
	 * Moves a player 2d6 spaces on the board.
	 * @return number of spaces moved
	 */
	public int move() {
		int movement = rollDice();	
		//        int movement = 1;		//TODO for testing

		int newPosition = currentPlayer.getPosition() + movement;
		if (newPosition > 39) {
			newPosition -= 39;
			currentPlayer.addMoney(200);
		}
		currentPlayer.setPosition(newPosition);
		currentPosition = newPosition;
		return movement;
	}

	/**
	 * The current player becomes the owner of the property that he is
	 * currently on. That properties price is taken out of the player's money.
	 * @return price - the price for the property
	 */
	public int buyProperty() {
		int price = board[currentPosition].getPrice();

		currentPlayer.subtractMoney(price);

		board[currentPosition].setOwnerNum(currentPlayerNum + 1);

		currentPlayer.addProperty(board[currentPosition].getColor());

		return price;
	}

	/**
	 * Changes the current player to the next one in order.
	 */
	public void nextTurn() {
		currentPlayerNum++;
		if (currentPlayerNum > players.size() - 1) {
			currentPlayerNum = 0;
		}
		currentPlayer = players.get(currentPlayerNum);
		currentPosition = currentPlayer.getPosition();
	}

	/**
	 * Gets the proper possible action for the space landed on by a player.
	 * @return 0: Nothing to buy and no rent to pay. Do nothing
	 *         1: Unowned property. Prompt to buy.
	 *         2: Owned property or other payment to be made. Prompt for
	 *         payment.
	 */
	public int propertyActions() {
		if ((board[currentPosition].getPrice() == 0
				&& board[currentPosition].getRent() == 0)
				|| board[currentPosition].getOwnerNum() == currentPlayerNum + 1) {
			return 0;
		} else if ((board[currentPosition].getPrice() != 0)
				&& (board[currentPosition].getOwnerNum() == 0 
				|| board[currentPosition].getOwnerNum() == currentPlayerNum + 1)) {
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
		int owner = board[currentPosition].getOwnerNum();
		int currentPlayerMoney = currentPlayer.getMoney();

		currentPlayer.subtractMoney(rent);

		if (currentPlayer.getMoney() < 0) {
			if (board[currentPosition].getOwnerNum() != -1) {
				players.get(owner - 1).addMoney(currentPlayerMoney);
			}
			return true;
		}
		if (board[currentPosition].getOwnerNum() != -1) {
			players.get(owner - 1).addMoney(rent);
		}
		return false;
	}

	/**
	 * Calculates amount of rent owed, taking into account owning all
	 * properties of one color or owning multiple railroads.
	 * @return Total rent owed.
	 */
	public int calculateRent() {
		int owner = board[currentPosition].getOwnerNum();
		char color = board[currentPosition].getColor();
		int rent = board[currentPosition].getRent();

		if ((color == 'b' || color == 'n' || color == 'u') && players.get(owner - 1).getProperties().get(color) == 2) {
			rent *= 2;
		} else if (color == 'r') {
			rent *= players.get(owner - 1).getProperties().get(color);
		} else if (players.get(owner - 1).getProperties().get(color) == 3) {
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
		board[1] = new Property("Mediterranean Avenue", 60, 2, 0, 'n', new ImageIcon("pictures/Mediterranean Ave.png"));
		board[2] = new Property("Community Chest", 0, 0, 0);
		board[3] = new Property("Baltic Avenue", 60, 4, 0, 'n', new ImageIcon("pictures/Baltic Ave.png"));
		board[4] = new Property("Income Tax", 0, 200, -1);
		board[5] = new Property("Reading Railroad", 200, 25, 0, '=', new ImageIcon("pictures/Reading Railroad.png"));
		board[6] = new Property("Oriental Avenue", 100, 6, 0, 't', new ImageIcon("pictures/Oriental Ave.png"));
		board[7] = new Property("Chance", 0, 0, 0);
		board[8] = new Property("Vermont Avenue", 100, 6, 0, 't', new ImageIcon("pictures/Vermont Ave.png"));
		board[9] = new Property("Connecticut Avenue", 120, 8, 0, 't', new ImageIcon("pictures/Connecticut Ave.png"));
		board[10] = new Property("Jail", 0, 0, 0);
		board[11] = new Property("St. Charles Place", 140, 10, 0, 'p', new ImageIcon("pictures/St. Charles Place.png"));
		board[12] = new Property("Electric Company", 150, 25, 0, 'u', new ImageIcon("pictures/Electric Company.png"));
		board[13] = new Property("States Avenue", 140, 10, 0, 'p', new ImageIcon("pictures/States Ave.png"));
		board[14] = new Property("Virginia Avenue", 160, 12, 0, 'p', new ImageIcon("pictures/Virginia Ave.png"));
		board[15] = new Property("Pennsylvania Railroad", 200, 25, 0, '=', new ImageIcon("pictures/Pennsylvania R.R.png"));
		board[16] = new Property("St. James Place", 180, 14, 0, 'o', new ImageIcon("pictures/St. James Place.png"));
		board[17] = new Property("Community Chest", 0, 0, 0);
		board[18] = new Property("Tennessee Avenue", 180, 14, 0, 'o', new ImageIcon("pictures/Tennessee Avenue.png"));
		board[19] = new Property("New York Avenue", 200, 16, 0, 'o', new ImageIcon("pictures/New York Avenue.png"));
		board[20] = new Property("Free Parking", 0, 0, 0);
		board[21] = new Property("Kentucky Avenue", 220, 18, 0, 'r', new ImageIcon("pictures/Kentucky Ave.png"));
		board[22] = new Property("Chance", 0, 0, 0);
		board[23] = new Property("Indiana Avenue", 220, 18, 0, 'r', new ImageIcon("pictures/Indiana Ave.png"));
		board[24] = new Property("Illinois Avenue", 240, 20, 0, 'r', new ImageIcon("pictures/Illinois Ave..png"));
		board[25] = new Property("B & O Railroad", 200, 25, 0, '=', new ImageIcon("pictures/B. and O. Railroad.png"));
		board[26] = new Property("Atlantic Avenue", 260, 22, 0, 'y', new ImageIcon("pictures/Atlantic Ave.png"));
		board[27] = new Property("Ventor Avenue", 260, 22, 0, 'y', new ImageIcon("pictures/Ventnor Ave.png"));
		board[28] = new Property("Water Works", 150, 25, 0, 'u', new ImageIcon("pictures/Water Works.png"));
		board[29] = new Property("Marvin Gardens", 280, 24, 0, 'y', new ImageIcon("pictures/Marvin Gardens.png"));
		board[30] = new Property("Go to Jail", 0, 0, 0);
		board[31] = new Property("Pacific Avenue", 300, 26, 0, 'g', new ImageIcon("pictures/Pacific Ave.png"));
		board[32] = new Property("North Carolina Avenue", 300, 26, 0, 'g', new ImageIcon("pictures/No. Carolina Ave.png"));
		board[33] = new Property("Community Chest", 0, 0, 0);
		board[34] = new Property("Pennsylvania Avenue", 320, 28, 0, 'g', new ImageIcon("pictures/Pennsylvania Ave.png"));
		board[35] = new Property("Short Line", 200, 25, 0, '=', new ImageIcon("pictures/Short Line R.R.png"));
		board[36] = new Property("Chance", 0, 0, 0);
		board[37] = new Property("Park Place", 350, 35, 0, 'b', new ImageIcon("pictures/Park Place.png"));
		board[38] = new Property("Luxury Tax", 0, 100, -1);
		board[39] = new Property("Boardwalk", 400, 50, 0, 'b', new ImageIcon("pictures/Boardwalk.png"));
	}
}
