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
	
	//---------------------------------------
	// Testing too many pizzas
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void tooManyPizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(11, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	//---------
	// Almost too many pizzas
	//---------
	@Test
	public void almostTooManyPizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(9, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}

	//---------------------------------------
	// Testing too few pizzas
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void tooFewPizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(0, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	//----------
	// Testing negative pizzas
	//----------
	@Test (expected = PizzaException.class)
	public void negativePizzas () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(-5, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	
	//---------------------------------------
	// Order time and delivery time too close together
	//---------------------------------------
	
	@Test (expected = PizzaException.class)
	public void deliverPizzaTooSoon () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(0, LocalTime.of(20, 5), LocalTime.of(20, 6));
	}
	
	//---------------------------------------
	// Order time and delivery time too far apart
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void deliverPizzaTooLate () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(20, 5), LocalTime.of(21, 5));
	}
	
	//---------------------------------------
	// Order 1 minute before opening hours
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void orderOneMinuteBeforeHours () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(18, 59), LocalTime.of(19, 9));
	}
	
	//---------
	// Order 1 minute after opening hours
	//---------
	@Test
	public void orderOnOpeningTime () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(19, 0), LocalTime.of(19, 10));
	}
	
	//---------------------------------------
	// Order after delivery hours
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void orderOnClosingTime () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(23, 0), LocalTime.of(23, 50));
	}
	
	//---------------------------------------
	// Order before 11 but delivery is after 11
	//---------------------------------------
	@Test
	public void orderTimeLegitButDeliveryTimeUnlegitButIKeepGoingBecauseImAGymnast () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(22, 59), LocalTime.of(23, 20));
	}
	
	//---------------------------------------
	// Delivery after 7 but order before 7
	//---------------------------------------
	@Test (expected = PizzaException.class)
	public void deliveryTimeLegitButOrderTimeUnlegitSoIMustQuit () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(1, LocalTime.of(18, 59), LocalTime.of(19, 20));
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
	
	@Test
	public void ordersAreCorrectCost () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(5, LocalTime.of(19, 0), LocalTime.of(19, 10));
		assertTrue(pizza1.getOrderCost() == 5.0 * 5);
	}
	
	@Test
	public void ordersAreCorrectPrice () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(5, LocalTime.of(19, 0), LocalTime.of(19, 10));
		assertTrue(pizza1.getOrderCost() == 12 * 5);
	}
	
	
	
	
}
