package services;

public interface BankService {
	void createAccount(String userId, String name, String password, String accountType);

	boolean login(String userId, String password);

	void deposit(String accountNumber, double amount);

	boolean withdraw(String accountNumber, double amount);

	void transfer(String fromAccount, String toAccount, double amount);

	double checkBalance(String accountNumber);

	void showTransactionHistory();
}