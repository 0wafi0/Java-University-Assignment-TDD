package asgn2Tests;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;


/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	
	
	// Make sure you tests for all possible exception.
	// Have a look at CustomerFactoryTests I have heaps of exception testing
	// I know it's ANAL but yeah
	
	//--------
	// Make Pizza correctly
	//--------
	@Test
	public void getPizzaTypeNormally () throws PizzaException {
		Pizza pizzaVeg = PizzaFactory.getPizza("PZV", 2, LocalTime.of(20, 30), LocalTime.of(20, 50));
		Pizza pizzaMarg = PizzaFactory.getPizza("PZM", 3, LocalTime.of(20, 30), LocalTime.of(20, 50));
		Pizza pizzaMeat = PizzaFactory.getPizza("PZL", 4, LocalTime.of(20, 30), LocalTime.of(20, 50));
		
		assertEquals(pizzaVeg.getPizzaType(), "vegetarian");
		assertEquals(pizzaMarg.getPizzaType(), "margherita");
		assertEquals(pizzaMeat.getPizzaType(), "meat lover");
	}
	
	
	//--------
	// Attempt invalid pizza code
	//--------
	@Test (expected = PizzaException.class)
	public void attemptInvalidPizzaCode () throws PizzaException {
		PizzaFactory.getPizza("ASD", 3, LocalTime.of(20, 30), LocalTime.of(20, 50));
	}
	
}
