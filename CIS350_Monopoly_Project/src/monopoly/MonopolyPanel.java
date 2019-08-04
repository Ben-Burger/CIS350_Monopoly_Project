package monopoly;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




/**
 * Creates the panel for the Monopoly GUI.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis
 * @version	7/20/2019 
 */
@SuppressWarnings("serial")
public class MonopolyPanel extends JPanel {

	/** Button listener for the panel. */ 
	private ButtonListener listener;

	/** Panel for the board. */
	private BoardPanel board;

	/** Text area for game event messages. */
	private JTextArea gameInfo;

	/** Scroll for the text area. */
	private JScrollPane scrollPane;

	/** Button to roll die. */
	private JButton rollButton;

	/** Button to end turn. */
	private JButton endTurnButton;

	/** Labels for current players' money totals. */
	private JLabel[] playerBank;

	/** Game object. */
	private Game game;

	/** Number of players in the game. */
	private int numOfPlayers;

	/** Current board position of the current player. */
	private int currentPosition = 0;

	/** Player whose turn it currently is. */
	private int currentPlayer = 0;

	/** Check to make sure the current player can only roll once. */
	private boolean hasRolled = false;

	/** Panels to display which players own which properties. */
	private PropertyPanel[] ownedProperties;

	/** Visual representation of the dice. */
	private GVdie die1, die2;

	/** Panel to hold dice. */
	private DicePanel dice = new DicePanel();


	/**
	 * Default constructor for a Monopoly panel.
	 */
	public MonopolyPanel() {

		numOfPlayers = promptUser();
		game = new Game(numOfPlayers);
		ownedProperties = new PropertyPanel[4];

		listener = new ButtonListener();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		board = new BoardPanel();
		board.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, 
				Color.black));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 9;
		this.add(board, c);


		gameInfo = new JTextArea();
		gameInfo.setEditable(false);
		scrollPane = new JScrollPane(gameInfo);
		scrollPane.setPreferredSize(new Dimension(300, 400));
		scrollPane.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 
				2, Color.black));
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.ipadx = 10;
		c.insets = new Insets(0, 20, 0, 0);
		this.add(scrollPane, c);



		playerBank = new JLabel[numOfPlayers];
		c.gridx = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50, 0, 0, 0); 
		for (int i = 0; i < 4; i++) {
			c.gridy = 3 + i;
			if (i == 3) {
				c.insets = new Insets(50, 0, 0, 0);
			}
			if (i < numOfPlayers) {
				JLabel label = new JLabel("Player " + (i + 1) + " has $" + game.getPlayerMoney(i + 1));
				label.setFont(new Font("Times New Roman", Font.BOLD, 18));
				label.setForeground(Color.BLUE);
				playerBank[i] = label;
				this.add(label, c);
			} else {
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
		c.gridy = 7;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50, 20, 0, 0);	
		c.anchor = GridBagConstraints.SOUTH;
		this.add(rollButton, c);


		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		endTurnButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 8;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(50, 20, 50, 0);
		c.anchor = GridBagConstraints.SOUTH;
		this.add(endTurnButton, c);


		die1 = game.getDie(1);
		die2 = game.getDie(2);
		dice.add(die1);
		dice.add(die2);
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.insets = new Insets(5,10,5,10);
		this.add(dice, c);

		c.gridy = 0;
		c.gridx = 2;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(0, 100, 0, 0);
		ownedProperties[0] = new PropertyPanel();
		JLabel label = new JLabel("Player 1 Properties");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setForeground(Color.RED); //red,blue,gray,black
		ownedProperties[0].add(label);
		this.add(ownedProperties[0], c);

		c.gridy = 0;
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.insets = new Insets(0, 50, 0, 0);
		ownedProperties[1] = new PropertyPanel();
		label = new JLabel("Player 2 Properties");
		label.setFont(new Font("Times New Roman", Font.BOLD, 20));
		label.setForeground(Color.BLUE); //red,blue,gray,black
		ownedProperties[1].add(label);
		this.add(ownedProperties[1], c);

		if (numOfPlayers > 2) {
			c.gridy = 1;
			c.gridx = 2;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.insets = new Insets(0, 100, 0, 0);
			ownedProperties[2] = new PropertyPanel();
			label = new JLabel("Player 3 Properties");
			label.setFont(new Font("Times New Roman", Font.BOLD, 20));
			label.setForeground(Color.GRAY); //red,blue,gray,black
			ownedProperties[2].add(label);
			this.add(ownedProperties[2], c);
		}

		if (numOfPlayers > 3) {
			c.gridy = 1;
			c.gridx = 3;
			c.gridwidth = 1;
			c.gridheight = 4;
			c.insets = new Insets(0, 50, 0, 0);
			ownedProperties[3] = new PropertyPanel();
			label = new JLabel("Player 4 Properties");
			label.setFont(new Font("Times New Roman", Font.BOLD, 20));
			label.setForeground(Color.BLACK); //red,blue,gray,black
			ownedProperties[3].add(label);
			this.add(ownedProperties[3], c);
		}
		playGame();
	}

	/**
	 * Starts the monopoly game.
	 */
	private void playGame() {

		updateGameInfo("Starting game with " + numOfPlayers + " players!");

		game = new Game(numOfPlayers);

		for (int i = 0; i < numOfPlayers; i++) {
			board.movePlayer(i + 1, 0, 0);
		}

		updateGameInfo("Player " + game.getCurrentPlayerNum() + "'s turn.");
	}

	/**
	 * Prompts the user for the number of players.
	 * Must be between 2 and 4.
	 * @return number of players
	 */
	private int promptUser() {

		boolean goodNum = false;
		int numOfPlayers = 0;

		while (!goodNum) {
			try {
				String num = JOptionPane.showInputDialog(null, "Enter in the number of players.\n"
						+ "Must be between 2 and 4.");
				numOfPlayers = Integer.parseInt(num);
			} catch (Exception e) {
				System.exit(0);
			}
			if (numOfPlayers >= 2 && numOfPlayers <= 4) {
				goodNum = true;
			} else {
				JOptionPane.showMessageDialog(null, "Enter valid number of players.");
			}
		}
		return numOfPlayers;
	}

	/**
	 * Checks the property that the current player landed on.
	 * @param propertyNum - the property that the current player is on
	 */
	private void checkProperty(final int propertyNum) {
		switch (game.propertyActions()) {
		case -1:
			specialProperty(propertyNum);
			break;
		case 0:
			unownedProperty(propertyNum);
			break;
		case 1:		// owned property
			ownedProperty(propertyNum);
			break;
		default:
			break;
		}
	}

	private void specialProperty(int propertyNum) {
		String cardMessage;
		int previousPosition;

		switch (game.getPropertyName(propertyNum)) {
		case "Community Chest":
			previousPosition = currentPosition;

			cardMessage = game.drawChest();

			JOptionPane.showMessageDialog(null, cardMessage, "COMMUNITY CHEST",  JOptionPane.INFORMATION_MESSAGE);

			currentPosition = game.getCurrentPlayerPosition();
			if (currentPosition != previousPosition) {
				board.movePlayer(currentPlayer, currentPosition, previousPosition);
			}

			if (game.checkBankrupt(currentPlayer)) {
				bankruptOptions();
			}

			updateBank();
			break;
		case "Income Tax":
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + ", you must pay $200 for Income Tax.");
			updateGameInfo("Player " + currentPlayer + " paid $200 for Income Tax.");
			game.subtractMoney(currentPlayer, 200);
			if (game.checkBankrupt(currentPlayer)) {
				bankruptOptions();
			}
			break;
		case "Chance":
			previousPosition = currentPosition;

			cardMessage= game.drawChance();

			JOptionPane.showMessageDialog(null, cardMessage, "CHANCE",  JOptionPane.INFORMATION_MESSAGE);

			currentPosition = game.getCurrentPlayerPosition();
			if (currentPosition != previousPosition) {
				board.movePlayer(currentPlayer, currentPosition, previousPosition);
			}

			if (game.checkBankrupt(currentPlayer)) {
				bankruptOptions();
			}
			
			updateBank();
			break;
		case "Free Parking":
			updateGameInfo("Player " + currentPlayer + " won free parking!");
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + ", you get free parking!");
			break;
		case "Go to Jail":
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " GO TO JAIL!");
			game.setCurrentPlayerPosition(10);
			board.movePlayer(currentPlayer, 10, 30);
			game.getCurrentPlayer().setJailturns(1);
			updateGameInfo("Player " + currentPlayer + " is sent to JAIL!");
			break;
		case "Luxury Tax":
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + ", you must pay $100 for Luxury Tax.");
			updateGameInfo("Player " + currentPlayer + " paid $100 for Luxury Tax.");
			game.subtractMoney(currentPlayer, 200);
			if (game.checkBankrupt(currentPlayer)) {
				bankruptOptions();
			}
			break;
		default:
			break;
		}
	}

	private void unownedProperty(int propertyNum) {
		if (game.getCurrentPlayerMoney() >= game.getPropertyPrice(currentPosition)) {
			int reply = JOptionPane.showConfirmDialog(null, "", "Would you like to buy this location? (cost: $" + game.getPropertyPrice(propertyNum) + ")",
					JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION, game.getPropertyImage(propertyNum));
			if (reply == JOptionPane.YES_OPTION) {
				game.buyProperty();
				updateGameInfo("Player " + currentPlayer + " has bought " + game.getPropertyName(propertyNum) + "!");
				updateBank();
				JLabel label = new JLabel(game.getPropertyName(propertyNum) + " (rent: $" + game.getPropertyRent(propertyNum) + ")");
				label.setFont(new Font("Times New Roman", Font.BOLD, 16));
				label.setForeground(playerColor(currentPlayer)); //red,blue,gray,black
				ownedProperties[currentPlayer - 1].add(label);
			}
		} else {
			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " cannot afford to buy " + game.getPropertyName(currentPosition) + ".");
		}
	}

	private void ownedProperty(int propertyNum) {
		if (game.getPropertyOwner(currentPosition) != game.getCurrentPlayerNum()) {	
			int rent = game.calculateRent();
			int propertyOwner = game.getPropertyOwner(propertyNum);

			JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " must pay $" + rent + " to Player " + propertyOwner + " for rent.");
			updateGameInfo("Player " + game.getCurrentPlayerNum() + " payed $" + rent + " in rent to Player " + propertyOwner + ".");
			game.payRent();
			if (game.checkBankrupt(currentPlayer)) {
				bankruptOptions();
			}
		}
	}

	/**
	 * Updates text area with game event.
	 * @param message - game event
	 */
	private void updateGameInfo(final String message) {
		gameInfo.append(message + "\n");
		gameInfo.setCaretPosition(gameInfo.getDocument().getLength());
	}

	/**
	 * Updates players' money totals.
	 */
	private void updateBank() {
		for (int i = 0; i < numOfPlayers; i++) {
			if (!game.getPlayer(i + 1).isBankrupt()) {
				playerBank[i].setText("Player " + (i + 1) + " has $" + game.getPlayerMoney(i + 1));
			} else {
				playerBank[i].setText("Player " + (i + 1) + " is bankrupt!");
			}
		}	
	}


	private void bankruptOptions() {
		updateBank();
		sellProperties();
		if (game.getCurrentPlayerMoney() <= 0) {
			game.setCurrentPlayerBankrupt(true);
			updateGameInfo("Player " + game.getCurrentPlayerNum() + " went bankrupt!");
		}
		updateBank();
	}

	private void sellProperties () {
		ArrayList<Property> properties = game.getProperties(currentPlayer);

		if (properties.size() > 0) {

			ArrayList<Object> params = new ArrayList<Object>();
			params.add("You are at $" + game.getCurrentPlayerMoney() + "\nSelect properties to sell:");

			for(int i = 0; i < properties.size(); i++)
			{
				JCheckBox checkbox = new JCheckBox();
				checkbox.setText(properties.get(i).getName() + ": $" + (properties.get(i).getPrice() / 2));
				checkbox.setSelected(false);
				params.add(checkbox);
			}

			Object[] realParams = new Object[params.size()];
			realParams = params.toArray(realParams);


			int n = JOptionPane.showConfirmDialog(null, realParams, "title", JOptionPane.OK_CANCEL_OPTION);


			if (n == JOptionPane.OK_OPTION) {
				for(int i = 1; i < realParams.length; i++) {
					if(((JCheckBox) realParams[i]).isSelected()) {
						game.sellProperty(properties.get(i - 1).getName());
						updateGameInfo("Player " + currentPlayer + " sold " + properties.get(i - 1).getName() + " for $" + properties.get(i - 1).getPrice() / 2);

						for (Component c : ownedProperties[currentPlayer - 1].getComponents()) {
							if (c instanceof JLabel) {
								if (((JLabel) c).getText().contains(properties.get(i - 1).getName())) {
									ownedProperties[currentPlayer - 1].remove(c);
								}
							}
						}
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(null, "You have no properties to sell.");
		}
	}

	/**
	 * If the player is in jail, displays the player's options.
	 */
	private void displayJailOptions() {
		String [] options=new String[]{"Try for doubles", "Pay $50 Bail"};
		int response =JOptionPane.showOptionDialog(null,
				"You are in Jail. What do you want to do?",
				"IN JAIL!", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.PLAIN_MESSAGE,
				null, options, null);
		switch(response) {
		case 0:
			int possiblemove = game.rollDice();
			if(die1.getValue() == die2.getValue()) {
				updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled doubles and got out of jail!");
				game.moveTo(possiblemove+10);
				board.movePlayer(game.getCurrentPlayerNum(), game.getCurrentPlayerPosition(), 10);
				updateGameInfo("Player " + game.getCurrentPlayerNum() + " is now at " + game.getPropertyName(game.getCurrentPlayerPosition()) + ".");

				JOptionPane.showMessageDialog(null, "Double " + die1.getValue()+"'s! You're free!");

				checkProperty(game.getCurrentPlayerPosition());
				hasRolled = true;
				game.getCurrentPlayer().setJailturns(0);
			}
			else if(game.getCurrentPlayer().getJailturns()>2) {
				game.getCurrentPlayer().setMoney(game.getCurrentPlayerMoney()-50);
				updateGameInfo("Player " + game.getCurrentPlayerNum() + " paid $50 dollars to get out of jail.");
				updateBank();
				JOptionPane.showMessageDialog(null, "You've ran out of attempts and paid $50. Press roll button.");
				game.getCurrentPlayer().setJailturns(0);

			}
			else {
				game.getCurrentPlayer().setJailturns(game.getCurrentPlayer().getJailturns()+1);
				hasRolled = true;
				updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled a " + die1.getValue() + " and a " + die2.getValue() + " and  did not get out of jail!");
			}

			break;
		case 1:
			game.getCurrentPlayer().setMoney(game.getCurrentPlayerMoney()-50);
			updateGameInfo("Player " + game.getCurrentPlayerNum() + " paid $50 dollars to get out of jail.");
			updateBank();
			game.getCurrentPlayer().setJailturns(0);
			break;

		default:

			break;
		}
	}

	/**
	 * Returns the color of the player.
	 * @param playernum - player number
	 * @return color of the player
	 */
	private Color playerColor(final int playernum) {
		switch (playernum) {
		case 1:
			return Color.RED;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.GRAY;
		case 4:
			return Color.BLACK;
		default:
			break;
		}
		return null;
	}

	/**
	 * ButtonListener class for Monopoly panel.
	 */
	private class ButtonListener implements ActionListener {

		/**
		 * Tells the GUI what to do when an action happens.
		 * @param e - ActionEvent used to indicate action happened
		 */
		public void actionPerformed(final ActionEvent e) {

			if (e.getSource() == rollButton) {
				if (!hasRolled) {
					int previousPosition = game.getCurrentPlayerPosition();

					int movement = game.move();

					currentPosition = game.getCurrentPlayerPosition();

					currentPlayer = game.getCurrentPlayerNum();
					board.movePlayer(currentPlayer, currentPosition, previousPosition);
					//					for(int j=0;j<4;j++) {
					//						board.addHouse(game.getCurrentPlayerPosition());
					//					}
					//					if(game.getCurrentPlayerPosition()>1)
					//					board.placeHotel(game.getCurrentPlayerPosition()-1);

					dice.removeAll();
					die1 = game.getDie(1);
					die2 = game.getDie(2);
					dice.add(die1);
					dice.add(die2);

					if (currentPosition < previousPosition) {
						JOptionPane.showMessageDialog(null, "Passed GO! Collect $200");
						updateBank();						
						updateGameInfo("Player " + game.getCurrentPlayerNum() + " collected $200 for passing GO.");
					}
					updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled a " + movement
							+ " and is now at " + game.getPropertyName(game.getCurrentPlayerPosition()) + ".");

					checkProperty(currentPosition);
					if(die1.getValue() != die2.getValue())
						hasRolled = true;
					else {
						updateGameInfo("Player " + game.getCurrentPlayerNum() + " rolled doubles, and gets to go again.");
						JOptionPane.showMessageDialog(null, "Rolled doubles! Roll again.");
					}
				}
			}

			if (e.getSource() == endTurnButton) {
				if (hasRolled) {
					hasRolled = false;
					game.nextTurn();

					int winner = game.checkForGameEnd();
					if (winner == 0) {
						updateGameInfo("Player " + game.getCurrentPlayerNum() + "'s turn.");
						if(game.getCurrentPlayer().getJailturns() > 0) 
							displayJailOptions();
					} else {
						updateGameInfo("Player " + winner + " won!");
						int reply = JOptionPane.showConfirmDialog(null, "Player " + winner + " won the game.\nWould you like to play again?", 
								"", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_OPTION) {
							Main.newGame();
						} else {
							System.exit(0);
						}
					}
				}
			}
		}
	}
}
