package assets;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

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

	public SpeedBoost(double value, long length, double cost, String name) {
		this.value = value;
		this.length = length;
		this.cost = cost;
		this.name = name;
		isPurchased = false;
	}

	// Sets expire date to [length / 24] days from the time of purchase.
	public void longSetPurchased() {
		isPurchased = true;
		expireDate = LocalDate.now().plusDays(length / 24);
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(length);
	}

	// Sets expire time to [length] hours from now.
	public void setPurchased() {
		if (isPurchased == true) {
			isPurchased = false;
			expireTime = LocalTime.now();
			expireDate = LocalDate.now();
			return;
		}
		isPurchased = true;
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusHours(length);
		if (expireTime.isBefore(LocalTime.now())) 
			expireDate = LocalDate.now().plusDays(1);
		else
			expireDate = LocalDate.now();
	}
	
	// Sets expire time to [length * 60] minutes from now (Same as setPurchased() except more precise)
	public void shortSetPurchased() {
		isPurchased = true;
		expireTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).plusMinutes(length * 60);
		if (expireTime.isBefore(LocalTime.now())) 
			expireDate = LocalDate.now().plusDays(1);
		else
			expireDate = LocalDate.now();
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public double getValue() {
		return value;
	}

	public double getLength() {
		return length;
	}

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
	
	public void checkTime() {
		try {
			if (LocalTime.now().isAfter(expireTime) && LocalDate.now().isAfter(expireDate)) 
				expire();
		} catch (NullPointerException e) {
			if (LocalTime.now().isAfter(expireTime)) {
				expire();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public String toString() {
		return name + " (" + currency.format(cost) + ")";
	}

	private void expire() {
		isPurchased = false;
		expireTime = null;
		expireDate = null;
	}
}
