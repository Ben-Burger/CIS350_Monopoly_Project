package Monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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

	/**
	 * Default constructor for a Monopoly panel.
	 */
	public MonopolyPanel () {

		listener = new ButtonListener();

		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		board = new BoardPanel();
		board.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
//		c.fill = GridBagConstraints.VERTICAL;
//		c.ipadx = 0;
//		c.ipady = 0;
//		c.insets = 0;
//		c.anchor = GridBagConstraints.WEST;
//		c.weightx = 1;
//		c.weighty = 1;
		this.add(board, c);

		gameInfo = new JTextArea("Welcome!");
		for(int i=0; i<100;i++)
			gameInfo.append("\nHello!");
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
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
//		c.fill = NONE;
//		c.ipadx = 0;
//		c.ipady = 0;
		c.insets = new Insets(450, 20, 0, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
		this.add(rollButton, c);

		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		endTurnButton.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.black));
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
//		c.fill = NONE;
//		c.ipadx = 0;
//		c.ipady = 0;
		c.insets = new Insets(0, 20, 0, 0);		// (top, left, bottom, right)
		c.anchor = GridBagConstraints.SOUTH;
//		c.weightx = 0.5;
//		c.weighty = 0.5;
		this.add(endTurnButton, c);
		
		
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

			}

			if(e.getSource() == endTurnButton) {

			}
		}
	}
}
