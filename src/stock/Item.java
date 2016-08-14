package stock;

public abstract class Item {

	private String itemName;
	private double price;

	public Item(String itemName, double price) {
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public double getPrice() {
		return price;
	}

}
