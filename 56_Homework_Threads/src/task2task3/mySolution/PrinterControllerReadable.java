package task2task3.mySolution;

public class PrinterControllerReadable {
    private static int threadsAmount = 5;

    public static void main(String[] args) {
        // create threads
        Printer[] printers = new Printer[threadsAmount];
        for (int i = 0; i < threadsAmount; i++) {
            printers[i] = new Printer(i + 1, 16 + i, 5, null);
        }

        // make chain refs to next printer
        for (int i = 0; i < threadsAmount; i++) {
            Printer next  = i < threadsAmount - 1 ? printers[i + 1] : printers[0];
            printers[i].setNextPrinter(next);
        }

        // start threads
        for (int i = 0; i < threadsAmount; i++) {
            printers[i].start();
        }
        printers[0].interrupt();
    }
}
