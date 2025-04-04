package models;

public class BankAccount {

	private String accountNumber;
	private double balance;
	private String type;
	private User owner;

	public BankAccount(String accountNumber, String type, User owner) {
		this.accountNumber = accountNumber;
		this.type = type;
		this.owner = owner;
		this.balance = 0.0;
	}

	public synchronized void deposit(double amount) {
		balance += amount;
	}

	public synchronized boolean withdraw(double amount) {
		if (amount > balance) {
			return false;
		}
		balance -= amount;
		return true;
	}

	public synchronized void transfer(BankAccount recipient, double amount) {
		if (withdraw(amount)) {
			recipient.deposit(amount);
		} else {
			throw new RuntimeException("Insufficient funds");
		}
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public String getType() {
		return type;
	}
}
