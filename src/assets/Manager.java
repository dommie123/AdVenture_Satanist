package assets;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import assets.Business;

public class Manager implements Purchasable, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8693883560268109282L;
	private boolean isPurchased;
	private double cost;
	private String name;
	private Business targetBusiness;
	
	private final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
	
	public Manager(double cost, String name) {
		isPurchased = false;
		this.cost = cost;
		this.name = name;
	}
	
	public void update(Business business) {
		business.setManaged();
	}
	
	public Business getTargetBusiness() {
		// TODO Auto-generated method stub
		return targetBusiness;
	}
	
	public void setTargetBusiness(Business business) {
		targetBusiness = business;
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
