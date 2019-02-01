/**
 * 
 */
package com.zobjektum.bom;

import java.awt.Color;
import java.awt.color.ColorSpace;

import javax.swing.text.StyleConstants.ColorConstants;

/**
 * The values which can be on a pizza cell.
 * 
 * @author Szilvássy
 */
public enum PizzaContent {
	/*
	 * @see {@link http://hslpicker.com/}
	 */
	MUSHROOM("M", new Color(107,255,240,100)),
	TOMATO("T", Color.RED);
	
	private final String INPUT_SYMBOL;
	
	private final Color COLOR;
	
	private PizzaContent(String inputSymbol, Color color) {
		INPUT_SYMBOL = inputSymbol;
		COLOR = color;
	}
	
	/**
	 * Gets the input symbol of the given enum.
	 * 
	 * @return
	 */
	public String getInputSymbol() {
		return INPUT_SYMBOL;
	}
	
	/**
	 * Gets the enum for the given input symbol.
	 * 
	 * @param inputSymbol
	 * @return
	 */
	public static PizzaContent getPizzaContent(String inputSymbol) {
		for(PizzaContent pizzaContent : PizzaContent.values()) {
			if(pizzaContent.getInputSymbol().equals(inputSymbol)) {
				return pizzaContent;
			}
		}
		return null;
	}
	
	public boolean isMushroom() {
		return this == MUSHROOM;
	}
	
	public boolean isTomato() {
		return this == TOMATO;
	}
	
	public Color getColor() {
		return COLOR;
	}
}
