import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrintStreamVsPrintWriter {
    private static final int N_LINES = 1_000_000;
    private static final String STRING = "1234567890";

    public static void main(String[] args) throws FileNotFoundException {
        testPrintStream();
        testPrintWriter();
        printFileSize();
    }


    private static void testPrintStream() throws FileNotFoundException {
        try (PrintStream wStream = new PrintStream("fileForPrintStream.txt")) {
            long start = System.currentTimeMillis();
            IntStream.range(0, N_LINES).forEach(s -> wStream.println(STRING));
            System.out.printf("running time for %s is %d ms\n", "'Print stream'", System.currentTimeMillis() - start);
        }
    }

    private static void testPrintWriter() throws FileNotFoundException {
        try (PrintWriter wWriter = new PrintWriter("fileForPrintWriter.txt")) {
            long start = System.currentTimeMillis();
            IntStream.range(0, N_LINES).forEach(s -> wWriter.println(STRING));
            System.out.printf("running time for %s is %d ms\n", "'Print writer'", System.currentTimeMillis() - start);
        }
    }

    private static void printFileSize() {
        File f1 = new File("fileForPrintStream.txt");
        File f2 = new File("fileForPrintWriter.txt");
        System.out.printf("Size of %s = %d bytes\n", f1.getName(), f1.length());
        System.out.printf("Size of %s = %d bytes\n", f2.getName(), f2.length());
    }
}
