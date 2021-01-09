package assets;

import java.io.Serializable;

public class HiddenBoost extends SpeedBoost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 872895072380L;
	private int businessesRequired;
	
	public HiddenBoost(double value, int businessesRequired) {
		super(value, Long.MAX_VALUE, 0, "");
		this.businessesRequired = businessesRequired;
	}
	
	public void activateTrigger() {
		super.setPurchased();
	}
	
	public int getBusinessesRequired() {
		return businessesRequired;
	}
	
	public boolean isTriggered() {
		return super.isPurchased();
	}
}
