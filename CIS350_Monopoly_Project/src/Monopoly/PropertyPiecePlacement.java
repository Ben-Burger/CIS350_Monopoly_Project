package Monopoly;

public class PropertyPiecePlacement {
	private int row0,row1,row2,row3,col0,col1,col2,col3;

	public PropertyPiecePlacement(int r0, int c0,int r1,
								  int c1,int r2, int c2,int r3, int c3) {
		row0=r0;
		col0=c0;
		row1=r1;
		col1=c1;
		row2=r2;
		col2=c2;
		row3=r3;
		col3=c3;
	}

	public int getNoPieceRow() {
		return row0;
	}
	public int getNoPieceCol() {
		return col0;
	}
	public int getOnePieceRow() {
		return row1;
	}
	public int getOnePieceCol() {
		return col1;
	}
	public int getTwoPieceRow() {
		return row2;
	}
	public int getTwoPieceCol() {
		return col2;
	}
	public int getThreePieceRow() {
		return row3;
	}
	public int getThreePieceCol() {
		return col3;
	}
}