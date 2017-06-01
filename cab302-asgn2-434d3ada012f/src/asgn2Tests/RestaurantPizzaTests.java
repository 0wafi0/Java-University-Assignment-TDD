package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.*;


/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	
	/**
	* Get customer properly by index works correctly
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test
	public void getPizzaByIndexProper () throws PizzaException, LogHandlerException, CustomerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");		
		// Check that this actually works on a Pizza object
		VegetarianPizza pizza2 = (VegetarianPizza)LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		assertEquals(restaurant.getPizzaByIndex(0), pizza2);
	}
	
	/**
	* Get pizza out of range throws pizza exception
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test (expected = PizzaException.class)
	public void attemptOutOfRangeGet () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		
		int numPizzas = restaurant.getNumPizzaOrders();
		
		restaurant.getPizzaByIndex(numPizzas + 5);
	}
	
	/**
	* Get negative pizza throws pizza exception
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test (expected = PizzaException.class)
	public void attemptNegativePizza () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.getPizzaByIndex(-5);
	}
	
	/**
	* Get number of pizzas
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test
	public void  getNumPizzasCorrect () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		assertEquals(restaurant.getNumPizzaOrders(), 3);
	}
	
	/**
	* Reset clears all pizzas
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test
	public void resetClearsList () throws PizzaException, CustomerException, LogHandlerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		restaurant.resetDetails();
		assertEquals(restaurant.getNumPizzaOrders(), 0);
	}
	
	/**
	* Get the correct total profit for the pizzas
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	**/
	@Test
	public void correctTotalProfit () throws PizzaException, CustomerException, LogHandlerException {
		double cost = 0;
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		pizzas.add(LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2"));
		pizzas.add(LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1"));
		pizzas.add(LogHandler.createPizza("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3"));
		
		for (Pizza pizza : pizzas) {
			cost += pizza.getOrderProfit();
		}
		
		assertTrue(restaurant.getTotalProfit() == cost);
	}
	
	/**
	* Same number of lines in file as nodes in list
	* @throws LogHandlerException Thrown if there are any problem in parsing the input of createPizza
	* @throws PizzaException Thrown if there are any errors unrelated to the LogHandler Exception
	* @throws CustomerException Thrown if any thing is wrong in processing the log file in restaurant.processLog()
	* 
	* */
	@Test
	public void testSizes () throws PizzaException, LogHandlerException, CustomerException {
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		pizzas.add(LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2"));
		pizzas.add(LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1"));
		pizzas.add(LogHandler.createPizza("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3"));
		
		assertEquals(pizzas.size(), restaurant.getNumPizzaOrders());
	}
	
	
	
	
}
