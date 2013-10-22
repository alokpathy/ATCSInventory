
/**
 * A specific item in the inventory.
 * @author Alok Tripathy, Cody Yang.
 *
 */
public class InventoryItem {

	private int itemID, itemCount;
	
	/**
	 * Constructs item to be placed in inventory.
	 * @param id       - I.D number of item.
	 * @param count    - Count number of item.
	 */
	public InventoryItem(int id, int count) {
		itemID = id;
		itemCount = count;
	}
	
	/**
	 * Gets the item's I.D number.
	 * @return         - Item's I.D number.
	 */
	public int getItemID() {
		return itemID;
	}
	
	/**
	 * Gets the item's count number.
	 * @return         - Item's count number.
	 */
	public int getItemCount() {
		return itemCount;
	}
}