package asgn2GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.PizzaRestaurant;

import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;

/**
 * This class is the graphical user interface for the rest of the system. 
 * Currently it is a �dummy� class which extends JFrame and implements Runnable and ActionLister. 
 * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
 * interact with the rest of the system. You may choose to implement this class as you like, including changing 
 * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
 * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
 * 
 * 
 * @author Person A and Person B
 *
 */
public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
	
	
	private PizzaRestaurant restaurant;
	
	private static final long serialVersionUID = -7031008862559936404L;
	
	// Title (do NOT let this be static!)
	private String title;
	
	// Dimensions
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	// Screen
	private Label titleLabel;
	private Container pizzaGUI;
	private JButton button;
	private JComboBox<String> options;
	
	
	// Files
	private final int numFiles = 3;
	private final String filePrefix = "2017010";
	private final String fileSuffix = ".txt";
	private String fileChosen;
	
	
	// Set up the GUI
	private void createGUI () { 
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		//Panel related code will go here
	} 

	// Creates event listener that updates the filename variable
	private ActionListener createEventListener () {
		ActionListener temp = new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println(options.getSelectedItem());
			}
		};
		return temp;
	}
	
	// Creates a JButton and attaches an event listener to it
	private JButton createButton (String label) {
		JButton button = new JButton(label);
		ActionListener buttonPress = createEventListener();
		button.addActionListener(buttonPress);
		return button;
	}
	
	// Puts all elements created in the constructor on the frame
	private void addEverythingToScreen () {
		pizzaGUI = this.getContentPane();
		pizzaGUI.add(titleLabel, BorderLayout.NORTH);
		pizzaGUI.add(options, BorderLayout.SOUTH);
		pizzaGUI.add(button, BorderLayout.SOUTH);
	}
	
	// Creates a combo box of all possible files
	private JComboBox<String> createComboBox () {
		JComboBox<String> comboBox = new JComboBox<String>();
		
		for (int i = 0; i < numFiles; i++) {
			comboBox.addItem(filePrefix + (i + 1) + fileSuffix);
		}
		return comboBox;
	}
	
	
	// I just took this from the tutorial...
	@Override
	public void run() {
		// TO DO
		JFrame.setDefaultLookAndFeelDecorated(true); 
	}
	
	
	
	/**
	 * Creates a new Pizza GUI with the specified title 
	 * @param title - The title for the supertype JFrame
	 */
	public PizzaGUI(String title) {
		// Update private fields
		this.title = title;
		
		createGUI();
		titleLabel = new Label(this.title);
		button = createButton("Submit");
		options = createComboBox();
		addEverythingToScreen();
		
		// Set GUI to visible
		this.setVisible(true);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
