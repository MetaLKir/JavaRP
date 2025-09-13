package task2task3;

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
            Printer next  = i < threadsAmount - 1 ? printers[i + 1] : null;
            printers[i].setNextPrinter(next);
        }
        printers[threadsAmount - 1].setNextPrinter(printers[0]);

        // start threads
        for (int i = 0; i < threadsAmount; i++) {
            printers[i].start();
        }
        printers[0].interrupt();
    }
}
