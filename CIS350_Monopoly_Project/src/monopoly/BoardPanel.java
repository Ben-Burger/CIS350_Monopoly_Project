package monopoly;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Creates the Monopoly board. 
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis 
 * @version	7/20/2019 
 */
@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

	/** Icon for a monopoly board. */
	private ImageIcon boardpic = new ImageIcon("pictures/MonopolyImage5.png");

	/** Image for a monopoly board. */
	private Image img = boardpic.getImage();

	/** Matrix used for player positions. */
	private JButton[][] matrix;

	//	private int[] playernumonproperty = new int[40];	// is this not used??

	/** The grid coordinates for each property. */
	private PropertyPiecePlacement[] piecePlacements;


	/**
	 * Default constructor for a Monopoly panel.
	 */
	public BoardPanel() {
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(new GridLayout(50, 50, 1, 1));
		matrix = new JButton[50][50];

		setPiecePlacements();
		for (int row = 0; row < 50; row++) {
			for (int col = 0; col < 50; col++) {
				JButton label = new JButton("");
				label.setFont(new Font("Times New Roman", Font.BOLD, 18));
				label.setForeground(Color.RED);
				label.setOpaque(false);
				label.setContentAreaFilled(false);
				label.setBorderPainted(false);
				matrix[row][col] = label;
				add(matrix[row][col]);
			}
		}
	}

	/**
	 * Draws the background picture.
	 * @param graphic - the graphic
	 */
	public void paintComponent(final Graphics graphic) {
		graphic.drawImage(img, 0, 0, null);
	}

	/**
	 * Moves the player icon to the correct position.
	 * @param playernum - the player number
	 * @param toproperty - the property number the player is moving to
	 * @param fromproperty - the property number the player is moving from
	 */
	public void movePlayer(final int playernum, final int toproperty, final int fromproperty) {
		removePiece(piecePlacements[fromproperty], playernum);
		placePiece(piecePlacements[toproperty], playernum);
	}

	/**
	 * Places the player icon on a property.
	 * @param property - the property number that the player is moving to
	 * @param playernum - the player number
	 */
	private void placePiece(final PropertyPiecePlacement property, final int playernum) {
		if (positionEmpty(matrix[property.getNoPieceRow()][property.getNoPieceCol()])) {
			matrix[property.getNoPieceRow()][property.getNoPieceCol()].setContentAreaFilled(true);
			matrix[property.getNoPieceRow()][property.getNoPieceCol()].setBackground(playerColor(playernum));
		} else if (positionEmpty(matrix[property.getOnePieceRow()][property.getOnePieceCol()])) {
			matrix[property.getOnePieceRow()][property.getOnePieceCol()].setContentAreaFilled(true);
			matrix[property.getOnePieceRow()][property.getOnePieceCol()].setBackground(playerColor(playernum));
		} else if (positionEmpty(matrix[property.getTwoPieceRow()][property.getTwoPieceCol()])) {
			matrix[property.getTwoPieceRow()][property.getTwoPieceCol()].setContentAreaFilled(true);
			matrix[property.getTwoPieceRow()][property.getTwoPieceCol()].setBackground(playerColor(playernum));
		} else {
			matrix[property.getThreePieceRow()][property.getThreePieceCol()].setContentAreaFilled(true);
			matrix[property.getThreePieceRow()][property.getThreePieceCol()].setBackground(playerColor(playernum));
		}
	}

	/**
	 * Removes the player icon from a property.
	 * @param property - the property number that the player is moving from
	 * @param playernum - the player number
	 */
	private void removePiece(final PropertyPiecePlacement property, final int playernum) {
		if (matrix[property.getNoPieceRow()][property.getNoPieceCol()].getBackground()==playerColor(playernum)) {
			clearPosition(matrix[property.getNoPieceRow()][property.getNoPieceCol()]);
		} else if (matrix[property.getOnePieceRow()][property.getOnePieceCol()].getBackground()==playerColor(playernum)) {
			clearPosition(matrix[property.getOnePieceRow()][property.getOnePieceCol()]);
		} else if (matrix[property.getTwoPieceRow()][property.getTwoPieceCol()].getBackground()==playerColor(playernum)) {
			clearPosition(matrix[property.getTwoPieceRow()][property.getTwoPieceCol()]);
		} else {
			clearPosition(matrix[property.getThreePieceRow()][property.getThreePieceCol()]);
		}
	}

	/**
	 * Checks to see if JButton has a player or not.
	 * @param position - the property number that the player is moving to
	 * @return true if the position is empty
	 */
	private boolean positionEmpty(final JButton position){
		return (position.getBackground() != playerColor(1)) 
				&& (position.getBackground() != playerColor(2)) 
				&& (position.getBackground() != playerColor(3)) 
				&& (position.getBackground() != playerColor(4)
				&& (position.getBackground() != Color.green));
	}

	/**
	 * Clears button position.
	 * @param position - the property number that the player is moving to
	 */
	private void clearPosition(final JButton position){
		position.setBorderPainted(false);
		position.setContentAreaFilled(false);
		position.setBackground(Color.MAGENTA);
	}

	/**
	 * Places a house icon on a property.
	 * @param propertyNum - the property number that the player is moving to
	 */
	public void addHouse(final int propertyNum){
		PropertyPiecePlacement property = piecePlacements[propertyNum];
		if (positionEmpty(matrix[property.getNoHouseRow()][property.getNoHouseCol()])) {
			matrix[property.getNoHouseRow()][property.getNoHouseCol()].setBorderPainted(true);
			matrix[property.getNoHouseRow()][property.getNoHouseCol()].setContentAreaFilled(true);
			matrix[property.getNoHouseRow()][property.getNoHouseCol()].setBackground(Color.green);
		} else if (positionEmpty(matrix[property.getOneHouseRow()][property.getOneHouseCol()])) {
			matrix[property.getOneHouseRow()][property.getOneHouseCol()].setBorderPainted(true);
			matrix[property.getOneHouseRow()][property.getOneHouseCol()].setContentAreaFilled(true);
			matrix[property.getOneHouseRow()][property.getOneHouseCol()].setBackground(Color.green);
		} else if (positionEmpty(matrix[property.getTwoHouseRow()][property.getTwoHouseCol()])) {
			matrix[property.getTwoHouseRow()][property.getTwoHouseCol()].setBorderPainted(true);
			matrix[property.getTwoHouseRow()][property.getTwoHouseCol()].setContentAreaFilled(true);
			matrix[property.getTwoHouseRow()][property.getTwoHouseCol()].setBackground(Color.green);
		} else {
			matrix[property.getThreeHouseRow()][property.getThreeHouseCol()].setBorderPainted(true);
			matrix[property.getThreeHouseRow()][property.getThreeHouseCol()].setContentAreaFilled(true);
			matrix[property.getThreeHouseRow()][property.getThreeHouseCol()].setBackground(Color.green);
		}
		
		revalidate();
		repaint();
	}

	/**
	 * Removes a house icon from a property.
	 * @param property - the property number that the player is moving from
	 * @param playernum - the player number
	 */
	public void removeHouse(final int propertyNum) {
		PropertyPiecePlacement property = piecePlacements[propertyNum];
//		if (!positionEmpty(matrix[property.getNoHouseRow()][property.getNoHouseCol()])) {
//			clearPosition(matrix[property.getNoHouseRow()][property.getNoHouseCol()]);
//		} else if (!positionEmpty(matrix[property.getOneHouseRow()][property.getOneHouseCol()])) {
//			clearPosition(matrix[property.getOneHouseRow()][property.getOneHouseCol()]);
//		} else if (!positionEmpty(matrix[property.getTwoHouseRow()][property.getTwoHouseCol()])) {
//			clearPosition(matrix[property.getTwoHouseRow()][property.getTwoHouseCol()]);
//		} else {
//			clearPosition(matrix[property.getThreeHouseRow()][property.getThreeHouseCol()]);
//		}
		
		if (!positionEmpty(matrix[property.getThreeHouseRow()][property.getThreeHouseCol()])) {
			clearPosition(matrix[property.getThreeHouseRow()][property.getThreeHouseCol()]);
		} else if (!positionEmpty(matrix[property.getTwoHouseRow()][property.getTwoHouseCol()])) {
			clearPosition(matrix[property.getTwoHouseRow()][property.getTwoHouseCol()]);
		} else if (!positionEmpty(matrix[property.getOneHouseRow()][property.getOneHouseCol()])) {
			clearPosition(matrix[property.getOneHouseRow()][property.getOneHouseCol()]);
		} else {
			clearPosition(matrix[property.getNoHouseRow()][property.getNoHouseCol()]);
		}
		
		
		revalidate();
		repaint();
	}
	/**
	 * Places a hotel icon on a property.
	 * @param propertyNum - the property number that the player is moving to
	 */
	public void placeHotel(final int propertyNum) {
		PropertyPiecePlacement property = piecePlacements[propertyNum];

		for (int i = 0; i < 4; i++) {
			removeHouse(propertyNum);
		}	
		matrix[property.getHotelRow1()][property.getHotelCol1()].setContentAreaFilled(true);
		matrix[property.getHotelRow1()][property.getHotelCol1()].setBackground(Color.RED);
		matrix[property.getHotelRow2()][property.getHotelCol2()].setContentAreaFilled(true);
		matrix[property.getHotelRow2()][property.getHotelCol2()].setBackground(Color.RED);
		
		revalidate();
		repaint();
	}

	/**
	 * Removes a hotel icon from a property.
	 * @param propertyNum - the property number that the player is moving from
	 */
	public void removeHotel(final int propertyNum) {
		System.out.println("INHERE");
		PropertyPiecePlacement property = piecePlacements[propertyNum];
		clearPosition(matrix[property.getHotelRow1()][property.getHotelCol1()]);
		clearPosition(matrix[property.getHotelRow2()][property.getHotelCol2()]);
		
		for (int i = 0; i < 4; i++) {
			addHouse(propertyNum);
		}
//		if (matrix[property.getNoHouseRow()][property.getNoHouseCol()].getText().contentEquals("2")) {
//			matrix[property.getNoHouseRow()][property.getNoHouseCol()].setText("");
//		} else if (matrix[property.getOneHouseRow()][property.getOneHouseCol()].getText().contentEquals("2")) {
//			matrix[property.getOneHouseRow()][property.getOneHouseCol()].setText("");
//		} else if (matrix[property.getTwoHouseRow()][property.getTwoHouseCol()].getText().contentEquals("2")) {
//			matrix[property.getTwoHouseRow()][property.getTwoHouseCol()].setText("");
//		} else {
//			matrix[property.getThreeHouseRow()][property.getThreeHouseCol()].setText("");
//		}
//		
//		revalidate();
//		repaint();
	}

	// is this method used??
	//
	//	private int findPlayersOnProperty(final PropertyPiecePlacement property) {
	//		if (matrix[property.getNoPieceRow()][property.getNoPieceCol()].getText().contentEquals("")) {
	//			return 0;
	//		} else if (matrix[property.getOnePieceRow()][property.getOnePieceCol()].getText().contentEquals("")) {
	//			return 1;
	//		} else if (matrix[property.getTwoPieceRow()][property.getTwoPieceCol()].getText().contentEquals("")) {
	//			return 2;
	//		} else {
	//			return 3;
	//		}
	//	}

	/**
	 * Returns the player color based on the player number.
	 * @param playernum - the player number
	 * @return color - the color of the player
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
	 * Establishes the grid coordinates for each property.
	 */
	private void setPiecePlacements() {
		piecePlacements = new PropertyPiecePlacement[40];

		// Manually create properties on the board.
		int[] positions = new int[]{ 48, 44, 49, 44, 48, 49, 49, 49};
		piecePlacements[0] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 41, 48, 42, 49, 41, 49, 42,
				43, 40, 43, 41, 43, 42, 43, 43};
		piecePlacements[1] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 37, 48, 38, 49, 37, 49, 38};
		piecePlacements[2] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 33, 48, 34, 49, 33, 49, 34,
				43, 32, 43, 33, 43, 34, 43, 35};
		piecePlacements[3] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 28, 48, 30, 49, 28, 49, 30};
		piecePlacements[4] = new PropertyPiecePlacement(positions);

		positions = new int[]{46, 24, 46, 25, 46, 23, 46, 26};
		piecePlacements[5] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 20, 48, 21, 49, 20, 49, 21,
				43, 19, 43, 20, 43, 21, 43, 22};
		piecePlacements[6] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 15, 48, 18, 49, 15, 49, 18};
		piecePlacements[7] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 12, 48, 13, 49, 12, 49, 13,
				43, 11, 43, 12, 43, 13, 43, 14};
		piecePlacements[8] = new PropertyPiecePlacement(positions);

		positions = new int[]{48, 7, 48, 8, 49, 7, 49, 8,
				43, 6, 43, 7, 43, 8, 43, 9};
		piecePlacements[9] = new PropertyPiecePlacement(positions);

		positions = new int[]{49, 0, 48, 0, 47, 0, 46, 0};
		piecePlacements[10] = new PropertyPiecePlacement(positions);

		positions = new int[]{42, 1, 42, 2, 43, 1, 43, 2,
				39, 6, 40, 6, 41, 6, 42, 6};
		piecePlacements[11] = new PropertyPiecePlacement(positions);

		positions = new int[]{37, 1, 37, 2, 39, 1, 39, 2};
		piecePlacements[12] = new PropertyPiecePlacement(positions);

		positions = new int[]{33, 1, 33, 2, 34, 1, 34, 2,
				32, 6, 33, 6, 34, 6, 35, 6};
		piecePlacements[13] = new PropertyPiecePlacement(positions);

		positions = new int[]{29, 1, 29, 2, 30, 1, 30, 2,
				27, 6, 28, 6, 29, 6, 30, 6};
		piecePlacements[14] = new PropertyPiecePlacement(positions);

		positions = new int[]{24, 4, 25, 4, 26, 4, 27, 4};
		piecePlacements[15] = new PropertyPiecePlacement(positions);

		positions = new int[]{20, 1, 20, 2, 21, 1, 21, 2,
				19, 6, 20, 6, 21, 6, 22, 6};
		piecePlacements[16] = new PropertyPiecePlacement(positions);

		positions = new int[]{16, 1, 16, 2, 17, 1, 17, 2};
		piecePlacements[17] = new PropertyPiecePlacement(positions);

		positions = new int[]{12, 1, 12, 2, 13, 1, 13, 2,
				10, 6, 11, 6, 12, 6, 13, 6};
		piecePlacements[18] = new PropertyPiecePlacement(positions);

		positions = new int[]{8, 1, 8, 2, 9, 1, 9, 2,
				6, 6, 7, 6, 8, 6, 9, 6};
		piecePlacements[19] = new PropertyPiecePlacement(positions);

		positions = new int[]{5, 1, 5, 2, 1, 5, 2, 5};
		piecePlacements[20] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 7, 1, 8, 2, 7, 2, 8,
				6, 7, 6, 8, 6, 9, 6, 10};
		piecePlacements[21] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 11, 1, 13, 2, 11, 2, 13};
		piecePlacements[22] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 16, 1, 17, 2, 16, 2, 17,
				6, 15, 6, 16, 6, 17, 6, 18};
		piecePlacements[23] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 20, 1, 21, 2, 20, 2, 21,
				6, 19, 6, 20, 6, 21, 6, 22};
		piecePlacements[24] = new PropertyPiecePlacement(positions);

		positions = new int[]{4, 23, 4, 24, 4, 25, 4, 26};
		piecePlacements[25] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 28, 1, 29, 2, 28, 2, 29,
				6, 27, 6, 28, 6, 29, 6, 30};
		piecePlacements[26] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 32, 1, 33, 2, 32, 2, 33,
				6, 32, 6, 33, 6, 34, 6, 35};
		piecePlacements[27] = new PropertyPiecePlacement(positions);

		positions = new int[]{4, 36, 4, 37, 4, 38, 4, 39};
		piecePlacements[28] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 41, 1, 42, 2, 41, 2, 42,
				6, 39, 6, 40, 6, 41, 6, 42};
		piecePlacements[29] = new PropertyPiecePlacement(positions);

		positions = new int[]{1, 44, 2, 44, 5, 47, 5, 48};
		piecePlacements[30] = new PropertyPiecePlacement(positions);

		positions = new int[]{8, 48, 8, 49, 9, 48, 9, 49,
				6, 43, 7, 43, 8, 43, 9, 43};
		piecePlacements[31] = new PropertyPiecePlacement(positions);

		positions = new int[]{12, 48, 12, 49, 13, 48, 13, 49,
				10, 43, 11, 43, 12, 43, 13, 43};
		piecePlacements[32] = new PropertyPiecePlacement(positions);

		positions = new int[]{16, 48, 16, 49, 17, 48, 17, 49};
		piecePlacements[33] = new PropertyPiecePlacement(positions);

		positions = new int[]{20, 48, 20, 49, 21, 48, 21, 49,
				19, 43, 20, 43, 21, 43, 22, 43};
		piecePlacements[34] = new PropertyPiecePlacement(positions);

		positions = new int[]{24, 46, 25, 46, 26, 46, 27, 46};
		piecePlacements[35] = new PropertyPiecePlacement(positions);

		positions = new int[]{29, 48, 29, 49, 30, 48, 30, 49};
		piecePlacements[36] = new PropertyPiecePlacement(positions);

		positions = new int[]{33, 48, 33, 49, 34, 48, 34, 49,
				31, 43, 32, 43, 33, 43, 34, 43};
		piecePlacements[37] = new PropertyPiecePlacement(positions);

		positions = new int[]{36, 46, 37, 46, 38, 46, 39, 46};
		piecePlacements[38] = new PropertyPiecePlacement(positions);

		positions = new int[]{41, 48, 41, 49, 42, 48, 42, 49,
				39, 43, 40, 43, 41, 43, 42, 43};
		piecePlacements[39] = new PropertyPiecePlacement(positions);
	}
}
