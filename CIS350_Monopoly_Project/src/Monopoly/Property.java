package Monopoly;

import javax.swing.*;

/**
 * Each property which will be a single tile on the overall
 * game board.
 * Tiles without buying/selling options (such as GO and jail)
 * will be identified with a price and rent of $0.
 */

public class Property {
    private String name;
    private int price;
    private int rent;
    private int ownerNum;
    private char color;
    public ImageIcon propertycard;

    public Property() {

    }

    public Property(String n, int price, int rent, int ownerNum) {
        this.name = n;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
    }

    public Property(String n, int price, int rent, int ownerNum, char color, ImageIcon card) {
        this.name = n;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
        this.color = color;
        this.propertycard = card;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public int getOwnerNum() {
        return ownerNum;
    }

    public void setOwnerNum(int ownerNum) {
        this.ownerNum = ownerNum;
    }

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }
}
