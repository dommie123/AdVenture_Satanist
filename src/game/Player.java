package game;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JOptionPane;

import assets.*;

public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1879347579100412176L;
	private String name;
	private double currentMoney;
	private double moneyEarned;			// The total amount of money the player has gained throughout the game
	

	public int progress = 0;

	public Player(String name) {
		this.name = name;
		currentMoney = 0.00;
	}

	public void gainRevenue(Business business) {
		currentMoney += business.getRevenue();
		moneyEarned += business.getRevenue();
	}

	public void buyBusiness(Business business) {
		if (spendMoney(business.getCost())) {
			business.setQuantityPurchased(business.getQuantityPurchased() + 1);
			business.update(true);
		}
	}

	public void buyMultiplier(Business business, Multiplier multiplier) {
		if (spendMoney(multiplier.getCost()) && !multiplier.isPurchased()) {
			multiplier.setPurchased();
			business.setMultiplier(business.getMultiplier() * multiplier.getValue());
			business.update(false);
		}
	}

	public void buySpeedBoost(List<Business> businesses, SpeedBoost boost) {
		if (spendMoney(boost.getCost()) && !boost.isPurchased()) {
			if (boost.getLength() >= 24) {
				boost.longSetPurchased();
			} else if (boost.getLength() < 1 && boost.getLength() > 0) {
				boost.shortSetPurchased();
			} else {
				boost.setPurchased();
			}

			// In the background, repeatedly check if boost is expired...
			Runnable r = new Runnable() {
				private boolean expired;
				
				public void run() {
					if (boost.isPurchased()) {
						expired = false;
						for (Business b : businesses)
							b.setWaitTime(b.getWaitTime() / (double) boost.getValue());
						while (boost.isPurchased()) {
							boost.checkTime();
						}
					}
					if (!expired) {
						for (Business b : businesses)
							b.setWaitTime(b.getWaitTime() * (double) boost.getValue());
						expired = true;
					}
				}
			};

			new Thread(r).start();
		}
	}
	
	public void buyManager(Business business, Manager manager) {
		if (spendMoney(manager.getCost()) && !manager.isPurchased()) {
			manager.setPurchased();
			manager.update(business);
		}
	}
	
	public void buyDemons(List<Business> businesses, List<Multiplier> multipliers, List<SpeedBoost> boosts, Demons demons) {
		currentMoney = 0;
		moneyEarned = 0;
		demons.setPurchased();
		demons.update(this);
		
		for (SpeedBoost sb : boosts) {
			sb.setExpireDate(LocalDate.now());
			sb.setExpireTime(LocalTime.now().truncatedTo(ChronoUnit.SECONDS));
		}
		for (Multiplier m : multipliers) {
			if (m.isPurchased()) m.setPurchased(); 		// If a multiplier has been purchased, sell it and make it available for purchase.
		}
		for (Business b : businesses) {
			if (b.isStarter()) b.setQuantityPurchased(1);
			else b.setQuantityPurchased(0);
			b.setMultiplier(b.getMultiplier() * demons.getMultiplier());
			b.update(true);
		}
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getCurrentMoney() {
		return currentMoney;
	}
	
	public double getMoneyEarned() {
		return moneyEarned;
	}

	public String getMoneyAsString() {
		DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
		return currency.format(currentMoney);
	}

	private boolean spendMoney(double moneySpent) {
		if (currentMoney >= moneySpent) {
			currentMoney -= moneySpent;
			return true;
		} else {
			JOptionPane.showMessageDialog(null, "You don't have enough money!", "Not enough money!",
				JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
}
