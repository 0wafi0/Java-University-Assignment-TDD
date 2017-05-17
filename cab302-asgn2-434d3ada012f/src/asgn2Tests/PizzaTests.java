package asgn2Tests;


//Imported packages
import static org.junit.Assert.*;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

import org.junit.Test;

import java.time.LocalTime;


/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	// TO DO
	
	//---------------------------------------
	// Testing too many pizzas
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void tooManyPizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(11, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}

	//---------------------------------------
	
	// Testing too few pizzas
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void tooFewPizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(0, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	//---------------------------------------
	
	
	// Ordering an invalid type of pizza will need to be done in PizzaFactory
	//---------------------------------------
	
	//---------------------------------------
	
	
	// Order time and delivery time too close together
	//---------------------------------------
	
	@Test (expected = PizzaException.class)
	public void pizzaTooSoon () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(0, LocalTime.of(20, 5), LocalTime.of(20, 6));
	}
	//---------------------------------------
	
	
	// Order time and delivery time too late
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void pizzaTooLate () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(20, 5), LocalTime.of(21, 5));
	}
	
	//---------------------------------------
	// Order before opening hours
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void orderBeforeHours () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(18, 30), LocalTime.of(18, 50));
	}
	
	//---------------------------------------
	// Order after delivery hours
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void orderAfterHours () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(23, 30), LocalTime.of(23, 50));
	}
	
	//---------------------------------------
	// Order before 11 but delivery is after 11
	//---------------------------------------
	@Test
	public void orderTimeLegitButDeliveryTimeUnlegitButIKeepGoingBecauseImAGymnast () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(22, 30), LocalTime.of(23, 20));
	}
	
	//---------------------------------------
	// Delivery after 7 but order before 7
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void deliveryLegitButOrderTimeUnlegitSoIMustQuit () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(18, 55), LocalTime.of(19, 20));
	}
	
	//---------------------------------------
	// getPizzaType()
	//---------------------------------------
	@Test
	public void successfulPizzaType () throws PizzaException {
		VegetarianPizza pizza1 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza1.getPizzaType(), "vegetarian");
		
		MeatLoversPizza pizza2 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza2.getPizzaType(), "meat lover");
		
		MargheritaPizza pizza3 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza3.getPizzaType(), "margherita");
	}
	
	//---------------------------------------
	// equals()
	//---------------------------------------
	@Test
	public void pizzasAreNotAllSame () throws PizzaException {
		VegetarianPizza pizza1 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertFalse(pizza1.equals(pizza2));
	}
	
	//---------------------------------------
	// getQuantity()
	//---------------------------------------
	@Test
	public void correctNumPizzas () throws PizzaException {
		VegetarianPizza pizza1 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(4, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MargheritaPizza pizza3 = new MargheritaPizza(2, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertEquals(pizza1.getQuantity(), 3);
		assertEquals(pizza2.getQuantity(), 4);
		assertEquals(pizza3.getQuantity(), 2);
	}
	
	
	@Test
	public void pizzasContainCorrectToppings () throws PizzaException {

		MargheritaPizza pizza1 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertTrue(pizza1.containsTopping(PizzaTopping.TOMATO)); 
		assertTrue(pizza1.containsTopping(PizzaTopping.CHEESE));

		
		VegetarianPizza pizza2 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertTrue(pizza2.containsTopping(PizzaTopping.TOMATO)); 
		assertTrue(pizza2.containsTopping(PizzaTopping.CHEESE));
		assertTrue(pizza2.containsTopping(PizzaTopping.EGGPLANT));
		assertTrue(pizza2.containsTopping(PizzaTopping.MUSHROOM));
		assertTrue(pizza2.containsTopping(PizzaTopping.CAPSICUM));
		
		MeatLoversPizza pizza3 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertTrue(pizza3.containsTopping(PizzaTopping.TOMATO)); 
		assertTrue(pizza3.containsTopping(PizzaTopping.CHEESE));
		assertTrue(pizza3.containsTopping(PizzaTopping.BACON));
		assertTrue(pizza3.containsTopping(PizzaTopping.PEPPERONI));
		assertTrue(pizza3.containsTopping(PizzaTopping.SALAMI));
		
	}
	
	
	
	@Test
	public void pizzasAreCorrectPrice () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza1.getPricePerPizza() == 8);
		
		VegetarianPizza pizza2 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza2.getPricePerPizza() == 10);
		
		MeatLoversPizza pizza3 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza3.getPricePerPizza() == 12);
	}
	
	
	@Test
	public void pizzasAreCorrectCost () throws PizzaException {
		
		MargheritaPizza pizza1 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza1.getCostPerPizza() == 1.5);
		
		VegetarianPizza pizza2 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza2.getCostPerPizza() == 5.5);
		
		MeatLoversPizza pizza3 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza3.getCostPerPizza() == 5.0);
		
	}
	
	
	
	
}
