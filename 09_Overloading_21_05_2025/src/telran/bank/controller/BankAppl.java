package telran.bank.controller;

import telran.bank.model.BankAccount;

public class BankAppl {
    public static void main(String[] args) {
        BankAccount[] bankAccounts = new BankAccount[6];

        System.out.println(bankAccounts[0]);
        bankAccounts[0] = new BankAccount(1000, "John", 1000);
        bankAccounts[0].display();
        System.out.println(bankAccounts[0].deposit(500));
        System.out.println(bankAccounts[0].getBalance());
        System.out.println(bankAccounts[0].withdraw(200));

        bankAccounts[1] = new BankAccount(200, "Peter", 5000);

        bankAccounts[2] = new BankAccount(3000, "Mary", "Bank#2", "Branch#1", 10_000);

        bankAccounts[3] = new BankAccount(4000, "Bank#3", "Branch#3");
        bankAccounts[3].display();

        System.out.println("=============================");
//        BankAccount ba = new BankAccount();
//        ba.display();
        bankAccounts[0].display("Not-mammoth");

        System.out.println("=============================");
        printBank(bankAccounts);
        System.out.println("Total balance: " + totalBalance(bankAccounts));

        System.out.println("============Wrapper===========");
        int num = Integer.parseInt("100500");
        System.out.println(num);
    }

    private static void printBank(BankAccount[] bankAccounts) {
        for (BankAccount acc : bankAccounts) {
            if (acc != null)
                acc.display();
        }
    }

    public static double totalBalance(BankAccount[] accs){
        double res = 0;
        for(BankAccount acc : accs){
            if (acc != null)
                res += acc.getBalance();
        }
        return res;
    }
}
