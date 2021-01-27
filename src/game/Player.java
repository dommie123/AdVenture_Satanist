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
/**
 * This class represents the user/player. The player is in charge of tasks, such as purchasing
 * various business and special items to enhance the player's experience as well as gaining
 * revenue from the businesses they have purchased and resetting their empire to further 
 * increase their business multiplier.
 * @author Dominick Wiley
 *
 */
public class Player implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1879347579100412176L;
	private String name;
	private double currentMoney;
	private double moneyEarned;			// The total amount of money the player has gained throughout the game
	

	public int progress = 0;

	/**
	 * Creates a new <code>Player</code> object and initializes their name according to the
	 * specified parameter. Also initializes default values for the player's current money and
	 * the money they have earned.
	 * @param name - the player's username
	 */
	public Player(String name) {
		this.name = name;
		currentMoney = 0.00;
		moneyEarned = currentMoney;
	}
	/**
	 * Gets the revenue generated from a target business and adds it to the player's
	 * current money and money earned.
	 * @param business - the target business
	 */
	public void gainRevenue(Business business) {
		currentMoney += business.getRevenue();
		moneyEarned += business.getRevenue();
	}
	/**
	 * Purchases a business from a list of available business. This method does nothing if
	 * the target business is unaffordable.
	 * @param businessm - the target business
	 */
	public void buyBusiness(Business business) {
		if (spendMoney(business.getCost())) {
			business.setQuantityPurchased(business.getQuantityPurchased() + 1);
			business.update(true);
		}
	}
	/**
	 * Purchases a multiplier from a list of available multipliers and adds the multiplier
	 * to the total multiplier of a target business. This method does nothing if the 
	 * target multiplier is unaffordable.
	 * @param business - the target business
	 * @param multiplier - the target multiplier
	 * @see buyBusiness()
	 */
	public void buyMultiplier(Business business, Multiplier multiplier) {
		if (spendMoney(multiplier.getCost()) && !multiplier.isPurchased()) {
			multiplier.setPurchased();
			business.setMultiplier(business.getMultiplier() * multiplier.getValue());
			business.update(false);
		}
	}
	/**
	 * Purchases a speed boost from a list of available speed boosts and reduces the wait
	 * time of all businesses by the value of the target speed boost for a limited time. 
	 * This method does nothing if the speed boost is unaffordable.
	 * @param businesses - the target business
	 * @param boost - the target speed boost
	 * @see buyBusiness()
	 */
	public void buySpeedBoost(List<Business> businesses, SpeedBoost boost) {
		if (spendMoney(boost.getCost()) && !boost.isPurchased()) {
			if (boost.getLength() >= 24) {
				boost.longSetPurchased();
			} else if (boost.getLength() < 1 && boost.getLength() > 0) {
				boost.shortSetPurchased();
			} else {
				boost.setPurchased();
			}
		}
	}
	/**
	 * Purchases a manager from a list of available managers and sets the business to generate
	 * revenue automatically regardless of user input. This method does nothing if the 
	 * manager is unaffordable.
	 * @param business
	 * @param manager
	 * @see buyBusiness()
	 */
	public void buyManager(Business business, Manager manager) {
		if (spendMoney(manager.getCost()) && !manager.isPurchased()) {
			manager.setPurchased();
			manager.update(business);
		}
	}
	/**
	 * Acquires a set number of demons based on the total money the player has earned and 
	 * resets that player's empire in exchange for an increased total multiplier across
	 * all businesses. 
	 * @param businesses - the list of businesses to reset
	 * @param multipliers - the list of multipliers to reset
	 * @param boosts - the list of speed boosts to reset
	 * @param demons - the number of demons accumulated
	 */
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
		
		// For every business, deactivate all of its hidden multipliers and speed boosts.
		for (Business b : businesses) {
			if (b.isStarter()) b.setQuantityPurchased(1);
			else b.setQuantityPurchased(0);
			b.setMultiplier(b.getMultiplier() * demons.getMultiplier());
			for (HiddenMultiplier hm : b.getMultipliers())
				hm.setPurchased();		
			for (HiddenBoost hb : b.getSpeedBoosts())
				hb.setPurchased();
			b.update(true);
		}
	}
	/**
	 * Gets the name of the player.
	 * @return the name of the player
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the amount of money the player currently has.
	 * @return the player's current money
	 */
	public double getCurrentMoney() {
		return currentMoney;
	}
	/**
	 * Gets the total amount of money the player has earned between the game start and the next time
	 * the player chooses to reset their empire.
	 * @return the total money earned by the player
	 */
	public double getMoneyEarned() {
		return moneyEarned;
	}
	/**
	 * Gets the player's current money as a formatted string.
	 * @return the player's current money as a formatted string
	 */
	public String getMoneyAsString() {
		DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
		return currency.format(currentMoney);
	}
	/*
	 * Checks whether the business/special item is affordable, then spends the player's 
	 * money in exchange for the target item.
	 */
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
