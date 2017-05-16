package asgn2Tests;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author WAfi
 */
public class LogHandlerCustomerTests {
	/**
	 * The first portion of these tests are going to check
	 * the functionality of create customer
	 */
	
	/**
	 * Following will be testing exceptions for LongHandler.createCustomer
	 */
	
	/**
	 * Empty string
	 * @throws CustomerException 
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException1() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("");
	}
	
	/**
	 * Empty string
	 * @throws CustomerException 
	 */
	@Test(expected = LogHandlerException.class)
	public void TestCreateCustomerException2() throws LogHandlerException, CustomerException {
		LogHandler.createCustomer("");
	}
}
