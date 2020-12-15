package assets;

import java.io.Serializable;

import game.Player;

public class Demons extends Multiplier implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6926854800577502049L;
	private int quantityPurchased;
	private int quantityCanPurchase;
	private double totalMultiplier;
	private double mIV;					// Marginal Increment Value
	private double value;

	public Demons(double value) {
		super(100000000, value, "Demon");
		quantityPurchased = 0;
		this.value = value;

		totalMultiplier = (double) value * quantityPurchased + 1;
		mIV = 0;
	}
	
	public void update(Player p) {
		double moneyEarned = p.getMoneyEarned() - mIV;
		if (moneyEarned >= 100000000) {
			while (moneyEarned > 0) {
				if (moneyEarned - 100000000 < 0) break;
				else moneyEarned -= 100000000;
				quantityCanPurchase++;
				mIV += 100000000;
			}
		}
		totalMultiplier = value * quantityPurchased + 1;
	}
	
	@Override
	public void setPurchased() {
		quantityPurchased += quantityCanPurchase;
		quantityCanPurchase = 0;
	}
	
	public int getQuantityPurchased() {
		return quantityPurchased;
	}
	
	public int getQuantityCanPurchase() {
		return quantityCanPurchase;
	}
	
	public double getMultiplier() {
		return totalMultiplier;
	}
	
	@Override
	public String toString() {
		return "Demons: " + quantityPurchased;
	}

}
