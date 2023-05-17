import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Team {

    private String teamName;
    private ArrayList<Athlete> players;
    public static final String[] POSITION_STRINGS = new String[] { "Setter", "Outside 1", "Middle 1", "Opposite",
            "Outside 2", "Middle 2", "Libero" };

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

    public void add(Athlete athlete) {

        players.add(athlete);
    }

    public Athlete get(int index) {

        return players.get(index);
    }

    public int size() {
        return players.size();
    }

    public boolean isEmpty() {
        return players.isEmpty();
    }

    public void remove(Athlete athlete) {
        players.remove(athlete);
    }

    public void remove(int index) {
        players.remove(index);
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
    
    public static Team generateTeam(int quality) {

        Random rand = new Random();
        int n = rand.nextInt(100);
        String animal = "";
        try {
            File animals = new File("src/data/animals.txt");
            Scanner animalScanner = new Scanner(animals);
            for (int i = 0; i <= n; i++) {
                animal = animalScanner.nextLine();
            }
            animalScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        String teamName = "The " + animal;

        ArrayList<Athlete> players = Athlete.generateAthletes(10, quality);

        return new Team(teamName, players);
    }
}
