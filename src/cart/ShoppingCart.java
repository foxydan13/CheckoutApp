package cart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

		for (Item item : products) {
			total += item.getPrice();
		}

		return total;
	}

	public static void main(String[] args) {

		ShoppingCart cart = new ShoppingCart();

		System.out.println(Constants.introText);
		System.out.println(Constants.enterItemText);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {

			while (true) {

				String input = br.readLine().toString();

				if (input.toUpperCase().equals("ORANGE")) {
					Item orange = new Orange();
					cart.addItem(orange);
					System.out.println(Constants.addSuccessText + cart.getBasketTotal()
							+ Constants.enterItemText);
				} else {
					if (input.toUpperCase().equals("APPLE")) {
						Item apple = new Apple();
						cart.addItem(apple);
						System.out.println(Constants.addSuccessText + cart.getBasketTotal()
								+ Constants.enterItemText);
					} else {
						if (input.toUpperCase().equals("DONE")) {

							System.out.println(Constants.basketTotalText + cart.getBasketTotal());
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
