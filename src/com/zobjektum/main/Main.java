/**
 * 
 */
package com.zobjektum.main;

import java.io.File;

import com.zobjektum.algorithm.Algorithm;
import com.zobjektum.bom.Pizza;
import com.zobjektum.bom.PizzaSolution;
import com.zobjektum.gui.PizzaViewerPanel;
import com.zobjektum.io.FileIO;
import com.zobjektum.parser.PizzaParser;

/**
 * Main entry point of the java app.
 * 
 * @author Szilvássy
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Main started");
		PizzaViewerPanel pizzaViewerPanel = new PizzaViewerPanel();
		File selectedFile = pizzaViewerPanel.load();
		if(selectedFile == null) {
			pizzaViewerPanel.displayError("No file was selected. Program quits.");
			return;
		}
		String fileContent = FileIO.getFileContent(selectedFile);
		System.out.println("Filecontent: " + fileContent);
                saveContentForExel(fileContent, selectedFile);
		Pizza pizza = PizzaParser.parsePizza(fileContent);
		System.out.println(pizza);
		PizzaSolution pizzaSolution = Algorithm.doLogic(pizza);
		System.out.println(pizzaSolution);
		System.out.println(pizza);
		pizzaViewerPanel.drawPizza(pizza);
		File solutionFile = pizzaViewerPanel.saveSolution(pizza, pizzaSolution);
		if(solutionFile == null) {
			pizzaViewerPanel.displayError("No file was selected. Program quits.");
			return;
		}
		FileIO.writeSolution(solutionFile, pizzaSolution);
		pizzaViewerPanel.displayInfo(solutionFile.getName() + " has been saved.");
		System.out.println("Main ended");
	}

        /**
         * Helper method to save the ingredients of the pizza as a comma separated value to be represented in Excel.
         * 
         * @param inputOriginalContent
         * @param inputOriginalFile 
         */
        private static void saveContentForExel(String inputOriginalContent, File inputOriginalFile) {
            final String FILE_POSTFIX = "_forExcel";
            final String EXTENSION_FOR_EXCEL = ".csv";
            final String CSV_SEPARATOR = ";";
            String inputOriginalFileName = inputOriginalFile.getName().substring(0, inputOriginalFile.getName().lastIndexOf(FileIO.INPUT_EXTENSION));
            String[] lines = inputOriginalContent.split(FileIO.LINE_SEPARATOR);
            //[0] is header line
            StringBuilder sbExcel = new StringBuilder();
            //copying content for Excel display
            for(int rowIndex=1; rowIndex<lines.length; rowIndex++) {
                String line = lines[rowIndex];
                if(rowIndex == 1) {
                    //adding the column indexes as first row
                    sbExcel.append("").append(CSV_SEPARATOR); //this is a placeholder for the row indexes, the first column below
                    for(int columnIndex=1; columnIndex<=lines[1].length(); columnIndex++) {
                        sbExcel.append(Integer.toString(columnIndex)).append(CSV_SEPARATOR);
                    }
                    sbExcel.append(FileIO.LINE_SEPARATOR);
                }
                sbExcel.append(Integer.toString(rowIndex)).append(CSV_SEPARATOR); //row index as first column
                for(int charIndex=0; charIndex<line.length(); charIndex++) {
                    char ingredient = line.charAt(charIndex);
                    sbExcel.append(ingredient).append(CSV_SEPARATOR);
                }
                sbExcel.append(FileIO.LINE_SEPARATOR);
            }
            File inputFileAsExcel = new File(inputOriginalFile.getParent() + FileIO.FILEPATH_SEPARATOR + inputOriginalFileName + FILE_POSTFIX + EXTENSION_FOR_EXCEL);
            System.out.println("Input content saved for Excel: " + inputFileAsExcel.getAbsolutePath());
            FileIO.writeSolution(inputFileAsExcel, sbExcel.toString());
        }
}
