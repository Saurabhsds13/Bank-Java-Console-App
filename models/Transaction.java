package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

	private String type;
	private double amount;
	private String timestamp;

	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
		this.timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	public void displayTransaction() {
		System.out.println(timestamp + " | " + type + " | ₹" + amount);
	}
}
