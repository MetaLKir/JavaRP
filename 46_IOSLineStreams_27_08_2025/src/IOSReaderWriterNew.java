import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOSReaderWriterNew {

    public static void main(String[] args) throws IOException {
//        FileReader inputStream = null;
//        FileWriter outputStream = null;
        File src = new File("fileTest.txt");
        src.createNewFile();

        try (
                FileReader inputStream = new FileReader(src);
                FileWriter outputStream = new FileWriter("duplicateSrc.txt")
        ) {
            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        }

    }
}
