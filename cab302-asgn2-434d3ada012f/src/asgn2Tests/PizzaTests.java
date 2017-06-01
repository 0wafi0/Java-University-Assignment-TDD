package asgn2Tests;


//Imported packages
import static org.junit.Assert.*;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;
import asgn2Restaurant.LogHandler;

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
	
	/**
	* Testing too many pizzas
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void tooManyPizzas () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(11, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	/**
	* Almost too many pizzas
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	* @throws LogHandlerException Thrown if any problem is encountered in passing the sling to the LogHandler.createPizza() function
	*/
	@Test
	public void almostTooManyPizzas () throws PizzaException, LogHandlerException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(9, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = (MeatLoversPizza)LogHandler.createPizza("20:00:00,20:30:00,Oroku Saki,0111222333,PUC,0,0,PZL,9");
		assertEquals(pizza1, pizza2);
	}

	/**
	* Testing too few pizzas
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void tooFewPizzas () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(0, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	/**
	* Testing negative pizzas
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void negativePizzas () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(-5, LocalTime.of(20, 5), LocalTime.of(20, 30));
	}
	
	
	/**
	* Order time and delivery time are identical
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void deliverPizzaImmediately () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 5), LocalTime.of(20, 6));
	}
	
	/**
	* Order time and delivery time are 1 minute apart
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void deliverPizza1Min () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 5), LocalTime.of(20, 6));
	}
	
	/**
	* Order time and delivery time are 9 minutes apart
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void deliverPizza9Min () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 5), LocalTime.of(20, 14));
	}
	
	/**
	* Order time and delivery time 10 mins apart
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test
	public void deliverPizza10Min () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 5), LocalTime.of(20, 15));
		
		assertTrue(pizza1.getCostPerPizza() == pizza1.getOrderCost());
		assertTrue(pizza1.getOrderPrice() == pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderPrice() / pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderCost() / pizza1.getCostPerPizza());
	}
	
	/**
	* Order time and delivery time too far apart
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void deliverPizzaTooLate () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(20, 5), LocalTime.of(21, 5));
	}
	
	/**
	* Order 1 minute before opening hours
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void orderOneMinuteBeforeHours () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(18, 59), LocalTime.of(19, 9));
	}
	
	/**
	* Order exactly on opening hours
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	* @throws LogHandlerException Thrown if any problem is encountered in passing
	* the sling to the LogHandler.createPizza() function
	*/
	@Test
	public void orderOnOpeningTime () throws PizzaException, LogHandlerException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(19, 0), LocalTime.of(19, 10));
		MeatLoversPizza pizza2 = (MeatLoversPizza)LogHandler.createPizza("19:00:00,19:10:00,Oroku Saki,0111222333,PUC,0,0,PZL,1");
		assertEquals(pizza1, pizza2);
	}
	
	/**
	* Order after delivery hours
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test (expected = PizzaException.class)
	public void orderOnClosingTime () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(23, 0), LocalTime.of(23, 50));
		
		assertTrue(pizza1.getCostPerPizza() == pizza1.getOrderCost());
		assertTrue(pizza1.getOrderPrice() == pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderPrice() / pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderCost() / pizza1.getCostPerPizza());
	}
	
	/**
	* Order before 11 but delivery is after 11
	* @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	* constructor or its underlying constructor within the context
	*/
	@Test
	public void orderTimeLegitButDeliveryTimeUnlegitButIKeepGoingBecauseImAGymnast () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(22, 59), LocalTime.of(23, 20));
		
		assertTrue(pizza1.getCostPerPizza() == pizza1.getOrderCost());
		assertTrue(pizza1.getOrderPrice() == pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderPrice() / pizza1.getPricePerPizza());
		assertTrue(pizza1.getQuantity() == pizza1.getOrderCost() / pizza1.getCostPerPizza());
	}
	
	/**
	 * Order 1 minute before 7. Delivery after 7. Make sure a PizzaException is thrown.
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test (expected = PizzaException.class)
	public void deliveryTimeLegitButOrderTimeUnlegitSoIMustQuit () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(1, LocalTime.of(18, 59), LocalTime.of(19, 20));
	}
	
	/**
	 * Test that the pizza types are correct
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void successfulPizzaType () throws PizzaException {
		VegetarianPizza pizza1 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza1.getPizzaType(), "vegetarian");
		
		MeatLoversPizza pizza2 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza2.getPizzaType(), "meat lover");
		MargheritaPizza pizza3 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertEquals(pizza3.getPizzaType(), "margherita");
	}
	
	/**
	 * Test that pizza objects are different when you change their type
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void pizzaTypesAreNotAllSame () throws PizzaException {
		VegetarianPizza pizza1 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertFalse(pizza1.equals(pizza2));
	}
	
	/**
	 * Test that pizza objects are different when you order different numbers of pizzas
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void pizzaQuantitiesChangePizzaObject () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(4, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertFalse(pizza1.equals(pizza2));
	}
	
	
	/**
	 * Test that the delivery time does not change the pizza objects. It just decides whether or not they are made.
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void pizzaDeliveryTimeDoesNotChangePizzaObject () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(3, LocalTime.of(20, 3), LocalTime.of(20, 35));
		
		assertTrue(pizza1.equals(pizza2));
	}
	
	
	/**
	 * Test that the pizza object gets initialized with the correct 
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void correctNumPizzas () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza2 = new MeatLoversPizza(4, LocalTime.of(20, 5), LocalTime.of(20, 30));
		MeatLoversPizza pizza3 = new MeatLoversPizza(2, LocalTime.of(20, 5), LocalTime.of(20, 30));
		
		assertEquals(pizza1.getQuantity(), 3);
		assertEquals(pizza2.getQuantity(), 4);
		assertEquals(pizza3.getQuantity(), 2);
	}
	
	/**
	 * Just a general test that each pizza object has the right toppings
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
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
	
	
	/**
	 * Just testing that the prices are initialized correctly
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void pizzasAreCorrectPrice () throws PizzaException {
		MargheritaPizza pizza1 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza1.getPricePerPizza() == 8);
		
		VegetarianPizza pizza2 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza2.getPricePerPizza() == 10);
		
		MeatLoversPizza pizza3 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza3.getPricePerPizza() == 12);
	}
	
	
	
	/**
	 * Testing that the costs are initialized correctly
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void pizzasAreCorrectCost () throws PizzaException {
		
		MargheritaPizza pizza1 = new MargheritaPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza1.getCostPerPizza() == 1.5);
		
		VegetarianPizza pizza2 = new VegetarianPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza2.getCostPerPizza() == 5.5);
		
		MeatLoversPizza pizza3 = new MeatLoversPizza(3, LocalTime.of(20, 5), LocalTime.of(20, 30));
		assertTrue(pizza3.getCostPerPizza() == 5.0);
		
	}
	
	/**
	 * Testing That orders are good
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void ordersAreCorrectCost () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(5, LocalTime.of(19, 0), LocalTime.of(19, 10));
		assertTrue(pizza1.getOrderCost() == 5.0 * 5);
	}
	
	/**
	 * Testing that orders give the right price
	 * @throws PizzaException Thrown if invalid parameters are pissed in the pizza
	 * constructor or its underlying constructor within the context
	 * */
	@Test
	public void ordersAreCorrectPrice () throws PizzaException {
		MeatLoversPizza pizza1 = new MeatLoversPizza(5, LocalTime.of(19, 0), LocalTime.of(19, 10));
		assertTrue(pizza1.getOrderPrice() == 12 * 5);
	}
	
	
	
	
}
