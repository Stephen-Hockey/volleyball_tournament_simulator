package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Athlete;
import main.Team;

public class TeamTest {

	@Test
	public void sizeTest() {
		Team testTeam = new Team();
		
		testTeam.getPlayers().add(new Athlete());
		testTeam.getPlayers().add(new Athlete());
		testTeam.getPlayers().add(new Athlete());
		
		assertEquals(testTeam.getPlayers().size(), testTeam.size());
	}
	
	@Test
	public void addTest() {
		Team testTeam = new Team();
		testTeam.add(new Athlete());
		
		assertEquals(1, testTeam.size());
	}
	
	@Test
	public void removeAthleteTest() {
		Team testTeam = new Team();
		Athlete testAthlete = Athlete.generateAthlete(0);
		testTeam.add(testAthlete);
		testTeam.remove(testAthlete);
		assertEquals(0, testTeam.size());
	}
	
	@Test
	public void getAthleteTest() {
		Team testTeam = new Team();
		Athlete testAthlete0 = new Athlete();
		Athlete testAthlete1 = new Athlete();
		testAthlete0.setName("Jeff");
		testAthlete1.setName("Bob");
		testTeam.add(testAthlete0);
		testTeam.add(testAthlete1);
		
		Athlete gotAthlete = testTeam.get(0);
		
		assertEquals("Jeff", gotAthlete.getName());
	}
	
	@Test
	public void removeIndexTest() {
		Team testTeam = new Team();
		Athlete testAthlete0 = new Athlete();
		Athlete testAthlete1 = new Athlete();
		testAthlete0.setName("Jeff");
		testAthlete1.setName("Bob");
		testTeam.add(testAthlete0);
		testTeam.add(testAthlete1);
		testTeam.remove(0);
		
		assertEquals("Bob", testTeam.get(0).getName());
	}
	
	@Test
	public void isEmptyTest() {
		Team testTeam = new Team();
		
		assertTrue(testTeam.isEmpty());
	}
	
	@Test
	public void swapTest() {
		Team testTeam = new Team();
		Athlete testAthlete0 = new Athlete();
		Athlete testAthlete1 = new Athlete();
		testAthlete0.setName("Jeff");
		testAthlete1.setName("Bob");
		testTeam.add(testAthlete0);
		testTeam.add(testAthlete1);
		
		testTeam.swap(0, 1);
		
		
		assertTrue(("Bob" == testTeam.get(0).getName()) && ("Jeff" == testTeam.get(1).getName()));
	}
	
	@Test
	public void getBestAthleteTest() {
		Team testTeam = new Team();
		Athlete badAthlete = new Athlete();
		Athlete goodAthlete = new Athlete();
		badAthlete.setStats(new int[] {0,0,0});
		goodAthlete.setStats(new int[] {99,99,99});
		testTeam.add(badAthlete);
		testTeam.add(goodAthlete);
		assertEquals(goodAthlete, testTeam.getBestAthlete());
	}
	
	@Test
	public void equalAthletesGetBestAthleteTest() {
		Team testTeam = new Team();
		Athlete testAthlete = Athlete.generateAthlete(0);
		
		testTeam.add(testAthlete);
		testTeam.add(testAthlete);
		testTeam.add(testAthlete);
		
		assertEquals(testTeam.get(0), testTeam.getBestAthlete());
	}
	
	@Test
	public void getTeamAverageStatsTest() {
		Team testTeam = new Team();
		Athlete testAthlete0 = new Athlete();
		Athlete testAthlete1 = new Athlete();
		Athlete testAthlete2 = new Athlete();
		testAthlete0.setStats(new int[] {0, 1, 70});
		testAthlete1.setStats(new int[] {0, 2, 60});
		testAthlete2.setStats(new int[] {0, 3, 20});
		
		testTeam.add(testAthlete0);
		testTeam.add(testAthlete1);
		testTeam.add(testAthlete2);
		
		int[] avgStats = testTeam.getTeamAverageStats();
		
		assertTrue((avgStats[0] == 0) && (avgStats[1] == 2) && (avgStats[2] == 50));
	}
	
	@Test
	public void generateTeamTypeTest() {
		Team testTeam = Team.generateTeam(5);
		assertEquals(Team.class, testTeam.getClass());
	}
	
	@Test
	public void generateTeamNameNotNullTest() {
		Team testTeam = Team.generateTeam(5);
		assertNotEquals(null, testTeam.getTeamName());
	}
	
	@Test
	public void generateTeamSizeTest() {
		Team testTeam = Team.generateTeam(5);
		assertEquals(10, testTeam.size());
	}
}
