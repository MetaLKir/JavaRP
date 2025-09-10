public class Printer extends Thread {
    char symbol;
    int numPrints;

    public Printer(char symbol, int numPrints) {
        this.symbol = symbol;
        this.numPrints = numPrints;
    }

    @Override
    public void run() {
        for (int i = 0; i < numPrints; i++) {
            System.out.print(symbol);
            try {
                sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
