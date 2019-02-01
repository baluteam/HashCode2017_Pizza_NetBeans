/**
 * 
 */
package com.zobjektum.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

import com.zobjektum.bom.Pizza;
import com.zobjektum.bom.PizzaCell;
import com.zobjektum.bom.PizzaSolution;
import com.zobjektum.io.FileIO;

/**
 * This class is used to visualize the pizza matrix
 * 
 * @author Szilvássy
 *
 */
public class PizzaViewerPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private static final String PROJECT_ROOT_NAME = "HashCode2017_Pizza_NetBeans";
	/**
	 * Folder name where we have the input data
	 */
	private static final String RESOURCES_FOLDER = "resources";
	/**
	 * Folder name where we store our solution as an output data
	 */
	private static final String OUTPUT_FOLDER = "output";
	private static final String OUTPUT_FILENAME = "solution";
	private static final String OUTPUT_EXTENSION = ".out";
	
	private static final int WIDTH = 800;
	private static final int HEIGHT = 600;
	private JPanel drawingPane;
	
	public PizzaViewerPanel() { }
	
	/**
	 * private constructor that can take a JPanel as an argument and creates a scrollable panel from it
	 * 
	 * @param drawPanel : the JPanel which we will surround with scroll bars
	 */
	private PizzaViewerPanel(JPanel drawPanel) {
		super(new BorderLayout());
        drawingPane = drawPanel;
        //background color of the pizza
        drawingPane.setBackground(Color.WHITE);
        drawingPane.setPreferredSize(drawPanel.getSize());
        JScrollPane scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        add(scroller, BorderLayout.CENTER);
	}
	
	/**
	 * Helper method to draw the pizza.
	 * 
	 * @param pizza
	 */
	public void drawPizza(Pizza pizza) {
		final String GUI_TITEL = "Google Hash Code 2017 - Pizza";
		final int PIZZA_CELL_SIZE = calculateProperCellSize(pizza);
		JFrame gui = new JFrame(GUI_TITEL);
		JPanel drawPanel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
			protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
				int startX = 0;
				int startY = 0;
				for(int rowIndex=0; rowIndex<pizza.getRows(); rowIndex++) {
					for(int columnIndex=0; columnIndex<pizza.getColumns(); columnIndex++) {
						PizzaCell pizzaCell = pizza.getPizzaCell(rowIndex, columnIndex);
						g.setColor(pizzaCell.getPizzaContent().getColor());
						g.fillRect(startX, startY, PIZZA_CELL_SIZE, PIZZA_CELL_SIZE);
						if(pizzaCell.isSlicedAlready()) {
							//draw X over cell if it is sliced already
							g.setColor(Color.BLUE);
							g.drawLine(startX, startY, startX+PIZZA_CELL_SIZE, startY+PIZZA_CELL_SIZE);
							g.drawLine(startX+PIZZA_CELL_SIZE, startY, startX, startY+PIZZA_CELL_SIZE);
						}
						//draw string in the cells
						g.setColor(Color.BLACK);
						g.drawString(pizzaCell.getPizzaContent().getInputSymbol(), startX, startY+g.getFontMetrics().getHeight());
						startX += PIZZA_CELL_SIZE;
					}
					startX = 0;
					startY += PIZZA_CELL_SIZE;
				}
			}
		};
		drawPanel.setSize(pizza.getColumns() * PIZZA_CELL_SIZE, pizza.getRows() * PIZZA_CELL_SIZE);
		JComponent newContentPane = new PizzaViewerPanel(drawPanel);
		newContentPane.setOpaque(true);
		gui.setSize(WIDTH,HEIGHT);
		gui.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2-WIDTH/2, Toolkit.getDefaultToolkit().getScreenSize().height/2-HEIGHT/2);
		gui.setContentPane(newContentPane);
		gui.pack();
		gui.setVisible(true);
		gui.setResizable(true);
		gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * Helper method to calculate a proper cell size for the pizza contents.
	 * 
	 * @param pizza
	 * @return
	 */
	private int calculateProperCellSize(Pizza pizza) {
		final int PIZZA_CELL_SIZE_ORIGIN = 10;
		if(pizza.getRows() * PIZZA_CELL_SIZE_ORIGIN <= HEIGHT && pizza.getColumns() * PIZZA_CELL_SIZE_ORIGIN <= WIDTH) {
			int cellSizeX = WIDTH / pizza.getColumns();
			int cellSizeY = HEIGHT / pizza.getRows();
			return (cellSizeX < cellSizeY) ? cellSizeX : cellSizeY;
		}
		return PIZZA_CELL_SIZE_ORIGIN;
	}
	
	/**
	 * Opens a dialog and returns the selected file.
	 * 
	 * @return
	 */
	public File load() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.addChoosableFileFilter(new FileFilter() {
			@Override
			public String getDescription() {
				return "Accepting only files with extension: " + FileIO.INPUT_EXTENSION;
			}
			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) {
					return true;
				}
				if(f.canRead() && f.getName().endsWith(FileIO.INPUT_EXTENSION)) {
					return true;
				}
				return false;
			}
		});
		try {
			URL inputDirectoryURL = PizzaViewerPanel.class.getResource(".");
			System.out.println("Current directory where program is runnign from: " + inputDirectoryURL);
			File inputDirectory = new File(inputDirectoryURL.toURI());
			String currentDirectoryName = inputDirectory.getName();
			while(!PROJECT_ROOT_NAME.equals(currentDirectoryName)) {
				inputDirectory = inputDirectory.getParentFile();
				currentDirectoryName = inputDirectory.getName();
			}
			File resourcesDirectory = new File(inputDirectory.getAbsolutePath() + File.separator + RESOURCES_FOLDER);
			System.out.println("Resources directory: " + resourcesDirectory);
			if(resourcesDirectory.isDirectory()) {
				fileChooser.setCurrentDirectory(resourcesDirectory);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		int returnVal = fileChooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
		}
		return null;
	}

	/**
	 * Helper method to show a save dialog with a proposed directory and file name and execute saving the file.
	 * 
	 * @param pizza
	 * @param pizzaSolution
	 * @return
	 */
	public File saveSolution(Pizza pizza, PizzaSolution pizzaSolution) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		try {
			URL inputDirectoryURL = PizzaViewerPanel.class.getResource(".");
			System.out.println("Current directory where program is runnign from: " + inputDirectoryURL);
			File inputDirectory = new File(inputDirectoryURL.toURI());
			String currentDirectoryName = inputDirectory.getName();
			while(!PROJECT_ROOT_NAME.equals(currentDirectoryName)) {
				inputDirectory = inputDirectory.getParentFile();
				currentDirectoryName = inputDirectory.getName();
			}
			File outputDirectory = new File(inputDirectory.getAbsolutePath() + File.separator + OUTPUT_FOLDER);
			System.out.println("Output directory: " + outputDirectory);
			if(outputDirectory.isDirectory()) {
				fileChooser.setCurrentDirectory(outputDirectory);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
		fileChooser.setSelectedFile(new File(OUTPUT_FILENAME + "_" + pizza.getRows() + "x" + pizza.getColumns() + "_" + dateFormat.format(new Date()) + OUTPUT_EXTENSION));
		int returnVal = fileChooser.showSaveDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
	
	/**
	 * Helper method to display the error in a dialog.
	 * 
	 * @param error
	 */
	public void displayError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Helper method to display the warning in a dialog.
	 * 
	 * @param error
	 */
	public void displayWarning(String warning) {
		JOptionPane.showMessageDialog(this, warning, "Warning", JOptionPane.WARNING_MESSAGE);
	}
	
	/**
	 * Helper method to display the info in a dialog.
	 * 
	 * @param error
	 */
	public void displayInfo(String info) {
		JOptionPane.showMessageDialog(this, info, "Info", JOptionPane.INFORMATION_MESSAGE);
	}
}
