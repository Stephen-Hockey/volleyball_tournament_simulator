package main;
import java.util.ArrayList;
import java.util.Random;


/**
 * The Match class describes a match against an opposing team.
 * 
 *
 * @author Lachlan Stewart and Stephen Hockey
 * @version 1.1, May 2023.
 */
public class Match {
	
	/**
	 * An ArrayList to keep track of the user's athlete's staminas in the current match.
	 */
    private ArrayList<Integer> matchStaminas;
    
    /**
     * The opposing team
     */
    private Team opposingTeam;
    
    /**
     * The current set, 0-indexed
     */
    private int setNumber;
    
    /**
     * A 5x2 integer array that holds all five set's scores.
     * An unplayed set will be represented by the array [0, 0]
     */
    private int[][] scores;
    
    /**
     * The overall set score e.g [2, 3], in which case,
     * the opposing team won 3 sets to 2.
     */
    private int[] setScore;
    
    /**
     * Keeps track of what index in each team of athlete should be facing off.
     * Increments by 1 with every point.
     * Initialised to 0 at the beginning of each set.
     */
    private int faceoffIndex;
        
    /**
     * Constructs a new Match object at the beginning of the match
     * with the given opposingTeam.
     * @param newOpposingTeam the opposing team
     */
    public Match(Team newOpposingTeam) {
        matchStaminas = new ArrayList<Integer>();
        for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
            matchStaminas.add(athlete.getStats()[0]);
        }
        opposingTeam = newOpposingTeam;
        setNumber = 0;
        scores = new int[5][2];
        setScore = new int[2];
        faceoffIndex = 0;
    }
    
    /**
     * Getter for the opposing team
     * @return the opposing team
     */
    public Team getOpposingTeam() {
        return opposingTeam;
    }
    
    /**
     * Setter for the opposing team 
     * @param newOpposingTeam the new opposing team
     */
    public void setOpposingTeam(Team newOpposingTeam) {
		opposingTeam = newOpposingTeam;
	}
    
    /**
     * Getter for the 2D integer array of scores
     * @return 2D integer array of scores
     */
    public int[][] getScores() {
        return scores;
    }

    /**
     * Setter for the 2D integer array of scores
     * @param newScores the new scores
     */
    public void setScores(int[][] newScores) {
        scores = newScores;
    }
    
    /**
     * Getter for the current set number
     * @return the current set number
     */
    public int getSetNumber() {
        return setNumber;
    }

    /**
     * Setter for the current set number
     * @param newSetNumber the new set number
     */
    public void setSetNumber(int newSetNumber) {
        setNumber = newSetNumber;
    }
    
    /**
     * Getter for the set score
     * @return the set score
     */
    public int[] getSetScore() {
        return setScore;
    }
    
    /**
     * Setter for the set score
     * @param newSetScore the new set score
     */
    public void setSetScore(int[] newSetScore) {
        setScore = newSetScore;
    }
    
    /**
     * Getter for the match stamina ArrayList
     * @return the match stamina ArrayList
     */
    public ArrayList<Integer> getMatchStaminas() {
		return matchStaminas;
	}
    
    /**
     * Setter for the match staminas ArrayList
     * @param newMatchStaminas the new match staminas ArrayList
     */
    public void setMatchStaminas(ArrayList<Integer> newMatchStaminas) {
		matchStaminas = newMatchStaminas;
	}
    
    /**
     * Getter for the faceoff index
     * @return the faceoff index
     */
    public int getFaceoffIndex() {
		return faceoffIndex;
	}
    
    /**
     * Setter for the faceoff index
     * @param newFaceoffIndex the new faceoff index
     */
    public void setFaceoffIndex(int newFaceoffIndex) {
		faceoffIndex = newFaceoffIndex;
	}
    
    /**
     * Gets the Match object's current set's score in a formatted String, e.g
     *   3 - 16
     * @return the current set's score as a String
     */
    public String getScoreString() {
    	return String.format("%3s - %-3s", scores[setNumber][0], scores[setNumber][1]);
    }
    
    /**
     * Gets the Match object's set score as a formatted String, e.g
     *  2 - 0
     * @return the set score as a String
     */
    public String getSetScoreString() {
    	return String.format("%2s - %-2s", setScore[0], setScore[1]);
    }
    
    /**
     * Compares the players in the faceoff and accordingly determines the winner of the point,
     * and decreases the user's athlete's stamina stat - by 1 if point won, and by 2 if point lost.
     * @return false if an injury occurred that needs to be dealt with by what calls this method.
     */
    public boolean playPoint() {
    	
        int winner = 0;
        int chanceOfWinning = 5;

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getStats()[1] >= opposingTeam.get(faceoffIndex).getStats()[2]) {
            chanceOfWinning += 3;
        } else {
            chanceOfWinning -= 1;
        }

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getStats()[2] >= opposingTeam.get(faceoffIndex).getStats()[1]) {
            chanceOfWinning += 1;
        } else {
            chanceOfWinning -= 3;
        }

        Random r = new Random();
        if (r.nextInt(10) >= chanceOfWinning) {
            matchStaminas.set(faceoffIndex, matchStaminas.get(faceoffIndex) - 1);
            winner = 1;
        }

        matchStaminas.set(faceoffIndex, matchStaminas.get(faceoffIndex) - 1);

        if (matchStaminas.get(faceoffIndex) <= 0) {
            matchStaminas.set(faceoffIndex, 0);
            GameEnvironment.getPlayerTeam().get(faceoffIndex).getStats()[0] = 0;
            
            return false;
        }
        
        if (winner == 0) {
        	scores[setNumber][0]++;
        } else {
        	scores[setNumber][1]++;
        }
        
        faceoffIndex = (faceoffIndex + 1) % 7;
        
        return true;
    }
    
    /**
     * Checks if either team has won the current set, then updates the scores accordingly if so.
     * @return true if current set has been won/lost
     */
    public boolean checkIfSetOver() {
    	
    	int winningScore = 25;
    	if (setNumber == 4) {
    		winningScore = 15;
    	}
    	
    	if ((scores[setNumber][0] >= winningScore) && (scores[setNumber][0] >= scores[setNumber][1] + 2)) {
            setScore[0]++;
            setNumber++;
            faceoffIndex = 0;
            return true;
        } else if ((scores[setNumber][1] >= winningScore) && (scores[setNumber][1] >= scores[setNumber][0] + 2)) {
            setScore[1]++;
            setNumber++;
            faceoffIndex = 0;
            return true;
        }
    	return false;
    }

    /**
     * Gets a rating value for the match that depends on game difficulty,
     * and how well the match went.
     * @return the match rating
     */
    public int getMatchRating() {
        int playerRating = (100 * (GameEnvironment.getDifficulty() + 1)) * (setScore[0] - setScore[1]);

        if (playerRating < 0) {
            playerRating = 0;
        }

        for (int i = 0; i < setNumber; i++) {
            playerRating += scores[i][0] - scores[i][1];
        }

        if (playerRating < 0) {
            playerRating = 0;
        }

        return playerRating;
    }

    /**
     * Gets the money won from the match. $0 if match lost. If match won, the amount depends on game difficulty,
     * and how good the set score is.
     * @return money won
     */
    public int getMoneyWon() {

        if (!matchWon()) {
            return 0;
        }
        return 150 + 10 * GameEnvironment.getWeek() - 20 * GameEnvironment.getDifficulty();
    }

    /**
     * Checks if the user won this match.
     * @return true if user has won 3 sets
     */
    public boolean matchWon() {
        return (setScore[0] == 3);
    }

}
