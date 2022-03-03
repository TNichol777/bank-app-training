package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import com.training.pms.model.Bank;
import com.training.pms.utility.DBConnection;

public class BankDAOImpl implements BankDAO {
	Connection connection = DBConnection.getConnection();

	public boolean addBankAccount(Bank bank) {
		System.out.println("##Adding account: " + bank);
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = connection.prepareStatement("insert into login values(default,?,?,?,?,default)");
			statement.setString(1, bank.getAccountName());
			statement.setString(2, bank.getPassword());
			statement.setInt(3, bank.getBalance());
			statement.setString(4, bank.getAccountType());

			rows = statement.executeUpdate();
			bank.setAccountId(0);
			System.out.println(rows + "inserted successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}

	public boolean doesAccountExist(String accountName) {
		boolean accountExists = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("Select * from login where userName = ?");
			stat.setString(1, accountName);

			ResultSet res = stat.executeQuery();
			accountExists = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountExists;
	}

	@Override
	public int getBalance(String accountName) {
		PreparedStatement stat;
		int balance = 0;
		try {
			stat = connection.prepareStatement("Select balance from login where username = ?");
			stat.setString(1, accountName);

			ResultSet res = stat.executeQuery();
			while (res.next()) {
				balance = res.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public void withdrawal(String accountName, int amount) {
		PreparedStatement stat;
		int currentBalance = getBalance(accountName);
		int updatedBalance = currentBalance - amount;
		try {
			stat = connection.prepareStatement("update login set balance = ? where username = ?");
			stat.setInt(1, updatedBalance);
			stat.setString(2, accountName);

			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deposit(String accountName, int amount) {
		PreparedStatement stat;
		int currentBalance = getBalance(accountName);
		int updatedBalance = currentBalance + amount;
		try {
			stat = connection.prepareStatement("update login set balance = ? where username = ?");
			stat.setInt(1, updatedBalance);
			stat.setString(2, accountName);

			stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void transfer(String accountName, String accountName2, int amount) throws SQLException {

	}

	@Override
	public List<Bank> getBank() {
		Statement stat;
		System.out.println("Printing all accounts ");
		List<Bank> Accounts = new ArrayList<Bank>();

		try {
			stat = connection.createStatement();
			ResultSet res = stat.executeQuery("select * from login");
			while (res.next()) {
				Bank bank = new Bank();
				bank.setAccountId(res.getInt(1));
				bank.setAccountName(res.getString(2));
				bank.setPassword(res.getString(3));
				bank.setBalance(res.getInt(4));
				bank.setAccountType(res.getString(5));
				bank.setStatus(res.getBoolean(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Accounts;
	}

	@Override
	public Bank getUser(String accountName) {
		PreparedStatement stat;

		System.out.println("Printing all accounts ");
		Bank bank = new Bank();

		try {
			stat = connection.prepareStatement("select * from login where userName = ?");
			stat.setString(1, accountName);
			ResultSet res = stat.executeQuery();
			res.next();
			bank.setAccountId(res.getInt(1));
			bank.setAccountName(res.getString(2));
			bank.setPassword(res.getString(3));
			bank.setBalance(res.getInt(4));
			bank.setAccountType(res.getString(5));
			bank.setStatus(res.getBoolean(6));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bank;
	}

	@Override
	public boolean updateBankAccount(String accountName) {
		PreparedStatement stat;
		boolean Status = getStatus(accountName);

		System.out.println("UPDATING ACCOUNTS");
		Bank bank = new Bank();

		try {
			stat = connection.prepareStatement("update login set status = true where userName = ?");
			stat.setString(1, accountName);
			int res = stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	private boolean getStatus(String accountName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bank getAccountType(String accountType) {
		PreparedStatement stat;
		Bank bank = new Bank();

		try {
			stat = connection.prepareStatement("select * from login where accounttype = ?");
			stat.setString(1, accountType);
			ResultSet res = stat.executeQuery();
			res.next();
			bank.setAccountId(res.getInt(1));
			bank.setAccountName(res.getString(2));
			bank.setPassword(res.getString(3));
			bank.setBalance(res.getInt(4));
			bank.setAccountType(res.getString(5));
			bank.setStatus(res.getBoolean(6));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bank;
	}

	@Override
	public boolean login(String accountName, String password) {
		boolean accountExists = false;
		PreparedStatement stat;
		try {
			stat = connection.prepareStatement("Select * from login where userName = ? and password = ?");
			stat.setString(1, accountName);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();
			accountExists = res.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accountExists;
	}

}
