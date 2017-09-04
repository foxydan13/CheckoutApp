package cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stock.Apple;
import stock.Item;
import stock.Orange;

public class ShoppingCart {

	private Map<Item, Integer> products;

    private static final Item ORANGE = new Orange();
    private static final Item APPLE = new Apple();

	public ShoppingCart() {
		this.products = new HashMap<>();
	}

	public Map<Item, Integer> getProducts() {
		return products;
	}

	public void addItem(Item item) {

		products.computeIfPresent(item, (key, oldVal) -> oldVal + 1);
		products.putIfAbsent(item,1);

	}

	public double getBasketTotal() {
		double total = 0.0;
        int appleCount = products.getOrDefault(APPLE, 0);
        int orangeCount = products.getOrDefault(ORANGE, 0);
		DecimalFormat df = new DecimalFormat("####0.00");

        total += appleCount * APPLE.getPrice();
        total += orangeCount * ORANGE.getPrice();

		return Double.parseDouble(df.format(total));
	}

	public double getDiscountedBasketTotal() {
		double discountedTotal = 0.0;
		int appleCount = products.getOrDefault(APPLE, 0);
		int orangeCount = products.getOrDefault(ORANGE, 0);
		DecimalFormat df = new DecimalFormat("####0.00");

		discountedTotal += getAppleOfferTotal(appleCount);
		discountedTotal += getOrangeOfferTotal(orangeCount);

		return Double.parseDouble(df.format(discountedTotal));
	}
	
	public double getAppleOfferTotal(int numberOfApples){
		double valueAfterOfferApplied = 0.0;
		valueAfterOfferApplied += (numberOfApples / 2) * APPLE.getPrice();
		valueAfterOfferApplied += (numberOfApples % 2) * APPLE.getPrice();
		
		return valueAfterOfferApplied;
	}
	
	public double getOrangeOfferTotal(int numberOfOranges){
		double valueAfterOfferApplied = 0.0;
		valueAfterOfferApplied += (numberOfOranges / 3) * (ORANGE.getPrice());
		valueAfterOfferApplied += (numberOfOranges % 3) * ORANGE.getPrice();

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
					cart.addItem(ORANGE);
					System.out.println(Constants.addSuccessText + df.format(cart.getBasketTotal())
							+ Constants.currentDiscountTotalText + df.format(cart.getDiscountedBasketTotal())
							+ Constants.enterItemText);
				} else {
					if (input.toUpperCase().equals("APPLE")) {

						cart.addItem(APPLE);
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
