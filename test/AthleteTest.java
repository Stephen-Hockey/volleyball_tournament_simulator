package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.Athlete;
import main.Item;

public class AthleteTest {
	
	@Test
	public void toStringTest() {
		Athlete testAthlete = new Athlete();
		testAthlete.setName("John Smith");
		assertEquals("John Smith", testAthlete.toString());
	}
	
	
	
	
	@Test
	public void addItemTest() {
		Athlete testAthlete = new Athlete();
		int[] initialAthleteStats = {1, 1, 0};
		testAthlete.setStats(initialAthleteStats);
		Item testItem = new Item();
		int[] initialItemStats = {0, 0, 1};
		testItem.setStats(initialItemStats);
		testAthlete.addItem(testItem);
		assertArrayEquals(new int[]{1, 1, 1}, testAthlete.getStats());
	}
	
	@Test
	public void getAvgStatTest() {
		Athlete testAthlete = new Athlete();
		int[] initialAthleteStats = {4, 5, 6};
		testAthlete.setStats(initialAthleteStats);
		assertEquals(5, testAthlete.getAvgStat());
	}
	
	@Test
	public void generateAthleteTypeTest() {
		Athlete testAthlete = Athlete.generateAthlete(5);
		assertEquals(Athlete.class, testAthlete.getClass());
	}
	
	@Test
	public void generateAthleteNameNotNullTest() {
		Athlete testAthlete = Athlete.generateAthlete(5);
		assertNotEquals(null, testAthlete.getName());
	}
	
	@Test
	public void generateAthletePricesTest() {
		Athlete testAthlete = Athlete.generateAthlete(5);
		assertTrue(testAthlete.getPrice() > testAthlete.getSellPrice());
	}

	@Test
	public void generateAthleteStatsRangeTest() {
		Athlete testAthlete = Athlete.generateAthlete(5);
		assertTrue((0 <= testAthlete.getStats()[0]) && (testAthlete.getStats()[0] <= 99));
	}
	
	@Test
	public void generateAthleteDescriptionNotNullTest() {
		Athlete testAthlete = Athlete.generateAthlete(5);
		assertNotEquals(null, testAthlete.getDescription());
	}
	
	@Test
	public void generateAthletesTest() {
		ArrayList<Athlete> testAthletes = Athlete.generateAthletes(22, 0);
		assertEquals(22, testAthletes.size());
	}
}
