import view.InputOutput;
import view.Item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class DateAfterBeforeItem implements Item {
    InputOutput inOut;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static Map<String, BiFunction<LocalDate, Integer, LocalDate>> operations;
    {
        operations = new HashMap<>();
        operations.put("before", LocalDate::minusDays);
        operations.put("after", LocalDate::plusDays);
    }

    public DateAfterBeforeItem(InputOutput inOut) {
        this.inOut = inOut;
    }

    @Override
    public String displayedName() {
        return "Date before/after days";
    }

    @Override
    public void perform() {
        String input1 = inOut.inputString("Enter first date format day/month/year (2/2/4 digits accordingly)");
        if (input1 == null || input1.isBlank()) return;
        input1 = input1.trim().replaceAll("[-_.,:\\s]", "/");
        LocalDate date = LocalDate.parse(input1, formatter);

        String operator = inOut.inputString("Choose what date you want to get:", new ArrayList<>(operations.keySet()));

        Integer days = inOut.inputInteger("Enter amount of days");
        if(days == null) return;

        inOut.outputLine(operations.get(operator).apply(date, days).format(formatter));
    }
}
