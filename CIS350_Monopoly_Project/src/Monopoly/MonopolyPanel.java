package Monopoly;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private JLabel gameInfo;
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
		//		c.gridx = 0;
		//		c.gridy = 0;
		c.fill = GridBagConstraints.VERTICAL;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		//		c.anchor = 0;
		//		c.weightx = 0;
		//		c.weighty = 0;
		this.add(board, c);

		gameInfo = new JLabel("test");
		c.gridx = 1;
		c.gridy = 0;
		//		c.fill = 0;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		c.anchor = GridBagConstraints.NORTH;
		//		c.weightx = 0;
		//		c.weighty = 0;
		this.add(gameInfo, c);

		rollButton = new JButton("roll");
		rollButton.addActionListener(listener);
		c.gridx = 1;
		c.gridy = 1;
		//		c.fill = 0;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		c.insets = new Insets(10, 50, 10, 50);
//		c.anchor = GridBagConstraints.NORTH;
		//		c.weightx = 0;
		//		c.weighty = 0;
		this.add(rollButton, c);

		endTurnButton = new JButton("end turn");
		endTurnButton.addActionListener(listener);
		c.gridx = 1;
		c.gridy = 2;
		//		c.fill = 0;
		//		c.ipadx = 0;
		//		c.ipady = 0;
		c.anchor = GridBagConstraints.NORTH;
		//		c.weightx = 0;
		//		c.weighty = 0;
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