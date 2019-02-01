/**
 * 
 */
package com.zobjektum.bom;

/**
 * Class representing a pizza slice
 * 
 * @author Szilvássy
 */
public class PizzaSlice {
	private final int ROW_INDEX_START;
	
	private final int ROW_INDEX_STOP;
	
	private final int COLUMN_INDEX_START;
	
	private final int COLUMN_INDEX_STOP;
	
	/**
	 * Checks are done that they may not be less or greater then ROWS or COLUMNS. 
	 * And the smaller one gets set as start so the order of r1 and r2 or c1 and c2 does not matter.
	 * 
	 * @param r1
	 * @param c1
	 * @param r2
	 * @param c2
	 * @param pizza
	 */
	public PizzaSlice(int r1, int c1, int r2, int c2, Pizza pizza) {
		ROW_INDEX_START = (r1 <= r2) ? r1 : r2;
		ROW_INDEX_STOP = (r1 <= r2) ? r2 : r1;
		COLUMN_INDEX_START = (c1 <= c2) ? c1 : c2;
		COLUMN_INDEX_STOP = (c1 <= c2) ? c2 : c1;
		if(r1 < 0 || r2 < 0 || pizza.getRows() <= r1 || pizza.getRows() <= r2) {
			throw new RuntimeException("r1:" + r1 + " or r2:" + r2 + " violates 0 <= r < " + pizza.getRows());
		}
		if(c1 < 0 || c2 < 0 || pizza.getColumns() <= c1 || pizza.getColumns() <= c2) {
			throw new RuntimeException("c1:" + c1 + " or c2:" + c2 + " violates 0 <= c < " + pizza.getColumns());
		}
	}
	
	public int r1() {
		return ROW_INDEX_START;
	}
	public int r2() {
		return ROW_INDEX_STOP;
	}
	public int c1() {
		return COLUMN_INDEX_START;
	}
	public int c2() {
		return COLUMN_INDEX_STOP;
	}
	public int getRowIndexStart() {
		return r1();
	}
	public int getRowIndexStop() {
		return r2();
	}
	public int getColumnIndexStart() {
		return c1();
	}
	public int getColumnIndexStop() {
		return c2();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PizzaSlice [");
		builder.append("r1=").append(r1());
		builder.append(", r2=").append(r2());
		builder.append(", c1=").append(c1());
		builder.append(", c2=").append(c2());
		builder.append("]");
		return builder.toString();
	}

	
}
