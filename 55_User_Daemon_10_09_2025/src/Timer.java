import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer extends Thread {
    private long timeUnit = 1000;
    private String timeFormat = "HH:mm:ss";

    public Timer() {
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
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.setTimeFormat("HH:mm:ss");
        timer.start();
        Thread.sleep(5000);
        System.out.println(timer.isAlive());
    }
}
