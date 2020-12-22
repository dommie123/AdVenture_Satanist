package assets;

import java.io.Serializable;

public class HiddenBoost extends SpeedBoost implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 872895072380L;
	private int businessesRequired;
	private boolean triggered;
	
	public HiddenBoost(double value, int businessesRequired) {
		super(value, Long.MAX_VALUE, 0, "");
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
