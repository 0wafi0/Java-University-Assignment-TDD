package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Wafi Hossain
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
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException1() throws CustomerException  {
		Customer temp = new DriverDeliveryCustomer("  ", "08888888281", 4, 6);
	}
	
	/**
	 * Invalid name empty name
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException2() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("", "08888888281", 4, 6);
	}
	
	/**
	 * Invalid Mobile number - empty
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException3() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "", 4, 6);
	}
	
	/**
	 * Invalid Mobile number - no leading 0
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException4() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "3232132254", 4, 6);
	}
	
	/**
	 * Invalid Mobile number too long
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException5() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "023213225423234", 4, 6);
	}
	
	/**
	 * Invalid Mobile number too short
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException6() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "023214", 4, 6);
	}
	
	/**
	 * Invalid Coordinates all 0
	 * @throws CustomerException exception thrown by the customer and its subclasses 
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException7() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 0, 0);
	}
	
	/**
	 * Invalid Coordinates x 11
	 * @throws CustomerException exception thrown by the customer and its subclasses 
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException8() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 11, 0);
	}
	
	/**
	 * Invalid Coordinates y 11
	 * @throws CustomerException exception thrown by the customer and its subclasses 
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException10() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 2, 11);
	}
	
	/**
	 * Invalid Coordinates all 11
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void TestConsumerException11() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 11, 11);
	}
	
	/**
	 * General testing of the abstract Customer class using DriverDeliveryClass
	 * This test will also test the 
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test
	public void GeneralTestAbstractCustomerClass() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 10, 10);
		Customer temp1 = new DriverDeliveryCustomer("Wafi", "0232146594", 10, 10);
		assertTrue(temp.equals(temp1));
		assertEquals(temp.getLocationX(), 10);
		assertEquals(temp.getLocationY(), 10);
		assertEquals(temp.getCustomerType(), "Delivery Driver");
		assertEquals(temp.getMobileNumber(), "0232146594");
		assertEquals(temp.getName(), "Wafi");
		assertTrue(temp1.equals(temp));		
		Customer temp2 = new DriverDeliveryCustomer("Ralph", "0232143494", 2, 0);
		assertEquals(temp2.getLocationX(), 2);
		assertEquals(temp2.getLocationY(), 0);
		assertEquals(temp2.getCustomerType(), "Delivery Driver");
		assertEquals(temp2.getMobileNumber(), "0232143494");
		assertEquals(temp2.getName(), "Ralph");		
	}
	
	/**
	 * Following will test the the DriverDeliveryCustomer class
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test
	public void GeneralTestDriverDeliveryCustomer() throws CustomerException {
		Customer temp = new DriverDeliveryCustomer("Wafi", "0232146594", 8, 10);
		assertEquals(temp.getCustomerType(), "Delivery Driver");
		assertEquals(temp.getDeliveryDistance(), 18, 0);			
		assertEquals(temp.getLocationX(), 8);
		assertEquals(temp.getLocationY(), 10);
	}
	
	/**
	 * Following will test the the DroneDeliveryCustomer class
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test
	public void GeneralTestDroneDeliveryCustomer() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146594", 10, 3);
		assertEquals(temp.getDeliveryDistance(), Math.sqrt(100+9), 0);		
		assertEquals(temp.getCustomerType(), "Delivery Drone");
		assertEquals(temp.getMobileNumber(), "0232146594");
		assertEquals(temp.getName(), "Wafi");
		assertEquals(temp.getLocationX(), 10);
		assertEquals(temp.getLocationY(), 3);
	}
	
	/**
	 * Following will test the the DroneDeliveryCustomer class
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test
	public void GeneralTestPickUpCustomer() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "0232146594", 0, 0);
		assertEquals(temp.getDeliveryDistance(), 0, 0);		
		assertEquals(temp.getCustomerType(), "Pick Up Customer");
		assertEquals(temp.getMobileNumber(), "0232146594");
		assertEquals(temp.getName(), "Wafi");
		assertEquals(temp.getLocationX(), 0);
		assertEquals(temp.getLocationY(), 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery1() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146594", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery2() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("", "0232146594", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery3() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("   ", "0232146594", 10, 10);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery4() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("aDEREQWREWQRWFSADFWQERWERQWE	QW   ", "0232146594", 2, 8);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery5() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "023214659", 10, 10);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery6() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146593234323", 2, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery7() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146593234323", 11, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery8() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146593234323", 11, 11);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestDroneDelivery9() throws CustomerException {
		Customer temp = new DroneDeliveryCustomer("Wafi", "0232146593234323", 3, 11);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer1() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "0232146594", 5, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer2() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "0232146594", 0, 2);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer3() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "0232146594", 2, 2);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer4() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "02321465943", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer5() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "02321465", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer6() throws CustomerException {
		Customer temp = new PickUpCustomer("Wafi", "4232146513", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer7() throws CustomerException {
		Customer temp = new PickUpCustomer("", "0232146513", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer8() throws CustomerException {
		Customer temp = new PickUpCustomer("ASDFEWQRQEDFSDAGFSDGFDHFHER", "0232146513", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer9() throws CustomerException {
		Customer temp = new PickUpCustomer("ASDFEWQRQEDFSDAGFSDGFDHFHER", "0232146513", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer10() throws CustomerException {
		Customer temp = new PickUpCustomer(" ", "0232146513", 0, 0);
	}
	
	/**
	 * Conduct more exception tests for the various classes
	 * @throws CustomerException exception thrown by the customer and its subclasses
	 */
	@Test(expected = CustomerException.class)
	public void ExceptionTestPickuUpCustomer11() throws CustomerException {
		Customer temp = new PickUpCustomer("23weqewsadasd341saffa", "0232146513", 0, 0);
	}
	
}
