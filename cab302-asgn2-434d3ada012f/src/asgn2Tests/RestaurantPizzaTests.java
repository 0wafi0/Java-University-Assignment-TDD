package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Restaurant.*;


/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	// TO DO
	
	//------------
	// Get customer properly by index works correctly
	//------------
	@Test
	public void getCustomerByIndexProper () throws LogHandlerException, CustomerException, PizzaException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		restaurant.getCustomerByIndex(0);
	}
	
	
	//--------
	// Get pizza out of range throws pizza exception
	//--------
	@Test (expected = PizzaException.class)
	public void attemptOutOfRangeGet () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		
		int numPizzas = restaurant.getNumPizzaOrders();
		
		restaurant.getPizzaByIndex(numPizzas + 5);
	}
	
	//--------
	// Get negative pizza throws pizza exception
	//--------
	@Test (expected = PizzaException.class)
	public void attemptNegativePizza () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.getPizzaByIndex(-5);
	}
	
	//--------
	// Get number of pizzas
	//--------
	@Test
	public void  getNumPizzasCorrect () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		assertEquals(restaurant.getNumPizzaOrders(), 3);
	}
	
	
	//--------
	// Reset clears all pizzas
	//--------
	@Test
	public void resetClearsList () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		restaurant.resetDetails();
		assertEquals(restaurant.getNumPizzaOrders(), 0);
	}
	
	//--------
	// Get the correct total profit for the pizzas
	//--------
	@Test
	public void correctTotalProfit () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		assertTrue(restaurant.getTotalProfit() == 29);
	}
	
	
	
	
	
}
