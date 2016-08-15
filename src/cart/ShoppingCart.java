package cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import stock.Apple;
import stock.Item;
import stock.Orange;

public class ShoppingCart {

	private List<Item> products;

	public ShoppingCart() {
		this.products = new ArrayList<Item>();
	}

	public List<Item> getProducts() {
		return products;
	}

	public void addItem(Item item) {
		products.add(item);
	}

	public double getBasketTotal() {
		double total = 0.0;
		DecimalFormat df = new DecimalFormat("####0.00");

		for (Item item : products) {
			total += item.getPrice();
		}

		return Double.parseDouble(df.format(total));
	}

	public double getDiscountedBasketTotal() {
		double discountedTotal = 0.0;
		int appleCount = 0;
		int orangeCount = 0;
		DecimalFormat df = new DecimalFormat("####0.00");

		for (Item item : products) {

			if (item instanceof Apple) {
				appleCount += 1;
			} else {
				if (item instanceof Orange) {
					orangeCount += 1;
				}
			}
		}
		
		discountedTotal += getAppleOfferTotal(appleCount);
		discountedTotal += getOrangeOfferTotal(orangeCount);

		return Double.parseDouble(df.format(discountedTotal));
	}
	
	public double getAppleOfferTotal(int numberOfApples){
		double valueAfterOfferApplied = 0.0;
		valueAfterOfferApplied += (numberOfApples / 2) * Constants.applePrice;
		valueAfterOfferApplied += (numberOfApples % 2) * Constants.applePrice;
		
		return valueAfterOfferApplied;
	}
	
	public double getOrangeOfferTotal(int numberOfOranges){
		double valueAfterOfferApplied = 0.0;
		valueAfterOfferApplied += (numberOfOranges / 3) * (Constants.orangePrice * 2);
		valueAfterOfferApplied += (numberOfOranges % 3) * Constants.orangePrice;

		return valueAfterOfferApplied;
	}

	public static void main(String[] args) {

		ShoppingCart cart = new ShoppingCart();
		DecimalFormat df = new DecimalFormat("####0.00");

		System.out.println(Constants.introText);
		System.out.println(Constants.enterItemText);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			while (true) {
				String input = br.readLine().toString();

				if (input.toUpperCase().equals("ORANGE")) {
					Item orange = new Orange();
					cart.addItem(orange);
					System.out.println(Constants.addSuccessText + df.format(cart.getBasketTotal())
							+ Constants.currentDiscountTotalText + df.format(cart.getDiscountedBasketTotal())
							+ Constants.enterItemText);
				} else {
					if (input.toUpperCase().equals("APPLE")) {
						Item apple = new Apple();
						cart.addItem(apple);
						System.out.println(Constants.addSuccessText + df.format(cart.getBasketTotal())
								+ Constants.currentDiscountTotalText + df.format(cart.getDiscountedBasketTotal())
								+ Constants.enterItemText);
					} else {
						if (input.toUpperCase().equals("DONE")) {

							System.out.println(Constants.basketTotalText + df.format(cart.getBasketTotal()));
							System.out.println(Constants.basketTotalAfterDiscountTest
									+ df.format((cart.getDiscountedBasketTotal())));
							System.out.println(Constants.goodbyeText);
							System.exit(0);
						} else {
							System.out.println(Constants.errorText);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Something went wrong - ");
			e.printStackTrace();
		}
	}
}
