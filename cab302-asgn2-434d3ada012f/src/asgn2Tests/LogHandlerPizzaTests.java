package asgn2Tests;

import java.util.ArrayList;

import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;


/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	// TO DO	
	
	//--------
	// Create Pizza Dataset normally
	//--------
	@Test
	public void createPizzaDataset () throws Exception {
		ArrayList<Pizza> pizza = LogHandler.populatePizzaDataset("logs/20170101.txt");
	}
	
	
	//--------
	// Attempt to create Pizza Dataset with weird filename
	//--------
	@Test (expected = LogHandlerException.class)
	public void createPizzaDatasetDodgyFilename () throws LogHandlerException, PizzaException {
		ArrayList<Pizza> pizza = LogHandler.populatePizzaDataset("log");
	}
	
	
	//--------
	// Attempt to create Pizza Dataset with weird file
	//--------
	
	
	
	//--------
	// Get customer with valid index
	//--------
	@Test
	public void getCustomerWithValidIndex () throws CustomerException, PizzaException, LogHandlerException {
		ArrayList<Pizza> pizza = LogHandler.populatePizzaDataset("logs/20170101.txt");
		pizza.get(0);
	}
	
	
	//--------
	// Create Pizza normally
	//--------
	@Test
	public void createPizzaNormally () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	
	//--------
	// Attempt to make pizza, but only have 8 inputs 
	//--------
	@Test (expected = LogHandlerException.class)
	public void createPizzaNotEnoughInfo () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	
	//--------
	// Attempt to make pizza, but have 10 inputs
	//--------
	@Test (expected = LogHandlerException.class)
	public void createPizzatTooMuchInfo () throws PizzaException, LogHandlerException {
		LogHandler.createPizza("aaaaaa,20:47:00,21:11:00,Caden Kumar,0862001010,PUC,0,0,PZL,9");
	}
	
	//--------
	// Attempt to make pizza, but have NO inputs
	//--------
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

