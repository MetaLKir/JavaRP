package bulls_and_cows;

import java.io.*;
import java.time.LocalDate;

public class BullsAndCowsAppl {
    public static void main(String[] args) {
        BullsAndCowsGame game = new BullsAndCowsGame();
        int round = 1;
        int[] result;
        String endStats;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter writer = new BufferedWriter(new FileWriter("BullsAndCowsLog.txt", true));
        ) {
            printWelcomeMessage();
            printRules();
            System.out.println("Before the game starts, enter you nickname: ");
            String userName = reader.readLine();
            System.out.printf("The game begins. Good luck %s\n", userName);
            writer.write("Session: " + LocalDate.now() + " | Player: " + userName);
            writer.newLine();

            while (true) {
                System.out.println("|=== Enter your guess number ===|");
                String userNumber = reader.readLine();

                if (userNumber.trim().equalsIgnoreCase("exit")) {
                    endStats = "Game finished with an EXIT on " + round + " round.";
                    break;
                }
                try {
                    result = game.check(userNumber);
                    String roundStats = "Round " + round + ": guess " + userNumber + ", bulls " + result[0] + ", cows " + result[1];
                    printAndLogMessage(roundStats, writer);

                    if (result[0] == userNumber.length()) {
                        System.out.println("You won!");
                        endStats = "Game finished with a WIN in " + round + " rounds. Answer is " + userNumber;
                        break;
                    }
                    round++;
                } catch (IllegalArgumentException e) { System.out.println(e.getMessage()); }
            }
            printAndLogMessage(endStats, writer);
            printAndLogMessage("=".repeat(50), writer);

        } catch (IOException e) { e.printStackTrace(); }
    }

    private static void printWelcomeMessage() {
        System.out.println("""
                ---------------------------------------
                |  Welcome to "Bulls and Cows" game!  |
                ---------------------------------------""");
    }

    private static void printRules() {
        System.out.println("""
                ============================= Rules =============================
                1. Here's a 4-digits number.
                   First digit can't be zero, digits can't repeat.
                2. Your goal is to guess the number. Try to do it in as few
                   attempts as you can.
                3. If the digit in your guess matches the digit in the number,
                   it's a "BULL". If the digit in your guess doesn't match,
                   but is present in the number, it's a "COW".
                4. Type "exit" if you want to exit the game.
                =================================================================""");
    }

    private static void printAndLogMessage(String message, BufferedWriter writer) throws IOException {
        System.out.println(message);
        System.out.println();
        writer.write(message);
        writer.newLine();
    }
}
