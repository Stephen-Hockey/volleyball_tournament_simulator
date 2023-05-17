import java.util.ArrayList;
import java.util.Collections;

public class Team {

    private String teamName;
    private ArrayList<Athlete> players;

    public Team() {
        teamName = "Default Team Name";
        players = new ArrayList<Athlete>();
    }

    public Team(String _teamName) {
        teamName = _teamName;
        players = new ArrayList<Athlete>();

    }

    public Team(String _teamName, ArrayList<Athlete> _players) {
        teamName = _teamName;
        players = _players;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String _teamName) {
        teamName = _teamName;
    }

    public ArrayList<Athlete> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Athlete> _players) {
        players = _players;
    }

    // TODO exceptions for following four methods
    public void add(Athlete athlete) {

        players.add(athlete);
    }

    public Athlete get(int index) {

        return players.get(index);
    }

    public void remove(Athlete athlete) {
        players.remove(athlete);
    }

    public void swap(int index1, int index2) {
        Collections.swap(players, index1, index2);
    }

    public String getDetails() {
        String details = "Name: " + teamName + "\n";
        for (Athlete athlete : players) {
            details += "\n" + athlete.getDetails();
        }
        return details;

    }

    @Override
    public String toString() {
        return "(Team) name=\"" + teamName + "\"";
    }

    public static void main(String[] args) {
    }
}
