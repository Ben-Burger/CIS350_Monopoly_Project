package monopoly;

import java.util.ArrayList;
import java.util.Random;

public class CardDecks {
    /** Cards belonging in the Chance Deck */
    private ArrayList<Card> chanceDeck;

    /** Cards belonging in the Community Chest Deck */
    private ArrayList<Card> communityChestDeck;

    /** Random object to pull random cards */
    private Random r;

    /**
     * Creates both decks of Monopoly cards.
     */
    public CardDecks() {
        chanceDeck = new ArrayList<>();
        communityChestDeck = new ArrayList<>();
        initialize();
        r = new Random();
    }

    /**
     * Gets cards left in the deck
     * @return Number of cards.
     */
    public int cardsLeftInChance() {
        return chanceDeck.size();
    }

    /**
     * Gets cards left in the deck
     * @return Number of cards.
     */
    public int cardsLeftInChest() {
        return communityChestDeck.size();
    }

    /**
     * Clears any remaining cards in decks and fills both decks with cards.
     */
    public void initialize() {
        makeChance();
        makeCommunityChest();
    }

    /**
     * Clears and populates Chance card deck.
     */
    public void makeChance() {
        chanceDeck.clear();

        chanceDeck.add(new Card("Advance to Go (Collect $200)", Card.Type.MOVE_TO_POSITION,
                0));
        chanceDeck.add(new Card("Advance to Illinois Ave - If you pass Go, collect $200",
                Card.Type.MOVE_TO_POSITION, 24));
        chanceDeck.add(new Card("Advance to St. Charles Place - If you pass Go, collect $200",
                Card.Type.MOVE_TO_POSITION, 11));
        chanceDeck.add(new Card("Advance token to nearest Utility.",
                Card.Type.MOVE_TO_NEAREST, 12));
        chanceDeck.add(new Card("Advance to nearest Railroad",
                Card.Type.MOVE_TO_NEAREST, 5));
        chanceDeck.add(new Card("Bank pays you a dividend of $50",
                Card.Type.RECEIVE_FROM_BANK, 50));
        chanceDeck.add(new Card("Get out of Jail free", Card.Type.JAIL_FREE,
                0));
        chanceDeck.add(new Card("Go Back Three Spaces",
                Card.Type.MOVE_BACK_SPACES, 3));
        chanceDeck.add(new Card("Go to Jail - Go directly to Jail - Do not pass Go, do not collect $200",
                Card.Type.MOVE_TO_POSITION, 10));
        chanceDeck.add(new Card("Pay poor tax of $15", Card.Type.PAY_TO_BANK,
                15));
        chanceDeck.add(new Card("Take a trip to Reading Railroad - If you pass Go, collect $200",
                Card.Type.MOVE_TO_POSITION, 5));
        chanceDeck.add(new Card("Take a walk on the Boardwalk - Advance token to Boardwalk",
                Card.Type.MOVE_TO_POSITION, 39));
        chanceDeck.add(new Card("You have been elected Chairman of the Board - Pay each player $50",
                Card.Type.PAY_TO_EVERYONE, 50));
        chanceDeck.add(new Card("Your building and loan matures - Collect $150",
                Card.Type.RECEIVE_FROM_BANK, 150));
        chanceDeck.add(new Card("You have won a crossword competition - Collect $100",
                Card.Type.RECEIVE_FROM_BANK, 100));
    }

    /**
     * Clears and populates Community Chest deck.
     */
    public void makeCommunityChest() {
        communityChestDeck.clear();

        communityChestDeck.add(new Card("Advance to Go (Collect $200)",
                Card.Type.MOVE_TO_POSITION, 0));
        communityChestDeck.add(new Card("Bank error in your favor - Collect $200",
                Card.Type.RECEIVE_FROM_BANK, 200));
        communityChestDeck.add(new Card("From sale of stock you get $50",
                Card.Type.RECEIVE_FROM_BANK, 50));
        communityChestDeck.add(new Card("Doctor's fee - Pay $50", Card.Type.PAY_TO_BANK,
                50));
        communityChestDeck.add(new Card("Get out of Jail Free", Card.Type.JAIL_FREE,
                0));
        communityChestDeck.add(new Card("Go to Jail - Go directly to jail - Do not pass Go - Do not collect $200",
                Card.Type.MOVE_TO_POSITION, 10));
        communityChestDeck.add(new Card("Grand Opera Night - Collect $50 from every player for opening night seats",
                Card.Type.RECEIVE_FROM_EVERYONE, 50));
        communityChestDeck.add(new Card("Holiday Fund matures - Receive $100",
                Card.Type.RECEIVE_FROM_BANK, 100));
        communityChestDeck.add(new Card("Income tax refund - Collect $20",
                Card.Type.RECEIVE_FROM_BANK, 20));
        communityChestDeck.add(new Card("It is your birthday - Collect $10 from everyone",
                Card.Type.RECEIVE_FROM_EVERYONE, 10));
        communityChestDeck.add(new Card("Life insurance matures - Collect $100",
                Card.Type.RECEIVE_FROM_BANK, 100));
        communityChestDeck.add(new Card("Pay hospital fees of $100",
                Card.Type.PAY_TO_BANK, 50));
        communityChestDeck.add(new Card("Pay school fees of $150",
                Card.Type.PAY_TO_BANK, 100));
        communityChestDeck.add(new Card("Receive $25 consultancy fee",
                Card.Type.RECEIVE_FROM_BANK, 25));
        communityChestDeck.add(new Card("You have won second prize in a beauty contest - Collect $10",
                Card.Type.RECEIVE_FROM_BANK, 10));
        communityChestDeck.add(new Card("You inherit $100",
                Card.Type.RECEIVE_FROM_BANK, 100));
    }

    /**
     * Gets a random Card from the Community Chest deck. Also removes whatever
     * card was chosen.
     * @return Card chosen.
     */
    public Card drawChest() {
        if (cardsLeftInChance() == 0) {
            makeCommunityChest();
        }
        int draw = r.nextInt(communityChestDeck.size());
        Card tmp = communityChestDeck.get(draw);
        communityChestDeck.remove(draw);
        return tmp;
    }

    /**
     * Gets a random Card from the Chance deck. Also removes whatever
     * card was chosen.
     * @return Card chosen.
     */
    public Card drawChance() {
        if (cardsLeftInChest() == 0) {
            makeChance();
        }
        int draw = r.nextInt(chanceDeck.size());
        Card tmp = chanceDeck.get(draw);
        chanceDeck.remove(draw);
        return tmp;
    }

    /**
     *
     * @param c 'a' for a Chance card. 'e' (or anything else) for a Chest card.
     * @param index Index of card in its ArrayList.
     * @return Requested Card.
     */
    public Card getCard(final char c, final int index) {
        if (c == 'a') {
            return chanceDeck.get(index);
        } else {
            return communityChestDeck.get(index);
        }
    }
}
