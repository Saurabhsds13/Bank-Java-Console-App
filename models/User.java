package models;

import java.util.ArrayList;
import java.util.List;

import utilities.SecurityUtil;

public class User {
	private String userId;
	private String name;
	private String passwordHash;
	private List<BankAccount> accounts;

	public User(String userId, String name, String password) {
		this.userId = userId;
		this.name = name;
		this.passwordHash = SecurityUtil.hashPassword(password);
		this.accounts = new ArrayList<>();
	}

	public boolean validatePassword(String password) {
		return SecurityUtil.hashPassword(password).equals(this.passwordHash);
	}

	public String getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public List<BankAccount> getAccounts() {
		return accounts;
	}

	public void addAccount(BankAccount account) {
		accounts.add(account);
	}
}
