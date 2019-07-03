package Monopoly;

public class Player {
    int playerNum;
    int money;
    //tracks placement according to index of Board
    int currentBoardPlacement;


    public Player() {
    }

    public Player(int playerNum, int money) {
        this.playerNum = playerNum;
        this.money = money;
        currentBoardPlacement = 0;
    }
}
