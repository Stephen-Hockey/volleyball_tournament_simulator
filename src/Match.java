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

    public int getFaceoffIndex() {
		return faceoffIndex;
	}
    
    
    public void playMatch() throws NoSubsException {
        boolean matchOver = false;
        while (!matchOver) {
            playSet();

            if ((setNumber > 4) || ((setScore[0] >= 3) || (setScore[1] >= 3))) {
                matchOver = true;
            }
        }
    }

    private void playSet() throws NoSubsException {
        int playerScore = 0;
        int opponentScore = 0;
        int winningScore = 25;
        if (setNumber == 4) {
            winningScore = 15;
        }

        boolean setOver = false;
        while (!setOver) {

            scores[setNumber][pointWinner(playerScore + opponentScore)]++;

            playerScore = scores[setNumber][0];
            opponentScore = scores[setNumber][1];

            if ((playerScore >= winningScore) && (playerScore >= opponentScore + 2)) {
                setScore[0]++;
                setOver = true;
            } else if ((opponentScore >= winningScore) && (opponentScore >= playerScore + 2)) {
                setScore[1]++;
                setOver = true;
            }
        }
        setNumber++;
    }

    private int pointWinner(int index) throws NoSubsException {
    	index = index % 7;
        int winner = 0;

        int chanceOfWinning = 5;

        if (GameEnvironment.getPlayerTeam().get(index).getEffectiveStats()[1] >= opposingTeam.get(index)
                .getStats()[2]) {
            chanceOfWinning += 3;
        } else {
            chanceOfWinning -= 1;
        }

        if (GameEnvironment.getPlayerTeam().get(index).getEffectiveStats()[2] >= opposingTeam.get(index)
                .getStats()[1]) {
            chanceOfWinning += 1;
        } else {
            chanceOfWinning -= 3;
        }

        Random r = new Random();
        if (r.nextInt(10) >= chanceOfWinning) {

            matchStaminas.set(index, matchStaminas.get(index) - 1);
            winner = 1;

        }

        matchStaminas.set(index, matchStaminas.get(index) - 1);

        if (matchStaminas.get(index) <= 0) {
            matchStaminas.set(index, 0);
            GameEnvironment.getPlayerTeam().get(index).getStats()[0] = 0; // actually injure them lol

            System.out.println("Oh no! " + GameEnvironment.getPlayerTeam().get(index).getName()
                    + " got injured facing off against " + opposingTeam.get(index).getName());

            int subIndex = GameFunction.getSubIndex(index);

            Collections.swap(matchStaminas, index, subIndex);
            GameEnvironment.getPlayerTeam().swap(index, subIndex);

            System.out.println(
                    "Subbed " + GameEnvironment.getPlayerTeam().get(subIndex).getNameWithNickname()
                            + " in for " + GameEnvironment.getPlayerTeam().get(index).getNameWithNickname());

        }

        return winner;
    }
    
    public boolean playPoint() {
    	
        int winner = 0;

        int chanceOfWinning = 5;

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getEffectiveStats()[1] >= opposingTeam.get(faceoffIndex)
                .getStats()[2]) {
            chanceOfWinning += 3;
        } else {
            chanceOfWinning -= 1;
        }

        if (GameEnvironment.getPlayerTeam().get(faceoffIndex).getEffectiveStats()[2] >= opposingTeam.get(faceoffIndex)
                .getStats()[1]) {
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

            /*System.out.println("Oh no! " + GameEnvironment.getPlayerTeam().get(index).getName()
                    + " got injured facing off against " + opposingTeam.get(index).getName());
			*/
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

        return setScore[0] == 3;
    }

	
    
}
