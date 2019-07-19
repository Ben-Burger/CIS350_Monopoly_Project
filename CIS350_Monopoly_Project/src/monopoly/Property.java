package monopoly;

import javax.swing.ImageIcon;

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
    private ImageIcon image;

    public Property() {

    }

    public Property(String name, int price, int rent, int ownerNum) {
    	this.name = name;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
    }

    public Property(String name, int price, int rent, int ownerNum, char color, ImageIcon image) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
        this.color = color;
        this.image = image;
    }
    
    public int getPrice() {
    	return price;
    }

	public ImageIcon getImage() {
		return image;
	}

	public void setimage(ImageIcon image) {
		this.image = image;
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

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
