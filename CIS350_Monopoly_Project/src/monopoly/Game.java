package monopoly;

import java.util.*;

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
	private GVdie die1 = new GVdie(80);

	/** Represents one of the two dice. */
	private GVdie die2 = new GVdie(80);

	/** Represents the money received if player lands on free parking. */
	private int freeParkingfund = 0;

	public boolean checkBankrupt(int playerNum) {
		if (players.get(playerNum - 1).getMoney() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public ArrayList<Property> getProperties(int playerNum) {
		return players.get(playerNum - 1).getPropertiesList();
	}

	/**
	 * Gets Community Chest and Chance cards.
	 * @return CardDeck of Cards
	 */
	public CardDecks getDecks() {
		return decks;
	}

	/**
	 * Subtracts money from total money
	 * @param playerNum Number of requested player
	 * @param amount Amount of money to be removed
	 * @return True if money being removed results in player being bankrupt.
	 */
	public void subtractMoney(final int playerNum, final int amount) {
		getPlayer(playerNum).subtractMoney(amount);
	}

	/**
	 * Adds money to total money
	 * @param playerNum Number of requested player
	 * @param amount Amount of money to be added
	 */
	public void addMoney(final int playerNum, final int amount) {
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
	 * Get the number of houses for a space.
	 * @param space Requested space
	 * @return Number of houses
	 */
	public int getPropertyHouses(int space) {
		return board[space].getHouses();
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
	 * Returns the free Parking amount.
	 * @return freeParkingfund - int of the amount in free Parking
	 */
	public int getFreeParkingfund() {
		return freeParkingfund;
	}

	/**
	 * Set the free Parking amount.
	 * @param freeParkingfund - amount in free Parking
	 */
	public void setFreeParkingfund(final int freeParkingfund) {
		this.freeParkingfund = freeParkingfund;
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

//		int movement = 7;		//TODO for testing



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
     * Moves player to specific spot. If passes go and not going directly
     * to jail, they receive $200.
     * @param spot
     */
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
				freeParkingfund += c.getNum();
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


				if (currentPosition > c.getNum())
						currentPlayer.addMoney(200);

				setCurrentPlayerPosition(c.getNum());

				if (c.getNum() == 10) {
					currentPlayer.setJailturns(1);
				}
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

		currentPlayer.addProperty(board[currentPosition]);

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
     *
     * @return ArrayList of properties capable of receiving a house.
     */
	public ArrayList<Property> canGetHouse() {
	    ArrayList<Property> eligible = new ArrayList<>();
	    Map<Character, Integer> colorHouse = new TreeMap<Character, Integer>();
        for (Property p: currentPlayer.getPropertiesList()) {
        	if (p.getHouses() < 5) {
	            if (p.getColor() == 'n' || p.getColor() == 'b') {
	                if (currentPlayer.getProperties().get(p.getColor()) == 2) {
	                    eligible.add(p);
	                    if (colorHouse.containsKey(p.getColor())){
	                        if (colorHouse.get(p.getColor()) < p.getHouses()) {
	                            colorHouse.replace(p.getColor(), p.getHouses());
	                        }
	                    } else {
	                        colorHouse.put(p.getColor(), p.getHouses());
	                    }
	                }
	            } else if (p.getColor() != 'r' && p.getColor() != 'u') {
	                if (currentPlayer.getProperties().get(p.getColor()) == 3) {
	                    eligible.add(p);
	                    if (colorHouse.containsKey(p.getColor())) {
	                        if (colorHouse.get(p.getColor()) < p.getHouses()) {
	                            colorHouse.replace(p.getColor(), p.getHouses());
	                        }
	                    } else {
	                        colorHouse.put(p.getColor(), p.getHouses());
	                    }
	                }
	            }
	        }
        }
//        eligible.removeIf(p -> colorHouse.get(p.getColor()) <= p.getHouses());

	    return eligible;
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

		if (owner != -1 && board[currentPosition].getHouses() == 0) {
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
	 * Returns the cost of a house at particular space.
	 * @param space The property space being requested.
	 * @return The cost of a house on that space
	 */
	public int houseCost(final int space) {
		int cost = 0;
		switch (board[space].getColor()) {
			case 'n':
			case 't':
				cost = 50;
				break;
			case 'p':
			case 'o':
				cost = 100;
				break;
			case 'r':
			case 'y':
				cost = 150;
				break;
			case 'g':
			case 'b':
				cost = 200;
			default:
		}

		return cost;
	}

	/**
	 * Buys a house on the given space. Removes the cost of the house from
	 * currentPlayer's money and adds house to the property.
	 * @param space The property space being requested.
	 * @return True if player has the money to buy the property,
	 * false otherwise.
	 */
	public boolean buyHouse(int space) {
		int cost = 0;
		switch (board[space].getColor()) {
			case 'n':
			case 't':
				cost = 50;
				break;
			case 'p':
			case 'o':
				cost = 100;
				break;
			case 'r':
			case 'y':
				cost = 150;
				break;
			case 'g':
			case 'b':
				cost = 200;
		}
		if (getCurrentPlayerMoney() >= cost) {
			board[space].addHouse();
			subtractMoney(getCurrentPlayerNum(), cost);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sells a house on the given space. Gives currentPlayer appropriate money
	 * for the house.
	 * @param space Which space is to be sold.
	 */
	public void sellHouse(final int space) {
		int sale = 0;
		switch (board[space].getColor()) {
			case 'n':
			case 't':
				sale = 25;
				break;
			case 'p':
			case 'o':
				sale = 50;
				break;
			case 'r':
			case 'y':
				sale = 75;
				break;
			case 'g':
			case 'b':
				sale = 100;
		}
		addMoney(getCurrentPlayerNum(), sale);
	    board[space].removeHouse();
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

		currentPlayer.removeProperty(board[index]);
		board[index].setOwnerNum(0);
		currentPlayer.setMoney(currentPlayer.getMoney() + (board[index].getPrice() / 2));

		return true;
	}
	
	/**
	 * Returns the requested die. Legal values for num are 1 and 2.
	 * @param num the number of the desired die
	 * @return the requested die value
	 */
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
		board[0] = new Property("GO", 0, new int[] {0}, -1, 0);
		board[1] = new Property("Mediterranean Avenue", 60, new int[]{2, 10, 30, 90, 16, 250}, 0, 'n', new ImageIcon("pictures/Mediterranean Ave.png"), 1);
		board[2] = new Property("Community Chest", 0, new int[] {0}, -1, 2);
		board[3] = new Property("Baltic Avenue", 60, new int[] {4, 20, 60, 180, 320, 450}, 0, 'n', new ImageIcon("pictures/Baltic Ave.png"), 3);
		board[4] = new Property("Income Tax", 0, new int[] {200}, -1, 4);
		board[5] = new Property("Reading Railroad", 200, new int[] {25}, 0, 'r', new ImageIcon("pictures/Reading Railroad.png"), 5);
		board[6] = new Property("Oriental Avenue", 100, new int[] {6, 30, 90, 170, 400, 550}, 0, 't', new ImageIcon("pictures/Oriental Ave.png"), 6);
		board[7] = new Property("Chance", 0, new int[] {0}, -1, 7);
		board[8] = new Property("Vermont Avenue", 100, new int[] {6, 30, 90, 270, 400, 550}, 0, 't', new ImageIcon("pictures/Vermont Ave.png"), 8);
		board[9] = new Property("Connecticut Avenue", 120, new int[] {8, 40, 100, 300, 450, 600}, 0, 't', new ImageIcon("pictures/Connecticut Ave.png"), 9);
		board[10] = new Property("Jail", 0, new int[] {0}, -1, 10);
		board[11] = new Property("St. Charles Place", 140, new int[] {10, 50, 150, 450, 625, 750}, 0, 'p', new ImageIcon("pictures/St. Charles Place.png"), 11);
		board[12] = new Property("Electric Company", 150, new int[] {25}, 0, 'u', new ImageIcon("pictures/Electric Company.png"), 12);
		board[13] = new Property("States Avenue", 140, new int[] {10, 50, 150, 450, 625, 750}, 0, 'p', new ImageIcon("pictures/States Ave.png"), 13);
		board[14] = new Property("Virginia Avenue", 160, new int[] {12, 60, 180, 500, 700, 900}, 0, 'p', new ImageIcon("pictures/Virginia Ave.png"), 14);
		board[15] = new Property("Pennsylvania Railroad", 200, new int[] {25}, 0, 'r', new ImageIcon("pictures/Pennsylvania R.R.png"), 15);
		board[16] = new Property("St. James Place", 180, new int[] {14, 70, 200, 550, 750, 950}, 0, 'o', new ImageIcon("pictures/St. James Place.png"), 16);
		board[17] = new Property("Community Chest", 0, new int[] {0}, -1, 17);
		board[18] = new Property("Tennessee Avenue", 180, new int[] {14, 70, 200, 550, 750, 950}, 0, 'o', new ImageIcon("pictures/Tennessee Avenue.png"), 18);
		board[19] = new Property("New York Avenue", 200, new int[] {16, 80, 220, 600, 800, 1000}, 0, 'o', new ImageIcon("pictures/New York Avenue.png"), 19);
		board[20] = new Property("Free Parking", 0, new int[] {0}, -1, 20);
		board[21] = new Property("Kentucky Avenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 0, 'r', new ImageIcon("pictures/Kentucky Ave.png"), 21);
		board[22] = new Property("Chance", 0, new int[] {0}, -1, 22);
		board[23] = new Property("Indiana Avenue", 220, new int[] {18, 90, 250, 700, 875, 1050}, 0, 'r', new ImageIcon("pictures/Indiana Ave.png"), 23);
		board[24] = new Property("Illinois Avenue", 240, new int[] {20, 100, 300, 750, 925, 1100}, 0, 'r', new ImageIcon("pictures/Illinois Ave..png"), 24);
		board[25] = new Property("B & O Railroad", 200, new int[] {25}, 0, 'r', new ImageIcon("pictures/B. and O. Railroad.png"), 25);
		board[26] = new Property("Atlantic Avenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 0, 'y', new ImageIcon("pictures/Atlantic Ave.png"), 26);
		board[27] = new Property("Ventor Avenue", 260, new int[] {22, 110, 330, 800, 975, 1150}, 0, 'y', new ImageIcon("pictures/Ventnor Ave.png"), 27);
		board[28] = new Property("Water Works", 150, new int[] {25}, 0, 'u', new ImageIcon("pictures/Water Works.png"), 28);
		board[29] = new Property("Marvin Gardens", 280, new int[] {24, 120, 360, 850, 1025, 1200}, 0, 'y', new ImageIcon("pictures/Marvin Gardens.png"), 29);
		board[30] = new Property("Go to Jail", 0, new int[] {0}, -1, 30);
		board[31] = new Property("Pacific Avenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 0, 'g', new ImageIcon("pictures/Pacific Ave.png"), 31);
		board[32] = new Property("North Carolina Avenue", 300, new int[] {26, 130, 390, 900, 1100, 1275}, 0, 'g', new ImageIcon("pictures/No. Carolina Ave.png"), 32);
		board[33] = new Property("Community Chest", 0, new int[] {0}, -1, 33);
		board[34] = new Property("Pennsylvania Avenue", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, 0, 'g', new ImageIcon("pictures/Pennsylvania Ave.png"), 34);
		board[35] = new Property("Short Line", 200, new int[] {25}, 0, 'r', new ImageIcon("pictures/Short Line R.R.png"), 35);
		board[36] = new Property("Chance", 0, new int[] {0}, -1, 36);
		board[37] = new Property("Park Place", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, 0, 'b', new ImageIcon("pictures/Park Place.png"), 37);
		board[38] = new Property("Luxury Tax", 0, new int[] {100}, -1, 38);
		board[39] = new Property("Boardwalk", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, 0, 'b', new ImageIcon("pictures/Boardwalk.png"), 39);
	}
}