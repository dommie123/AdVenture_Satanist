package assets;

import java.io.Serializable;

public class HiddenMultiplier extends Multiplier implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 612893461279834L;
	private int businessesRequired;
	private boolean triggered;
	
	public HiddenMultiplier(double value, int businessesRequired) {
		super(0, value, "");
		this.businessesRequired = businessesRequired;
		triggered = false;
	}
	
	public void activateTrigger() {
		triggered = true;
	}
	
	public int getBusinessesRequired() {
		return businessesRequired;
	}
	
	public boolean isTriggered() {
		return triggered;
	}
}
