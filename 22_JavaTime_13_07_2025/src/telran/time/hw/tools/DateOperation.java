package telran.time.hw.tools;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Comparator;

public class DateOperation {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");


    public static int getAge(String birthDate) {
        LocalDate birth = LocalDate.parse(birthDate, formatter);
        LocalDate current = LocalDate.now(); // .now() was 15.07.2025 at the moment
        return (int) ChronoUnit.YEARS.between(birth, current);
    }


    public static String[] sortStringDates(String[] dates) {
        String[] sortedDates = Arrays.copyOf(dates, dates.length);

        Comparator<String> comparator = (s1, s2) -> {
            LocalDate ld1 = LocalDate.parse(s1, formatter);
            LocalDate ld2 = LocalDate.parse(s2, formatter);
            return ld1.compareTo(ld2);
        };

        Arrays.sort(sortedDates, comparator);
        return sortedDates;
    }
}
