/**
 * 
 */
package com.zobjektum.parser;

import com.zobjektum.bom.Pizza;
import com.zobjektum.bom.PizzaCell;
import com.zobjektum.bom.PizzaContent;
import com.zobjektum.io.FileIO;

/**
 * Class for parsing the String content of the input and creating the {@link PizzaContent} from it.
 * 
 * @author Szilvássy
 */
public class PizzaParser {
	private static final String HEADER_SEPARATOR = FileIO.DATA_SEPARATOR;
	
	private static final int MIN_HEADER_INPUT_VALUE = 1;
	
	private static final int MAX_HEADER_INPUT_VALUE = 1000;
	
	public static Pizza parsePizza(String inputContent) {
		assert (inputContent != null && "".equals(inputContent)); //assertion that content is not null or empty
		try {
			String[] lines = inputContent.split(FileIO.LINE_SEPARATOR);
			String header = lines[0];
			String[] headerInfo = header.split(HEADER_SEPARATOR);
			int headerIndex = 0;
			int numberOfRows = Integer.parseInt(headerInfo[headerIndex++].trim());
			int numberOfColumns = Integer.parseInt(headerInfo[headerIndex++].trim());
			int atLeastNumberOfIngredientsOnASlice = Integer.parseInt(headerInfo[headerIndex++].trim());
			int maxAllowedTotalNumberOfIngredientsOnASlice = Integer.parseInt(headerInfo[headerIndex++].trim());
			checkInputHeaders(numberOfRows);
			checkInputHeaders(numberOfColumns);
			checkInputHeaders(atLeastNumberOfIngredientsOnASlice);
			checkInputHeaders(maxAllowedTotalNumberOfIngredientsOnASlice);
			PizzaCell[][] pizzaCells = new PizzaCell[numberOfColumns][numberOfRows];
			for(int rowIndexInFile=1; rowIndexInFile<lines.length; rowIndexInFile++) {
				String pizzaRow = lines[rowIndexInFile].trim();
				for(int columnIndex=0; columnIndex<pizzaRow.length(); columnIndex++) {
					PizzaContent pizzaCell = PizzaContent.getPizzaContent(String.valueOf(pizzaRow.charAt(columnIndex)));
					pizzaCells[columnIndex][rowIndexInFile-1] = new PizzaCell(pizzaCell);
				}
			}
			return new Pizza(numberOfRows, numberOfColumns, atLeastNumberOfIngredientsOnASlice, maxAllowedTotalNumberOfIngredientsOnASlice, pizzaCells);
		}
		catch(ArrayIndexOutOfBoundsException | NullPointerException e) {
			throw new RuntimeException("Probably the input content's format is invalid.", e);
		}
	}
	
	private static void checkInputHeaders(int inputHeader) {
		if(inputHeader < MIN_HEADER_INPUT_VALUE || MAX_HEADER_INPUT_VALUE < inputHeader) {
			throw new RuntimeException("Input is not between the allowed values: " + inputHeader);
		}
	}
}
