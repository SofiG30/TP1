package tp1.logic;

/**
 * 
 * Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	final private int COL;
	final private int ROW;

	//TODO fill your code
	
	public Position(int r, int c) {
		ROW = r;
		COL = c;
	}
	
	public int getCol() {
		return COL;
	
	}
	
	public int getRow() {
		return ROW;
	}
	@Override
	public String toString() {
		//convert row to letter
		char rowChar = (char)('A' + ROW);
		//add letter + number
		return rowChar + Integer.toString(COL + 1);
		
	}
	//create equals method to check if positions are equal
	
	public boolean equals(Position one) {
		if(this.ROW == one.ROW && this.COL == one.COL) {
			return true;
		}
		else {
			return false;
		}
	}

}
