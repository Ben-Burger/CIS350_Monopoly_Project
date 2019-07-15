package Monopoly;

import java.util.Map;
import java.util.TreeMap;

public class Player {
    int playerNum;
    int money;
    Map<Character, Integer> properties;
    //tracks placement according to index of Board
    int boardPosition;


    public Player() {
    }

    /**
     * Creates a player in the game.
     * @param playerNum Player number in the game such as Player 1, Player
     *                  2, etc.
     * @param money Total amount of money had by this player
     */
    public Player(int playerNum, int money) {
        properties = new TreeMap<>();
        this.playerNum = playerNum;
        this.money = money;
        boardPosition = 0;
    }

    public void addProperty(char color) {
        if (properties.containsKey(color)) {
            properties.replace(color, properties.get(color) + 1);
        } else {
            properties.put(color, 1);
        }
    }
}