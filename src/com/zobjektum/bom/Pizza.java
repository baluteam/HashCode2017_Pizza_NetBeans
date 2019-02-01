/**
 * 
 */
package com.zobjektum.bom;

/**
 * Represents a Pizza.
 * 
 * @author Szilvássy
 */
public class Pizza {
	private final int ROWS;
	
	private final int COLUMNS;
	
	private final int AT_LEAST_NUM_OF_INGREDIENTS_ON_A_SLICE;
	
	private final int TOTAL_NUMBER_OF_INGREDIENTS_ON_A_SLICE;
	
	private final PizzaCell[][] PIZZA_CELLS;
	
	public Pizza(int rows, int columns, int atLeastNumberOfIngredientsOnASlice, int maxAllowedTotalNumberOfIngredientsOnASlice, PizzaCell[][] pizzaCells) {
		ROWS = rows;
		COLUMNS = columns;
		AT_LEAST_NUM_OF_INGREDIENTS_ON_A_SLICE = atLeastNumberOfIngredientsOnASlice;
		TOTAL_NUMBER_OF_INGREDIENTS_ON_A_SLICE = maxAllowedTotalNumberOfIngredientsOnASlice;
		PIZZA_CELLS = pizzaCells;
	}
	
	public int R() {
		return ROWS;
	}
	public int getRows() {
		return R();
	}
	public int C() {
		return COLUMNS;
	}
	public int getColumns() {
		return C();
	}
	public int L() {
		return AT_LEAST_NUM_OF_INGREDIENTS_ON_A_SLICE;
	}
	public int getAtLeastNumberOfIngredientsOnASlice() {
		return L();
	}
	public int H() {
		return TOTAL_NUMBER_OF_INGREDIENTS_ON_A_SLICE;
	}
	public int getMaxAllowedTotalNumberOfIngredientsOnASlice() {
		return H();
	}
	public PizzaCell[][] getPizzaCells() {
		return PIZZA_CELLS;
	}
	
	public PizzaCell getPizzaCell(int rowIndex, int columnIndex) {
		return PIZZA_CELLS[columnIndex][rowIndex];
	}
	
	/**
	 * Helper method to get back the number of the pizza cells containing the given pizza content.
	 * 
	 * @param content
	 * @return
	 */
	public int getNumberOfContentInPizza(PizzaContent content) {
		return getNumberOfContentInPizzaSlice(content, 0, ROWS-1, 0, COLUMNS-1);
	}
	
	/**
	 * Helper method to get back the number of the pizza cells containing the given pizza content on the given pizza slice.
	 * 
	 * @param content
	 * @param rowIndexStart inclusive, and 0 <= rowIndexStart <= rowIndexStop < ROWS
	 * @param rowIndexStop inclusive, and 0 <= rowIndexStart <= rowIndexStart < ROWS
	 * @param columnIndexStart inclusive, and 0 <= columnIndexStart <= columnIndexStop < COLUMNS
	 * @param columnIndexStop inclusive, and 0 <= columnIndexStart <= columnIndexStop < COLUMNS
	 * @return
	 */
	public int getNumberOfContentInPizzaSlice(PizzaContent content, int rowIndexStart, int rowIndexStop, int columnIndexStart, int columnIndexStop) {
		int counter = 0;
		for(int rowIndex=rowIndexStart; rowIndex<=rowIndexStop; rowIndex++) {
			for(int columnIndex=columnIndexStart; columnIndex<=columnIndexStop; columnIndex++) {
				if(getPizzaCell(rowIndex,columnIndex).getPizzaContent() == content) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Helper method to get back the number of already sliced pizza cells on the whole pizza
	 * 
	 * @return
	 */
	public int getNumberOfAlreadySlicedCellsTotal() {
		int counter = 0;
		for(int rowIndex=0; rowIndex<ROWS; rowIndex++) {
			for(int columnIndex=0; columnIndex<COLUMNS; columnIndex++) {
				if(getPizzaCell(rowIndex,columnIndex).isSlicedAlready()) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Get back how many percentage of the total pizza is sliced already.
	 * 
	 * @return
	 */
	public double getNumberOfAlreadySlicedCellsTotalAsPercentage() {
		int numberOfCellsTotal = ROWS * COLUMNS;
		int alreadySlicedCells = getNumberOfAlreadySlicedCellsTotal();
		double percentage = ((double)alreadySlicedCells / (double)numberOfCellsTotal) * 100d;
		final double ROUND_TO_PRECISION = 100d;
		return ((double)Math.round(percentage * ROUND_TO_PRECISION)) / ROUND_TO_PRECISION;
	}
	
	/**
	 * Helper method to reset the pizza cell's sliced parameter to false on the whole pizza, thus getting back the original one.
	 */
	public void resetPizzaNotToBeSliced() {
		for(int rowIndex=0; rowIndex<ROWS; rowIndex++) {
			for(int columnIndex=0; columnIndex<COLUMNS; columnIndex++) {
				getPizzaCell(rowIndex,columnIndex).setSlicedAlready(false);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pizza [");
		builder.append("ROWS=").append(ROWS);
		builder.append(", COLUMNS=").append(COLUMNS);
		builder.append(", AT_LEAST_NUM_OF_INGREDIENTS_ON_A_SLICE=").append(AT_LEAST_NUM_OF_INGREDIENTS_ON_A_SLICE);
		builder.append(", TOTAL_NUMBER_OF_INGREDIENTS_ON_A_SLICE=").append(TOTAL_NUMBER_OF_INGREDIENTS_ON_A_SLICE);
		builder.append(", totalNumberOfMushrooms=").append(getNumberOfContentInPizza(PizzaContent.MUSHROOM));
		builder.append(", totalNumberOfTomato=").append(getNumberOfContentInPizza(PizzaContent.TOMATO));
		builder.append(", pizzaSlicedPercentage=").append(getNumberOfAlreadySlicedCellsTotalAsPercentage()).append("%");
		builder.append("]");
		return builder.toString();
	}

	
}
