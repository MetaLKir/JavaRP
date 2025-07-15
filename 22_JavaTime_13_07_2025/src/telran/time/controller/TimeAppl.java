package telran.time.controller;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class TimeAppl {
    public static void main(String[] args) {
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current date: " + currentDate);
        LocalTime localTime = LocalTime.now();
        System.out.println("Local time: " + localTime);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("Local date and time: " + localDateTime);
        LocalDate gagarin = LocalDate.of(1961, 4, 12);
        System.out.println("Gagarin's flight: " + gagarin);
        System.out.println(gagarin.atStartOfDay());
        System.out.println(gagarin.getDayOfWeek());
        System.out.println(gagarin.getDayOfYear());
        System.out.println(gagarin.getDayOfMonth());
        System.out.println(gagarin.isBefore(currentDate));
        LocalDate newDate = currentDate.plusDays(100);
        System.out.println("New date: " + newDate);
        System.out.println("Current date: " + currentDate);
        newDate = currentDate.plus(100, ChronoUnit.MONTHS);
        System.out.println(newDate);
        System.out.println(localTime.plus(100, ChronoUnit.MINUTES));
        System.out.println(localDateTime.plus(5, ChronoUnit.HALF_DAYS));
        System.out.println(ChronoUnit.DAYS.between(gagarin, currentDate));
        System.out.println(LocalDate.EPOCH);
        System.out.println("===== DATE FORMATTER =====");
        DateTimeFormatter df = DateTimeFormatter.BASIC_ISO_DATE;
        System.out.println(gagarin.format(df));
        df = DateTimeFormatter.ofPattern("dd/MMMM/yyyy", new Locale("en"));
        System.out.println(gagarin.format(df));
        String date = "04/02/2207";
        df = DateTimeFormatter.ofPattern("[dd/MM/yyyy][yyyy-MM-dd]");
        LocalDate localDate1 = LocalDate.parse(date, df);
        System.out.println(localDate1);
    }
}
