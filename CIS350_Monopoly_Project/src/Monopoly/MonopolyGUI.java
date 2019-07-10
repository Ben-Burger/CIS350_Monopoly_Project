package Monopoly;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

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
	 * @param String frameLabel - label for the window
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

		panel.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.blue));

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

		int numOfPlayers = startGame();
	}

	public static int startGame() {


		// asks user for the desired board size
		boolean goodNum = false;
		int numOfPlayers = 0;

		// stays in loop until valid size is entered or
		// user hits cancel
		while (!goodNum) {
			try {
				String num = JOptionPane.showInputDialog(null, "Enter in the number of players: \n Between 2 and 4.");

				numOfPlayers = Integer.parseInt(num);
			}
			catch(Exception e) {
				System.exit(0);   // exit the program
			}
			if (numOfPlayers >= 2 && numOfPlayers <= 4)
				goodNum = true;

			else
				JOptionPane.showMessageDialog(null, "Enter valid number of players.");
		}
		return numOfPlayers;
	}
}
