package asgn2Pizzas;

import java.time.LocalTime;
import java.util.ArrayList;

import asgn2Exceptions.PizzaException;
import java.time.temporal.ChronoUnit;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and MeatLoversPizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	
	/**
	 *  Declare private variables
     * */	
	int _quantity;
	LocalTime _orderTime;
	LocalTime _deliveryTime;
	String _type;
	double _price;
	double _cost;
	ArrayList<PizzaTopping> _toppings;
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 */ 	
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException{
		_quantity = quantity;
		if(quantity > 10 || quantity < 1) {
			throw new PizzaException("Invalid number of pizzas");
		}
		if(!(type == "margherita") && !(type == "vegetarian") && !(type == "meat lover")) {
			throw new PizzaException("Invalid type of pizza");
		}
		if(orderTime.isAfter(deliveryTime) || ChronoUnit.MINUTES.between(orderTime, deliveryTime) < 10 || ChronoUnit.HOURS.between(orderTime, deliveryTime) >= 1) {
			throw new PizzaException("Invalid delivery time!");
		}
		if(orderTime.getHour() < 19) {
			throw new PizzaException("Invalid order time!!");
		}
		_orderTime = orderTime;
		_deliveryTime = deliveryTime;
		_type = type;
		_price = price;
	}

	/**
	 * Calculates how much a pizza would could to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		if(_type == "margherita") {
			_cost += PizzaTopping.TOMATO.getCost();
			_cost += PizzaTopping.CHEESE.getCost();
			_toppings.add(PizzaTopping.CHEESE);
			_toppings.add(PizzaTopping.TOMATO);
		}
		else if(_type == "vegetarian") {
			_cost += PizzaTopping.TOMATO.getCost();
			_cost += PizzaTopping.CHEESE.getCost();
			_cost += PizzaTopping.EGGPLANT.getCost();
			_cost += PizzaTopping.MUSHROOM.getCost();
			_cost += PizzaTopping.CAPSICUM.getCost();
			_toppings.add(PizzaTopping.CHEESE);
			_toppings.add(PizzaTopping.TOMATO);
			_toppings.add(PizzaTopping.EGGPLANT);
			_toppings.add(PizzaTopping.MUSHROOM);
			_toppings.add(PizzaTopping.CAPSICUM);
		}
		else if(_type == "meat lover") {
			_cost += PizzaTopping.TOMATO.getCost();
			_cost += PizzaTopping.CHEESE.getCost();
			_cost += PizzaTopping.BACON.getCost();
			_cost += PizzaTopping.PEPPERONI.getCost();
			_cost += PizzaTopping.SALAMI.getCost();	
			_toppings.add(PizzaTopping.CHEESE);
			_toppings.add(PizzaTopping.TOMATO);
			_toppings.add(PizzaTopping.BACON);
			_toppings.add(PizzaTopping.PEPPERONI);
			_toppings.add(PizzaTopping.SALAMI);
		}
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		return _cost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		return _price;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		return _cost*_quantity;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		return _price*_quantity;
	}
	
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		return getOrderPrice() - getOrderCost();
	}
	

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		return _toppings.contains(topping);
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return _quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return _type;
	}


	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}

	
}
