package asgn2Restaurant;


import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;



// My imports:
import java.io.*;
import java.util.StringTokenizer;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {
	
	private static String logFile1 = "20170101.txt";
	private static String logFile2 = "20170102.txt";
	private static String logFile3 = "20170103.txt";
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 */	
	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		// TO DO
		FileInputStream inputStream;
		BufferedReader bufferReader;
		InputStreamReader streamReader;
		String line;
		ArrayList<Customer> customerDataset = new ArrayList<Customer>();
		
		//Not sure if this bit of exception is to be thrown.
		if (!filename.equals(logFile1) && !filename.equals(logFile2) && !filename.equals(logFile3)) {
			throw new LogHandlerException();
		}
		try {
			inputStream = new FileInputStream(filename);
			streamReader = new InputStreamReader(inputStream);
			bufferReader = new BufferedReader(streamReader);
			
			do {
				line = bufferReader.readLine();
				Customer customer = createCustomer(line);
				customerDataset.add(customer);
			} while (line != null);
			
			inputStream.close();
			bufferReader.close();
			streamReader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("This happens when 'filename' is invalid");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("This happens when 'bufferReader' throws an exception");
		}
		
		return customerDataset;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		ArrayList<Pizza> output = new ArrayList<Pizza>();
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            while ((line = br.readLine()) != null) {
            	Pizza temp = createPizza(line);
            	output.add(temp);
            }
        } catch (IOException e) {
            throw new LogHandlerException("problem in parsing the log file in populatePizzaDataset");
        }
        return output;
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		// TO DO
		String[] customerInfo = line.split(",");
		
		if(customerInfo == null) {
        	throw new LogHandlerException("Error detected in the log");
        }
        if(customerInfo.length != 9) {
        	throw new LogHandlerException("Error detected in the log");
        }
        for(int i = 0; i < 2; i++) {
        	if(customerInfo[i] == "") {
        		throw new LogHandlerException("Error detected in the log");
        	}
        }
		
		String name = customerInfo[2];
		String mobileNumber = customerInfo[3];
		String type = customerInfo[4];
		int locationX;
		int locationY;
		try {
			locationX = Integer.parseInt(customerInfo[5]);
			locationY = Integer.parseInt(customerInfo[6]);
		}
		catch (NumberFormatException exception) {
			throw new LogHandlerException("can't parse integer values for coordinates");
		} 
		
		Customer customer = CustomerFactory.getCustomer(type, name, mobileNumber, locationX, locationY);
		
		return customer;
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		Pizza temp;
		String[] data = line.split(",");
        if(data == null) {
        	throw new LogHandlerException("Error detected in the log");
        }
        if(data.length != 9) {
        	throw new LogHandlerException("Error detected in the log");
        }
        for(int i = 0; i < 2; i++) {
        	if(data[i] == "") {
        		throw new LogHandlerException("Error detected in the log");
        	}
        }
        for(int i = 7; i < 9; i++) {
        	if(data[i] == "") {
        		throw new LogHandlerException("Error detected in the log");
        	}
        }
        try {
        	temp = PizzaFactory.getPizza(data[7], Integer.parseInt(data[8]), LocalTime.parse(data[0]), LocalTime.parse(data[1]));
        } catch(NumberFormatException a) {
        	throw new LogHandlerException("Problem parsing the quantity of pizzas from logfile");
        } catch(DateTimeParseException a) {
        	throw new LogHandlerException("Problem parsing the Time from logfile");        
        }
        return temp;
	}
	
}
