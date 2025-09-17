import java.util.concurrent.locks.ReentrantLock;

public class AccountR {
    int balance;
    final ReentrantLock lock = new ReentrantLock();

    public AccountR(int balance) {
        this.balance = balance;
    }
}
