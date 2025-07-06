package telran.date.tests;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DateTest {

    @Test
    void test() {
        String[] dates = { "07-05-1990", "28-01-2010", "11-08-1990", "15-01-2010", "16/06/1970" };
        String[] expected = { "16/06/1970", "07-05-1990", "11-08-1990", "15-01-2010", "28-01-2010" };
        Comparator<String> comp = (d1, d2) -> {
            d1 = d1.replace('/', '-');
            d2 = d2.replace('/', '-');
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date1 = LocalDate.parse(d1, formatter);
            LocalDate date2 = LocalDate.parse(d2, formatter);
            return date1.compareTo(date2);
        };
        Arrays.sort(dates, comp);
        assertArrayEquals(expected, dates);
    }
}
