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
	
	/** 
	 * Testing getPizzaTypeNormally
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test
	public void getPizzaTypeNormally () throws PizzaException {
		Pizza pizzaVeg = PizzaFactory.getPizza("PZV", 2, LocalTime.of(20, 30), LocalTime.of(20, 50));
		Pizza pizzaMarg = PizzaFactory.getPizza("PZM", 3, LocalTime.of(20, 30), LocalTime.of(20, 50));
		Pizza pizzaMeat = PizzaFactory.getPizza("PZL", 4, LocalTime.of(20, 30), LocalTime.of(20, 50));
		
		assertEquals(pizzaVeg.getPizzaType(), "vegetarian");
		assertEquals(pizzaMarg.getPizzaType(), "margherita");
		assertEquals(pizzaMeat.getPizzaType(), "meat lover");
		
		assertEquals(pizzaVeg.getQuantity(), 2);
		assertEquals(pizzaMarg.getQuantity(), 3);
		assertEquals(pizzaMeat.getQuantity(), 4);		
	}
	
	
	/**
	 * Invalid Code Name
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionTests1 () throws PizzaException {
		PizzaFactory.getPizza("ASD", 3, LocalTime.of(20, 30), LocalTime.of(20, 50));
	}
	
	/**
	 * Invalid Deliverytime One hour
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionDeliverytimeOneHour () throws PizzaException {
		PizzaFactory.getPizza("PZV", 3, LocalTime.of(20, 30), LocalTime.of(21, 30));
	}
	
	/**
	 * Invalid Deliverytime too short too cook
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionDeliverytimetooShort () throws PizzaException {
		PizzaFactory.getPizza("PZV", 3, LocalTime.of(19, 30), LocalTime.of(19, 32));
	}
	
	/**
	 * Invalid Deliverytime Delivery time before Order time
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */	
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionDeliverytimeDeliveryBeforOrder () throws PizzaException {
		PizzaFactory.getPizza("PZV", 3, LocalTime.of(19, 30), LocalTime.of(16, 32));
	}
	
	/**
	 * Invalid Deliverytime Order time too late
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionDeliverytimeOrderAfterClosingHours () throws PizzaException {
		PizzaFactory.getPizza("PZV", 3, LocalTime.of(23, 0), LocalTime.of(1, 32));
	}
	
	/**
	 * Invalid Numbers of Pizzas - Negative
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionInvalidPizzaNumbersNegative () throws PizzaException {
		PizzaFactory.getPizza("PZV", -1, LocalTime.of(22, 0), LocalTime.of(22, 32));
	}
	
	/**
	 * Invalid Numbers of Pizzas - Zero
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionInvalidPizzaNumbersZero () throws PizzaException {
		PizzaFactory.getPizza("PZV", 0, LocalTime.of(22, 0), LocalTime.of(22, 32));
	}
	
	/**
	 * Invalid Numbers of Pizzas - Eleven
	 * @throws PizzaException in this context if any invalid parameters
	 * are passed into getPizza() function
	 */
	@Test (expected = PizzaException.class)
	public void PizzaFactoryExceptionInvalidPizzaNumbersEleven () throws PizzaException {
		PizzaFactory.getPizza("PZM", 11, LocalTime.of(22, 0), LocalTime.of(22, 32));
	}
	
}
