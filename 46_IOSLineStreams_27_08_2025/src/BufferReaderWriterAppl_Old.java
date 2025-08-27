import java.io.*;
import java.util.Arrays;

public class BufferReaderWriterAppl_Old {

    public static void main(String[] args) throws IOException {
        File src = new File("result_old.txt");

        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(src));

        while (true) {
            System.out.println("Enter numbers separated by space, or type exit >>> ");
            String line = bReader.readLine();
            if (line == null || line.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                break;
            }
            if (line.isBlank()) {
                System.out.println("Please enter number!!!");
            }
            String[] numbers = line.trim().split("\\D+");
            int sum = Arrays.stream(numbers).
                    filter(s -> !s.isEmpty()).
                    mapToInt(Integer::parseInt).
                    sum();
            System.out.println("Sum: " + sum);
            bWriter.write("Sum: " + sum);
            bWriter.newLine();

            bWriter.flush(); // push from buffer to file
        }
        bReader.close();
        bWriter.close();
    }
}
