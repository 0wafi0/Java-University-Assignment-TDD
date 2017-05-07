package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	/**
	 * Following tests will focus on the asgn2Customers.Customer abstract class.
	 * Firstly we will look over exceptions and use the DriverDeliveryCustomer class
	 */
	
	/**
	 * Invalid name empty spaces
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException1() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("  ", "08888888281", 4, 6);
	}
	
	/**
	 * Invalid name empty name
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException2() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("", "08888888281", 4, 6);
	}
	
	/**
	 * Invalid Mobile number - empty
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException3() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "", 4, 6);
	}
	
	/**
	 * Invalid Mobile number - no leading 0
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException4() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "3232132254", 4, 6);
	}
	
	/**
	 * Invalid Mobile number too long
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException5() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "023213225423234", 4, 6);
	}
	
	/**
	 * Invalid Mobile number too short
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException6() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "023214", 4, 6);
	}
	
	/**
	 * Invalid Coordinates all 0
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException7() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 0, 0);
	}
	
	/**
	 * General testing of the abstract Customer class using DriverDeliveryClass
	 */
	@Test
	public void GeneralTestAbstractCustomerClass() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 8, 8);
		Customer temp1 = new DriverDeliveryCustomer("Wafi", "0232146594", 8, 8);
		assertTrue(temp.equals(temp1));
		assertEquals(temp.getLocationX(), 8);
		assertEquals(temp.getLocationY(), 8);
		assertEquals(temp.getCustomerType(), "DVC");
		assertEquals(temp.getMobileNumber(), "0232146594");
		assertEquals(temp.getName(), "Wafi");
		assertTrue(temp1.equals(temp));		
		Customer temp2 = new DriverDeliveryCustomer("Ralph", "0232143494", 2, 0);
		assertEquals(temp2.getLocationX(), 2);
		assertEquals(temp2.getLocationY(), 0);
		assertEquals(temp2.getCustomerType(), "DVC");
		assertEquals(temp2.getMobileNumber(), "0232143494");
		assertEquals(temp2.getName(), "Ralph");		
	}
	
	/**
	 * Following will test the the DriverDeliveryCustomer class
	 */
	@Test
	public void GeneralTestDriverDeliveryCustomer() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 8, 8);
		assertEquals(temp.getCustomerType(), "DVC");		
	}
}
