import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Input {

    // breaks for an empty options array
    public static int askMultichoiceQuestion(String question, ArrayList<String> options) {
        int input = 0;

        System.out.println(question);
        for (int i = 0; i < options.size(); i++) {
            System.out.println(String.format("%-4s%s", "[" + i + "]", options.get(i)));
        }

        Scanner scanner = new Scanner(System.in);
        boolean inputAccepted = false;
        while (!inputAccepted) {
            try {
                String inputString = scanner.nextLine();
                input = Integer.parseInt(inputString);

                if ((input < 0) || (input >= options.size())) {
                    throw new InvalidInputException();
                }

                inputAccepted = true;
            } catch (NumberFormatException e) {
                System.out.println("An error occurred: Please enter an integer");
            } catch (InvalidInputException e) {
                System.out.println("An error occurred: Please enter an integer between 0 and " + (options.size() - 1));
            }
        }
        return input;
    }

    public static boolean askYesNoQuestion(String question) {

        String input = "";

        System.out.println(question);

        Scanner scanner = new Scanner(System.in);
        boolean inputAccepted = false;
        while (!inputAccepted) {
            input = scanner.nextLine();
            try {

                if (!((input.equalsIgnoreCase("y")) || (input.equalsIgnoreCase("n")))) {
                    throw new InvalidInputException();
                }
                inputAccepted = true;
            } catch (InvalidInputException e) {
                System.out.println("An error occurred: Please enter 'y' for yes, or enter 'n' for no");
            }
        }

        return input.equalsIgnoreCase("y");
    }

    public static String askForString(String question, int lowerBound, int upperBound) {
        String input = "";

        System.out.println(question);

        Scanner scanner = new Scanner(System.in);
        boolean inputAccepted = false;
        while (!inputAccepted) {
            input = scanner.nextLine();
            try {
                if ((input.length() < lowerBound) || (input.length() > upperBound)) {
                    throw new InvalidInputException();
                }
                if (!input.matches("[a-zA-Z0-9 ]*")) {
                    throw new InvalidInputException();
                }
                inputAccepted = true;
            } catch (InvalidInputException e) {
                System.out.println("An error occurred: Please enter between " + lowerBound + " and " + upperBound
                        + " non-special characters");
            }
        }

        return input;
    }

    public static int askForInt(String question, int lowerBound, int upperBound) {
        int input = 0;

        System.out.println(question);

        Scanner scanner = new Scanner(System.in);
        boolean inputAccepted = false;
        while (!inputAccepted) {
            try {
                String inputString = scanner.nextLine();
                input = Integer.parseInt(inputString);

                if ((input < lowerBound) || (input > upperBound)) {
                    throw new InvalidInputException();
                }
                inputAccepted = true;
            } catch (NumberFormatException e) {
                System.out.println("An error occurred: Please enter an integer");
            } catch (InvalidInputException e) {
                System.out.println(
                        "An error occurred: Please enter an integer between " + lowerBound + " and " + upperBound);
            }
        }
        return input;
    }

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void pause(String message) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(message);
        scanner.nextLine();
    }
}
