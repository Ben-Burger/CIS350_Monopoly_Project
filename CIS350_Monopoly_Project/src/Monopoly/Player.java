package Monopoly;

public class Player {
    int playerNum;
    int money;
    //tracks placement according to index of Board
    int currentBoardPlacement;


    public Player() {
    }

    /**
     * Creates a player in the game.
     * @param playerNum Player number in the game such as Player 1, Player
     *                  2, etc.
     * @param money Total amount of money had by this player
     */
    public Player(int playerNum, int money) {
        this.playerNum = playerNum;
        this.money = money;
        currentBoardPlacement = 0;
    }
}
