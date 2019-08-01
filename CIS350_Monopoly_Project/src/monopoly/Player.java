package monopoly;

import java.util.Map;
import java.util.TreeMap;

/**
 * Player class for a monopoly game.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	7/20/2019 
 */
public class Player {
	
	/** Player number. */
    private int playerNum;
    
    /** Amount of money owned by a player. */
    private int money;
    
    /** The amount of properties per color owned by a player. */
    private Map<Character, Integer> properties;
    
    /** The position of the player. */
    private int boardPosition;

    /** If the player is bankrupt or not. */
    private boolean bankrupt;
    
    /** How many turns the player has been in jail. */
    private int jailturns;

	/**
     * Creates a player in the game.
     * @param playerNum -player number in the game such as Player 1, Player 2, etc.
     * @param money - total amount of money had by this player
     */
    public Player(final int playerNum, final int money) {
        properties = new TreeMap<>();
        this.playerNum = playerNum;
        this.money = money;
        boardPosition = 0;
        bankrupt = false;
        jailturns = 0;
    }

    /**
     * Returns the player number.
     * @return playerNum - the player number
     */
    public int getPlayerNum() {
    	return playerNum;
    }
    
    /**
     * Sets the player's board position.
     * @param boardPosition - the new baord position
     */
    public void setPosition(final int boardPosition) {
    	this.boardPosition = boardPosition;
    }
    
    /**
     * Returns the position of the player.
     * @return boardPosition - the position of the player
     */
    public int getPosition() {
    	return boardPosition;
    }
    
    /**
     * Returns the amount of money owned by the player.
     * @return money - the amount of money 
     */
    public int getMoney() {
    	return money;
    }
    
    /**
     * Sets the amount of money owned by the player.
     * @param amount - the new amount of money
     */
    public void setMoney(final int amount) {
    	this.money = amount;
    }
    
    /**
     * Adds money to the current amount owned by the player.
     * @param amount - the amount to be added
     */
    public void addMoney(final int amount) {
    	money += amount;
    }
    
    /**
     * Subtracts money from the current amount owned by the player.
     * @param amount - the amount to be subtracted
     */
    public void subtractMoney(final int amount) {
    	money -= amount;
    }
    
    
    /**
     * Returns the number of properties by color owned by the player.
     * @return properties - the map of properties by color
     */
    public Map<Character, Integer> getProperties() {
    	return properties;
    }
    
    /**
     * Adds a property to the player.
     * @param color the color of the property
     */
    public void addProperty(final char color) {
        if (properties.containsKey(color)) {
            properties.replace(color, properties.get(color) + 1);
        } else {
            properties.put(color, 1);
        }
    }

	/**
	 * Checks if a player is bankrupt.
	 * @return bankrupt - true or false
	 */
	public boolean isBankrupt() {
		return bankrupt;
	}

	/**
	 * Set the player bankrupt or not bankrupt.
	 * @param bankrupt - true or false
	 */
	public void setBankrupt(final boolean bankrupt) {
		this.bankrupt = bankrupt;
	}
	
	/**
	 * Returns the number of turns in jail
	 * @return jailturns - integer 0-3
	 */
	public int getJailturns() {
		return jailturns;
	}

	/**
	 * Set the player number of jail turns.
	 * @param jailturns - integer value
	 */
	public void setJailturns(int jailturns) {
		this.jailturns = jailturns;
	}

    /**
     * Removes a property of the given color.
     * @param color the color of the property
     */
    public void removeProperty(final char color) {
        if (properties.get(color) == 1) {
            properties.remove(color);
        } else {
            properties.replace(color, properties.get(color) - 1);
        }
    }
}