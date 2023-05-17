import java.util.Random;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Stadium {

    public static Team generateTeam(int quality) {

        Random rand = new Random();
        int n = rand.nextInt(100);
        String animal = "";
        try {
            File animals = new File("data/animals.txt");
            Scanner animalScanner = new Scanner(animals);
            for (int i = 0; i <= n; i++) {
                animal = animalScanner.nextLine();
            }
            animalScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        String teamName = "The " + animal;

        ArrayList<Athlete> players = Market.generateAthletes(10, quality);

        return new Team(teamName, players);
    }

    public static void main(String[] args) {
        Team ateam = Stadium.generateTeam(9);
        String adetails = ateam.getDetails();
        System.out.println(adetails);
        Team bteam = Stadium.generateTeam(0);
        String bdetails = bteam.getDetails();
        System.out.println(bdetails);
        Team cteam = Stadium.generateTeam(45);
        String cdetails = cteam.getDetails();
        System.out.println(cdetails);
    }
}
