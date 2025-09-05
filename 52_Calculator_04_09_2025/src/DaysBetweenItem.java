import view.InputOutput;
import view.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DaysBetweenItem implements Item {
    InputOutput inOut;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String separators = "[-_.,:\\s]";
    String actualSeparator = "/";
    String messageFormat = "(format day/month/year, 2/2/4 digits accordingly)";

    public DaysBetweenItem(InputOutput inOut) {
        this.inOut = inOut;
    }

    @Override
    public String displayedName() {
        return "Days between dates";
    }

    @Override
    public void perform() {
        String input1 = inOut.inputString("Enter first date " + messageFormat);
        if (input1 == null || input1.isBlank()) return;
        input1 = input1.trim().replaceAll(separators, actualSeparator);
        LocalDate date1 = LocalDate.parse(input1, formatter);

        String input2 = inOut.inputString("Enter second date " + messageFormat);
        if (input2 == null || input2.isBlank()) return;
        input2 = input2.trim().replaceAll(separators, actualSeparator);
        LocalDate date2 = LocalDate.parse(input2, formatter);

        long days = ChronoUnit.DAYS.between(date1, date2);
        inOut.outputLine("Days between: " + days);
    }
}
