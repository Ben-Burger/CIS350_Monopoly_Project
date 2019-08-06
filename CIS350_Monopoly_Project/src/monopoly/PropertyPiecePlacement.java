package monopoly;

/**
 * Class that helps determine where to place the player icons on a property.
 *
 * @author	Ben Burger, Ian Hall-Watt, Reuben Nyenhuis 
 * @version	8/5/2019 
 */
public class PropertyPiecePlacement {
	
	/** Rows and columns of the property. */
	private int row0, row1, row2, row3, col0, col1, col2, col3;
	
	/** Positions of the houses for property. */
	private int hs1row, hs1col, hs2row, hs2col, hs3row, hs3col, hs4row, hs4col;

	/**
	 * Initialize the rows and columns for a property.
	 * @param positions - the positions for the rows and columns
	 */
	public PropertyPiecePlacement(final int[] positions) {
		row0 = positions[0];
		col0 = positions[1];
		row1 = positions[2];
		col1 = positions[3];
		row2 = positions[4];
		col2 = positions[5];
		row3 = positions[6];
		col3 = positions[7];
		
		if (positions.length > 8) {
			hs1row = positions[8]; 
			hs1col = positions[9]; 
			hs2row = positions[10]; 
			hs2col = positions[11]; 
			hs3row = positions[12];
			hs3col = positions[13]; 
			hs4row = positions[14];
			hs4col = positions[15]; 
		}
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Returns row for no houses on property.
	 * @return row - row 0
	 */
	public int getNoHouseRow() {
		return hs1row;
	}
	
	/**
	 * Returns column for no houses on property.
	 * @return column - column 0
	 */
	public int getNoHouseCol() {
		return hs1col;
	}
	
	/**
	 * Returns row for one house on property.
	 * @return row - row 1
	 */
	public int getOneHouseRow() {
		return hs2row;
	}
	
	/**
	 * Returns column for one house on property.
	 * @return column - column 1
	 */
	public int getOneHouseCol() {
		return hs2col;
	}
	
	/**
	 * Returns row for two houses on property.
	 * @return row - row 2
	 */
	public int getTwoHouseRow() {
		return hs3row;
	}
	
	/**
	 * Returns column for two houses on property.
	 * @return column - column 2
	 */
	public int getTwoHouseCol() {
		return hs3col;
	}
	
	/**
	 * Returns row for three houses on property.
	 * @return row - row 3
	 */
	public int getThreeHouseRow() {
		return hs4row;
	}
	
	/**
	 * Returns column for three houses on property.
	 * @return column - column 3
	 */
	public int getThreeHouseCol() {
		return hs4col;
	}
	
	/**
	 * Returns row for three houses on property.
	 * @return row - row 3
	 */
	public int getHotelRow1() {
		return hs2row;
	}
	
	/**
	 * Returns column for three houses on property.
	 * @return column - column 3
	 */
	public int getHotelCol1() {
		return hs2col;
	}
	/**
	 * Returns row for three houses on property.
	 * @return row - row 3
	 */
	public int getHotelRow2() {
		return hs3row;
	}
	
	/**
	 * Returns column for three houses on property.
	 * @return column - column 3
	 */
	public int getHotelCol2() {
		return hs3col;
	}
}
