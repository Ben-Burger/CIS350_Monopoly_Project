package Monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardPanel extends JPanel{
	/** Icon for a monopoly board. */
	private ImageIcon boardpic = new ImageIcon("MonopolyImage.png");
	
	/** Image for a monopoly board. */
	private Image img = boardpic.getImage();
	
	private JLabel[][] matrix;
	
	private int playernumonproperty[] = new int[40];
	
	
	
	
	
	/**
	 * Default constructor for a Monopoly panel.
	 */
	public BoardPanel () {
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);		
		setLayout(new GridLayout(50,50,1,1));
		matrix = new JLabel[50][50];
		
		for (int row = 0; row < 50; row++) {
			for (int col = 0; col < 50; col++) {
				JLabel label = new JLabel("");
			    label.setFont(new Font("Times New Roman", Font.BOLD, 18));
			    label.setForeground(Color.RED);
				matrix[row][col] = label;
//				matrix[row][col] = new JLabel("",boardpic,SwingConstants.CENTER);
				add(matrix[row][col]);					
			}

		}
		
		for (int row = 1; row < 3; row++) {
			for (int col = 0; col < 50; col++) {
				matrix[row][col].setText("1");
				matrix[50-row][col].setText("1");
			}
		}
		
		for (int col = 1; col < 3; col++) {
			for (int row = 0; row < 50; row++) {
				matrix[row][col].setText("1");
				matrix[row][50-col].setText("1");
			}
		}
		
		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	
	public void movePlayer(int playernum, int property, int movement) {
		JLabel playerlabel = new JLabel(""+playernum);
		playerlabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		playerlabel.setForeground(playerColor(playernum));
		
	}
	
	private Color playerColor(int playernum) {
		switch(playernum) {
			case 1:
				return Color.RED;
			case 2: 
				return Color.BLUE;
			case 3:
				return Color.WHITE;
			case 4: 
				return Color.BLACK;
		}
		return null;
	}
}
