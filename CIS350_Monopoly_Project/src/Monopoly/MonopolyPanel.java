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
 * @version	7/11/2019 
 */
@SuppressWarnings("serial")
public class MonopolyPanel extends JPanel {

	private ButtonListener listener;
	private BoardPanel board;
	private JTextArea gameInfo;
	private JScrollPane scrollPane;
	private JButton rollButton;
	private JButton endTurnButton;
	private JPanel bank;
	private JLabel[] playerbank;

	private Game game;
	private int numOfPlayers;
	private boolean rolled;


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
		board.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 4;
		this.add(board, c);

		
		gameInfo = new JTextArea();
		gameInfo.setEditable(false);
		scrollPane = new JScrollPane(gameInfo);
		scrollPane.setPreferredSize(new Dimension(300, 400));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 10;
		c.insets = new Insets(0, 20, 0, 0);		// (top, left, bottom, right)
		this.add(scrollPane, c);
		
		
		bank = new JPanel();
		playerbank = new JLabel[4];
		for(int i = 0; i < numOfPlayers; i++) {
			JLabel label = new JLabel("Player "+(i+1)+" has $"+game.getPlayers().get(i).money);
			label.setFont(new Font("Times New Roman", Font.BOLD, 18));
			label.setForeground(Color.BLUE);
			playerbank[i]=label;
			bank.add(label);
		}
		bank.setPreferredSize(new Dimension(200, 200));
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(75, 20, 0, 0);		// (top, left, bottom, right)
		this.add(bank, c);
	
		rollButton = new JButton("roll");
		rollButton.addActionListener(listener);
		rollButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(150, 20, 0, 0);	// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		this.add(rollButton, c);

		
		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		endTurnButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(0, 20, 50, 0);	// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		this.add(endTurnButton, c);

		
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
		updateGameInfo("Player " + game.getCurrentPlayerNum() + "'s turn.");
		rolled = false;
	}


	private void showProperty(int propertyNum) {

		int reply = JOptionPane.showConfirmDialog(null, "", "Would you like to buy this location?:",
				JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, game.board[propertyNum].propertycard);
		if (reply == JOptionPane.YES_OPTION) {
			int rent = game.buyProperty();
			updateGameInfo("Player " + game.getCurrentPlayerNum() + " bought " + game.getPropertyName(game.getCurrentPlayerPosition()) + 
					" for $" + rent + ".");
			updateBank();
		}
	}
	
	private void payRent() {
		int rent = game.calculateRent();
		int propertyOwner = game.getPropertyOwner(game.getCurrentPlayerPosition());
		
		updateGameInfo("Player " + game.getCurrentPlayerNum() + " payed $" + rent + " in rent to Player " + propertyOwner+ ".");
		
		boolean bankrupt = game.payRent();
		
		if(bankrupt) {
			updateGameInfo("Player " + game.getCurrentPlayerNum() + "went bankrupt!");
		}
		updateBank();
	}
	
	private void updateGameInfo(String message) {
		gameInfo.append(message + "\n");
		gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
	}

	private void updateBank() {
		for(int i=0; i<numOfPlayers; i++) {
			playerbank[i].setText("Player "+(i+1)+" has $"+game.getPlayers().get(i).money);
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
				if(rolled == false) {
					int previousPosition = game.getCurrentPlayerPosition();
					int movement = game.move();
					board.movePlayer(game.getCurrentPlayerNum(), game.getCurrentPlayerPosition(), previousPosition);
	
					updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled a " + movement + "."); 
					
					if( (previousPosition + movement) >= 40 ) {
						updateGameInfo("Player " + game.getCurrentPlayerNum() + " collected $200 for passing GO.");
						updateBank();
					}
	
					rolled = true;
					
					if(game.getPropertyOwner(game.getCurrentPlayerPosition()) == 0) {
						showProperty(game.getCurrentPlayerPosition());
					}
					else {
						payRent();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayerNum()+ " has already rolled!");
				}
			}

			if(e.getSource() == endTurnButton) {
				if(rolled == true) {
					game.nextTurn();
					turn();
				}
				else {
					JOptionPane.showMessageDialog(null, "Player " + game.getCurrentPlayerNum()+ " must roll before ending their turn!");
				}
			}
		}
	}
}
