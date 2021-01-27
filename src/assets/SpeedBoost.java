package assets;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
/**
 * This is the SpeedBoost class. When a player activates this item, it boosts the speed at which 
 * every business generates revenue for a limited time. Once that time is up, the boost will expire,
 * rendering it inactive until the player purchases it again.
 * @author Dominick Wiley
 *
 */
public class SpeedBoost implements Purchasable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2104589478341101987L;
	private double value; // The value of the multiplier (i.e. 2 is doubled, 3 is tripled)
	private long length; // The length of time (could be minutes or hours depending on method used)
	private double cost;
	private String name;
	private boolean isPurchased;

	private LocalDate expireDate; // The date at which the speed boost will expire
	private LocalTime expireTime; // The time at which the speed boost will expire
	
	private final DecimalFormat currency = (DecimalFormat) NumberFormat.getCurrencyInstance();
	
	private Timer currentThread;

	/**
	 * Creates the <code>SpeedBoost</code> and initializes its wait time reduction value, length of activity,
	 * cost in "hell dollars", and name of the boost.
	 * @param value - the value of the wait time reduction
	 * @param length - the length (in hours) of the boost
	 * @param cost - the amount of money the boost costs
	 * @param name - the name of the boost
	 */
	public SpeedBoost(double value, long length, double cost, String name) {
		this.value = value;
		this.length = length;
		this.cost = cost;
		this.name = name;
		isPurchased = false;
	}

	/**
	 *  Sets expire date to <code>length / 24</code> days from the time of purchase.
	 *  @see setPurchased()
	 */
	public void longSetPurchased() {
		if (isPurchased == true) {
			expire();
			return;
		}
		isPurchased = true;
		expireDate = LocalDate.now().plusDays(length / 24);
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(length);
		
		ActionListener r = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				checkTime();
				
			}
		};
		
		currentThread = new Timer(100, r);
		currentThread.start();
	}

	/**
	 *  Sets expire time to <code>length</code> hours from now. If already purchased, expire.
	 */
	public void setPurchased() {
		if (isPurchased == true) {
			expire();
			return;
		}
		isPurchased = true;
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(length);
		if (expireTime.isBefore(LocalTime.now())) 
			expireDate = LocalDate.now().plusDays(1);
		else
			expireDate = LocalDate.now();
		
		ActionListener r = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				checkTime();
				
			}
		};
		
		currentThread = new Timer(100, r);
		currentThread.start();
	}
	
	/**
	 *  Sets expire time to <code>length * 60</code> minutes from now (Same as setPurchased() except more precise)
	 *  @see setPurchased()
	 */
	public void shortSetPurchased() {
		if (isPurchased == true) {
			expire();
			return;
		}
		isPurchased = true;
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(length * 60);
		if (expireTime.isBefore(LocalTime.now())) 
			expireDate = LocalDate.now().plusDays(1);
		else
			expireDate = LocalDate.now();
		
		ActionListener r = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				checkTime();
				
			}
		};
		
		currentThread = new Timer(100, r);
		currentThread.start();
	}

	/**
	 * Checks whether the player has purchased this speed boost
	 */
	public boolean isPurchased() {
		return isPurchased;
	}
	/**
	 * Gets the value of the wait time reduction
	 * @return the value of the wait time reduction
	 */
	public double getValue() {
		return value;
	}
	/**
	 * Gets the time length of the boost.
	 * @return the length of the boost in hours
	 */
	public double getLength() {
		return length;
	}
	/**
	 * Gets the cost of the boost in "hell dollars".
	 * @return how much (in hell dollars) the boost will cost
	 */
	public double getCost() {
		return cost;
	}
	
	// Methods exclusive for test cases
	public LocalTime getExpireTime() {
		return expireTime;
	}
	
	public LocalDate getExpireDate() {
		return expireDate;
	}
	// End Test Case Methods
	
	// Debug Menu exclusive Methods
	public void setExpireTime(LocalTime time) {
		expireTime = time.truncatedTo(ChronoUnit.SECONDS);
	}
	
	public void setExpireDate(LocalDate date) {
		expireDate = date;
	}
	
	public String getName() {
		return name;
	}
	// End Debug Methods
	
	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}
	/*
	 * Checks to see if the current time is after the boost's expire time.
	 */
	private void checkTime() {
		try {
			if (expireDate != null)
				if (LocalTime.now().isAfter(expireTime) && (LocalDate.now().isAfter(expireDate) || LocalDate.now().isEqual(expireDate))) 
					expire();
			else 
				if (LocalTime.now().isAfter(expireTime))
					expire();
		} catch (NullPointerException e) {
			expire();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	/*
	 * Expires this boost, so it becomes unusable until it is purchased again.
	 */
	private void expire() {
		isPurchased = false;
		expireTime = null;
		expireDate = null;
	}
}
