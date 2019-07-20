package monopoly;

/**
 * Class that helps determine where to place the player icons on a property.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis 
 * @version	7/20/2019 
 */
public class PropertyPiecePlacement {
	
	/** Rows and columns of the property. */
	private int row0, row1, row2, row3, col0, col1, col2, col3;

	/**
	 * Initialize the rows and columns for a property.
	 * @param r0 - row 0
	 * @param c0 - column 0
	 * @param r1 - row 1
	 * @param c1 - column 1
	 * @param r2 - row 2
	 * @param c2 - column 2
	 * @param r3 - row 3
	 * @param c3 - column 3
	 */
	public PropertyPiecePlacement(final int r0, final int c0, final int r1, final int c1, final int r2, final int c2, final int r3, final int c3) {
		row0 = r0;
		col0 = c0;
		row1 = r1;
		col1 = c1;
		row2 = r2;
		col2 = c2;
		row3 = r3;
		col3 = c3;
	}

	/**
	 * Returns row for no pieces on property.
	 * @return row - row 0
	 */
	public int getNoPieceRow() {
		return row0;
	}
	
	/**
	 * Returns column for no pieces on property.
	 * @return column - column 0
	 */
	public int getNoPieceCol() {
		return col0;
	}
	
	/**
	 * Returns row for one piece on property.
	 * @return row - row 1
	 */
	public int getOnePieceRow() {
		return row1;
	}
	
	/**
	 * Returns column for one piece on property.
	 * @return column - column 1
	 */
	public int getOnePieceCol() {
		return col1;
	}
	
	/**
	 * Returns row for two pieces on property.
	 * @return row - row 2
	 */
	public int getTwoPieceRow() {
		return row2;
	}
	
	/**
	 * Returns column for two pieces on property.
	 * @return column - column 2
	 */
	public int getTwoPieceCol() {
		return col2;
	}
	
	/**
	 * Returns row for three pieces on property.
	 * @return row - row 3
	 */
	public int getThreePieceRow() {
		return row3;
	}
	
	/**
	 * Returns column for three pieces on property.
	 * @return column - column 3
	 */
	public int getThreePieceCol() {
		return col3;
	}
}
