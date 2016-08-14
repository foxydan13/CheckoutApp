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

}
