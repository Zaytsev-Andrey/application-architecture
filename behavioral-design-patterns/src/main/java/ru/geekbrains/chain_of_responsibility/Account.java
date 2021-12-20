package ru.geekbrains.chain_of_responsibility;

public class Account {

    private Account successor;

    private final double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    protected void setSuccessor(Account successor) {
        this.successor = successor;
    }

    public void pay(double amountToPay) {
        if (canPay(amountToPay)) {
            System.out.printf("Paid %s using %s%n", amountToPay, this.getClass().getSimpleName());
        } else if (successor != null) {
            System.out.printf("Cannot pay using %s. Proceeding ..%n", this.getClass().getSimpleName());
            successor.pay(amountToPay);
        } else {
            throw new RuntimeException("None of the accounts have enough balance");
        }
    }

    private boolean canPay(double amount) {
        return balance >= amount;
    }

}
