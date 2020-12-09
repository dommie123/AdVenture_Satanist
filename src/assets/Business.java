package assets;

import java.text.*;

public class Business implements Purchasable {
	private double currentCost;
	private double initialCost;
	private double initialRevenue;
	private double currentRevenue;
	private double costMultiplier;
	private double waitTime;			// Wait Time in seconds
	private double multiplier;
	private int quantityPurchased;
	private boolean isStarter;			// Is this the business the player starts with?
	private boolean autoManaged;
	private String name;
	private DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();

	public Business(double cost, double revenue, double waitTime, boolean isStarter, String name) {
		this.isStarter = isStarter;
		autoManaged = false;
		multiplier = 1;
		costMultiplier = 1;

		if (isStarter) {
			quantityPurchased = 1;
			currentCost = quantityPurchased * (cost * multiplier);
		}
		else {
			quantityPurchased = 0;
			currentCost = (quantityPurchased + 1) * (cost * costMultiplier);
		}
		
		this.initialCost = currentCost;
		
		if (quantityPurchased > 0) {
			this.initialRevenue = revenue * quantityPurchased * multiplier;
			this.currentRevenue = initialRevenue;
		}
		else {
			this.initialRevenue = revenue * multiplier;
		}
		
		this.waitTime = waitTime;
		this.name = name;
	}

	public void update(boolean purchased) {
		// If an instance of this business has been purchased, execute the below statement.
		if (purchased) 
			costMultiplier *= 1.9;
		
		if (quantityPurchased > 0) 
			currentRevenue = initialRevenue * quantityPurchased * multiplier;
		else 
			initialRevenue *= multiplier;
		
		currentCost = initialCost * costMultiplier;
	}
	
	public boolean isPurchased() {
		return quantityPurchased >= 1;
	}
	
	public boolean isAutoManaged() {
		return autoManaged;
	}
	
	public boolean isStarter() {
		return isStarter;
	}

	public double getCost() {
		return currentCost;
	}

	public double getRevenue() {
		return currentRevenue;
	}

	public void setRevenue(double revenue) {
		this.currentRevenue = revenue;
	}

	public double getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(double waitTime) {
		this.waitTime = waitTime;
	}

	public double getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}

	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	
	public void setPurchased() {
		setQuantityPurchased(1);
	}

	public void setQuantityPurchased(int quantityPurchased) {
		this.quantityPurchased = quantityPurchased;
	}
	
	public void setManaged() {
		autoManaged = true;
	}

	public String getCostAsString() {
		return currency.format(currentCost);
	}
	
	public boolean equals(Business b) {
		return this.name == b.name && this.initialRevenue == b.initialRevenue
				&& this.initialCost == b.initialCost;
	}

	@Override
	public String toString() {
		return quantityPurchased + " " + name + " (" + currency.format(currentRevenue) + ")";
	}
}
