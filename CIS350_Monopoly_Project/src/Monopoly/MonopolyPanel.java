package Monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Creates the panel for the Monopoly GUI.
 * 
 * @author	Ben Burger
 * @version	7/8/2019 
 */
@SuppressWarnings("serial")
public class MonopolyPanel extends JPanel {

	private ButtonListener listener;
	private BoardPanel board;
	private JTextArea gameInfo;
	private JScrollPane scrollPane;
	private JButton rollButton;
	private JButton endTurnButton;
	private JLabel[] playerbank;

	private Game game;
	private int numOfPlayers;


	/**
	 * Default constructor for a Monopoly panel.
	 */
	public MonopolyPanel () {

		numOfPlayers = promptUser();
		game = new Game(numOfPlayers);


		listener = new ButtonListener();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		board = new BoardPanel();
		board.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 8;
		//		c.fill = GridBagConstraints.VERTICAL;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		//		c.insets = 0;
		//		c.anchor = GridBagConstraints.WEST;
		//		c.weightx = 1;
		//		c.weighty = 1;
		this.add(board, c);

		gameInfo = new JTextArea();
		//		for(int i=0; i<100;i++)
		//			gameInfo.append("\nHello!");
		gameInfo.setEditable(false);
		scrollPane = new JScrollPane(gameInfo);
		scrollPane.setPreferredSize(new Dimension(200, 400));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		//		c.fill = NONE;
		c.ipadx = 10;
		//		c.ipady = 0;
		c.insets = new Insets(0, 20, 0, 0);			// (top, left, bottom, right)
		//		c.anchor = GridBagConstraints.NORTH;
		//		c.weightx = 0.5;
		//		c.weighty = 0.5;
		this.add(scrollPane, c);

		rollButton = new JButton("roll");
		rollButton.addActionListener(listener);
		rollButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		//		c.fill = NONE;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		c.insets = new Insets(150, 20, 0, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		//		c.weightx = 0.5;
		//		c.weighty = 0.5;
		this.add(rollButton, c);

		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		endTurnButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		//		c.fill = NONE;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		c.insets = new Insets(0, 20, 50, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		//		c.weightx = 0.5;
		//		c.weighty = 0.5;
		this.add(endTurnButton, c);
		
		playerbank = new JLabel[numOfPlayers];
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50, 20, 0, 0);
		for(int i=0; i<numOfPlayers; i++) {
			c.gridy = 2+i;
			JLabel label = new JLabel("Player "+(i+1)+" has $"+game.getPlayers().get(i).money);
			label.setFont(new Font("Times New Roman", Font.BOLD, 18));
			label.setForeground(Color.BLUE);
			playerbank[i]=label;
			this.add(label, c);
		}


		playGame();
	}

	public void playGame() {

		gameInfo.append("Starting game with " + numOfPlayers + " players!\n");

		try {
			game = new Game(numOfPlayers);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		for(int i=0; i<numOfPlayers; i++) {
			board.movePlayer(i+1, 0, 0);
		}

		


		turn();		

	}

	public int promptUser() {

		boolean goodNum = false;
		int numOfPlayers = 0;

		while (!goodNum) {
			try {
				String num = JOptionPane.showInputDialog(null, "Enter in the number of players.\n"
						+ "Must be between 2 and 4.");
				numOfPlayers = Integer.parseInt(num);
			}
			catch(Exception e) {
				System.exit(0);   
			}
			if (numOfPlayers >= 2 && numOfPlayers <= 4)
				goodNum = true;

			else
				JOptionPane.showMessageDialog(null, "Enter valid number of players.");
		}
		return numOfPlayers;
	}


	private void turn() {
		gameInfo.append("Player " + game.getCurrentPlayerNum() + "'s turn.\n");
		gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
	}


	private void showProperty(int propertyNum) {

		int reply = JOptionPane.showConfirmDialog(null, "", "Would you like to buy this location?:",
				JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, game.board[propertyNum].propertycard);
		if (reply == JOptionPane.YES_OPTION) {
		}
	}

	/**
	 * ButtonListener class for Monopoly panel.
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Tells the GUI what to do when an action happens.
		 * @param e - ActionEvent used to indicate action happened
		 */
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == rollButton) {
				int previousPosition = game.getCurrentPlayerPosition();
				int movement = game.move();
				board.movePlayer(game.getCurrentPlayerNum(), game.getCurrentPlayerPosition(), previousPosition);
				gameInfo.append("Player " + game.getCurrentPlayerNum()+ " rolled a " + movement + ".\n");
				gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
				showProperty(game.getCurrentPlayerPosition());
			}

			if(e.getSource() == endTurnButton) {
				game.nextTurn();
				turn();
			}
		}
	}
}
