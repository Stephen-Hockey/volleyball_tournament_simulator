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
            ArrayList<Athlete> draft = Athlete.generateAthletes(5, 10 - round);
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
            opposingTeams.add(Team.generateTeam(GameEnvironment.getWeek() * (GameEnvironment.getDifficulty() + 1)));
        }

        ArrayList<Item> marketItems = new ArrayList<Item>();
        for (int i = 0; i < 5; i++) {
            marketItems.add(Item.generateItem());
        }

        ArrayList<Athlete> marketAthletes = new ArrayList<Athlete>();
        for (int i = 0; i < 5; i++) {
            marketAthletes.add(Athlete.generateAthlete(GameEnvironment.getWeek()));
        }

        int currentWeek = GameEnvironment.getWeek();
        while ((GameEnvironment.getGameRunning()) && (GameEnvironment.getWeek() == currentWeek)) {
            ArrayList<String> homeOptions = new ArrayList<String>(
                    Arrays.asList("View Game Info",
                            "Go To " + GameEnvironment.getPlayerTeam().getTeamName() + "'s Clubhouse",
                            "Go To The Market"));
            int totalMatchesPlayed = GameEnvironment.getRecord()[0] + GameEnvironment.getRecord()[1]
                    + GameEnvironment.getRecord()[2];

            if (GameEnvironment.getWeek() > totalMatchesPlayed) {
                homeOptions.add("Go To The Stadium");
                if (currentWeek < GameEnvironment.getFinalWeek()) {
                    homeOptions.add("Take A Bye (Advance One Week)");
                }
            } else {
                if (currentWeek < GameEnvironment.getFinalWeek()) {
                    homeOptions.add("Advance One Week");
                }
            }
            if (currentWeek == GameEnvironment.getFinalWeek()) {
                homeOptions.add("End Season");
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
                        if (currentWeek < GameEnvironment.getFinalWeek()) {
                            takeBye();
                        } else {
                            GameEnvironment.setGameRunning(false);
                        }
                    }
                    break;
                case 4:
                    if (currentWeek < GameEnvironment.getFinalWeek()) {
                        takeBye();
                    } else {
                        GameEnvironment.setGameRunning(false);
                    }
                    break;
            }
            Input.pause("\n<Press ENTER to continue>");
        }
    }

    private static void viewGameInfo() {
        System.out.println("\nName: " + GameEnvironment.getPlayerName() + "\nRating: "
                + GameEnvironment.getPlayerRating() + "\nMoney: $" + GameEnvironment.getMoney() + "\nWeek "
                + GameEnvironment.getWeek() + " of " + GameEnvironment.getFinalWeek());

    }

    private static void goToClub() {
        System.out.println();
        ArrayList<String> clubOptions = new ArrayList<String>(
                Arrays.asList("View Team", "View Items", "Exit Clubhouse"));
        switch (Input.askMultichoiceQuestion("Please select what action you want to take.", clubOptions)) {
            case 0:
                viewTeam();
                break;
            case 1:
                viewInventory();
                break;
            case 2:
                System.out.println("\n...Exiting Clubhouse");
                break;
        }
    }

    private static void viewInventory() {
        System.out.println();

        if (GameEnvironment.getInventory().isEmpty()) {
            System.out.println("You have no items in your inventory");
            return;
        }

        System.out.println("Your items:");
        for (Item item : GameEnvironment.getInventory()) {
            System.out.println(item.getInventoryString());
        }

        if (GameEnvironment.getPlayerTeam().getPlayers().isEmpty()) {
            return;
        }

        if (Input.askYesNoQuestion("Would you like to give an item to a player?\n(y/n)")) {
            ArrayList<String> itemStrings = new ArrayList<String>();
            for (Item item : GameEnvironment.getInventory()) {
                itemStrings.add(item.getInventoryString());
            }
            int itemIndex = Input.askMultichoiceQuestion("Which item?", itemStrings);
            ArrayList<String> athleteStrings = new ArrayList<String>();
            for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
                athleteStrings.add(String.format("%s|%s", athlete.getDraftString(), athlete.getItemsListed()));
            }
            int athleteIndex = Input.askMultichoiceQuestion(
                    "To give to which athlete?\n"
                            + String.format("%28s|%7s|%7s|%7s|%s", "NAME", Purchasable.statNames[0],
                                    Purchasable.statNames[1], Purchasable.statNames[2], "ITEMS"),
                    athleteStrings);

            GameEnvironment.getPlayerTeam().getPlayers().get(athleteIndex)
                    .addItem(GameEnvironment.getInventory().get(itemIndex));
            System.out.println("Successfully gave " + GameEnvironment.getInventory().get(itemIndex).getName() + " to "
                    + GameEnvironment.getPlayerTeam().getPlayers().get(athleteIndex).getNameWithNickname());
            GameEnvironment.getInventory().remove(itemIndex);
        }
    }

    private static void viewTeam() {

        System.out.println();

        if (GameEnvironment.getPlayerTeam().getPlayers().isEmpty()) {
            System.out.println("How did you manage to end up with ZERO players? Go buy some from the market.");
            return;
        }

        System.out.println(GameEnvironment.getPlayerTeam().getTeamName() + ": ");
        System.out.println(String.format("%36s|%7s|%7s|%7s|%s", "NAME", Purchasable.statNames[0],
                Purchasable.statNames[1], Purchasable.statNames[2], "ITEMS"));

        ArrayList<String> athleteStrings = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            if (i < GameEnvironment.getPlayerTeam().size()) {
                System.out.println(String.format("%-12s%s|%s", "(" + Team.POSITION_STRINGS[i] + ")",
                        GameEnvironment.getPlayerTeam().get(i).getDraftString(),
                        GameEnvironment.getPlayerTeam().get(i).getItemsListed()));
                athleteStrings.add(String.format("%-12s%s|%s", "(" + Team.POSITION_STRINGS[i] + ")",
                        GameEnvironment.getPlayerTeam().get(i).getDraftString(),
                        GameEnvironment.getPlayerTeam().get(i).getItemsListed()));
            } else {
                System.out.println("(" + Team.POSITION_STRINGS[i] + ")");
            }
        }
        for (int i = 7; i < 14; i++) {
            if (i < GameEnvironment.getPlayerTeam().size()) {
                System.out.println(String.format("%-12s%s|%s", "(Reserve)",
                        GameEnvironment.getPlayerTeam().get(i).getDraftString(),
                        GameEnvironment.getPlayerTeam().get(i).getItemsListed()));
                athleteStrings.add(String.format("%-12s%s|%s", "(Reserve)",
                        GameEnvironment.getPlayerTeam().get(i).getDraftString(),
                        GameEnvironment.getPlayerTeam().get(i).getItemsListed()));
            } else {
                System.out.println("(Reserve)");
            }
        }

        if (Input.askYesNoQuestion("Would you like to swap two player positions?\n(y/n)")) {
            int athleteIndex1 = Input.askMultichoiceQuestion("Pick an athlete you'd like to move.\n"
                    + String.format("%40s|%7s|%7s|%7s|%s", "NAME", Purchasable.statNames[0],
                            Purchasable.statNames[1], Purchasable.statNames[2], "ITEMS"),
                    athleteStrings);
            int athleteIndex2 = Input.askMultichoiceQuestion("Pick the athlete that you want to swap with "
                    + GameEnvironment.getPlayerTeam().get(athleteIndex1).getNameWithNickname() + ".\n"
                    + String.format("%40s|%7s|%7s|%7s|%s", "NAME", Purchasable.statNames[0],
                            Purchasable.statNames[1], Purchasable.statNames[2], "ITEMS"),
                    athleteStrings);
            GameEnvironment.getPlayerTeam().swap(athleteIndex1, athleteIndex2);
            System.out.println("Successfully swapped "
                    + GameEnvironment.getPlayerTeam().get(athleteIndex2).getNameWithNickname() + " and "
                    + GameEnvironment.getPlayerTeam().get(athleteIndex1).getNameWithNickname());
        }
    }

    private static void goToStadium(ArrayList<Team> opposingTeams) {

        System.out.println();

        ArrayList<String> stadiumOptions = new ArrayList<String>();
        for (Team team : opposingTeams) {
            stadiumOptions.add("Play a match against " + team.getTeamName());
        }
        stadiumOptions.add("Exit Stadium");
        int choice = Input.askMultichoiceQuestion("Please select what action you want to take.",
                stadiumOptions);

        if (choice == opposingTeams.size()) {
            System.out.println("\n...Exiting Stadium");
        } else {

            for (int i = 0; i < 7; i++) {
                if (i >= GameEnvironment.getPlayerTeam().size()) {
                    System.out.println("\nYou must have at least 7 players in your team before you can play a match.");
                    return;
                }
                if (GameEnvironment.getPlayerTeam().get(i).getStats()[0] == 0) {
                    System.out.println(
                            "\nYour starting roster (Setter - Libero) must be healthy before you can play a match.");
                    return;
                }
            }

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
        System.out.println();

        ArrayList<String> marketOptions = new ArrayList<String>(
                Arrays.asList("Buy Items", "Sell Items", "Buy Athletes", "Sell Athletes", "Exit Market"));
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
                System.out.println("\n...Exiting Market");
                break;
        }
    }

    private static void sellAthletes() {
        System.out.println();
        if (GameEnvironment.getPlayerTeam().isEmpty()) {
            System.out.println("You have no athletes to sell!");
            return;
        }

        ArrayList<String> sellStrings = new ArrayList<String>();
        for (Athlete athleteToSell : GameEnvironment.getPlayerTeam().getPlayers()) {
            sellStrings.add(athleteToSell.getSellString());
        }
        sellStrings.add("Cancel");
        int choice = Input.askMultichoiceQuestion("Please select which athlete you would like to sell.",
                sellStrings);

        if (choice < GameEnvironment.getPlayerTeam().size()) {
            GameEnvironment
                    .setMoney(GameEnvironment.getMoney() + GameEnvironment.getPlayerTeam().get(choice).getSellPrice());
            System.out.println("Sold " + GameEnvironment.getPlayerTeam().get(choice).getNameWithNickname() + " for $"
                    + GameEnvironment.getPlayerTeam().get(choice).getSellPrice());

            GameEnvironment.getPlayerTeam().remove(choice);
        }
    }

    private static void buyAthletes(ArrayList<Athlete> marketAthletes) {
        System.out.println();

        if (GameEnvironment.getPlayerTeam().size() >= 14) {
            System.out.println("Your team is already full!\n");
            return;
        }

        ArrayList<String> marketAthleteStrings = new ArrayList<String>();

        for (Athlete marketAthlete : marketAthletes) {
            marketAthleteStrings.add(marketAthlete.getMarketString());
        }

        marketAthleteStrings.add("Cancel");
        int choice = Input.askMultichoiceQuestion("Please select what athlete you would like to buy.\n"
                + String.format("%28s|%6s|%7s|%7s|%7s", "NAME", "COST", Purchasable.statNames[0],
                        Purchasable.statNames[1], Purchasable.statNames[2]),
                marketAthleteStrings);

        if (choice < marketAthletes.size()) {
            if (marketAthletes.get(choice).getPrice() <= GameEnvironment.getMoney()) {
                GameEnvironment.setMoney(GameEnvironment.getMoney() - marketAthletes.get(choice).getPrice());
                GameEnvironment.getPlayerTeam().add(marketAthletes.get(choice));
                System.out.println(
                        "Bought " + marketAthletes.get(choice).getName() + " for $"
                                + marketAthletes.get(choice).getPrice());
                marketAthletes.remove(choice);
            } else {
                System.out.println("You don't have enough money!");
            }
        }
    }

    private static void sellItems() {
        System.out.println();
        if (GameEnvironment.getInventory().isEmpty()) {
            System.out.println("You have no items to sell!");
            return;
        }

        ArrayList<String> sellStrings = new ArrayList<String>();
        for (Item itemToSell : GameEnvironment.getInventory()) {
            sellStrings.add(itemToSell.getSellString());
        }
        sellStrings.add("Cancel");
        int choice = Input.askMultichoiceQuestion("Please select what item you would like to sell.",
                sellStrings);

        if (choice < GameEnvironment.getInventory().size()) {
            GameEnvironment
                    .setMoney(GameEnvironment.getMoney() + GameEnvironment.getInventory().get(choice).getSellPrice());
            System.out.println("Sold " + GameEnvironment.getInventory().get(choice).getName() + " for $"
                    + GameEnvironment.getInventory().get(choice).getSellPrice());
            GameEnvironment.getInventory().remove(choice);
        }
    }

    private static void buyItems(ArrayList<Item> marketItems) {
        System.out.println();
        ArrayList<String> marketItemStrings = new ArrayList<String>();
        for (Item marketItem : marketItems) {
            marketItemStrings.add(marketItem.getMarketString());
        }
        marketItemStrings.add("Cancel");
        int choice = Input.askMultichoiceQuestion("Please select what item you would like to buy.",
                marketItemStrings);

        if (choice < marketItems.size()) {
            if (marketItems.get(choice).getPrice() <= GameEnvironment.getMoney()) {
                GameEnvironment.setMoney(GameEnvironment.getMoney() - marketItems.get(choice).getPrice());
                GameEnvironment.getInventory().add(marketItems.get(choice));
                System.out.println(
                        "Bought " + marketItems.get(choice).getName() + " for $" + marketItems.get(choice).getPrice());
                marketItems.remove(choice);
            } else {
                System.out.println("You don't have enough money!");
            }
        }
    }

    private static void takeBye() {

        int healthyPlayersCount = 0;
        for (Athlete athlete : GameEnvironment.getPlayerTeam().getPlayers()) {
            if (athlete.getStats()[0] > 0) {
                healthyPlayersCount++;
            }
        }

        System.out.println();

        if (healthyPlayersCount < 7) {
            System.out.println("You do not have enough healthy players to proceed.");
            if (Input.askYesNoQuestion(
                    "Would you like to give up?\nEnter 'y' to quit this season.\nEnter 'n' to keep playing.")) {
                GameEnvironment.setGameRunning(false);

            }
            return;
        }

        if (Input.askYesNoQuestion(
                "Are you sure you want to advance to the next week?\nYou'll gain no further points for this week\n(y/n)")) {
            int totalMatchesPlayed = GameEnvironment.getRecord()[0] + GameEnvironment.getRecord()[1]
                    + GameEnvironment.getRecord()[2];

            if (GameEnvironment.getWeek() > totalMatchesPlayed) {
                GameEnvironment.getRecord()[2] = GameEnvironment.getWeek() - totalMatchesPlayed;
            }
            GameEnvironment.setWeek(GameEnvironment.getWeek() + 1);
        }
    }

    public static void endGame() {
        System.out.println("The end of your season has arrived.");
        System.out.println("Team: " + GameEnvironment.getPlayerTeam().getTeamName());
        System.out.println(
                "Endured " + GameEnvironment.getWeek() + " of " + GameEnvironment.getFinalWeek() + " weeks.");
        System.out.println("Money Gained: $" + GameEnvironment.getMoney());
        System.out.println("Player Rating: " + GameEnvironment.getPlayerRating());
        System.out.println("Thanks for playing!!!\n");
    }
}