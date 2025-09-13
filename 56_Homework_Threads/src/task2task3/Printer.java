package task2task3;

public class Printer extends Thread {
    private int number;
    private int amount;
    private int portion;
    private Printer nextPrinter;
    private static final Object mutex = new Object();

    public Printer(int number, int amount, int portion, Printer nextPrinter) {
        this.number = number;
        this.amount = amount;
        this.portion = portion;
        this.nextPrinter = nextPrinter;
    }

    public void setNextPrinter(Printer nextPrinter) {
        this.nextPrinter = nextPrinter;
    }

    @Override
    public void run() {
        int remain = amount;
        while (remain > 0) {
            try {
                sleep(10_000);
            } catch (InterruptedException e) {
                synchronized (mutex) {
                    int partToPrint = Math.min(portion, remain);
                    for (int i = 0; i < partToPrint; i++) {
                        System.out.print(number);
                        remain--;
                    }
                    System.out.println();
                    if (nextPrinter != null) nextPrinter.interrupt();
                }
            }
        }
    }
}
