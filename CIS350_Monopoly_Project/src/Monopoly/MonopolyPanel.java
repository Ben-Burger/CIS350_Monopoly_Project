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
	private JLabel[] playerbank;

	private Game game;
	private int numOfPlayers;
	private int currentPosition=0;
	private int currentPlayer=0;
	private boolean hasrolled=false;
	private PropertyPanel[] ownedProperties;


	/**
	 * Default constructor for a Monopoly panel.
	 */
	public MonopolyPanel () {

		numOfPlayers = promptUser();
		game = new Game(numOfPlayers);
		ownedProperties = new PropertyPanel[4];

		listener = new ButtonListener();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		board = new BoardPanel();
		board.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 8;
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



		playerbank = new JLabel[numOfPlayers];
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50,0,0,0);
		for(int i=0; i<4; i++) {
			c.gridy = 2+i;
			if(i==3) c.insets = new Insets(50,0,0,0);
			if(i<numOfPlayers) {
				JLabel label = new JLabel("Player "+(i+1)+" has $"+game.getPlayers().get(i).money);
				label.setFont(new Font("Times New Roman", Font.BOLD, 18));
				label.setForeground(Color.BLUE);
				playerbank[i]=label;
				this.add(label, c);
			}
			else {
				JLabel label = new JLabel(" ");
				label.setFont(new Font("Times New Roman", Font.BOLD, 18));
				label.setForeground(Color.BLUE);
				this.add(label, c);
			}
		}


		rollButton = new JButton("roll");
		rollButton.addActionListener(listener);
		rollButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 6;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(100, 20, 0, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		this.add(rollButton, c);


		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		endTurnButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50, 20, 50, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
		this.add(endTurnButton, c);

		c.gridy=0;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(0,100,0,0);
		ownedProperties[0]=new PropertyPanel();
		JLabel label = new JLabel("Player 1 Properties");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setForeground(Color.RED); //red,blue,gray,black
		ownedProperties[0].add(label);
		this.add(ownedProperties[0], c);

		c.gridy=0;
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.insets = new Insets(0,50,0,0);
		ownedProperties[1]=new PropertyPanel();
		label = new JLabel("Player 2 Properties");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setForeground(Color.BLUE); //red,blue,gray,black
		ownedProperties[1].add(label);
		this.add(ownedProperties[1], c);

		if(numOfPlayers>2) {
			c.gridy=1;
			c.gridx = 2;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.insets = new Insets(0,100,0,0);
			ownedProperties[2]=new PropertyPanel();
			label = new JLabel("Player 3 Properties");
			label.setFont(new Font("Times New Roman", Font.BOLD, 20));
			label.setForeground(Color.GRAY); //red,blue,gray,black
			ownedProperties[2].add(label);
			this.add(ownedProperties[2], c);
		}

		if(numOfPlayers>3) {
			c.gridy=1;
			c.gridx = 3;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.insets = new Insets(0,50,0,0);
			ownedProperties[3]=new PropertyPanel();
			label = new JLabel("Player 4 Properties");
			label.setFont(new Font("Times New Roman", Font.BOLD, 20));
			label.setForeground(Color.BLACK); //red,blue,gray,black
			ownedProperties[3].add(label);
			this.add(ownedProperties[3], c);
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
		updateGameInfo("Player " + game.getCurrentPlayerNum() + "'s turn.");
	}


	private void checkProperty(int propertyNum) {
		switch(game.propertyActions()){
			case 0:
				if(game.board[propertyNum].name.contentEquals("Go to Jail")) {
					gameInfo.append("Player "+currentPlayer+" is sent to JAIL!\n");
					gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
					game.getCurrentPlayer().boardPosition=10;
					board.movePlayer(currentPlayer,10, 30);
					JOptionPane.showMessageDialog(null,
							"Player "+currentPlayer+" GO TO JAIL!");
				}
				if(game.board[propertyNum].name.contentEquals("Free Parking")) {
					gameInfo.append("Player "+currentPlayer+" won free parking!\n");
					gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
					JOptionPane.showMessageDialog(null,
							"Player "+currentPlayer+", you get free parking!");
				}
				break;
			case 1:
				int reply = JOptionPane.showConfirmDialog(null, "", "Would you like to buy this location? (cost: $"+game.board[propertyNum].price+")",
						JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, game.board[propertyNum].propertycard);
				if (reply == JOptionPane.YES_OPTION) {
					game.buyProperty();
					gameInfo.append("Player "+currentPlayer+" has bought "+game.board[propertyNum].name+"!\n");
					gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
					playerbank[currentPlayer-1].setText("Player "+(currentPlayer)+" has $"+game.getPlayers().get(currentPlayer-1).money);

					JLabel label = new JLabel(game.board[propertyNum].name+" (rent: $"+game.board[propertyNum].rent+")");
					label.setFont(new Font("Times New Roman", Font.BOLD, 16));
					label.setForeground(playerColor(currentPlayer)); //red,blue,gray,black
					ownedProperties[currentPlayer-1].add(label);
				}
				break;
			case 2:
				int rent = game.calculateRent();
				int propertyOwner = game.getPropertyOwner(game.getCurrentPlayerPosition());

				if(game.board[propertyNum].price>0) {
					JOptionPane.showMessageDialog(null,
							"Player "+currentPlayer+" must pay $"+rent+" to Player "+
									propertyOwner+" for rent.");
				}

				updateGameInfo("Player " + game.getCurrentPlayerNum() + " payed $" + rent + " in rent to Player " + propertyOwner+ ".");

				boolean bankrupt = game.payRent();

				if(bankrupt) {
					updateGameInfo("Player " + game.getCurrentPlayerNum() + "went bankrupt!");
				}
				updateBank();
				break;
		}
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
				if(!hasrolled) {
					int previousPosition = game.getCurrentPlayerPosition();
					int movement = game.move();
					currentPosition = game.getCurrentPlayerPosition();
					currentPlayer = game.getCurrentPlayerNum();
					board.movePlayer(currentPlayer,currentPosition, previousPosition);
					if(currentPosition<previousPosition) {
						JOptionPane.showMessageDialog(null,"Passed GO! Collect $200");
						playerbank[currentPlayer-1].setText("Player "+(currentPlayer)+" has $"+game.getPlayers().get(currentPlayer-1).money);
						updateGameInfo("Player " + game.getCurrentPlayerNum() + " collected $200 for passing GO.");
					}
					updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled a " + movement
							+ " and is now at "+game.board[currentPosition].name+".");
					checkProperty(currentPosition);
					hasrolled=true;
				}
			}

			if(e.getSource() == endTurnButton) {
				if(hasrolled == true) {
					hasrolled = false;
					game.nextTurn();
					turn();
				}
			}
		}
	}

	private Color playerColor(int playernum) {
		switch(playernum) {
			case 1:
				return Color.RED;
			case 2:
				return Color.BLUE;
			case 3:
				return Color.GRAY;
			case 4:
				return Color.BLACK;
		}
		return null;
	}
}