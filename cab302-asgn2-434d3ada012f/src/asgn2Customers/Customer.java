package asgn2Customers;

import asgn2Exceptions.CustomerException;

/** An abstract class to represent a customer at the Pizza Palace restaurant.
 *  The Customer class is used as a base class of PickUpCustomer, 
 *  DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses overwrites
 *  the abstract method getDeliveryDistance. A description of the class's
 * fields and their constraints is provided in Section 5.2 of the Assignment Specification.  
 * 
 * @author Person B
*/
public abstract class Customer {

	private String name;
	private String mobileNumber;
	private int locationX;
	private int locationY;
	private String type;
	
	private final int MIN_NAME_LENGTH = 1;
	private final int MAX_NAME_LENGTH = 20;
	private final int MOBILE_LENGTH = 10;
	private final String PICK_UP = "PUC";
	private final String DRONE_DELIVER = "DNC";
	private final String DRIVER_DELIVER = "DVC";
	private final int MAX_DISTANCE = 10;
	private final char SPACE = ' ';
	
	
	private int countCharacterInString (String string, char character) {
		int count = 0;
		for (int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == character) {
				count++;
			}
		}
		return count;
	}
	
	private void checkIfInputIsFine (String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException {
		// Mobile Number must be 10 chars long and start with 0
		if (mobileNumber.length() != MOBILE_LENGTH || mobileNumber.charAt(0) != '0') {
			throw new CustomerException();
		}
		
		// Customer name must be 1 - 20 characters long. Cannot only be white spaces.
		if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH || countCharacterInString(name, SPACE) == name.length()) {
			throw new CustomerException();
		}
		
		// Make sure the type is actually valid (NOT ASKED FOR! Maybe move this to a test?)
		if (!type.equals(PICK_UP) && !type.equals(DRIVER_DELIVER) && !type.equals(DRONE_DELIVER)) {
			throw new CustomerException();
		}
		// Ensure location IS 0,0 when type is pickup
		if (type.equals(PICK_UP) && (locationX != 0 || locationY != 0)) {
			throw new CustomerException();
		}
		
		// Ensure location ISN'T 0,0 when type is delivery
		if ((type.equals(DRONE_DELIVER) || type.equals(DRIVER_DELIVER)) && (locationX == 0 && locationY == 0)) {
			throw new CustomerException();
		}
		
		// Ensure locationX and locationY are both less than or equal to 10
		if (locationX > MAX_DISTANCE && locationY > MAX_DISTANCE) {
			throw new CustomerException();
		}
	}
	
	/**
	 *  This class represents a customer of the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.2. 
	 *  A CustomerException is thrown if the any of the constraints listed in Section 5.2 of the Assignment Specification
	 *  are violated. 
	 *  
  	 * <P> PRE: True
  	 * <P> POST: All field values are set
  	 * 
	 * @param name - The Customer's name 
	 * @param mobileNumber - The customer mobile number
	 * @param locationX - The customer x location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param locationY - The customer y location relative to the Pizza Palace Restaurant measured in units of 'blocks' 
	 * @param type - A human understandable description of this Customer type
	 * @throws CustomerException if supplied parameters are invalid 
	 * 
	 */
	public Customer(String name, String mobileNumber, int locationX, int locationY, String type) throws CustomerException{
		// TO DO
		checkIfInputIsFine(name, mobileNumber, locationX, locationY, type);
		this.name = name;
		this.mobileNumber = mobileNumber;
		this.locationX = locationX;
		this.locationY = locationY;
		this.type = type;
	}
	
	/**
	 * Returns the Customer's name.
	 * @return The Customer's name.
	 */
	public final String getName () {
		// TO DO
		return this.name;
	}
	
	/**
	 * Returns the Customer's mobile number.
	 * @return The Customer's mobile number.
	 */
	public final String getMobileNumber () {
		// TO DO
		return this.mobileNumber;
	}

	/**
	 * Returns a human understandable description of the Customer's type. 
	 * The valid alternatives are listed in Section 5.2 of the Assignment Specification. 
	 * @return A human understandable description of the Customer's type.
	 */
	public final String getCustomerType () {
		// TO DO
		return this.type;
	}
	
	/**
	 * Returns the Customer's X location which is the number of blocks East or West 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's X location
	 */
	public final int getLocationX () {
		// TO DO
		return this.locationX;
	}

	/**
	 * Returns the Customer's Y location which is the number of blocks North or South 
	 * that the Customer is located relative to the Pizza Palace restaurant. 
	 * @return The Customer's Y location
	 */
	public final int getLocationY () {
		// TO DO
		return this.locationY;
	}

	/**
	 * An abstract method that returns the distance between the Customer and 
	 * the restaurant depending on the mode of delivery. 
	 * @return The distance between the restaurant and the Customer depending on the mode of delivery.
	 */
	public abstract double getDeliveryDistance();

	
	
	/**
	 * Compares *this* Customer object with an instance of an *other* Customer object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 *  You do not need to test this method.
	 * 
	 * @return true if *this* Customer object and the *other* Customer object have the same values returned for 	
	 * getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
	 */
	@Override
	public boolean equals(Object other){
		Customer otherCustomer = (Customer) other;

		return ( (this.getName().equals(otherCustomer.getName()))  &&
			(this.getMobileNumber().equals(otherCustomer.getMobileNumber())) && 
			(this.getLocationX() == otherCustomer.getLocationX()) && 
			(this.getLocationY() == otherCustomer.getLocationY()) && 
			(this.getCustomerType().equals(otherCustomer.getCustomerType())) );			
	}

}
