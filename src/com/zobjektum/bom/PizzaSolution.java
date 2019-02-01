/**
 * 
 */
package com.zobjektum.bom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the solution object which contains our solution, what we need to write out into the output file.
 * 
 * @author Szilvássy
 */
public class PizzaSolution {
	List<PizzaSlice> pizzaSlices;
	
	public PizzaSolution() {
		pizzaSlices = new ArrayList<>();
	}
	
	/**
	 * Method that checks if the given slices overlaps with an already added sliced pizza cell. If not it adds the new slices and returns true. 
	 * If overlapping was detected, the new slices is not added and false is returned.
	 * 
	 * @param pizzaSlice
	 * @param pizza
	 * @return true if no overlaps and slice could be added
	 */
	public boolean addSlice(PizzaSlice pizzaSlice, Pizza pizza) {
		// 0 <= r1,r2 < R and 0 <= c1,c2 < C is already checked when the pizza slice was created
		int numberOfCellsInTheSlice = 0;
		Map<String,Integer> numberOfIngredients = new HashMap<>();
		for(PizzaContent pizzaContent : PizzaContent.values()) {
			String mapKey = pizzaContent.name();
			int initialValue = 0;
			numberOfIngredients.put(mapKey, initialValue);
		}
		//we just need to check here that the newly added slice does not overlap with the others
		for(int rowIndex=pizzaSlice.getRowIndexStart(); rowIndex<=pizzaSlice.getRowIndexStop(); rowIndex++) {
			for(int columnIndex=pizzaSlice.getColumnIndexStart(); columnIndex<=pizzaSlice.getColumnIndexStop(); columnIndex++) {
				PizzaCell pizzaCell = pizza.getPizzaCell(rowIndex, columnIndex);
				if(pizzaCell.isSlicedAlready()) {
					System.out.println(pizza.getPizzaCell(rowIndex, columnIndex) + " was sliced already at r:" + rowIndex + ", c:" + columnIndex);
					return false;
				}
				numberOfCellsInTheSlice++;
				String mapKey = pizzaCell.getPizzaContent().name();
				int previousNumberOfIngredientOnThisSlice = numberOfIngredients.get(mapKey);
				int currentNumberOfIngredientOnThisSlice = previousNumberOfIngredientOnThisSlice + 1;
				numberOfIngredients.put(mapKey, currentNumberOfIngredientOnThisSlice);
			}
		}
		//check if there are no more cells in the slice then allowed
		if(pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice() < numberOfCellsInTheSlice) {
			System.out.println("There are " + numberOfCellsInTheSlice + " ingredients on this slice, but only " + pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice() + "is allowed. " + pizzaSlice);
			return false;
		}
		//check if the minimum requirements are fullfilled as well
		for(PizzaContent pizzaContent : PizzaContent.values()) {
			String mapKey = pizzaContent.name();
			int numberOfIngredientOnTheSlice = numberOfIngredients.get(mapKey);
			if(numberOfIngredientOnTheSlice < pizza.getAtLeastNumberOfIngredientsOnASlice()) {
				System.out.println("There is " + numberOfIngredientOnTheSlice + " " + pizzaContent.name() + " on this slice, but there should be at least " + pizza.getAtLeastNumberOfIngredientsOnASlice() + ". " + pizzaSlice);
				return false;
			}
		}
		//now we can set everything to sliced
		for(int rowIndex=pizzaSlice.getRowIndexStart(); rowIndex<=pizzaSlice.getRowIndexStop(); rowIndex++) {
			for(int columnIndex=pizzaSlice.getColumnIndexStart(); columnIndex<=pizzaSlice.getColumnIndexStop(); columnIndex++) {
				pizza.getPizzaCell(rowIndex, columnIndex).setSlicedAlready(true);
			}
		}
		pizzaSlices.add(pizzaSlice);
		return true;
	}
	
	public int U() {
		return pizzaSlices.size();
	}
	public int getNumberOfSlices() {
		return U();
	}
	public List<PizzaSlice> getPizzaSlices() {
		return pizzaSlices;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PizzaSolution [");
		builder.append("numberOfSlices=").append(getNumberOfSlices());
		builder.append(", pizzaSlices=").append(pizzaSlices.toString());
		builder.append("]");
		return builder.toString();
	}
}
