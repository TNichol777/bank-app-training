package com.training.pms.dao;

import java.sql.SQLException;
import java.util.List;

import com.training.pms.model.Bank;

public interface BankDAO {

	public boolean addBankAccount(Bank bank);

	public boolean updateBankAccount(String accountName);
	
	//public Bank searchByAccountName(String accountName);

	public boolean doesAccountExist(String accountName);

	public int getBalance(String accountName);

	public void withdrawal(String accountName, int amount);

	public void deposit(String accountName, int amount);

	public void transfer(String accountName, String accountName2, int amount) throws SQLException;

	public boolean login(String accountName, String password);

	public List<Bank> getBank();

	public Bank getUser(String accountName);
	
	public Bank getAccountType(String accountType);




}
