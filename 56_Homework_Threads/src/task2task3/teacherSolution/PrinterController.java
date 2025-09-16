package task2task3.teacherSolution;

public class PrinterController {
    private static final int COUNT_OF_THREADS = 5;
    private static final int PORTION_SIZE = 10;
    private static final int TOTAL_AMOUNT = 34;

    public static void main(String[] args) throws InterruptedException {
        Printer[] printers = new Printer[COUNT_OF_THREADS];

        for (int i = 0; i < printers.length; i++) {
            printers[i] = new Printer(i + 1, PORTION_SIZE, TOTAL_AMOUNT);
        }

        for (int i = 0; i < printers.length; i++) {
            printers[i].setNextThread(printers[(i + 1) % COUNT_OF_THREADS]);
        }

        for (Printer p : printers) {
            p.start();
        }
        printers[0].interrupt();

        for (Thread p : printers) {
            try {
                p.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
