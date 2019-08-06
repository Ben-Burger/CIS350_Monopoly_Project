package monopoly;

/**
 * Class that represents a Community Chest or Chance card.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	8/5/2019
 */
public class Card {
    /** Name of card. */
    private String name;

    /** Type of action on card. */
    private Type type;

    /** Specifies amount to be paid or be moved to. */
    private int num;

    /**
     * @return Name of card.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Type of card.
     */
    public Type getType() {
        return type;
    }

    /**
     * @return Amount/Location specified by card
     */
    public int getNum() {
        return num;
    }

    /**
     * The type of card.
     */
    enum Type {
        /** Provides Player with card to get out of jail. */
        JAIL_FREE,

        /** Moves Player to Certain Position. */
        MOVE_TO_POSITION,

        /** Moves Player back a number of spaces. */
        MOVE_BACK_SPACES,

        /** Moves Player to the nearest utility or railroad. */
        MOVE_TO_NEAREST,

        /** Player pays an amount to all other players. */
        PAY_TO_EVERYONE,

        /** Player loses an amount of money. */
        PAY_TO_BANK,

        /** Player receives an amount from everyone. */
        RECEIVE_FROM_EVERYONE,

        /** Player receives an amount of money. */
        RECEIVE_FROM_BANK
    }

    /**
     *Creates a card with the given parameters.
     * @param n Name of Property.
     * @param a Type of action to be taken.
     * @param number Amount to be paid or location to be moved to.
     */
    public Card(final String n, final Type a, final int number) {
        this.name = n;
        this.type = a;
        this.num = number;
    }
}
