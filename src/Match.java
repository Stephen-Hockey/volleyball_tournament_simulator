import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Match {
    private ArrayList<Integer> matchStaminas;
    private Team opposingTeam;
    private int setNumber;
    private int[][] scores;
    private int[] setScore;

    public Match(Team _opposingTeam) {
        matchStaminas = new ArrayList<Integer>();
        for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
            matchStaminas.add(athlete.getStats()[0]);
        }
        opposingTeam = _opposingTeam;
        setNumber = 0;
        scores = new int[5][2];
        setScore = new int[2];
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

            // TODO write actual point scoring method

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

        if (GameEnvironment.getPlayerTeam().get(index).getStats()[1] >= opposingTeam.get(index).getStats()[2]) {
            chanceOfWinning += 3;
        } else {
            chanceOfWinning -= 1;
        }

        if (GameEnvironment.getPlayerTeam().get(index).getStats()[2] >= opposingTeam.get(index).getStats()[1]) {
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
                            + " for " + GameEnvironment.getPlayerTeam().get(index).getNameWithNickname());

        }

        return winner;
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
        return (setScore[0] > setScore[1]);
    }

    @Override
    public String toString() {
        return "(Match) against " + opposingTeam;
    }

    public static void main(String[] args) {

        Match m = new Match(new Team());

        System.out.println(
                "Set Score: " + Arrays.toString(m.getSetScore()) + " Scores: " + Arrays.deepToString(m.getScores()));
        try {
            m.playMatch();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(
                "Set Score: " + Arrays.toString(m.getSetScore()) + " Scores: " + Arrays.deepToString(m.getScores()));

        Match n = new Match(new Team());

        System.out.println(n);

    }
}
