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
	private ImageIcon boardpic = new ImageIcon("MonopolyImage5.png");
	
	/** Image for a monopoly board. */
	private Image img = boardpic.getImage();
	
	private JLabel[][] matrix;
	
	private int playernumonproperty[] = new int[40];
	

    public PropertyPiecePlacement[] piecePlacements;
	 
	
	
	
	
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
		
		setPiecePlacements();
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
		
//		for (int row = 1; row < 3; row++) {
//			for (int col = 0; col < 50; col++) {
//				matrix[row][col].setText("1");
//				matrix[50-row][col].setText("1");
//			}
//		}
//		
//		for (int col = 1; col < 3; col++) {
//			for (int row = 0; row < 50; row++) {
//				matrix[row][col].setText("1");
//				matrix[row][50-col].setText("1");
//			}
//		}
		
		for(int i=0;i<40;i++) {
			movePlayer(1,i,0);
			movePlayer(2,i,0);
			movePlayer(3,i,0);
			movePlayer(4,i,0);
		}
//		
//		movePlayer(1,3,0);
//		movePlayer(3,3,0);
//		movePlayer(4,3,0);
//		movePlayer(2,3,0);
		
		
		
		
		
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	
	public void movePlayer(int playernum, int toproperty, int fromproperty) {
		int row = 0;
		int col = 0;
		
		placePiece(piecePlacements[toproperty],playernum);
		removePiece(piecePlacements[fromproperty],playernum);
		
		
	}
	
	public void placePiece(PropertyPiecePlacement property, int playernum) {
		if(matrix[property.getNoPieceRow()]
		[property.getNoPieceCol()].getText().contentEquals("")) {
			matrix[property.getNoPieceRow()]
			[property.getNoPieceCol()].setForeground(playerColor(playernum));
			matrix[property.getNoPieceRow()]
				[property.getNoPieceCol()].setText(""+playernum);
		}
		else if(matrix[property.getOnePieceRow()]
				[property.getOnePieceCol()].getText().contentEquals("")) {
					matrix[property.getOnePieceRow()]
					[property.getOnePieceCol()].setForeground(playerColor(playernum));
					matrix[property.getOnePieceRow()]
						[property.getOnePieceCol()].setText(""+playernum);
		}
		else if(matrix[property.getTwoPieceRow()]
				[property.getTwoPieceCol()].getText().contentEquals("")) {
					matrix[property.getTwoPieceRow()]
					[property.getTwoPieceCol()].setForeground(playerColor(playernum));
					matrix[property.getTwoPieceRow()]
						[property.getTwoPieceCol()].setText(""+playernum);
		}
		else {
					matrix[property.getThreePieceRow()]
					[property.getThreePieceCol()].setForeground(playerColor(playernum));
					matrix[property.getThreePieceRow()]
						[property.getThreePieceCol()].setText(""+playernum);
		}

//		switch(findPlayersOnProperty(property)) {
//		case 0: 
//			matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].setForeground(playerColor(playernum));
//			matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].setText(""+playernum);
//			return;
//		case 1: 
//			matrix[property.getOnePieceRow()]
//					[property.getOnePieceCol()].setForeground(playerColor(playernum));
//			matrix[property.getOnePieceRow()]
//					[property.getOnePieceCol()].setText(""+playernum);
//			return;
//		case 2: 
//			matrix[property.getTwoPieceRow()]
//					[property.getTwoPieceCol()].setForeground(playerColor(playernum));
//			matrix[property.getTwoPieceRow()]
//					[property.getTwoPieceCol()].setText(""+playernum);
//			return;
//		case 3: 
//			matrix[property.getThreePieceRow()]
//					[property.getThreePieceCol()].setForeground(playerColor(playernum));
//			matrix[property.getThreePieceRow()]
//					[property.getThreePieceCol()].setText(""+playernum);
//			return;
//		}
		
	}
	
	public void removePiece(PropertyPiecePlacement property, int playernum) {
		if(matrix[property.getNoPieceRow()]
				[property.getNoPieceCol()].getText().contentEquals(""+playernum)) 
			matrix[property.getNoPieceRow()]
					[property.getNoPieceCol()].setText("");
		else if(matrix[property.getOnePieceRow()]
				[property.getOnePieceCol()].getText().contentEquals(""+playernum)) 
			matrix[property.getOnePieceRow()]
					[property.getOnePieceCol()].setText("");
		else if(matrix[property.getTwoPieceRow()]
				[property.getTwoPieceCol()].getText().contentEquals(""+playernum)) 
			matrix[property.getTwoPieceRow()]
					[property.getTwoPieceCol()].setText("");
		else matrix[property.getThreePieceRow()]
				[property.getThreePieceCol()].setText("");
//				
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setText(
//			
//			matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].setText(
//		switch(findPlayersOnProperty(property)) {
//		case 1: 
//			matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].setText("");
//			return;
//		case 2: 
//			if(matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].getText().contentEquals(""+playernum)) {
//				
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setText(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getText());
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setForeground(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getForeground());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setText("");
//			}
//			
//			else matrix[property.getOnePieceRow()]
//					[property.getOnePieceCol()].setText("");
//			
//		case 3: 
//			if(matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].getText().contentEquals(""+playernum)) {
//				
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setText(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getText());
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setForeground(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getForeground());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setText(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getText());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setForeground(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getForeground());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setText("");
//			}
//			
//			else if(matrix[property.getOnePieceRow()]
//					[property.getOnePieceCol()].getText().contentEquals(""+playernum)) {
//				
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setText(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getText());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setForeground(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getForeground());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setText("");
//			}
//			
//			else matrix[property.getTwoPieceRow()]
//					[property.getTwoPieceCol()].setText("");
//		
//		case 4:
//			if(matrix[property.getNoPieceRow()]
//					[property.getNoPieceCol()].getText().contentEquals(""+playernum)) {
//				System.out.println("HERE");
//				
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setText(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getText());
//				matrix[property.getNoPieceRow()]
//						[property.getNoPieceCol()].setForeground(
//							matrix[property.getOnePieceRow()]
//								[property.getOnePieceCol()].getForeground());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setText(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getText());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setForeground(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getForeground());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setText(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getText());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setForeground(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getForeground());
//				matrix[property.getThreePieceRow()]
//						[property.getThreePieceCol()].setText("");
//			}
//			
//			else if(matrix[property.getOnePieceRow()]
//					[property.getOnePieceCol()].getText().contentEquals(""+playernum)) {
//				
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setText(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getText());
//				matrix[property.getOnePieceRow()]
//						[property.getOnePieceCol()].setForeground(
//							matrix[property.getTwoPieceRow()]
//								[property.getTwoPieceCol()].getForeground());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setText(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getText());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setForeground(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getForeground());
//				matrix[property.getThreePieceRow()]
//						[property.getThreePieceCol()].setText("");
//			}
//			
//			else if(matrix[property.getTwoPieceRow()]
//					[property.getTwoPieceCol()].getText().contentEquals(""+playernum)) {
//				
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setText(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getText());
//				matrix[property.getTwoPieceRow()]
//						[property.getTwoPieceCol()].setForeground(
//							matrix[property.getThreePieceRow()]
//								[property.getThreePieceCol()].getForeground());
//				matrix[property.getThreePieceRow()]
//						[property.getThreePieceCol()].setText("");
//			}
//			
//			else matrix[property.getThreePieceRow()]
//					[property.getThreePieceCol()].setText("");
//		}
		
		
			
		
	}
	
	private int findPlayersOnProperty(PropertyPiecePlacement property) {
		if(matrix[property.getNoPieceRow()]
				[property.getNoPieceCol()].getText().contentEquals("")) 
			return 0;
		else if(matrix[property.getOnePieceRow()]
				[property.getOnePieceCol()].getText().contentEquals("")) 
			return 1;
		else if(matrix[property.getTwoPieceRow()]
				[property.getTwoPieceCol()].getText().contentEquals("")) 
			return 2;
		
		else return 3;
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
	
	void setPiecePlacements() {
		piecePlacements = new PropertyPiecePlacement[40];
	     // Manually create properties on the board.
		piecePlacements[0] = new PropertyPiecePlacement(48, 44, 49, 44, 48, 49, 49, 49);
		piecePlacements[1] = new PropertyPiecePlacement(48, 41, 48, 42, 49, 41, 49, 42);
		piecePlacements[2] = new PropertyPiecePlacement(48, 37, 48, 38, 49, 37, 49, 38);
		piecePlacements[3] = new PropertyPiecePlacement(48, 33, 48, 34, 49, 33, 49, 34);
		piecePlacements[4] = new PropertyPiecePlacement(48, 28, 48, 30, 49, 28, 49, 30);
		piecePlacements[5] = new PropertyPiecePlacement(46, 24, 46, 25, 46, 23, 46, 26);
		piecePlacements[6] = new PropertyPiecePlacement(48, 20, 48, 21, 49, 20, 49, 21);
		piecePlacements[7] = new PropertyPiecePlacement(48, 15, 48, 18, 49, 15, 49, 18);
		piecePlacements[8] = new PropertyPiecePlacement(48, 12, 48, 13, 49, 12, 49, 13);
		piecePlacements[9] = new PropertyPiecePlacement(48, 7, 48, 8, 49, 7, 49, 8);
		piecePlacements[10] = new PropertyPiecePlacement(49, 0, 48, 0, 47, 0, 46, 0);
		piecePlacements[11] = new PropertyPiecePlacement(42, 1, 42, 2, 43, 1, 43, 2);
		piecePlacements[12] = new PropertyPiecePlacement(37, 1, 37, 2, 39, 1, 39, 2);
		piecePlacements[13] = new PropertyPiecePlacement(33, 1, 33, 2, 34, 1, 34, 2);
		piecePlacements[14] = new PropertyPiecePlacement(29, 1, 29, 2, 30, 1, 30, 2);
		piecePlacements[15] = new PropertyPiecePlacement(24, 4, 25, 4, 26, 4, 27, 4);
		piecePlacements[16] = new PropertyPiecePlacement(20, 1, 20, 2, 21, 1, 21, 2);
		piecePlacements[17] = new PropertyPiecePlacement(16, 1, 16, 2, 17, 1, 17, 2);
		piecePlacements[18] = new PropertyPiecePlacement(12, 1, 12, 2, 13, 1, 13, 2);
		piecePlacements[19] = new PropertyPiecePlacement(8, 1, 8, 2, 9, 1, 9, 2);
		piecePlacements[20] = new PropertyPiecePlacement(5, 1, 5, 2, 1, 5, 2, 5);
		piecePlacements[21] = new PropertyPiecePlacement(1, 7, 1, 8, 2, 7, 2, 8);
		piecePlacements[22] = new PropertyPiecePlacement(1, 11, 1, 13, 2, 11, 2, 13);
		piecePlacements[23] = new PropertyPiecePlacement(1, 16, 1, 17, 2, 16, 2, 17);
		piecePlacements[24] = new PropertyPiecePlacement(1, 20, 1, 21, 2, 20, 2, 21);
		piecePlacements[25] = new PropertyPiecePlacement(4, 23, 4, 24, 4, 25, 4, 26);
		piecePlacements[26] = new PropertyPiecePlacement(1, 28, 1, 29, 2, 28, 2, 29);
		piecePlacements[27] = new PropertyPiecePlacement(1, 32, 1, 33, 2,32, 2, 33);
		piecePlacements[28] = new PropertyPiecePlacement(4, 36, 4, 37, 4, 38, 4, 39);
		piecePlacements[29] = new PropertyPiecePlacement(1, 41, 1, 42, 2,41, 2, 42);
		piecePlacements[30] = new PropertyPiecePlacement(1, 44, 2, 44, 5,47, 5, 48);
		piecePlacements[31] = new PropertyPiecePlacement(8, 48, 8, 49, 9,48, 9, 49);
		piecePlacements[32] = new PropertyPiecePlacement(12, 48, 12, 49, 13,48, 13, 49);
		piecePlacements[33] = new PropertyPiecePlacement(16, 48, 16, 49, 17,48, 17, 49);
		piecePlacements[34] = new PropertyPiecePlacement(20, 48, 20, 49, 21,48, 21, 49);
		piecePlacements[35] = new PropertyPiecePlacement(24, 46, 25, 46, 26,46, 27, 46);
		piecePlacements[36] = new PropertyPiecePlacement(29, 48, 29, 49, 30,48, 30, 49);
		piecePlacements[37] = new PropertyPiecePlacement(33, 48, 33, 49, 34,48, 34, 49);
		piecePlacements[38] = new PropertyPiecePlacement(36, 46, 37, 46, 38,46, 39, 46);
		piecePlacements[39] = new PropertyPiecePlacement(41, 48, 41, 49, 42,48, 42, 49);
		
		
	}
}
