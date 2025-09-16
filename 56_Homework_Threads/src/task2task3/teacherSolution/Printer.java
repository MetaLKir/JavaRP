package task2task3.teacherSolution;

public class Printer extends Thread {
    int portionSize;
    int totalAmount;
    Thread nextThread;

    public Printer(int number, int portionSize, int totalAmount) {
        setName(String.valueOf(number));
        this.portionSize = portionSize;
        this.totalAmount = totalAmount;
    }

    public void setNextThread(Thread nextThread) {
        this.nextThread = nextThread;
    }

    @Override
    public void run() {
        int printed = 0;
        while (printed < totalAmount) {
            try {
                sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                int toPrint = Math.min(portionSize, totalAmount - printed);
                for (int i = 0; i < toPrint; i++) {
                    System.out.print(getName());
                }
                System.out.println();
                printed += toPrint;
                if (nextThread != null) nextThread.interrupt();
            }
        }


    }
}
