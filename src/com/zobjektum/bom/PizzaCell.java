/**
 * 
 */
package com.zobjektum.bom;

/**
 * Class representing a pizza cell with helper fields.
 * 
 * @author Szilvássy
 */
public class PizzaCell {
	private PizzaContent pizzaContent;
	private boolean slicedAlready;
	
	public PizzaCell(PizzaContent pizzaContent) {
		this.pizzaContent = pizzaContent;
		this.slicedAlready = false;
	}
	
	public PizzaCell(PizzaContent pizzaContent, boolean slicedAlready) {
		super();
		this.pizzaContent = pizzaContent;
		this.slicedAlready = slicedAlready;
	}


	public PizzaContent getPizzaContent() {
		return pizzaContent;
	}
	public void setPizzaContent(PizzaContent pizzaContent) {
		this.pizzaContent = pizzaContent;
	}
	public boolean isSlicedAlready() {
		return slicedAlready;
	}
	public void setSlicedAlready(boolean slicedAlready) {
		this.slicedAlready = slicedAlready;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PizzaCell [");
		builder.append("pizzaContent=").append(pizzaContent.name());
		builder.append(", slicedAlready=").append(slicedAlready);
		builder.append("]");
		return builder.toString();
	}
	
}
