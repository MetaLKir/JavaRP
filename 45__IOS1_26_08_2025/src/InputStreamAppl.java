import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InputStreamAppl {
    public static void main(String[] args) throws IOException {
        InputStream inputGreet = new FileInputStream("greet.txt");
//        System.out.println(input.read());
//        System.out.println(input.read());
//        System.out.println(input.read());
//        int c;
//        while((c = input.read()) > 0) System.out.printf("%c", c);

//        byte[] buffer1 = new byte[inputGreet.available()];
//        System.out.println(inputGreet.available());
//        inputGreet.read(buffer1);
//        System.out.println(Arrays.toString(buffer1));
//        System.out.println(new String(buffer1));

//        byte[] buffer2 = new byte[16_000_000];
//        int n = inputGreet.read(buffer2);
//        System.out.println(new String(Arrays.copyOf(buffer2, n)));

        byte[] buffer3 = new byte[7];
        int v = 0;
        String str = "";
        while ((v = inputGreet.read(buffer3)) > 0) {
            System.out.print(v + " ");
            str += new String(Arrays.copyOf(buffer3, v));
            System.out.println(str);
        }
    }
}
