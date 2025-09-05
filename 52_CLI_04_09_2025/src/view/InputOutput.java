package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;

public interface InputOutput {
    String inputString(String prompt);

    void output(Object obj);

    default void outputLine(Object obj) {
        //output(obj.toString()); // could be NullPointerException
        output(String.valueOf(obj));
    }

    default <R> R inputObject(String prompt, String errorPrompt, Function<String, R> mapper) {
        while (true) {
            String text = inputString(prompt);
            if (text == null) return null;
            R res = mapper.apply(text); // regNumber String, year int, engine double
            //s56fdg,2022,5.8 => [s56fdg, 2022, 5.8] => parse String, parse int, parse double
            //regNumber = s56fdg , year = 2022, engine = 5.8, new Car(regNumber, year, engine)
            if (res != null) return res;
            outputLine(errorPrompt);
        }
    }

    default Integer inputInteger(String prompt) {
        return inputObject(prompt, "it's not a number", s -> {
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    default Integer inputInteger(String prompt, Integer minValue, Integer maxValue) {
        return inputObject(
                prompt,
                String.format("it's not a number in range [%d - %d]", minValue, maxValue),
                s -> {
                    try {
                        Integer res = Integer.parseInt(s);
                        return res >= minValue && res <= maxValue ? res : null;
                    } catch (NumberFormatException e) {
                        return null;
                    }
                });
    }

    default Double inputDouble(String prompt) {
        return inputObject(prompt, "It's not a double number", s -> {
            try {
                Double res = Double.parseDouble(s);
                return res;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    default Long inputLong(String prompt) {
        return inputObject(prompt, "It's not a long number", s -> {
            try {
                Long res = Long.parseLong(s);
                return res;
            } catch (NumberFormatException e) {
                return null;
            }
        });
    }

    default String inputString(String prompt, List<String> options) {
        return inputObject(
                String.format("%s %s", prompt, options),
                "String is not in options",
                s -> options.contains(s) ? s : null);
    }

    default LocalDate inputDate(String prompt, String format) {
        return inputObject(
                prompt + " " + format,
                "wrong date " + format,
                s -> {
                    try {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
                        return LocalDate.parse(s, dtf);
                    } catch (Exception e) {
                        return null;
                    }
                });
    }

}
