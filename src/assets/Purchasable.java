package assets;
/**
 * This is the the Purchasable interface. Every object within the game implementing this
 * interface is considered "for sale" and can be purchased using "hell dollars".
 * @author Dominick Wiley
 *
 */
public interface Purchasable {
	/**
	 * Checks whether the item has been purchased by the player.
	 * @return - true if the player has purchased the item
	 */
	public abstract boolean isPurchased();
	/**
	 * Sets the state of this item to "Purchased". This is typically used to indicate that 
	 * an item has been purchased by the player or to toggle whether the item is active.
	 */
	public abstract void setPurchased();
	/**
	 * Gets the value of the item in "hell dollars". 
	 * @return the cost of the item in "hell dollars". 
	 */
	public abstract double getCost();
}
