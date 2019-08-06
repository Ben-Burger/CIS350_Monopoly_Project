package monopoly;

/**
 * Main driver class that creates the Monopoly GUI.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	8/5/2019 
 */
public final class Main {

	/** New private default constructor. */
	private Main() {
	}
	
	/** The GUI for the monopoly game. */
	private static MonopolyGUI gui;
	
	/**
	 * Creates a new monopoly GUI.
	 */
	public static void newGame() {
		gui.dispose();
		gui = new MonopolyGUI("Monopoly Game");
	}
	
	/**
	 * Main method that displays a Monopoly GUI.
	 * @param args - arguments for main method
	 */
	public static void main(final String[] args) {
		gui = new MonopolyGUI("Monopoly Game");
	}
}
