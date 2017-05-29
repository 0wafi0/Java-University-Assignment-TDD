package asgn2GUIs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
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
	
	private JPanel buttonPanel;
	
	// Table
	private String[] columnNames = {"Customer Name", 
									"Mobile Number", 
									"Customer Type", 
									"X Location", 
									"Y Location", 
									"Delivery Distance", 
									"Pizza Type", 
									"Order Price", 
									"Order Cost", 
									"Order Profit"};
	
	
	// Files
	private final int numFiles = 3;
	private final String filePrefix = "logs/2017010";
	private final String fileSuffix = ".txt";
	private String fileChosen;
	
	// Storage for Customers and Pizzas
	ArrayList<Customer> customers;
	ArrayList<Pizza> pizzas;
	
	/**
	 * Set the GUI up
	 */
	private void createGUI() { 
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(3, 1));
		//Panel related code will go here
	}
	
	private String convertCustomerType (String code) {
		String type = "";
		if (code.equals("DVC")) {
			type = "Delivery Driver";
		}
		if (code.equals("PUC")) {
			type = "Pick Up Customer";
		}
		if (code.equals("DNC")) {
			type = "Delivery Drone";
		}
		return type;
	}
	
	
	private JScrollPane createTable () {
		String[][] orders = new String[customers.size()][10];
		
		
		for (int i = 0; i < customers.size(); i++) {
			orders[i][0] = customers.get(i).getName();
			orders[i][1] = customers.get(i).getMobileNumber();
			orders[i][2] = convertCustomerType(customers.get(i).getCustomerType());
			orders[i][3] = Integer.toString(customers.get(i).getLocationX());
			orders[i][4] = Integer.toString(customers.get(i).getLocationY());
			orders[i][5] = pizzas.get(i).getPizzaType();
			orders[i][6] = Integer.toString(pizzas.get(i).getQuantity());
			orders[i][7] = Double.toString(pizzas.get(i).getOrderPrice());
			orders[i][8] = Double.toString(pizzas.get(i).getOrderCost());
			orders[i][9] = Double.toString(pizzas.get(i).getOrderProfit());
		}
		
		JTable table = new JTable(orders, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		return scrollPane;
	}
	
	
	private void createSecondScreen () {
		JFrame secondScreen = new JFrame("Ello guvna");
		secondScreen.add(createTable());
		secondScreen.setSize(WIDTH, HEIGHT);
		secondScreen.setVisible(true);
	}

	/**
	 * standard action listener for any buttons created
	 */
	private ActionListener createEventListener() {
		ActionListener temp = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				// if submit button is pressed
				if(event.getActionCommand().equals("Submit")) {
					fileChosen = options.getSelectedItem().toString();
					try {
						LoadFile();
						createSecondScreen();
						
					} catch (CustomerException e) {
						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "CustomerException thrown", JOptionPane.ERROR_MESSAGE);
					} catch (LogHandlerException e) {
						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "LogHandlerException thrown", JOptionPane.ERROR_MESSAGE);
					} catch (PizzaException e) {
						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "PizzaException", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		};
		return temp;
	}
	
	/**
	 * Load the file
	 * @throws LogHandlerException 
	 * @throws CustomerException 
	 * @throws PizzaException 
	 */
	private void LoadFile() throws CustomerException, LogHandlerException, PizzaException {
		customers = LogHandler.populateCustomerDataset(fileChosen);
		pizzas = LogHandler.populatePizzaDataset(fileChosen);
		
	}
	
	/**
	 * create a button
	 */
	private JButton createButton(String label) {
		JButton button = new JButton(label);
		ActionListener buttonPress = createEventListener();
		button.addActionListener(buttonPress);
		return button;
	}
	
	/**
	 * Adds all the elements to the window
	 */
	private void addEverythingToScreen () {
		pizzaGUI = this.getContentPane();
		pizzaGUI.add(titleLabel, BorderLayout.NORTH);
		pizzaGUI.add(options, BorderLayout.SOUTH);
		pizzaGUI.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the required combo box
	 */
	private JComboBox<String> createComboBox() {
		JComboBox<String> comboBox = new JComboBox<String>();
		
		for (int i = 0; i < numFiles; i++) {
			comboBox.addItem(filePrefix + (i + 1) + fileSuffix);
		}
		return comboBox;
	}
	
	
	/**
	 * Obscure function copied from the tute
	 */
	@Override
	public void run() {
		// TO DO
		JFrame.setDefaultLookAndFeelDecorated(true); 
	}
	
	private JPanel createButtonPanel () {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 3));
		
		button = createButton("Submit");
		panel.add(button);
		
		button = createButton("Calculate Price");
		panel.add(button);
		
		button = createButton("Reset Information");
		panel.add(button);
		
		return panel;
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
		buttonPanel = createButtonPanel();
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


//package asgn2GUIs;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//
//import javax.swing.JPanel;
//import javax.swing.text.DefaultCaret;
//
//import asgn2Customers.Customer;
//import asgn2Exceptions.CustomerException;
//import asgn2Exceptions.LogHandlerException;
//import asgn2Exceptions.PizzaException;
//import asgn2Pizzas.Pizza;
//import asgn2Restaurant.LogHandler;
//import asgn2Restaurant.PizzaRestaurant;
//
//import javax.swing.JFrame;
//
//import java.awt.*;
//
//import javax.swing.*;
//
///**
// * This class is the graphical user interface for the rest of the system. 
// * Currently it is a �dummy� class which extends JFrame and implements Runnable and ActionLister. 
// * It should contain an instance of an asgn2Restaurant.PizzaRestaurant object which you can use to 
// * interact with the rest of the system. You may choose to implement this class as you like, including changing 
// * its class signature � as long as it  maintains its core responsibility of acting as a GUI for the rest of the system. 
// * You can also use this class and asgn2Wizards.PizzaWizard to test your system as a whole
// * 
// * 
// * @author Person A and Person B
// *
// */
//public class PizzaGUI extends javax.swing.JFrame implements Runnable, ActionListener {
//	
//	
//	private PizzaRestaurant restaurant;
//	
//	private static final long serialVersionUID = -7031008862559936404L;
//	
//	// Title (do NOT let this be static!)
//	private String title;
//	
//	// Dimensions
//	public static final int WIDTH = 600;
//	public static final int HEIGHT = 600;
//	
//	// Screen
//	private Label titleLabel;
//	private Container pizzaGUI;
//	private JButton button;
//	private JComboBox<String> options;
//	
//	
//	// Files
//	private final int numFiles = 3;
//	private final String filePrefix = "logs/2017010";
//	private final String fileSuffix = ".txt";
//	private String fileChosen;
//	
//	// Storage for Customers and Pizzas
//	ArrayList<Customer> customers;
//	ArrayList<Pizza> pizzas;
//	
//	/**
//	 * Set the GUI up
//	 */
//	private void createGUI() { 
//		setSize(WIDTH, HEIGHT);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new GridLayout(3, 1));
//		//Panel related code will go here
//	} 
//
//	/**
//	 * standard action listener for any buttons created
//	 */
//	private ActionListener createEventListener() {
//		ActionListener temp = new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				// if submit button is pressed
//				if(event.getActionCommand().equals("Submit")) {
//					fileChosen = options.getSelectedItem().toString();
//					try {
//						LoadFile();
//						
//					} catch (CustomerException e) {
//						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "CustomerException thrown", JOptionPane.ERROR_MESSAGE);
//					} catch (LogHandlerException e) {
//						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "LogHandlerException thrown", JOptionPane.ERROR_MESSAGE);
//					} catch (PizzaException e) {
//						JOptionPane.showMessageDialog(pizzaGUI, e.toString(), "PizzaException", JOptionPane.ERROR_MESSAGE);
//					}
//				}
//			}
//		};
//		return temp;
//	}
//	
//	/**
//	 * Load the file
//	 * @throws LogHandlerException 
//	 * @throws CustomerException 
//	 * @throws PizzaException 
//	 */
//	private void LoadFile() throws CustomerException, LogHandlerException, PizzaException {
//		customers = LogHandler.populateCustomerDataset(fileChosen);
//		pizzas = LogHandler.populatePizzaDataset(fileChosen);
//		
//	}
//	
//	/**
//	 * create a button
//	 */
//	private JButton createButton(String label) {
//		JButton button = new JButton(label);
//		ActionListener buttonPress = createEventListener();
//		button.addActionListener(buttonPress);
//		return button;
//	}
//	
//	/**
//	 * Adds all the elements to the window
//	 */
//	private void addEverythingToScreen () {
//		pizzaGUI = this.getContentPane();
//		pizzaGUI.add(titleLabel, BorderLayout.NORTH);
//		pizzaGUI.add(options, BorderLayout.SOUTH);
//		pizzaGUI.add(button, BorderLayout.SOUTH);
//	}
//	
//	/**
//	 * Creates the required combo box
//	 */
//	private JComboBox<String> createComboBox() {
//		JComboBox<String> comboBox = new JComboBox<String>();
//		
//		for (int i = 0; i < numFiles; i++) {
//			comboBox.addItem(filePrefix + (i + 1) + fileSuffix);
//		}
//		return comboBox;
//	}
//	
//	
//	/**
//	 * Obscure function copied from the tute
//	 */
//	@Override
//	public void run() {
//		// TO DO
//		JFrame.setDefaultLookAndFeelDecorated(true); 
//	}
//	
//	
//	
//	/**
//	 * Creates a new Pizza GUI with the specified title 
//	 * @param title - The title for the supertype JFrame
//	 */
//	public PizzaGUI(String title) {
//		// Update private fields
//		this.title = title;
//		
//		createGUI();
//		titleLabel = new Label(this.title);
//		button = createButton("Submit");
//		options = createComboBox();
//		addEverythingToScreen();
//		
//		// Set GUI to visible
//		this.setVisible(true);		
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
