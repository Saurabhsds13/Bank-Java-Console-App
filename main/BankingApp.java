package main;

import java.util.Scanner;

import services.BankService;
import services.BankServiceImpl;

public class BankingApp {

	public static void main(String[] args) {

		BankService bankService = new BankServiceImpl();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("\n===== Banking System =====");
			System.out.println("1. Create Account");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter User ID: ");
				String userId = scanner.nextLine();
				System.out.print("Enter Name: ");
				String name = scanner.nextLine();
				System.out.print("Enter Password: ");
				String password = scanner.nextLine();
				System.out.print("Enter Account Type (Savings/Checking): ");
				String accountType = scanner.nextLine();
				bankService.createAccount(userId, name, password, accountType);
				break;
			case 2:
				System.out.print("Enter User ID: ");
				String loginId = scanner.nextLine();
				System.out.print("Enter Password: ");
				String loginPassword = scanner.nextLine();
				if (bankService.login(loginId, loginPassword)) {
					System.out.println("Login Successful!");
					handleBankingOperations(scanner, bankService, loginId);
				} else {
					System.out.println("Invalid Credentials!");
				}
				break;
			case 3:
				System.out.println("Thank you for using our Banking System!");
				scanner.close();
				return;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private static void handleBankingOperations(Scanner scanner, BankService bankService, String userId) {

		while (true) {
			System.out.println("\n===== Banking Operations =====");
			System.out.println("1. Deposit");
			System.out.println("2. Withdraw");
			System.out.println("3. Transfer");
			System.out.println("4. Check Balance");
			System.out.println("5. Logout");
			System.out.print("Choose an option: ");

			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				System.out.print("Enter Account Number: ");
				String depositAcc = scanner.nextLine();
				System.out.print("Enter Amount: ");
				double depositAmount = scanner.nextDouble();
				bankService.deposit(depositAcc, depositAmount);
				System.out.println("Deposit Successful.");
				break;
			case 2:
				System.out.print("Enter Account Number: ");
				String withdrawAcc = scanner.nextLine();
				System.out.print("Enter Amount: ");
				double withdrawAmount = scanner.nextDouble();
				if (bankService.withdraw(withdrawAcc, withdrawAmount)) {
					System.out.println("Withdrawal Successful.");
				} else {
					System.out.println("Insufficient Funds.");
				}
				break;
			case 3:
				System.out.print("Enter Source Account Number: ");
				String fromAcc = scanner.nextLine();
				System.out.print("Enter Destination Account Number: ");
				String toAcc = scanner.nextLine();
				System.out.print("Enter Amount: ");
				double transferAmount = scanner.nextDouble();
				bankService.transfer(fromAcc, toAcc, transferAmount);
				System.out.println("Transfer Successful.");
				break;
			case 4:
				System.out.print("Enter Account Number: ");
				String balanceAcc = scanner.nextLine();
				System.out.println("Current Balance: " + bankService.checkBalance(balanceAcc));
				break;

			case 5:
				bankService.showTransactionHistory();
				break;
			case 6:
				System.out.println("Logging out...");
				return;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}
}
