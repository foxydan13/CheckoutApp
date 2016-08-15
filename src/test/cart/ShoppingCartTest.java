package test.cart;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cart.ShoppingCart;
import stock.Apple;
import stock.Item;
import stock.Orange;

public class ShoppingCartTest {

	private ShoppingCart cart;
	private Item apple;
	private Item orange;

	@Before
	public void setUp() throws Exception {
		cart = new ShoppingCart();
		apple = new Apple();
		orange = new Orange();
	}

	@After
	public void tearDown() throws Exception {
		cart = null;
		apple = null;
		orange = null;
	}

	@Test
	public void testShoppingCartEmptyOnCreation() {
		assertTrue(cart.getProducts().size() == 0);
		assertTrue(cart.getBasketTotal() == 0.0);
	}

	@Test
	public void testAddItem() {
		cart.addItem(apple);

		assertTrue(cart.getProducts().size() == 1);
		assertTrue(cart.getBasketTotal() == 0.60);
	}

	@Test
	public void testAddItems() {
		cart.addItem(apple);
		cart.addItem(orange);
		cart.addItem(apple);

		assertTrue(cart.getProducts().size() == 3);
		assertTrue(cart.getBasketTotal() == 1.45);
	}

	@Test
	public void testAppleOffer() {
		assertTrue(cart.getAppleOfferTotal(1) == 0.6);
		assertTrue(cart.getAppleOfferTotal(2) == 0.6);
		assertTrue(cart.getAppleOfferTotal(3) == 1.2);
	}

	@Test
	public void testOrangeOffer() {
		assertTrue(cart.getOrangeOfferTotal(2) == 0.5);
		assertTrue(cart.getOrangeOfferTotal(3) == 0.5);
		assertTrue(cart.getOrangeOfferTotal(4) == 0.75);
	}

	@Test
	public void testBasketTotal2AppleDiscount() {
		cart.addItem(apple);
		cart.addItem(apple);

		assertTrue(cart.getProducts().size() == 2);
		assertTrue(cart.getBasketTotal() == 1.2);
		assertTrue(cart.getDiscountedBasketTotal() == 0.6);
	}

	@Test
	public void testBasketTotal3AppleDiscount() {
		cart.addItem(apple);
		cart.addItem(apple);
		cart.addItem(apple);

		assertTrue(cart.getProducts().size() == 3);
		assertTrue(cart.getBasketTotal() == 1.8);
		assertTrue(cart.getDiscountedBasketTotal() == 1.2);
	}

	@Test
	public void testBasketTotal2OrangeDiscount() {
		cart.addItem(orange);
		cart.addItem(orange);

		assertTrue(cart.getProducts().size() == 2);
		assertTrue(cart.getBasketTotal() == 0.5);
		assertTrue(cart.getDiscountedBasketTotal() == 0.5);
	}

	@Test
	public void testBasketTotal3OrangeDiscount() {
		cart.addItem(orange);
		cart.addItem(orange);
		cart.addItem(orange);

		assertTrue(cart.getProducts().size() == 3);
		assertTrue(cart.getBasketTotal() == 0.75);
		assertTrue(cart.getDiscountedBasketTotal() == 0.5);
	}

	@Test
	public void testBasketTotal4OrangeDiscount() {
		cart.addItem(orange);
		cart.addItem(orange);
		cart.addItem(orange);
		cart.addItem(orange);

		assertTrue(cart.getProducts().size() == 4);
		assertTrue(cart.getBasketTotal() == 1.0);
		assertTrue(cart.getDiscountedBasketTotal() == 0.75);
	}

}
