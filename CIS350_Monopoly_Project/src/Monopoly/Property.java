package Monopoly;

/**
 * Each property which will be a single tile on the overall
 * game board.
 * Tiles without buying/selling options (such as GO and jail)
 * will be identified with a price and rent of $0.
 */

public class Property {
    String name;
    int price;
    int rent;
    int ownerNum;
    char color;

    public Property() {

    }

    public Property(String n, int price, int rent, int ownerNum) {
        this.name = n;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
    }

    public Property(String n, int price, int rent, int ownerNum, char color) {
        this.name = n;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
        this.color = color;
    }
}
