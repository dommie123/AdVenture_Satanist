package assets;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import assets.Business;

public class Manager implements Purchasable {
	
	private boolean isPurchased;
	private double cost;
	private String name;
	
	private final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
	
	public Manager(double cost, String name) {
		isPurchased = false;
		this.cost = cost;
		this.name = name;
	}
	
	public void update(Business business) {
		business.setManaged();
	}

	@Override
	public boolean isPurchased() {
		return isPurchased;
	}

	@Override
	public void setPurchased() {
		isPurchased = true;
	}

	@Override
	public double getCost() {
		return cost;
	}
	
	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}
}
