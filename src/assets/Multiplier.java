package assets;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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

	public Multiplier(double cost, double value, String name) {
		this.cost = cost;
		this.value = value;
		this.name = name;
		purchased = false;
	}

	public double getCost() {
		return cost;
	}

	public double getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public boolean isPurchased() {
		return purchased;
	}

	public void setPurchased() {
		purchased = !purchased;
	}
	
	public Business getTargetBusiness() {
		return targetBusiness;
	}
	
	public void setTargetBusiness(Business business) {
		targetBusiness = business;
	}

	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}
}
