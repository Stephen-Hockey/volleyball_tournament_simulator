package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import main.Athlete;
import main.GameEnvironment;
import main.Match;
import main.Team;

public class GameEnvironmentTest {

	@Test
	public void addMatchTest() {
		//GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		GameEnvironment.setMatches(new ArrayList<Match>());
		GameEnvironment.addMatch(testMatch);
		assertEquals(1, GameEnvironment.getMatches().size());
	}
	
	@Test
	public void hasFullTeamSevenPlayersTest() {
		Athlete healthyAthlete = new Athlete();
		healthyAthlete.setStats(new int[] {99,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		for (int i = 0; i < 7; i++) {
			GameEnvironment.getPlayerTeam().add(healthyAthlete);
		}
		assertTrue(GameEnvironment.hasFullTeam());
	}
	
	@Test
	public void hasFullTeamSixPlayersTest() {
		Athlete healthyAthlete = new Athlete();
		healthyAthlete.setStats(new int[] {99,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		for (int i = 0; i < 6; i++) {
			GameEnvironment.getPlayerTeam().add(healthyAthlete);
		}
		assertFalse(GameEnvironment.hasFullTeam());
	}
	
	@Test
	public void hasFullTeamUnhealthyPlayersTest() {
		Athlete injuredAthlete = new Athlete();
		injuredAthlete.setStats(new int[] {0,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		for (int i = 0; i < 12; i++) {
			GameEnvironment.getPlayerTeam().add(injuredAthlete);
		}
		assertFalse(GameEnvironment.hasFullTeam());
	}
	
	@Test
	public void hasHealthyStartersSevenPlayersTest() {
		Athlete healthyAthlete = new Athlete();
		healthyAthlete.setStats(new int[] {99,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		for (int i = 0; i < 7; i++) {
			GameEnvironment.getPlayerTeam().add(healthyAthlete);
		}
		assertTrue(GameEnvironment.hasHealthyStarters());
	} 
	
	@Test
	public void hasHealthyStartersSixPlayersTest() {
		Athlete healthyAthlete = new Athlete();
		healthyAthlete.setStats(new int[] {99,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		for (int i = 0; i < 6; i++) {
			GameEnvironment.getPlayerTeam().add(healthyAthlete);
		}
		assertFalse(GameEnvironment.hasHealthyStarters());
	} 

	@Test
	public void hasHealthyStartersInjuredStarterTest() {
		Athlete healthyAthlete = new Athlete();
		Athlete injuredAthlete = new Athlete();
		injuredAthlete.setStats(new int[] {0,99,99});
		healthyAthlete.setStats(new int[] {99,99,99});
		GameEnvironment.setPlayerTeam(new Team());
		GameEnvironment.getPlayerTeam().add(injuredAthlete);
		for (int i = 0; i < 11; i++) {
			GameEnvironment.getPlayerTeam().add(healthyAthlete);
		}
		assertFalse(GameEnvironment.hasHealthyStarters());
	} 	
	@Test
	public void setUpWeekTest() {
		GameEnvironment.setUpWeek();
		assertTrue((!GameEnvironment.getWeeklyGamePlayed())&&(GameEnvironment.getWeek() == 2));
	} 
	
	@Test
	public void setUpWeekSizesTest() {
		GameEnvironment.setUpWeek();
		assertTrue((GameEnvironment.getCurrentWeekMarketAthletes().size() == 5)&&(GameEnvironment.getCurrentWeekMarketItems().size() == 5)&&(GameEnvironment.getCurrentWeekOpposingTeams().size() == 3));
	} 
	
	
}
