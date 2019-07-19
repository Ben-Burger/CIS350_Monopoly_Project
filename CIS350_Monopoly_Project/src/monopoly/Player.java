package monopoly;

import java.util.Map;
import java.util.TreeMap;

public class Player {
	
	/** Player number. */
    private int playerNum;
    
    private int money; 
    private Map<Character, Integer> properties;
    //tracks placement according to index of Board
    private int boardPosition;


    public Player() {
    }

    /**
     * Creates a player in the game.
     * @param playerNum Player number in the game such as Player 1, Player
     *                  2, etc.
     * @param money Total amount of money had by this player
     */
    public Player(final int playerNum, final int money) {
        properties = new TreeMap<>();
        this.playerNum = playerNum;
        this.money = money;
        boardPosition = 0;
    }

    public int getPlayerNum() {
    	return playerNum;
    }
    
    public void setPosition(int boardPosition) {
    	this.boardPosition = boardPosition;
    }
    
    public int getPosition() {
    	return boardPosition;
    }
    
    public int getMoney() {
    	return money;
    }
    
    public void setMoney(int amount) {
    	this.money = amount;
    }
    
    public void addMoney(int amount) {
    	money += amount;
    }
    
    public void subtractMoney(int amount) {
    	money -= amount;
    }
    
    public Map<Character, Integer> getProperties() {
    	return properties;
    }
    
    public void addProperty(char color) {
        if (properties.containsKey(color)) {
            properties.replace(color, properties.get(color) + 1);
        } else {
            properties.put(color, 1);
        }
    }
}
