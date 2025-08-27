import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class EmployeeCsvAppl {

    private static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new FileReader("employee.csv"))) {
            String str = br.readLine();
            String[] cells = str.split(",");
            double salary = 0;
            int count = 0;
            long age = 0;
            printCells(cells);

            str = br.readLine();
            while (str != null) {
                count++;
                cells = str.split(",");
                salary += Double.parseDouble(cells[2].trim());
                LocalDate birthday = LocalDate.parse(cells[3].trim(), DMY);
                age += ChronoUnit.YEARS.between(birthday, LocalDate.now());
                printCells(cells);
                str = br.readLine();
            }
            System.out.println("Total employees: " + count);
            System.out.println("Total salary: " + salary);
            System.out.println("Average salary: " + salary / count);
            System.out.println("Average age: " + age / count);
        }
    }

    private static void printCells(String[] cells) {
        for (int i = 0; i < cells.length; i++) {
            System.out.print(cells[i] + "\t\t");
        }
        System.out.println();
    }
}
