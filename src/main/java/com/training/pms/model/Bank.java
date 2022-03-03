package com.training.pms.model;

import java.util.Objects;

public class Bank {
 private int accountId;
 private String accountName;
 private int balance;
 private String password;
 private String accountType;
 private boolean status;
 
 

public Bank() {
	 
 }
public Bank(String accountName,  String password, int balance, String accountType, boolean status) {
	super();
	this.accountName = accountName;
	this.balance = balance;
	this.password = password;
	this.accountType = accountType;
	this.status = status;
}
	
	public Bank(String accountName,  String password, int balance, String accountType) {
		super();
		this.accountName = accountName;
		this.balance = balance;
		this.password = password;
		this.accountType = accountType;
	
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public String getAccountName() {
	return accountName;
}
public void setAccountName(String accountName) {
	this.accountName = accountName;
}
public int getBalance() {
	return balance;
}
public void setBalance(int balance) {
	this.balance = balance;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getAccountType() {
	return accountType;
}
public void setAccountType(String accountType) {
	this.accountType = accountType;
}
public boolean getStatus() {
	return status;
}
public void setStatus(boolean status) {
	this.status = status;
}


@Override
public int hashCode() {
	return Objects.hash(accountId, accountName, accountType, balance, password, status);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Bank other = (Bank) obj;
	return accountId == other.accountId && Objects.equals(accountName, other.accountName)
			&& Objects.equals(accountType, other.accountType) && balance == other.balance
			&& Objects.equals(password, other.password) && status == other.status;
}
@Override
public String toString() {
	return "Bank [accountId=" + accountId + ", accountName=" + accountName + ", balance=" + balance + ", password="
			+ password + ", accountType=" + accountType + ", status=" + status + "]";
}



 
 
}