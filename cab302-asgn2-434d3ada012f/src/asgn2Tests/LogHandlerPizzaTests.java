package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;


/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	
	/**
	 * Create pizza datasets normally
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 **/
	@Test(expected = LogHandlerException.class)
	public void createPizzaDatasetLogHandlerException1 () throws PizzaException, LogHandlerException {
		ArrayList<Pizza> pizza = LogHandler.populatePizzaDataset(" ");
	}
	
	/**
	 * Test get populatePizzaDataset
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 */
	@Test
	public void testPizzaGet () throws CustomerException, PizzaException, LogHandlerException {
		ArrayList<Pizza> pizza = LogHandler.populatePizzaDataset("logs/20170101.txt");
		// Check that this actually works on a Pizza object
		VegetarianPizza pizza2 = (VegetarianPizza)LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		assertEquals(pizza.get(0), pizza2);
	}
	

	/**
	 *	Every line is read correctly in the log file
	 * */
	@Test
	public void testEveryLogFile1Line () throws PizzaException, LogHandlerException, CustomerException {
		
		PizzaRestaurant restaurant = new PizzaRestaurant();
		restaurant.processLog("logs/20170101.txt");
		
		ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
		
		pizzas.add(LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2"));
		pizzas.add(LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1"));
		pizzas.add(LogHandler.createPizza("21:00:00,21:35:00,Oroku Saki,0111222333,PUC,0,0,PZL,3"));

		for (int i = 0; i < pizzas.size(); i++) {
			assertEquals(pizzas.get(i), restaurant.getPizzaByIndex(i));
		}
	}

	/**
	 * Create pizza datasets normally
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 */
	@Test
	public void createPizzaNormally () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	/**
	 *	Attempt to make pizza, but only have 8 inputs 
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 * */
	@Test (expected = LogHandlerException.class)
	public void createPizzaNotEnoughInfo () throws PizzaException, LogHandlerException  {
		LogHandler.createPizza("21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	/**
	 *	Attempt to make pizza, but have 10 inputs
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 * */
	@Test (expected = LogHandlerException.class)
	public void createPizzatTooMuchInfo () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("aaaaaa,20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	/**
	 *	Attempt to make pizza, but have NO inputs
	 * @throws LogHandlerException 
	 * @throws PizzaException 
	 * */
	@Test (expected = LogHandlerException.class)
	public void createPizzaNoInfo () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("");
	}
	
	//--------
	// Attempt to make one too many pizzas
	//--------
	@Test (expected = PizzaException.class)
	public void makeTooManyPizzas () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,11");
	}
	
	//--------
	// Attempt to make negative pizzas
	//--------
	@Test (expected = PizzaException.class)
	public void makeNegativePizzas () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,-1");
	}
	
	//--------
	// Attempt to make zero pizzas
	//--------
	@Test (expected = PizzaException.class)
	public void makeZeroPizzas () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,0");
	}
	
	//--------
	// Attempt to make float pizzas
	//--------
	@Test (expected = LogHandlerException.class)
	public void makeFloatPizzas () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,4.5");
	}
		
	//--------
	// Attempt a weirdly formatted time string
	//--------
	@Test (expected = LogHandlerException.class)
	public void makePizzaWithInvalidTime () throws LogHandlerException, PizzaException {
		LogHandler.createPizza("2047:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,4.5");
		LogHandler.createPizza("20:47:00,2111:00,Caden Kumar,0862001010,PUC,0,0,PZL,4.5");
	}
	
	//--------
	// Have blank inputs
	//--------
	@Test (expected = LogHandlerException.class)
	public void makePizzaWithBlankInputs () throws PizzaException, LogHandlerException {
		LogHandler.createPizza(",,,,,,,,");
	}

	
}

