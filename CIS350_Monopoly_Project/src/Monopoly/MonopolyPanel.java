package Monopoly;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Creates the panel for the Monopoly GUI.
 * 
 * @author	Ben Burger
 * @version	7/8/2019 
 */
@SuppressWarnings("serial")
public class MonopolyPanel extends JPanel {
	//Button Listener for general GUI buttons
	ButtonListener listener = new ButtonListener();	

	//Instantiate the chess graphical panel
	public static BoardPanel panel1 = new BoardPanel();


	GridBagConstraints loc = new GridBagConstraints();
	
	public MonopolyPanel () {
		setLayout(new GridBagLayout());

		loc.gridx=0;
		loc.gridy=0;
		loc.anchor=loc.WEST;
		add(panel1,loc);
	}
	
	
	private class ButtonListener implements ActionListener {
		// --------------------------------------------------------
		// Updates the chess model when the button is pushed.
		// --------------------------------------------------------
		public void actionPerformed(ActionEvent event) {

			JComponent comp = (JComponent) event.getSource();
		}
	}
		
}



