package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import models.BankAccount;
import models.User;

public class BankServiceImpl implements BankService {

	private Map<String, User> users = new HashMap<>();
	private Map<String, BankAccount> accounts = new HashMap<>();
	private List<String> transactions = new ArrayList<>();

	@Override
	public void createAccount(String userId, String name, String password, String accountType) {

		if (users.containsKey(userId)) {
			throw new RuntimeException("User already exists");
		}

		User user = new User(userId, name, password);
		String accountNumber = UUID.randomUUID().toString().substring(0, 8);
		BankAccount account = new BankAccount(accountNumber, accountType, user);
		user.addAccount(account);
		users.put(userId, user);
		accounts.put(accountNumber, account);
		System.out.println("Account created successfully. Account Number: " + accountNumber);
	}

	@Override
	public boolean login(String userId, String password) {
		return users.containsKey(userId) && users.get(userId).validatePassword(password);
	}

	@Override
	public void deposit(String accountNumber, double amount) {
		if (!accounts.containsKey(accountNumber)) {
			throw new RuntimeException("Account not found");
		}
		accounts.get(accountNumber).deposit(amount);
		transactions.add("Deposited " + amount + " to " + accountNumber);
	}

	@Override
	public boolean withdraw(String accountNumber, double amount) {
		if (!accounts.containsKey(accountNumber)) {
			throw new RuntimeException("Account not found");
		}
		boolean success = accounts.get(accountNumber).withdraw(amount);
		if (success) {
			transactions.add("Withdrew " + amount + " from " + accountNumber);
		}
		return success;
	}

	@Override
	public void transfer(String fromAccount, String toAccount, double amount) {
		if (!accounts.containsKey(fromAccount) || !accounts.containsKey(toAccount)) {
			throw new RuntimeException("Invalid account number(s)");
		}
		accounts.get(fromAccount).transfer(accounts.get(toAccount), amount);
		transactions.add("Transferred " + amount + " from " + fromAccount + " to " + toAccount);
	}

	@Override
	public double checkBalance(String accountNumber) {
		if (!accounts.containsKey(accountNumber)) {
			throw new RuntimeException("Account not found");
		}
		return accounts.get(accountNumber).getBalance();
	}

	@Override
	public void showTransactionHistory() {
		transactions.forEach(System.out::println);
	}
}
