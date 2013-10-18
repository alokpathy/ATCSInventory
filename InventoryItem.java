
public class InventoryItem {

	private int itemID, itemCount;
	public InventoryItem(int id, int count) {
		itemID = id;
		itemCount = count;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public int getItemCount() {
		return itemCount;
	}
}
