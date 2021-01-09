package assets;

import java.io.Serializable;

public class HiddenMultiplier extends Multiplier implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 612893461279834L;
	private int businessesRequired;
	
	public HiddenMultiplier(double value, int businessesRequired) {
		super(0, value, "");
		this.businessesRequired = businessesRequired;
	}
	
	// Activates a hidden multiplier by setting its state to "purchased".
	public void activateTrigger() {
		super.setPurchased();		
	}
	
	public int getBusinessesRequired() {
		return businessesRequired;
	}
	
	// This method is meant only to make the code easier to read.
	public boolean isTriggered() {
		return super.isPurchased();
	}
}
