package test.stock;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import stock.Apple;
import stock.Item;
import stock.Orange;

public class ItemTest {
	
	private Item orange;
	private Item apple;

	@Before
	public void setUp() throws Exception {
		orange = new Orange();
		apple = new Apple();
	}

	@After
	public void tearDown() throws Exception {
		orange = null;
		apple = null;
	}

	@Test
	public void testGetItemName() {
		assertEquals(orange.getItemName(), "Orange");
		assertEquals(apple.getItemName(), "Apple");
	}
	
	@Test
	public void testGetPrice() {
		assertTrue(orange.getPrice() == 0.25);
		assertTrue(apple.getPrice() == 0.60);
	}

}
