public class AccountM {
    int balance;
    final Object mutex = new Object();

    public AccountM(int balance) {
        this.balance = balance;
    }
}
