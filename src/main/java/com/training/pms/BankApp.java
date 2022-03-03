package com.training.pms;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.BankDAO;
import com.training.pms.dao.BankDAOImpl;
import com.training.pms.model.Bank;
import com.training.pms.utility.DBConnection;

public class BankApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	BankDAO bankDAO = new BankDAOImpl();
	boolean result;
	Bank bank = new Bank();
	List<Bank> Accounts = new ArrayList<Bank>();

	public void startBankApp() throws IOException, SQLException {
		int accountId = 0;
		String accountName = null;
		Integer balance = null;
		String password = null;
		String accountType;

		while (true) { // MAIN MENU LOOP
			System.out.println("====================================");
			System.out.println("BANKING  -  APP  -  MENU");
			System.out.println("1. Register ");
			System.out.println("2. Login ");
			System.out.println("9. E X I T ");
			System.out.println("====================================");
			System.out.println("Please enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				System.out.println("WELCOME TO THE REGISTRATION SECTION");

				System.out.println("C Customer or E Employee: ");
				accountType = scanner.next();

				System.out.println("Please enter account username: ");
				accountName = scanner.next();

				if (bankDAO.doesAccountExist(accountName)) {
					System.out.println(
							"account with username: " + accountName + " already exists, please try another username");
					continue;
				}

				System.out.println("Please enter account password: ");
				password = scanner.next();

				System.out.println("Please enter account starting balance: ");
				balance = scanner.nextInt();

				bank = new Bank(accountName, password, balance, accountType);

				bankDAO.addBankAccount(bank);
				System.out.println("Congratulations, your account: " + accountName + " has been created");

				break;

			case 2:
				System.out.println("LOGIN TO YOUR ACCOUNT");

				// TAKE INPUT FROM USER
				System.out.println("Please enter account username: ");
				accountName = scanner.next();
				System.out.println("Please enter account password: ");
				password = scanner.next();

				if (!bankDAO.login(accountName, password)) {
					System.out.println("Error logging in: Make sure your username and password is correct");
					continue;
				}
				Bank user = bankDAO.getUser(accountName);
				if (!user.getStatus()) {
					System.out.println("ACCOUNT IS NOT ACTIVE");

					continue;
				}

				while (true) { // CUSTOMER MENU LOOP
					System.out.println("1: View current balance");
					System.out.println("2: Make a withdrawal");
					System.out.println("3: Make a depsit");
					System.out.println("4: Transfer funds");
					System.out.println("5: Logout");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println("++++++++++++++++++++++++++");
					System.out.println("6: View pending accounts");
					System.out.println("7: Approve accounts");
					System.out.println("5: Logout");
					choice = scanner.nextInt();

					switch (choice) {
					case 1:
						System.out.println("Current Balance is: " + bankDAO.getBalance(accountName));
						break;
					case 2: // update withdrawal
						System.out.println("Enter withdrawal amount: ");
						int amountToWithdraw = scanner.nextInt();
						bankDAO.withdrawal(accountName, amountToWithdraw);
						System.out.println("Withdrawal successfull");
						break;
					case 3: // update deposit
						System.out.println("Enter deposit amount: ");
						int amountToDeposit = scanner.nextInt();
						bankDAO.deposit(accountName, amountToDeposit);
						System.out.println("Deposit successfull");
						break;
					case 4:
						// transfer
						System.out.println("Transfer funds");
						int debitorBalance = 0, creditorBalance = 0;

						System.out.println("Enter the account number to debit the amount :");
						int sender = scanner.nextInt();

						System.out.println("Enter the account number to credit the amount :");
						int reciever = scanner.nextInt();

						System.out.println("Enter the amout to be  transferred :");
						int amount = scanner.nextInt();
						
						Connection connection = DBConnection.getConnection();
						CallableStatement stat = connection.prepareCall("call transferAmountAndGetbalance(?,?,?,?,?)");
						stat.setInt(1, sender);
						stat.setInt(2, reciever);
						stat.setInt(3, amount);
						
						stat.registerOutParameter(4, Types.INTEGER);
						stat.setInt(4, debitorBalance);

						stat.registerOutParameter(5, Types.INTEGER);
						stat.setInt(5, creditorBalance);
						
						stat.execute();
						
						debitorBalance = stat.getInt(4);
						creditorBalance = stat.getInt(5);
						
						System.out.println("Transfer done/completed and the balance is : ");
						System.out.println("Debitor balance :"+debitorBalance);
						System.out.println("Creditor balance :"+creditorBalance);

						break;
					case 5: // view status false
						System.out.println("Thank you for using Bank TN");
						System.exit(0);
						break;
					case 6: // VIEW ACCOUNTS
						/*
						 * bank = bankDAO.getBank(); if (Accounts.size() == 0) {
						 * System.out.println("NO ACCOUNTS"); continue; } printBankDetails(Accounts);
						 * break;
						 */

					case 7: // UPDATE accounts
						System.out.println("Enter account Name to update");
						accountName = scanner.next();

						if (!bankDAO.doesAccountExist(accountName)) {
							System.out.println("Account " + accountName + "does not exist");
							continue;
						}
						bank = new Bank();
						bankDAO.updateBankAccount(accountName);
						System.out.println(accountName + " APPROVED");
						break;

					default:
						System.out.println("Inavlid choice, please enter (1-4) or 5 to EXIT");
					}
					break;

				}

			case 9: // EXIT
				System.out.println("Thank you for using Bank TN");
				System.exit(0);

			default:
				System.out.println("Inavlid choice, please enter (1 or 2) or 9 to EXIT");
				break;
			}
		}
	}

	public void printBankDetails(List<Bank> Accounts) {
		Iterator<Bank> iterator = Accounts.iterator();
		while (iterator.hasNext()) {
			Bank temp = iterator.next();
			System.out.println(temp);
		}
	}
}
