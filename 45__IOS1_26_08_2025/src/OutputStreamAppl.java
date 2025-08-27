import java.io.*;

public class OutputStreamAppl {
    public static void main(String[] args) throws IOException {
        OutputStream output = new FileOutputStream("greet.txt");
        String hello = "hello group from Karmiel";
        output.write(hello.getBytes());
        output.close();

        OutputStream output1 = new FileOutputStream(new File("greet1.txt"));
        output1.close();
    }
}
