package asgn2Tests;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.PizzaFactory;


/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	// TO DO
	
	@Test (expected = PizzaException.class)
	public void assertWhenNotPizzaType () throws PizzaException {
		PizzaFactory.getPizza("AAAA", 3, LocalTime.of(20, 5, 5), LocalTime.of(20, 30, 5));
	}
	
	@Test
	public void createMLPizzaWithoutError () throws PizzaException {
		PizzaFactory.getPizza("PZL", 3, LocalTime.of(20, 5,  5), LocalTime.of(20, 30, 5));
	}

	@Test
	public void createVegePizzaWithoutError () throws PizzaException {
		PizzaFactory.getPizza("PZV", 3, LocalTime.of(20, 5,  5), LocalTime.of(20, 30, 5));
	}
	
	@Test
	public void createMargPizzaWithoutError () throws PizzaException {
		PizzaFactory.getPizza("PZM", 3, LocalTime.of(20, 5, 5), LocalTime.of(20, 30, 5));
	}
	
}
