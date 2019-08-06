package monopoly;

import javax.swing.ImageIcon;

/**
 * Each property which will be a single tile on the overall
 * game board.
 * Tiles without buying/selling options (such as GO and jail)
 * will be identified with a price and rent of $0.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	8/5/2019
 */
public class Property {
    /** Number of houses on property. 5 houses means 1 hotel */
    private int houses;

	/** Name of the property. */
    private String name;

    /** Price of the property. */
    private int price;

    /** Rent for the property. */
    private int[] rent;

    /** Owner number of the property. */
    private int ownerNum;

    /** Color of the property. */
    private char color;

    /** Image of the property, pops up when player lands on it. */
    private ImageIcon image;
    
    /** Position of the property. */
    private int position;

    /**
     * Creates a special property without color or an image.
     * @param name - name of the property
     * @param price - price of the property
     * @param r - rent for the property
     * @param ownerNum - owner number for the property
     * @param position - the board position of the property
     */
    public Property(final String name, final int price, final int[] r, final int ownerNum, final int position) {
    	this.name = name;
        this.price = price;
        this.ownerNum = ownerNum;
        this.color = '0';
        this.rent = new int[r.length];
        this.rent = new int[r.length];
        this.position = position;
        for (int i = 0; i < r.length; i++) {
            this.rent[i] = r[i];
        }
    }

    /**
     * Creates a special property without color or an image.
     * @param name - name of the property
     * @param price - price of the property
     * @param r - rent for the property
     * @param ownerNum - owner number for the property
     * @param color - color of the property
     * @param image - image of the property
     * @param position - the board position of the property
     */
    public Property(final String name, final int price, final int[] r, final int ownerNum, final char color, final ImageIcon image, final int position) {
        this.name = name;
        this.price = price;
        this.ownerNum = ownerNum;
        this.color = color;
        this.image = image;
        this.position = position;
        this.rent = new int[r.length];
        for (int i = 0; i < r.length; i++) {
            this.rent[i] = r[i];
        }
    }

    /**
	 * Returns the name of the property.
	 * @return name - name of the property
	 */
	public String getName() {
		return name;
	}

    /**
     * Sets the name of the property.
     * @param name - name of the property
     */
    public void setName(final String name) {
    	this.name = name;
    }

    /**
     * Returns price of the property.
     * @return price - price of the property
     */
    public int getPrice() {
    	return price;
    }

    /**
     * Sets the price of the property.
     * @param price - price of the property
     */
    public void setPrice(final int price) {
    	this.price = price;
    }

    /**
	 * Returns the rent for the property.
	 * @return rent - rent for the property
	 */
	public int getRent() {
		return rent[houses];
	}

	/**
	 * Returns the owner number of the property.
	 * @return ownerNum - owner number of the property
	 */
	public int getOwnerNum() {
		return ownerNum;
	}

	/**
	 * Sets the owner number of the property.
	 * @param ownerNum - owner number of the property
	 */
	public void setOwnerNum(final int ownerNum) {
		this.ownerNum = ownerNum;
	}

    /**
    * Returns the color of the property.
    * @return color - color of the property
    */
	public char getColor() {
		return color;
	}

	/**
    * Sets the color of the property.
    * @param color - color of the property
    */
    public void setColor(final char color) {
		this.color = color;
	}

    /**
     * Increments houses by one.
     */
    public void addHouse() {
        houses++;
    }

    /**
     * Decrements houses by one.
     */
    public void removeHouse() {
        houses--;
    }

    /**
     * This methods returns the amount of houses on the property.
     * 
     * @return int - the amount of houses on the proeprty
     */
	public int getHouses() {
        return houses;
    }

    /**
     * Sets the houses on a property to be equal to a number within 0 and 5.
     * @param num New number of houses on property within 0 and 5
     * @return true if an acceptable number, false otherwise.
     */
    public boolean setHouses(final int num) {
        if (num >= 0 && num <= 5) {
            houses = num;
            return true;
        }
        return false;
    }

    /**
     * Returns the image of the property.
     * @return image - image for the property
     */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
     * Returns position of the property.
     * @return position - position of the property
     */
    public int getPosition() {
    	return position;
    }
	@Override
    public String toString() { 
        return name; 
    } 
}
