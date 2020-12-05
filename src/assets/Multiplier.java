package assets;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Multiplier implements Purchasable {
	private double cost;
	private double value;
	private String name;
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

	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}
}
