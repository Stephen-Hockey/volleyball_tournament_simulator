import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameFunction {

    public static void setupGame() {
        GameEnvironment.setPlayerName(Input.askForString("Kia ora new player! What is your name?", 3, 999));
        System.out.println("So your name is " + GameEnvironment.getPlayerName()
                + ". Yes, that could be the name of a future volleyball hall-of-famer...");

        String teamName = Input.askForString("What would you like to call your team?", 3, 15);
        GameEnvironment.setPlayerTeam(new Team(teamName));
        System.out.println("Very good choice.");

        GameEnvironment.setFinalWeek(Input.askForInt("How many weeks will your season last?", 5, 15));
        System.out.println(GameEnvironment.getPlayerTeam().getTeamName() + " will attempt to endure "
                + GameEnvironment.getFinalWeek() + " weeks.");

        GameEnvironment.setDifficulty(Input.askMultichoiceQuestion("At what skill level are you going to play?",
                new ArrayList<>(Arrays.asList("Beginner", "Intermediate", "Advanced"))));

        System.out.println("We're almost ready to start the season. Now you just need to select your team.");
        System.out.println("That means we're heading to the draft!");
        System.out.println("You get to choose one athlete each round, for 10 rounds, so choose wisely.");
        System.out.println("The athletes you didn't choose will be gone by the time you get to pick again!");
        System.out.println("When you're ready...");
        Input.pause("<Press ENTER to continue>");

        getTeamFromDraft(10, 5);

        giveNicknames();

        System.out.println("\nAlright! Hooray!");
        System.out.println("You're all set for your season, and I wish you the best of luck.");
        Input.pause("<Press ENTER to start the season>");

        GameEnvironment.setWeek(1);
        GameEnvironment.setMoney(500 - 250 * GameEnvironment.getDifficulty());
        GameEnvironment.setPlayerRating(0);
        GameEnvironment.setRecord(new int[] { 0, 0, 0 });
        GameEnvironment.setInventory(new ArrayList<Item>());
        GameEnvironment.setGameRunning(true);
    }

    public static void getTeamFromDraft(int rounds, int numChoices) {
        boolean isDrafting = true;
        for (int round = 1; round <= rounds; round++) {
            ArrayList<Athlete> draft = Market.generateAthletes(5, 10 - round);
            Athlete pick;

            Random random = new Random();

            if (isDrafting) {
                ArrayList<String> roundOptions = new ArrayList<String>();

                for (Athlete athlete : draft) {
                    roundOptions.add(athlete.getDraftString());
                }

                roundOptions.add("Stop drafting and add " + (rounds + 1 - round) + " randomly picked players to "
                        + GameEnvironment.getPlayerTeam().getTeamName());

                String roundQuestion = "Your #" + round + " pick...\n"
                        + String.format("%28s|%7s|%7s|%7s", "NAME", Purchasable.statNames[0],
                                Purchasable.statNames[1], Purchasable.statNames[2]);

                int pickIndex = Input.askMultichoiceQuestion(roundQuestion, roundOptions);
                if (pickIndex == numChoices) {
                    isDrafting = false;
                    pick = draft.get(random.nextInt(numChoices));

                } else {
                    pick = draft.get(pickIndex);
                }
            } else {
                pick = draft.get(random.nextInt(numChoices));
            }
            GameEnvironment.getPlayerTeam().add(pick);
            System.out.println("Picked " + pick.getName());
        }
    }

    public static void giveNicknames() {
        while (Input.askYesNoQuestion("Would you like to give a nickname to any of your players?\n(y/n)")) {

            ArrayList<String> options = new ArrayList<String>();

            for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
                options.add(athlete.getNameWithNickname());
            }

            int indexToNickname = Input.askMultichoiceQuestion("Who would you like to nickname?", options);
            String nicknameString = Input.askForString(
                    "Please enter " + GameEnvironment.getPlayerTeam().get(indexToNickname).getName()
                            + "'s new nickname:",
                    1, 24);
            GameEnvironment.getPlayerTeam().get(indexToNickname).setNickname(nicknameString);
        }
    }

    public static void playWeek() {

        ArrayList<Team> opposingTeams = new ArrayList<Team>();
        for (int i = 0; i < 3; i++) {
            opposingTeams.add(Stadium.generateTeam(GameEnvironment.getWeek() * (GameEnvironment.getDifficulty() + 1)));
        }

        ArrayList<Item> marketItems = new ArrayList<Item>();
        for (int i = 0; i < 5; i++) {
            marketItems.add(Market.generateItem());
        }

        ArrayList<Athlete> marketAthletes = new ArrayList<Athlete>();
        for (int i = 0; i < 10; i++) {
            marketAthletes.add(Market.generateAthlete(GameEnvironment.getWeek()));
        }

        int currentWeek = GameEnvironment.getWeek();
        while ((GameEnvironment.getGameRunning()) && (GameEnvironment.getWeek() == currentWeek)) {
            ArrayList<String> homeOptions = new ArrayList<String>(
                    Arrays.asList("View Game Info",
                            "Go To The " + GameEnvironment.getPlayerTeam().getTeamName() + " Clubhouse",
                            "Go To The Market"));
            int totalMatchesPlayed = GameEnvironment.getRecord()[0] + GameEnvironment.getRecord()[1]
                    + GameEnvironment.getRecord()[2];

            if (GameEnvironment.getWeek() > totalMatchesPlayed) {
                homeOptions.add("Go To The Stadium");
            }

            if (currentWeek < GameEnvironment.getFinalWeek()) {
                homeOptions.add("Take A Bye (Advance One Week)");
            }

            switch (Input.askMultichoiceQuestion("Please select what action you want to take.", homeOptions)) {
                case 0:
                    viewGameInfo();
                    break;
                case 1:
                    goToClub();
                    break;
                case 2:
                    goToMarket(marketItems, marketAthletes);
                    break;
                case 3:
                    if (GameEnvironment.getWeek() > totalMatchesPlayed) {
                        goToStadium(opposingTeams);
                    } else {
                        takeBye();
                    }
                    break;
                case 4:
                    takeBye();
                    break;
            }
            Input.pause("<Press ENTER to continue>");
        }
    }

    private static void viewGameInfo() {
        System.out.println("\nName: " + GameEnvironment.getPlayerName() + "\nRating: "
                + GameEnvironment.getPlayerRating() + "\nMoney: $" + GameEnvironment.getMoney() + "\nWeek "
                + GameEnvironment.getWeek() + " of " + GameEnvironment.getFinalWeek() + "\n");

    }

    private static void goToClub() {
        ArrayList<String> clubOptions = new ArrayList<String>(
                Arrays.asList("View Team", "View Items", "Exit Clubhouse"));
        // TODO
        switch (Input.askMultichoiceQuestion("Please select what action you want to take.", clubOptions)) {
            case 0:
                viewTeam();
                break;
            case 1:
                viewInventory();
                break;
            case 2:
                System.out.println("...Exiting Clubhouse");
                break;
        }
    }

    private static void viewInventory() {
    }

    private static void viewTeam() {
    }

    private static void goToStadium(ArrayList<Team> opposingTeams) {

        ArrayList<String> stadiumOptions = new ArrayList<String>();
        for (Team team : opposingTeams) {
            stadiumOptions.add("Play a match against " + team.getTeamName());
        }
        stadiumOptions.add("Exit Stadium");
        int choice = Input.askMultichoiceQuestion("Please select what action you want to take.",
                stadiumOptions);

        if (choice == opposingTeams.size()) {
            System.out.println("...Exiting Stadium");
        } else {

            System.out.println(GameEnvironment.getPlayerTeam().getDetails());

            Match match = new Match(opposingTeams.get(choice));

            try {
                match.playMatch();
            } catch (NoSubsException e) {
                match.setSetScore(new int[] { 0, 3 });
                match.setScores(new int[][] { { 0, 25 }, { 0, 25 }, { 0, 25 } });
                match.setSetNumber(3);
                GameEnvironment.getRecord()[1] += 1;
                System.out.println("Yikes, you lost against " + match.getOpposingTeam().getTeamName()
                        + " because your team got too injured.");
                System.out.println("You must have at least 7 healthy players before advancing to the next week.");
                return;
            }

            if (match.matchWon()) {
                GameEnvironment.getRecord()[0] += 1;
                System.out.println("Yay! You won your match with " + match.getOpposingTeam().getTeamName());
            } else {
                GameEnvironment.getRecord()[1] += 1;
                System.out.println("Oh dear... you lost your match with " + match.getOpposingTeam().getTeamName());
            }

            System.out.println();

            System.out.println(String.format("      %16s %-16s", GameEnvironment.getPlayerTeam().getTeamName(),
                    match.getOpposingTeam().getTeamName()));
            for (int i = 0; i < match.getSetNumber(); i++) {
                System.out
                        .println(String.format("Set %s:%16s %-16s", i + 1, match.getScores()[i][0],
                                match.getScores()[i][1]));
            }

            System.out.println("+" + match.getMatchRating() + " to your Player Rating");
            GameEnvironment.setPlayerRating(GameEnvironment.getPlayerRating() + match.getMatchRating());
            System.out.println("+$" + match.getMoneyWon());
            GameEnvironment.setMoney(GameEnvironment.getMoney() + match.getMoneyWon());

            System.out.println(GameEnvironment.getPlayerTeam().getDetails());

        }
    }

    public static int getSubIndex(int index) throws NoSubsException {

        if (GameEnvironment.getPlayerTeam().getPlayers().size() <= 7) {
            System.out.println(
                    "Sorry, you cannot sub "
                            + GameEnvironment.getPlayerTeam().getPlayers().get(index).getNameWithNickname()
                            + " as you don't have any reserves.");
            throw new NoSubsException();
        }

        ArrayList<Athlete> subOptions = new ArrayList<Athlete>();
        ArrayList<String> subOptionsStrings = new ArrayList<String>();
        ArrayList<Integer> subOptionsIndices = new ArrayList<Integer>();

        for (int i = 7; i < GameEnvironment.getPlayerTeam().getPlayers().size(); i++) {
            if (GameEnvironment.getPlayerTeam().getPlayers().get(i).getStats()[0] > 0) {
                subOptions.add(GameEnvironment.getPlayerTeam().getPlayers().get(i));
                subOptionsStrings.add(GameEnvironment.getPlayerTeam().getPlayers().get(i).getNameWithNickname());
                subOptionsIndices.add(i);
            }
        }

        if (subOptions.isEmpty()) {
            System.out.println(
                    "Sorry, you cannot sub "
                            + GameEnvironment.getPlayerTeam().getPlayers().get(index).getNameWithNickname()
                            + " as you don't have any healthy reserves.");
            throw new NoSubsException();
        }

        return subOptionsIndices.get(Input.askMultichoiceQuestion(
                "Who would you like to substitute for "
                        + GameEnvironment.getPlayerTeam().getPlayers().get(index).getNameWithNickname() + "?",
                subOptionsStrings));
    }

    private static void goToMarket(ArrayList<Item> marketItems, ArrayList<Athlete> marketAthletes) {
        ArrayList<String> marketOptions = new ArrayList<String>(
                Arrays.asList("Buy Items", "Sell Items", "Buy Athletes", "Sell Athletes", "Exit Market"));

        // TODO
        switch (Input.askMultichoiceQuestion("Please select what action you want to take.", marketOptions)) {
            case 0:
                buyItems(marketItems);
                break;
            case 1:
                sellItems();
                break;
            case 2:
                buyAthletes(marketAthletes);
                break;
            case 3:
                sellAthletes();
                break;
            case 4:
                System.out.println("...Exiting Market");
                break;
        }
    }

    private static void sellAthletes() {
    }

    private static void buyAthletes(ArrayList<Athlete> marketAthletes) {
    }

    private static void sellItems() {
    }

    private static void buyItems(ArrayList<Item> marketItems) {
    }

    private static void takeBye() {

        if (Input.askYesNoQuestion(
                "Are you sure you want to take a bye and advance to the next week?\nYou'll gain no further points for this week\n(y/n)")) {
            int totalMatchesPlayed = GameEnvironment.getRecord()[0] + GameEnvironment.getRecord()[1]
                    + GameEnvironment.getRecord()[2];
            if (GameEnvironment.getWeek() > totalMatchesPlayed) {
                GameEnvironment.getRecord()[2] = GameEnvironment.getWeek() - totalMatchesPlayed;
            }
            GameEnvironment.setWeek(GameEnvironment.getWeek() + 1);
        }
    }

    public static void endGame() {
        // TODO
    }

}