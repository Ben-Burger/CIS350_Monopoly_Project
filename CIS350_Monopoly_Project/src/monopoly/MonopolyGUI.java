package monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * GUI for the Monopoly game board.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	7/20/2019
 */
@SuppressWarnings("serial")
public final class MonopolyGUI extends JFrame implements ActionListener {

	/** Menu bar for main menu. */
	private JMenuBar menus;

	/** Main menu. */
	private JMenu mainMenu;

	/** Exit button for main menu. */
	private JMenuItem exitItem;

	/**
	 * Default constructor for a Monopoly GUI.
	 * @param frameLabel - label for the window
	 */
	private MonopolyGUI(final String frameLabel) {

		super(frameLabel);

		menus = new JMenuBar();
		mainMenu = new JMenu("Menu");
		exitItem = new JMenuItem("exit");

		mainMenu.add(exitItem);
		menus.add(mainMenu);

		exitItem.addActionListener(this);

		this.setJMenuBar(menus);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MonopolyPanel panel = new MonopolyPanel();

		this.getContentPane().add(panel);

		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}

	/**
	 * Tells the GUI what to do when an action happens.
	 * @param e - ActionEvent used to indicate action happened
	 */
	public void actionPerformed(final ActionEvent e) {

		if (e.getSource() == exitItem) {
			System.exit(1);
		}
	}

	/**
	 * Main method that displays a Monopoly GUI.
	 * @param args - arguments for main method
	 */
	public static void main(final String[] args) {
		new MonopolyGUI("Monopoly Game");
	}
}
