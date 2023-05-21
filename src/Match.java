import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Match {
    private ArrayList<Integer> matchStaminas;
    private Team opposingTeam;
    private int setNumber;
    private int[][] scores;
    private int[] setScore;
    private int faceoffIndex;
    
    public Match(Team _opposingTeam) {
        matchStaminas = new ArrayList<Integer>();
        for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
            matchStaminas.add(athlete.getEffectiveStats()[0]);
        }
        opposingTeam = _opposingTeam;
        setNumber = 0;
        scores = new int[5][2];
        setScore = new int[2];
        faceoffIndex = 0;
    }

    public Team getOpposingTeam() {
        return opposingTeam;
    }

    public int[][] getScores() {
        return scores;
    }

    public void setScores(int[][] _scores) {
        scores = _scores;
    }

    public int getSetNumber() {
        return setNumber;
    }

    public void setSetNumber(int _setNumber) {
        setNumber = _setNumber;
    }

    public int[] getSetScore() {
        return setScore;
    }

    public void setSetScore(int[] _setScore) {
        setScore = _setScore;
    }
    
    public String getScoreString() {
    	return String.format("%3s - %-3s", scores[setNumber][0], scores[setNumber][1]);
    }
    
    public String getSetScoreString() {
    	return String.format("%2s - %-2s", setScore[0], setScore[1]);
    }

    public ArrayList<Integer> getMatchStaminas() {
		return matchStaminas;
	}
    
    public int getFaceoffIndex() {
		return faceoffIndex;
	}
    
    public boolean playPoint() {
    	
        int winner = 0;
        int chanceOfWinning = 5;

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getEffectiveStats()[1] >= opposingTeam.get(faceoffIndex).getStats()[2]) {
            chanceOfWinning += 3;
        } else {
            chanceOfWinning -= 1;
        }

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getEffectiveStats()[2] >= opposingTeam.get(faceoffIndex).getStats()[1]) {
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

    public int getMatchRating() {
        int playerRating = (100 * GameEnvironment.getDifficulty() + 1) * (setScore[0] - setScore[1]);

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

    public int getMoneyWon() {

        if (!matchWon()) {
            return 50;
        }
        return 150 + 10 * GameEnvironment.getWeek() - 20 * GameEnvironment.getDifficulty();
    }

    public boolean matchWon() {
        return (setScore[0] == 3);
    }

}
