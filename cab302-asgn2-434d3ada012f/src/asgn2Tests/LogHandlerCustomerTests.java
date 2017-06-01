package asgn2Tests;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Pizzas.PizzaFactory;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author WAfi
 */
public class LogHandlerCustomerTests {	
	/**
	 * Following will be testing exceptions for LongHandler.createCustomer
	 */
	
	/**
	 * Empty string
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException1() throws CustomerException, LogHandlerException  {
		LogHandler.createCustomer("");
	}
	 
	/**
	 * Try one of the lines from the Logfiles without a couple of entries
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file  
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException2() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,");
	}
	
	/**
	 * Try one of the lines from the Logfiles replace the y coordinate with a letter
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException3() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,a,PZV,2");
	}
	
	/**
	 * Try one of the lines from the Logfiles replace one of the y coordinate with a number and letters
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file 
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException4() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,8asdw,PZV,2");
	}
	
	/**
	 * Try one of the lines from the Logfiles replace one of the x coordinate with a number and letters
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException5() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,a,8,PZV,2");
	}
	
	/**
	 * Try one of the lines from the Logfiles replace one of the x coordinate with a number and letters
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException6() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,sad6asfef,8,PZV,2");
	}
	
	/**
	 * Try one of the lines from the Logfiles replace one of the x coordinate with a number and letters
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException7() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,f,1,PZV,2");
	}	
	
	/**
	 * Now we are to test the function with general test cases
	 */	
	
	/**
	 * Test CreateCustomer with a DVC test case
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test
	public void TestCreateCustomerGeneral1() throws LogHandlerException, CustomerException {
		Customer temp1 = LogHandler.createCustomer("19:00:00,19:20:00,sadfsfswerwqrqrqwrsg,0134563231,DVC,10,10,DVC,2");
		Customer temp2 =  CustomerFactory.getCustomer("DVC", "sadfsfswerwqrqrqwrsg", "0134563231", 10, 10);
		assertTrue(temp1.equals(temp2));
	}
	
	/**
	 * Test CreateCustomer with a DVC test case
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test
	public void TestCreateCustomerGeneral2() throws LogHandlerException, CustomerException {
		Customer temp1 = LogHandler.createCustomer("19:00:00,19:20:00,Mosquito,0134563231,DNC,8,10,DVC,2");
		Customer temp2 =  CustomerFactory.getCustomer("DNC", "Mosquito", "0134563231", 8, 10);
		assertTrue(temp1.equals(temp2));
	}	
	
	/**
	 * Following will be testing exceptions for LongHandler.populateCustomerDataset()
	 */
	
	/**
	 * No File
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test(expected = LogHandlerException.class)
	public void populateCustomerDatasetException1() throws LogHandlerException, CustomerException {
		LogHandler.populateCustomerDataset("asdfawetqwer");
	}
	
	/**
	 * Test with the 20170101.txt
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test
	public void populateCustomerDatasetGeneralTestCase1() throws LogHandlerException, CustomerException {				
		ArrayList<Customer> temp = new ArrayList<Customer>();
		temp = LogHandler.populateCustomerDataset("logs/20170101.txt");
		assertTrue(temp.size() == 3);
		assertTrue(temp.get(0).getCustomerType().equals("Delivery Driver"));
		assertTrue(temp.get(1).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(2).getCustomerType().equals("Pick Up Customer"));
		assertTrue(temp.get(0).getName().equals("Casey Jones"));
		assertTrue(temp.get(1).getName().equals("April O'Neal"));
		assertTrue(temp.get(2).getName().equals("Oroku Saki"));
		assertEquals(temp.get(0).getLocationX(), 5);
		assertEquals(temp.get(0).getLocationY(), 5);
		assertEquals(temp.get(1).getLocationX(), 3);
		assertEquals(temp.get(1).getLocationY(), 4);
	}
	
	/**
	 * Test with the 20170102.txt
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test
	public void populateCustomerDatasetGeneralTestCase2() throws LogHandlerException, CustomerException {				
		ArrayList<Customer> temp = new ArrayList<Customer>();
		temp = LogHandler.populateCustomerDataset("logs/20170102.txt");
		assertTrue(temp.size() == 10);
		assertTrue(temp.get(0).getCustomerType().equals("Delivery Driver"));
		assertTrue(temp.get(1).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(2).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(3).getCustomerType().equals("Pick Up Customer"));
		assertTrue(temp.get(4).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(5).getCustomerType().equals("Pick Up Customer"));
		assertTrue(temp.get(6).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(7).getCustomerType().equals("Delivery Drone"));
		assertTrue(temp.get(8).getCustomerType().equals("Delivery Driver"));
		assertTrue(temp.get(9).getCustomerType().equals("Pick Up Customer"));
		
		assertTrue(temp.get(0).getName().equals("Emma Brown"));
		assertTrue(temp.get(1).getName().equals("Lucas Anderson"));
		assertTrue(temp.get(2).getName().equals("Sophia Singh"));
		assertTrue(temp.get(3).getName().equals("Bella Chen"));
		assertTrue(temp.get(4).getName().equals("Sophia Brown"));
		assertTrue(temp.get(5).getName().equals("Eli Wang"));
		assertTrue(temp.get(6).getName().equals("Riley Brown"));
		assertTrue(temp.get(7).getName().equals("Emma Chen"));
		assertTrue(temp.get(8).getName().equals("Jackson Taylor"));
		assertTrue(temp.get(9).getName().equals("Caden Kumar"));
		
		assertEquals(temp.get(0).getLocationX(), -1);
		assertEquals(temp.get(0).getLocationY(), 0);
		assertEquals(temp.get(1).getLocationX(), -4);
		assertEquals(temp.get(1).getLocationY(), 5);
		assertEquals(temp.get(2).getLocationX(), 1);
		assertEquals(temp.get(2).getLocationY(), 8);
		assertEquals(temp.get(4).getLocationX(), -2);
		assertEquals(temp.get(4).getLocationY(), 4);
		assertEquals(temp.get(6).getLocationX(), -2);
		assertEquals(temp.get(6).getLocationY(), 0);
		assertEquals(temp.get(7).getLocationX(), -4);
		assertEquals(temp.get(7).getLocationY(), 2);
		assertEquals(temp.get(8).getLocationX(), -5);
		assertEquals(temp.get(8).getLocationY(), -10);
		
	}	
	
	/**
	 * Test with the 20170103.txt
	 * @throws LogHandlerException Exceptions thrown if there are errors in the logfile
	 * @throws CustomerException Thrown if there is an underlying exception other than errors in parsing
	 * the log file
	 */
	@Test
	public void populateCustomerDatasetGeneralTestCase3() throws LogHandlerException, CustomerException { 
		ArrayList<Customer> temp = new ArrayList<Customer>();
		temp = LogHandler.populateCustomerDataset("logs/20170103.txt");
		assertTrue(temp.size() == 100);
	}
	
}
