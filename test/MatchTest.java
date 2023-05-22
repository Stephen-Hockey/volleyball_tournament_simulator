package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.GameEnvironment;
import main.Match;
import main.Team;

public class MatchTest {

	@Test
	public void getScoreStringTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{25,0},{25,0},{17,18},{0,0},{0,0}});
		testMatch.setSetNumber(2);
		assertEquals(" 17 - 18 ", testMatch.getScoreString());
	}
	
	@Test
	public void getSetScoreStringTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {3,1});
		assertEquals(" 3 - 1 ", testMatch.getSetScoreString());
	}
	
	@Test
	public void playPointIncrementsScoreTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.getMatchStaminas().set(0, 99);		
		testMatch.playPoint();
		assertTrue((testMatch.getScores()[0][0] + testMatch.getScores()[0][1]) == 1);
	}
	
	@Test
	public void playPointDecreasesStaminaTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.getMatchStaminas().set(0, 99);
		int staminaBefore = testMatch.getMatchStaminas().get(0);
		testMatch.playPoint();
		int staminaAfter = testMatch.getMatchStaminas().get(0);
		assertTrue(staminaBefore > staminaAfter);
	}
	
	@Test
	public void playPointInjuryOccursTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.getMatchStaminas().set(0, 0);
		assertFalse(testMatch.playPoint());
	}
	
	@Test
	public void playPointIfInjuryNoPointsAwardedTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.getMatchStaminas().set(0, 0);
		testMatch.playPoint();
		assertTrue((testMatch.getScores()[0][0] == 0) && (testMatch.getScores()[0][1] == 0));
	}
	
	@Test
	public void checkIfSetOverTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{25,23},{0,0},{0,0},{0,0},{0,0}});
		assertTrue(testMatch.checkIfSetOver());
	}
	
	@Test
	public void checkIfSetOverNotOverTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{26,25},{0,0},{0,0},{0,0},{0,0}});
		assertFalse(testMatch.checkIfSetOver());
	}	
	
	@Test
	public void checkIfSetOverFifthSetTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{25,0},{25,0},{0, 25},{0,25},{15,13}});
		testMatch.setSetNumber(4);
		assertTrue(testMatch.checkIfSetOver());
	}
	
	@Test
	public void checkIfSetOverUpdatesPropertiesOnWinTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{25,0},{0,0},{0,0},{0,0},{0,0}});
		testMatch.checkIfSetOver();
		assertTrue((testMatch.getSetScore()[0] == 1) && (testMatch.getSetNumber() == 1) && (testMatch.getFaceoffIndex() == 0));
	}
	
	@Test
	public void checkIfSetOverUpdatesPropertiesOnLossTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setScores(new int[][] {{0,25},{0,0},{0,0},{0,0},{0,0}});
		testMatch.checkIfSetOver();
		assertTrue((testMatch.getSetScore()[1] == 1) && (testMatch.getSetNumber() == 1) && (testMatch.getFaceoffIndex() == 0));
	}
	
	@Test
	public void getMatchRatingMinimumZeroTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {0,3});
		testMatch.setScores(new int[][] {{0,25},{0,25},{0,25},{0,0},{0,0}});
		testMatch.setSetNumber(3);
		
		assertEquals(0, testMatch.getMatchRating());
	}
	
	@Test
	public void getMatchRatingWinTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {3,2});
		testMatch.setScores(new int[][] {{25,23},{25,23},{0,25},{0,25},{15,13}});
		testMatch.setSetNumber(5);
		GameEnvironment.setDifficulty(0);
		
		assertTrue(testMatch.getMatchRating() > 0);
	}
	
	@Test
	public void getMatchWonWinTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {2,3});
		
		assertFalse(testMatch.matchWon());
	}
	
	@Test
	public void getMatchWonLossTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {3,2});
		
		assertTrue(testMatch.matchWon());
	}
	
	@Test
	public void getMoneyWonWinTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {3, 2});
		GameEnvironment.setWeek(1);
		GameEnvironment.setDifficulty(2);
		
		assertTrue(testMatch.getMoneyWon() > 0);
	}
	
	@Test
	public void getMoneyWonLossTest() {
		GameEnvironment.setPlayerTeam(Team.generateTeam(0));
		Match testMatch = new Match(Team.generateTeam(0));
		testMatch.setSetScore(new int[] {2,3});
		
		assertEquals(0, testMatch.getMoneyWon());
	}
}
