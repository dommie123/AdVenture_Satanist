package assets;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
/**
 * This is one of the special items that is used in the game. When purchased, it will 
 * increase the total multiplier of a target business for as long as the player's empire
 * is still running.
 * @author Dominick Wliey
 *
 */
public class Multiplier implements Purchasable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4436738214317638733L;
	private double cost;
	private double value;
	private String name;
	private Business targetBusiness;
	private boolean purchased;
	private final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
	/**
	 * Creates a new <code>Multiplier</code> and initializes its cost in "hell dollars", its value, 
	 * and its name.
	 * @param cost - the cost of the multiplier in "hell dollars"
	 * @param value - the value of the multiplier
	 * @param name - the name of the multiplier
	 */
	public Multiplier(double cost, double value, String name) {
		this.cost = cost;
		this.value = value;
		this.name = name;
		purchased = false;
	}
	/**
	 * Gets the multiplier's cost in "hell dollars"
	 * @return the cost of the multiplier in "hell dollars"
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * Gets the multiplier's value. 
	 * @return the value of the multiplier
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Gets the multiplier's name
	 * @return the name of the multiplier
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the business whose value to increase.
	 * @return the target business
	 */
	public Business getTargetBusiness() {
		return targetBusiness;
	}
	/**
	 * Sets the business whose value to increase to a new target business.
	 * @param business - the new target business
	 */
	public void setTargetBusiness(Business business) {
		targetBusiness = business;
	}
	
	// Overridden Methods
	public boolean isPurchased() {
		return purchased;
	}
	
	public void setPurchased() {
		purchased = !purchased;
	}

	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}
	// End Overridden Methods
}
