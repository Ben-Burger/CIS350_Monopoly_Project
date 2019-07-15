package Monopoly;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * GUI for the Monopoly game board.
 *
 * @author	Ben Burger
 * @version	7/8/2019
 */
@SuppressWarnings("serial")
public class MonopolyGUI extends JFrame implements ActionListener {

	//	private static final long serialVersionUID = 1L; 

	private JMenuBar menus;
	private JMenu mainMenu;
	private JMenuItem exitItem;

	/**
	 * Default constructor for a Monopoly GUI.
	 * @param \String frameLabel - label for the window
	 */
	public MonopolyGUI(String frameLabel) {

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
	public void actionPerformed(ActionEvent e) {

		// MenuBar option of exiting
		if(e.getSource() == exitItem)
			System.exit(1);
	}

	/**
	 * Main method that displays a Monopoly GUI.
	 */
	public static void main (String args[]) {
		new MonopolyGUI("Monopoly Game");
	}
}