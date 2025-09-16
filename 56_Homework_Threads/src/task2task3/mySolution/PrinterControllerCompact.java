package task2task3.mySolution;

public class PrinterControllerCompact {
    private static int threadsAmount = 5;

    public static void main(String[] args) {
        Printer[] printers = new Printer[threadsAmount];
        for (int i = 0; i < threadsAmount; i++) {
            Printer next = i > 0 ? printers[i - 1] : null;
            printers[i] = new Printer(threadsAmount - i, 16 + i, 5, next);
            printers[i].start();
        }
        printers[0].setNextPrinter(printers[threadsAmount - 1]);
        printers[threadsAmount - 1].interrupt();
    }
}
