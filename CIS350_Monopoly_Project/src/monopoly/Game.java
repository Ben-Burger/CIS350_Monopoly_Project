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

	/** Chance and Community Chest cards. */
	private CardDecks decks;

	/** Player whose turn it currently is. */
	private Player currentPlayer;
	
	/** Player number of the current player. (0-3) */
	private int currentPlayerNum;
	
	/** Current position of the current player. */
	private int currentPosition;
	
	/** The game board consisting of all the properties. */
	private Property[] board;
	
	/** All of the players in the game. */
	private ArrayList<Player> players;
	
	/** Represents one of the two dice. */
	GVdie die1 = new GVdie(80);

	/** Represents one of the two dice. */
	GVdie die2 = new GVdie(80);

	public boolean subtractMoney(int playerNum, int amount) {
		getPlayer(playerNum).subtractMoney(amount);
		if (getPlayerMoney(playerNum) <= 0) {
			return true;
		}
		return false;
	}
	
	public void addMoney(int playerNum, int amount) {
		getPlayer(playerNum).addMoney(amount);
	}
	
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
	 * Returns the amount of money owned by the current player.
	 * @return money - the amount of money owned
	 */
	public int getCurrentPlayerMoney() {
		return currentPlayer.getMoney();
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
	 * Checks if the current player is bankrupt.
	 * @return bankrupt - true or false
	 */
	public boolean checkCurrentPlayerBankrupt() {
		return currentPlayer.isBankrupt();
	}
	
	/**
	 * Set the current player bankrupt or not bankrupt.
	 * @param bankrupt - true or false
	 */
	public void setCurrentPlayerBankrupt(final boolean bankrupt) {
		currentPlayer.setBankrupt(bankrupt);
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
		players = new ArrayList<>();
		for (int i = 1; i <= numPlayers; i++) {
			players.add(new Player(i, 1500));		//TODO for testing
		}
		createProperties();
		currentPlayerNum = 0;
		currentPlayer = players.get(0);
		currentPosition = 0;
		decks = new CardDecks();
	}

	/**
	 * Rolls two dice and gets the sum.
	 * @return Total of two six sided dice rolls
	 */
	public int rollDice() {
//		Random d6 = new Random();
//		return d6.nextInt(6) + d6.nextInt(6) + 2;
		die1.roll();
		die2.roll();

		return die1.getValue() + die2.getValue();
	}

	/**
	 * Moves a player 2d6 spaces on the board.
	 * @return number of spaces moved
	 */
	public int move() {
		int movement = rollDice();
//		        int movement = 1;		//TODO for testing

		int newPosition = currentPlayer.getPosition() + movement;
		if (newPosition > 39) {
			newPosition -= 39;
			currentPlayer.addMoney(200);
		}
		currentPlayer.setPosition(newPosition);
		currentPosition = newPosition;
		return movement;
	}

	public void moveTo(int spot) {
		int tmp = currentPosition;
		setCurrentPlayerPosition(spot);
		if (spot != 10 && spot < tmp) {
			currentPlayer.addMoney(200);
		}
	}

	/**
	 * Draws a card from the Community Chest deck and performs the necessary
	 * actions required by the card.
	 */
	public String drawChest() {
		Card card = decks.drawChest();
		useCard(card);
		return card.getName();
	}

	/**
	 * Draws a card from the Chance deck and performs the necessary actions
	 * required by the card.
	 */
	public String drawChance() {
		Card card = decks.drawChance();
		useCard(card);
		return card.getName();
	}

	public void drawSpecificCard(final char c, final int index) {
		useCard(decks.getCard(c, index));
	}

	/**
	 * Uses the parameters on the given card to enact the correct consequences
	 * of the card.
	 * @param c Card drawn from either Community Chest or Chance deck.
	 */
	private void useCard(Card c) {
		switch (c.getType()) {
			case JAIL_FREE:
				currentPlayer.addProperty('j');
				break;
			case PAY_TO_BANK:
				currentPlayer.setMoney(currentPlayer.getMoney() - c.getNum());
				break;
			case MOVE_BACK_SPACES:
				setCurrentPlayerPosition(currentPosition - c.getNum());
				break;
			case MOVE_TO_NEAREST:
				if (c.getNum() == 5) {
					if (currentPosition > 35 || currentPosition < 5) {
						moveTo(5);
					} else if (currentPosition > 5 && currentPosition < 15) {
						setCurrentPlayerPosition(15);
					} else if (currentPosition > 15 && currentPosition < 25) {
						setCurrentPlayerPosition(25);
					} else {
						setCurrentPlayerPosition(35);
					}
				} else {
					if (currentPosition > 12 && currentPosition < 28) {
						setCurrentPlayerPosition(28);
					} else {
						moveTo(12);
					}
				}
				break;
			case PAY_TO_EVERYONE:
				int payment = c.getNum() * (players.size() - 1);
				currentPlayer.subtractMoney(payment);
				int max = players.size();
				for (int i = 0; i < max; i++) {
					if (i + 1 != getCurrentPlayerNum()) {
						players.get(i).addMoney(c.getNum());
					}
				}
				break;
			case MOVE_TO_POSITION:
				setCurrentPlayerPosition(c.getNum());
				break;
			case RECEIVE_FROM_BANK:
				currentPlayer.setMoney(currentPlayer.getMoney() + c.getNum());
				break;
			case RECEIVE_FROM_EVERYONE:
				int received = c.getNum() * (players.size() - 1);
				currentPlayer.addMoney(received);
				int mx = players.size();
				for (int i = 0; i < mx; i++) {
					if (i + 1 != getCurrentPlayerNum()) {
						players.get(i).subtractMoney(c.getNum());
					}
				}
				break;
		}
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
		if (currentPlayerNum >= players.size()) {
			currentPlayerNum = 0;
		}
		
		currentPlayer = players.get(currentPlayerNum);
		currentPosition = currentPlayer.getPosition();
		
		if (currentPlayer.isBankrupt()) {
			currentPlayerNum++;
			if (currentPlayerNum >= players.size()) {
				currentPlayerNum = 0;
			}
		}
		
		currentPlayer = players.get(currentPlayerNum);
		currentPosition = currentPlayer.getPosition();
	}

	/**
	 * Gets the proper possible action for the space landed on by a player.
	 * @return 	-1: Special property (chance, community chest, free parking, etc.)  
	 *         	 0: Unowned property. Prompt to buy.
	 *           1: Owned property. Prompt for payment.
	 */
	public int propertyActions() {
		if (board[currentPosition].getOwnerNum() > 0) {
			return 1;
		}	
		return board[currentPosition].getOwnerNum();
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
			currentPlayer.setMoney(0);
			if (owner != -1) {
				players.get(owner - 1).addMoney(currentPlayerMoney);
			}
			return true;
		} else if (owner != -1) {
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

		if (owner != -1) {
			if ((color == 'b' || color == 'n' || color == 'u')
					&& players.get(owner - 1).getProperties().get(color) == 2) {
				rent *= 2;
			} else if (color == 'r') {
				rent *= players.get(owner - 1).getProperties().get(color);
			} else if (players.get(owner - 1).getProperties().get(color) == 3) {
				rent *= 2;
			} 
		}
		return rent;
	}
	
	/**
	 * Checks if the game is over.
	 * @return playerNum - the winner's player number, or 0 if the game is still going
	 */
	public int checkForGameEnd() {
		int numActivePlayers = 0;
		int activePlayer = 0;
		
		for (int i = 0; i < players.size(); i++) {
			if (!players.get(i).isBankrupt()) {
				numActivePlayers++;
				activePlayer = i + 1;
			}
		}
		
		if (numActivePlayers == 1) {
			return activePlayer;
		}
		
		return 0;
	}

	/**
	 * Takes the given property and removes any owner attached to it. Removes
	 * appropriate property count from current player's property Map. Increases
	 * current players money by half the properties price (its mortgage value).
	 * @param name Player input of the property name
	 * @return false if property not found. Otherwise true.
	 */
	public boolean sellProperty(String name) {
		int index = -1;
		for (int i = 0; i < board.length; i++) {
				if (board[i].getName().equalsIgnoreCase(name)) {
					index = i;
					break;
			}
		}

		if (index == -1) {
			return false;
		}

		currentPlayer.removeProperty(board[index].getColor());
		board[index].setOwnerNum(0);
		currentPlayer.setMoney(currentPlayer.getMoney() + (board[index].getPrice() / 2));

		return true;
	}
	
	/*******************************************************************
	 * Returns the requested die. Legal values for num are 1 and 2.
	 * @param num the number of the desired die
	 * @return the requested die value
	 ******************************************************************/
	public GVdie getDie(int num) {
		if (num == 1)
			return die1;
		else
			return die2;
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
		board[0] = new Property("GO", 0, 0, -1);
		board[1] = new Property("Mediterranean Avenue", 60, 2, 0, 'n', new ImageIcon("pictures/Mediterranean Ave.png"));
		board[2] = new Property("Community Chest", 0, 0, -1);
		board[3] = new Property("Baltic Avenue", 60, 4, 0, 'n', new ImageIcon("pictures/Baltic Ave.png"));
		board[4] = new Property("Income Tax", 0, 200, -1);
		board[5] = new Property("Reading Railroad", 200, 25, 0, 'r', new ImageIcon("pictures/Reading Railroad.png"));
		board[6] = new Property("Oriental Avenue", 100, 6, 0, 't', new ImageIcon("pictures/Oriental Ave.png"));
		board[7] = new Property("Chance", 0, 0, -1);
		board[8] = new Property("Vermont Avenue", 100, 6, 0, 't', new ImageIcon("pictures/Vermont Ave.png"));
		board[9] = new Property("Connecticut Avenue", 120, 8, 0, 't', new ImageIcon("pictures/Connecticut Ave.png"));
		board[10] = new Property("Jail", 0, 0, -1);
		board[11] = new Property("St. Charles Place", 140, 10, 0, 'p', new ImageIcon("pictures/St. Charles Place.png"));
		board[12] = new Property("Electric Company", 150, 25, 0, 'u', new ImageIcon("pictures/Electric Company.png"));
		board[13] = new Property("States Avenue", 140, 10, 0, 'p', new ImageIcon("pictures/States Ave.png"));
		board[14] = new Property("Virginia Avenue", 160, 12, 0, 'p', new ImageIcon("pictures/Virginia Ave.png"));
		board[15] = new Property("Pennsylvania Railroad", 200, 25, 0, 'r', new ImageIcon("pictures/Pennsylvania R.R.png"));
		board[16] = new Property("St. James Place", 180, 14, 0, 'o', new ImageIcon("pictures/St. James Place.png"));
		board[17] = new Property("Community Chest", 0, 0, -1);
		board[18] = new Property("Tennessee Avenue", 180, 14, 0, 'o', new ImageIcon("pictures/Tennessee Avenue.png"));
		board[19] = new Property("New York Avenue", 200, 16, 0, 'o', new ImageIcon("pictures/New York Avenue.png"));
		board[20] = new Property("Free Parking", 0, 0, -1);
		board[21] = new Property("Kentucky Avenue", 220, 18, 0, 'r', new ImageIcon("pictures/Kentucky Ave.png"));
		board[22] = new Property("Chance", 0, 0, -1);
		board[23] = new Property("Indiana Avenue", 220, 18, 0, 'r', new ImageIcon("pictures/Indiana Ave.png"));
		board[24] = new Property("Illinois Avenue", 240, 20, 0, 'r', new ImageIcon("pictures/Illinois Ave..png"));
		board[25] = new Property("B & O Railroad", 200, 25, 0, 'r', new ImageIcon("pictures/B. and O. Railroad.png"));
		board[26] = new Property("Atlantic Avenue", 260, 22, 0, 'y', new ImageIcon("pictures/Atlantic Ave.png"));
		board[27] = new Property("Ventor Avenue", 260, 22, 0, 'y', new ImageIcon("pictures/Ventnor Ave.png"));
		board[28] = new Property("Water Works", 150, 25, 0, 'u', new ImageIcon("pictures/Water Works.png"));
		board[29] = new Property("Marvin Gardens", 280, 24, 0, 'y', new ImageIcon("pictures/Marvin Gardens.png"));
		board[30] = new Property("Go to Jail", 0, 0, -1);
		board[31] = new Property("Pacific Avenue", 300, 26, 0, 'g', new ImageIcon("pictures/Pacific Ave.png"));
		board[32] = new Property("North Carolina Avenue", 300, 26, 0, 'g', new ImageIcon("pictures/No. Carolina Ave.png"));
		board[33] = new Property("Community Chest", 0, 0, -1);
		board[34] = new Property("Pennsylvania Avenue", 320, 28, 0, 'g', new ImageIcon("pictures/Pennsylvania Ave.png"));
		board[35] = new Property("Short Line", 200, 25, 0, 'r', new ImageIcon("pictures/Short Line R.R.png"));
		board[36] = new Property("Chance", 0, 0, -1);
		board[37] = new Property("Park Place", 350, 35, 0, 'b', new ImageIcon("pictures/Park Place.png"));
		board[38] = new Property("Luxury Tax", 0, 100, -1);
		board[39] = new Property("Boardwalk", 400, 50, 0, 'b', new ImageIcon("pictures/Boardwalk.png"));
	}
}
