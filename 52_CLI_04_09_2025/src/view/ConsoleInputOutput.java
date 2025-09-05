package view;

import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String inputString(String prompt) {
        outputLine(prompt + " or cancel for exit");
        String text = scanner.nextLine().trim();
        return text.equalsIgnoreCase("cancel") ? null : text;
    }

    @Override
    public void output(Object obj) {
        System.out.println(obj);
    }
}
