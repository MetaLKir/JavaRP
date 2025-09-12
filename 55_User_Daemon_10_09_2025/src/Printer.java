import java.util.Scanner;

public class Printer extends Thread {
    private String str;
    private int len;

    public Printer(String str) {
        this.str = str;
        len = str.length();
        setDaemon(true);
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println(str.charAt(i));
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                i++;
                if (i >= len) i = 0;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Printer printer = new Printer("abcd");
        printer.start();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("q")) {
                break;
            } else {
                printer.interrupt();
            }
        }
        scanner.close();
    }
}
//abcde => a a a a (interrupt) => b b b b b => c c...   q => end;
