package telran.bank.model;

public class BankAccount {
    private long account;
    private String owner = "Anonymous";
    private String bank;
    private String branch;
    private double balance;

    public BankAccount() {
    }

    public BankAccount(long account, String owner, double balance) {
        this.account = account;
        this.owner = owner;
        this.balance = balance;
        this.bank = "Bank#1";
        this.branch = "Branch#1";
    }

    public BankAccount(long account, String owner, String bank, String branch, double balance) {
        this.account = account;
        this.owner = owner;
        this.bank = bank;
        this.branch = branch;
        this.balance = balance;
    }

    public BankAccount(long account, String bank, String branch) {
        this.account = account;
        this.bank = bank;
        this.branch = branch;
    }

    public boolean deposit(double sum){
        boolean isSuccess = false;
        if (sum >= 0){
            balance += sum;
            isSuccess = true;
        }
        return isSuccess;
    }

    public boolean withdraw(double sum){
        boolean isSuccess = false;
        if (sum < balance){
            balance -= sum;
            isSuccess = true;
        }
        return isSuccess;
    }

    public long getAccount() {
        return account;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public String getBranch() {
        return branch;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }


    public void display(){
        System.out.println("Acc.: " + account + ", Owner: " + owner + ", balance: " + balance);
    }

    public void display(String greeting){
        System.out.println("Acc.: " + account + ", Owner: " + greeting + " " + owner + ", balance: " + balance);
    }
}
