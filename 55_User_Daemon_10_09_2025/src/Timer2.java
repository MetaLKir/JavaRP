import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer2 extends Thread {
    private long timeUnit = 1000;
    private String timeFormat = "HH:mm:ss";

    public Timer2() {
        setDaemon(true);
    }

    public void setTimeUnit(long timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    @Override
    public void run() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(timeFormat);
        LocalTime lt = null;
        while (true) {
            lt = LocalTime.now();
            System.out.println(lt.format(dtf));
            try {
                sleep(timeUnit);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Timer2 timer = new Timer2();
        timer.setTimeFormat("hh:mm:ss a");
        timer.start();
        Thread.sleep(5000);
//        System.out.println(timer.isInterrupted()); // false
        timer.interrupt();
//        System.out.println(timer.isInterrupted()); // true
        timer = new Timer2();
        timer.start();
        Thread.sleep(5000);
    }
}
