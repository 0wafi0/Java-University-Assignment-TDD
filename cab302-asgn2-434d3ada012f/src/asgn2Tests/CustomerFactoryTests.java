package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Pizzas.Pizza;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Wafi Hossain
 *
 */
public class CustomerFactoryTests {
	/**
	 * Test the exceptions for Customer Factory Tests
	 * test for random customer code
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException1() throws CustomerException {
		CustomerFactory.getCustomer("Oh dear", "Scott", "0582342341", 0, 0);
	}
	
	/**
	 * put in appropriate customer code but invalid number
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException2() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "Scott", "asfas32233", 10, 10);
	}
	
	/**
	 * Try testing for invalid empty name
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException3() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "", "0582342341", 0, 0);
	}
	
	/**
	 * Try testing for invalid white spaces name
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException4() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "   ", "0582342341", 0, 0);
	}
	
	/**
	 * Try testing for name that is 21 characters
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException5() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "lalalalalalalalalalaa", "0582342341", 0, 0);
	}
	
	/**
	 * test for coordinate exceptions for PUC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException6() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "lalalalalalalalalala", "0582342341", 3, 0);
	}
	
	/**
	 * test for coordinate exceptions for PUC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException7() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "lalalalalalalalalala", "0582342341", 0, 6);
	}
	
	/**
	 * test for coordinate exceptions for PUC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException8() throws CustomerException {
		CustomerFactory.getCustomer("PUC", "lalalalalalalalalala", "0582342341", 5, 6);
	}
	
	/**
	 * test for coordinate exceptions for DNC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException14() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "lalalalalalalalalala", "0582342341", 11, 11);
	}
	
	/**
	 * test for coordinate exceptions for DNC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException15() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "lalalalalalalalalala", "0582342341", 11, 0);
	}
	
	/**
	 * test for coordinate exceptions for DNC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException16() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "lalalalalalalalalala", "0582342341", 2, 11);
	}
	
	/**
	 * test for coordinate exceptions for DNC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException9() throws CustomerException {
		CustomerFactory.getCustomer("DNC", "lalalalalalalalalala", "0582342341", 0, 0);
	}
	
	/**
	 * test for coordinate exceptions for DVC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException10() throws CustomerException {
		CustomerFactory.getCustomer("DVC", "lalalalalalalalalala", "0582342341", 0, 0);
	}
	
	/**
	 * test for coordinate exceptions for DVC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException11() throws CustomerException {
		CustomerFactory.getCustomer("DVC", "lalalalalalalalalala", "0582342341", 11, 0);
	}
	
	/**
	 * test for coordinate exceptions for DVC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException12() throws CustomerException {
		CustomerFactory.getCustomer("DVC", "lalalalalalalalalala", "0582342341", 1, 11);
	}
	
	/**
	 * test for coordinate exceptions for DVC
	 */
	@Test(expected = CustomerException.class)
	public void TestCostumerFactoryException13() throws CustomerException {
		CustomerFactory.getCustomer("DVC", "lalalalalalalalalala", "0582342341", 11, 11);
	}
	
	/**
	 * General testing of the class for various cases
	 * 
	 */
	@Test
	public void GeneralTesting() throws CustomerException {
		Customer temp = CustomerFactory.getCustomer("DNC", "I am a pokemon", "0582342341", 5, 6);
		assertTrue(temp != null);
		assertEquals(temp.getCustomerType(), "DNC");
		temp = CustomerFactory.getCustomer("DVC", "I am a pokemon", "0582342341", 5, 6);
		assertTrue(temp != null);
		assertEquals(temp.getCustomerType(), "DVC");
		temp = CustomerFactory.getCustomer("PUC", "I am a pokemon", "0582342341", 0, 0);
		assertTrue(temp != null);
		assertEquals(temp.getCustomerType(), "PUC");
	}
	
	
	
	
	
}
