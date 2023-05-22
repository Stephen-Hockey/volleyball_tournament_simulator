package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import main.Item;

public class ItemTest {

	@Test
	public void toStringTest() {
		Item testItem = new Item();
		testItem.setName("Shoes");
		assertEquals("Shoes", testItem.toString());
	}
	
	@Test
	public void generateItemTypeTest() {
		Item testItem = Item.generateItem();
		assertEquals(Item.class, testItem.getClass());
	}
	
	@Test
	public void generateitemNameNotNullTest() {
		Item testItem = Item.generateItem();
		assertNotEquals(null, testItem.getName());
	}
	
	@Test
	public void generateItemPricesTest() {
		Item testItem = Item.generateItem();
		assertTrue(testItem.getPrice() > testItem.getSellPrice());
	}

	@Test
	public void generateItemStatsRangeTest() {
		Item testItem = Item.generateItem();
		assertTrue((0 <= testItem.getStats()[0]) && (testItem.getStats()[0] <= 20));
	}
	
	@Test
	public void generateItemDescriptionNotNullTest() {
		Item testItem = Item.generateItem();
		assertNotEquals(null, testItem.getDescription());
	}
	
	@Test
	public void generateItemsTest() {
		ArrayList<Item> testItems = Item.generateItems(10);
		assertEquals(10, testItems.size());
	}
}
