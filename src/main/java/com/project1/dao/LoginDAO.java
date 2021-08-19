package com.project1.dao;

import java.util.List;

import com.projec1.model.Customer;
import com.projec1.model.Transaction;
import com.projec1.model.User;
import com.project1.exception.BankingException;

public interface LoginDAO {
	public boolean isValidLoginCredentials(User user) throws BankingException;
	public boolean isValidLoginCredentialsCustomer(User user) throws BankingException;
	public List<Transaction> getTransaction()throws BankingException;
	public int createAccount(Customer customer) throws BankingException;
	public Transaction openAccount(Transaction transaction) throws BankingException;

	public int updateCustomerId(String userId,String name) throws BankingException;
	public void updateCustomerId(int userId, int transactionId) throws BankingException;
	
	
	public int getCustomerId(String userId) throws BankingException;
	public int getCustomerByCustomerId(String userEmail) throws BankingException;
 
	public boolean getCheckForAccount(int id) throws BankingException;
	

    public float getTransactionCredit(int accountId) throws BankingException;
	
	public float getTransactionDebit(int accountId) throws BankingException;

	public float getTransactionTransfer(int accountId) throws BankingException;
	
	public float getTransactionTotalAmount(int accountId) throws BankingException;
	
	
	public int deleteCustomerId(String userId) throws BankingException;
	public int deleteAccountId(int accountId) throws BankingException;

	public List<Transaction> getViewStatement(int accountId)throws BankingException;
}
