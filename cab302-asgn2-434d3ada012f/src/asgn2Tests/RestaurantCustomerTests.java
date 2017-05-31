package asgn2Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Person A
 */
public class RestaurantCustomerTests {
	
		/**
		 * Testing getPizzaByIndex and more general testing
		 * Using logfile 20170101.txt
		 * @throws CustomerException 
		 */
		@Test(expected = CustomerException.class)
		public void TestgetPizzaByIndexException() throws LogHandlerException, CustomerException, PizzaException {
			PizzaRestaurant restaurant = new PizzaRestaurant();
			restaurant.processLog("logs/20170101.txt");			
			restaurant.getCustomerByIndex(restaurant.getNumCustomerOrders()+5);
		}
		
		/**
		 * Testing getPizzaByIndex and more general testing
		 * Using logfile 20170101.txt
		 * @throws CustomerException 
		 */
		@Test
		public void TestgetPizzaByIndexGeneral () throws LogHandlerException, CustomerException, PizzaException {
			PizzaRestaurant restaurant = new PizzaRestaurant();
			restaurant.processLog("logs/20170101.txt");
			assertTrue(restaurant.getNumCustomerOrders() == 3);
			Customer temp = restaurant.getCustomerByIndex(0);
			assertTrue(temp.getCustomerType().equals("DVC"));
			assertTrue(temp.getName().equals("Casey Jones"));
			assertEquals(temp.getLocationX(), 5);
			assertEquals(temp.getLocationY(), 5);
			temp = restaurant.getCustomerByIndex(1);
			assertTrue(temp.getCustomerType().equals("DNC"));
			assertTrue(temp.getName().equals("April O'Neal"));
			assertEquals(temp.getLocationX(), 3);
			assertEquals(temp.getLocationY(), 4);
			temp = restaurant.getCustomerByIndex(2);
			assertTrue(temp.getCustomerType().equals("PUC"));
			assertTrue(temp.getName().equals("Oroku Saki"));			
		}
		
		/**
		 * Testing getNumCustomerOrders()
		 * Using logfile 20170103.txt
		 * @throws CustomerException 
		 */
		@Test
		public void TestgetNumCustomerOrders() throws LogHandlerException, CustomerException, PizzaException {
			PizzaRestaurant restaurant = new PizzaRestaurant();
			restaurant.processLog("logs/20170103.txt");
			assertTrue(restaurant.getNumCustomerOrders() == 100);					
		}
		
		/**
		 * Testing TotalDeliveryDistance
		 * Using logfile 20170103.txt
		 * @throws CustomerException 
		 */
		@Test
		public void TestgetTotalDeliveryDistance() throws LogHandlerException, CustomerException, PizzaException {
			PizzaRestaurant restaurant = new PizzaRestaurant();
			restaurant.processLog("logs/20170101.txt");
			assertTrue(restaurant.getTotalDeliveryDistance() == 15);								
		}
		
		
}
