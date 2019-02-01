/**
 * 
 */
package com.zobjektum.algorithm;

import com.zobjektum.bom.Pizza;
import com.zobjektum.bom.PizzaSlice;
import com.zobjektum.bom.PizzaSolution;

/**
 * This is the class representing the algorithm which makes a {@link PizzaSolution}
 * 
 * @author Szilvássy
 */
public class Algorithm {
	public static PizzaSolution doLogic(Pizza pizza) {
		PizzaSolution pizzaSolution = new PizzaSolution();
		doLogic(pizza, pizzaSolution);
		return pizzaSolution;
	}
	
	/**
	 * This is the method stub where the algorithm needs to be implemented.
	 * 
	 * @param pizza
	 * @param pizzaSolution
	 */
	private static void doLogic(Pizza pizza, PizzaSolution pizzaSolution) {
		//TODO implement algorithm here
                //doLogicExample(pizza, pizzaSolution);
                //doLogicSmall(pizza, pizzaSolution);
                //doLogicMedium_3(pizza, pizzaSolution);
		doLogicBig_2(pizza, pizzaSolution);
	}
        
        /**
         * Solution of the example file.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicExample(Pizza pizza, PizzaSolution pizzaSolution) {
            boolean sliceAdded = pizzaSolution.addSlice(new PizzaSlice(0, 0, 2, 1, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(0, 2, 2, 2, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(0, 3, 2, 4, pizza), pizza);
        }
        
        /**
         * Solution of the small file.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicSmall(Pizza pizza, PizzaSolution pizzaSolution) {
            boolean sliceAdded = pizzaSolution.addSlice(new PizzaSlice(0, 0, 0, 4, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(1, 0, 5, 0, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(1, 1, 5, 1, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(1, 2, 5, 2, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(1, 3, 5, 3, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(1, 4, 5, 4, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(0, 5, 1, 6, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(2, 5, 3, 6, pizza), pizza);
            sliceAdded = pizzaSolution.addSlice(new PizzaSlice(4, 5, 5, 6, pizza), pizza);
        }
        
        /**
         * Solution of the medium file. (84.7%)
         * From left to right then from top to the bottom.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicMedium_1(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int r=0; r<numOfRows; r++) {
                int lengthOfSlice = maxCells-1;
                for(int c=0; c<numOfColumns; c++) {
                    if(numOfColumns <= c+maxCells) {
                        lengthOfSlice = numOfColumns-c-1;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c, r, c+lengthOfSlice, pizza), pizza);
                    c += lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the medium file. (84.18%)
         * From right to left then from top to the bottom.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicMedium_2(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int r=0; r<numOfRows; r++) {
                int lengthOfSlice = maxCells-1;
                for(int c=numOfColumns-1; 0<=c; c--) {
                    if(c-maxCells < 0) {
                        lengthOfSlice = c;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c-lengthOfSlice, r, c, pizza), pizza);
                    c -= lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the medium file. (84.72%)
         * From top to the bottom then from the left to right.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicMedium_3(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int c=0; c<numOfColumns; c++) {
                int lengthOfSlice = maxCells-1;
                for(int r=0; r<numOfRows; r++) {
                    if(numOfRows <= r+maxCells) {
                        lengthOfSlice = numOfRows-r-1;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c, r+lengthOfSlice, c, pizza), pizza);
                    r += lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the medium file. (83.48%)
         * From bottom to the top then from the left to right.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicMedium_4(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int c=0; c<numOfColumns; c++) {
                int lengthOfSlice = maxCells-1;
                for(int r=numOfRows-1; 0<=r; r--) {
                    if(r-maxCells < 0) {
                        lengthOfSlice = r;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r-lengthOfSlice, c, r, c, pizza), pizza);
                    r -= lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the big file. (57.37%)
         * From left to right then from top to the bottom.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicBig_1(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int r=0; r<numOfRows; r++) {
                int lengthOfSlice = maxCells-1;
                for(int c=0; c<numOfColumns; c++) {
                    if(numOfColumns <= c+maxCells) {
                        lengthOfSlice = numOfColumns-c-1;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c, r, c+lengthOfSlice, pizza), pizza);
                    c += lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the big file. (57.41%)
         * From right to left then from top to the bottom.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicBig_2(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int r=0; r<numOfRows; r++) {
                int lengthOfSlice = maxCells-1;
                for(int c=numOfColumns-1; 0<=c; c--) {
                    if(c-maxCells < 0) {
                        lengthOfSlice = c;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c-lengthOfSlice, r, c, pizza), pizza);
                    c -= lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the big file. (57.22%)
         * From top to the bottom then from the left to right.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicBig_3(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int c=0; c<numOfColumns; c++) {
                int lengthOfSlice = maxCells-1;
                for(int r=0; r<numOfRows; r++) {
                    if(numOfRows <= r+maxCells) {
                        lengthOfSlice = numOfRows-r-1;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r, c, r+lengthOfSlice, c, pizza), pizza);
                    r += lengthOfSlice;
                }
            }
        }
        
        /**
         * Solution of the big file. (57.26%)
         * From bottom to the top then from the left to right.
         * 
         * @param pizza
         * @param pizzaSolution 
         */
        private static void doLogicBig_4(Pizza pizza, PizzaSolution pizzaSolution) {
            int minIngredients = pizza.getAtLeastNumberOfIngredientsOnASlice();
            int maxCells = pizza.getMaxAllowedTotalNumberOfIngredientsOnASlice();
            int numOfRows = pizza.getRows();
            int numOfColumns = pizza.getColumns();
            boolean sliceAdded;
            for(int c=0; c<numOfColumns; c++) {
                int lengthOfSlice = maxCells-1;
                for(int r=numOfRows-1; 0<=r; r--) {
                    if(r-maxCells < 0) {
                        lengthOfSlice = r;
                    }
                    sliceAdded = pizzaSolution.addSlice(new PizzaSlice(r-lengthOfSlice, c, r, c, pizza), pizza);
                    r -= lengthOfSlice;
                }
            }
        }
}
