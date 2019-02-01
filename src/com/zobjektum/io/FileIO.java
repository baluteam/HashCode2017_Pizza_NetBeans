package com.zobjektum.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.zobjektum.bom.PizzaSlice;
import com.zobjektum.bom.PizzaSolution;

/**
 * Helper class to read/write the input and output file.
 * 
 * @author Szilvássy
 *
 */
public class FileIO {
	public static final String INPUT_EXTENSION = ".in";
	public static final String LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String FILEPATH_SEPARATOR = System.getProperty("file.separator"); //the \ or / that separates directories in a file name for instance
	public static final String DATA_SEPARATOR = " ";
	
	/**
	 * Helper method to read up .in input files.
	 * 
	 * @param pathToFile
	 * @return
	 */
	public static File getFile(String pathToFile) {
		File file = new File(pathToFile);
		if(!file.isFile()) {
			throw new RuntimeException(pathToFile + " is not a file.");
		}
		if(!file.getName().endsWith(INPUT_EXTENSION)) {
			throw new RuntimeException(pathToFile + " does not have the expected extension: " + INPUT_EXTENSION);
		}
		return file;
	}
	
	/**
	 * Helper method to get back the string content of a file.
	 * 
	 * @param file
	 * @return
	 */
	public static String getFileContent(File file) {
		if(file == null || !file.isFile() || !file.canRead()) {
			return null;
		}
		BufferedReader reader = null;
		StringBuffer sb = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;
			while((line = reader.readLine()) != null) {
				sb.append(line).append(LINE_SEPARATOR);
			}
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found: " + file);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException while reading file: " + file);
		}
		finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
        
        /**
         * Gets the pizza solution format that needs to be written out into an output file.
         * 
         * @param pizzaSolution
         * @return 
         */
        private static String getPizzaSolution(PizzaSolution pizzaSolution) {
            StringBuilder sb = new StringBuilder();
            sb.append(pizzaSolution.getNumberOfSlices()).append(LINE_SEPARATOR);
            for(PizzaSlice pizzaSlice : pizzaSolution.getPizzaSlices()) {
                    sb.append(pizzaSlice.r1()).append(DATA_SEPARATOR);
                    sb.append(pizzaSlice.c1()).append(DATA_SEPARATOR);
                    sb.append(pizzaSlice.r2()).append(DATA_SEPARATOR);
                    sb.append(pizzaSlice.c2()).append(LINE_SEPARATOR);
            }
            return sb.toString();
        }
	
	/**
	 * Helper method to write out our solution in the expected format into the given file.
	 * 
	 * @param soultionFile
	 * @param pizzaSolution
	 */
	public static void writeSolution(File soultionFile, PizzaSolution pizzaSolution) {
            writeSolution(soultionFile, getPizzaSolution(pizzaSolution));
	}
        
        /**
	 * Helper method to write out a string into the given file.
	 * 
	 * @param soultionFile
	 * @param fileContent
	 */
	public static void writeSolution(File soultionFile, String fileContent) {
		if(soultionFile == null) {
			return;
		}
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(soultionFile));
			writer.write(fileContent);
			writer.flush();
		} 
		catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException while writing file: " + soultionFile);
		}
		finally {
			if(writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
