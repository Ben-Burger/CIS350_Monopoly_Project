package monopoly;

import javax.swing.ImageIcon;

/**
 * Each property which will be a single tile on the overall
 * game board.
 * Tiles without buying/selling options (such as GO and jail)
 * will be identified with a price and rent of $0.
 * 
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	7/20/2019 
 */
public class Property {
	/** Name of the property. */
    private String name;
    
    /** Price of the property. */
    private int price;
    
    /** Rent for the property. */
    private int rent;
    
    /** Owner number of the property. */
    private int ownerNum;
    
    /** Color of the property. */
    private char color;
    
    /** Image of the property, pops up when player lands on it. */
    private ImageIcon image;

    /**
     * Creates a special property without color or an image.
     * @param name - name of the property
     * @param price - price of the property
     * @param rent - rent for the property
     * @param ownerNum - owner number for the property
     */
    public Property(final String name, final int price, final int rent, final int ownerNum) {
    	this.name = name;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
    }

    /**
     * Creates a special property without color or an image.
     * @param name - name of the property
     * @param price - price of the property
     * @param rent - rent for the property
     * @param ownerNum - owner number for the property
     * @param color - color of the property
     * @param image - image of the property
     */
    public Property(final String name, final int price, final int rent, final int ownerNum, final char color, final ImageIcon image) {
        this.name = name;
        this.price = price;
        this.rent = rent;
        this.ownerNum = ownerNum;
        this.color = color;
        this.image = image;
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
		return rent;
	}

	/**
	 * Sets the rent for the property.
	 * @param rent - rent for the property
	 */
	public void setRent(final int rent) {
		this.rent = rent;
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
     * Returns the image of the property.
     * @return image - image for the property
     */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Sets the image for the property.
	 * @param image - image for the property
	 */
	public void setimage(final ImageIcon image) {
		this.image = image;
	}
}
