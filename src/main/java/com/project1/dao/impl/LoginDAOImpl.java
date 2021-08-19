package com.project1.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.projec1.model.Customer;
import com.projec1.model.Transaction;
import com.projec1.model.User;
import com.project1.dao.LoginDAO;
import com.project1.dbutil.PostgresConnection;
import com.project1.exception.BankingException;

public class LoginDAOImpl implements LoginDAO{
	private static Logger log = Logger.getLogger(LoginDAOImpl.class);
	public boolean isValidLoginCredentials(User user) throws BankingException {
		boolean b=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select emp_email_id from project0.employee where emp_email_id=? and emp_password=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}else {
				log.info("Invaid Login Credentials");
				throw new BankingException("Invaid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return b;
	}

	@Override
	public List<Transaction> getTransaction() throws BankingException {
		List<Transaction> transactionList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select accountid,transfer,withdraw,deposit,total_amount,transactionid from project0.transaction";
			//String sql="select * from project0.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
			    Transaction transaction=new Transaction();
				transaction.setTransactionId(resultSet.getInt("accountid"));
				transaction.setTransfer(resultSet.getFloat("transfer"));
				transaction.setWithdraw(resultSet.getFloat("withdraw"));
				transaction.setDeposit(resultSet.getFloat("deposit"));
				transaction.setTotalAmount(resultSet.getFloat("total_amount"));
				transactionList.add(transaction);
			}if(transactionList.size()==0) {
				log.info("No Account exists as of now.. go ahead create one...");
				throw new BankingException("No Account exists as of now.. go ahead create one...");
			}
		}
			catch (ClassNotFoundException | SQLException e) {
				log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		return transactionList;
	}

	@Override
	public int createAccount(Customer customer) throws BankingException {
		int c=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into project0.customer(customer_name,contact_no,email_id,city,state,country,pan_number,password) values(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setInt(2, customer.getContactNumber());
			preparedStatement.setString(3, customer.getEmailId());
			preparedStatement.setString(4, customer.getCity());
			preparedStatement.setString(5, customer.getState());
			preparedStatement.setString(6, customer.getCountry());
			preparedStatement.setString(7, customer.getPanNumber());
			preparedStatement.setString(8, customer.getPassword());

			c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					customer.setCustomerId(resultSet.getInt(1));
				}
			} else {
				log.info("User Registration Failure Please Retry!!!");
				throw new BankingException("User Registration Failure Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}

		return c;
	}

	@Override
	public boolean isValidLoginCredentialsCustomer(User user) throws BankingException {
		boolean b=false;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select email_id from project0.customer where email_id=? and password=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				b=true;
			}else {
				log.info("Invaid Login Credentials");
				throw new BankingException("Invaid Login Credentials");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured.. Contact SYSADMIN!!!!!!!!!!!!!!!!!");
		}
		return b;
	}

	@Override
	public Transaction openAccount(Transaction transaction) throws BankingException {
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql = "insert into project0.transaction(transfer,withdraw,deposit,total_amount,accountid) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setFloat(1, transaction.getTransfer());
			preparedStatement.setFloat(2, transaction.getWithdraw());
			preparedStatement.setFloat(3, transaction.getDeposit());
			preparedStatement.setFloat(4, transaction.getTotalAmount());
			preparedStatement.setInt(5, transaction.getCustomerid());

			int c = preparedStatement.executeUpdate();
			if (c == 1) {
				ResultSet resultSet = preparedStatement.getGeneratedKeys();
				if (resultSet.next()) {
					transaction.setTransactionId(resultSet.getInt(1));
				}
			} else {
				log.info("User Account Opening Failure Please Retry!!!");
				throw new BankingException("User Account Opening Failure Please Retry!!!");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}

		return transaction;
	}

	
	@Override
	public float getTransactionCredit(int accountId) throws BankingException {
		float cr=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select deposit from project0.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  cr=resultSet.getFloat("deposit");
			} else {
				log.info("No Account found with id " + accountId);
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return cr;
	}

	@Override
	public float getTransactionDebit(int accountId) throws BankingException {
		float db=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select withdraw from project0.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  db=resultSet.getFloat("withdraw");
			} else {
				log.info("No Account found with id " + accountId);
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return db;
	}

	@Override
	public float getTransactionTransfer(int accountId) throws BankingException {
		float tf=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select total_amount from project0.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  tf=resultSet.getFloat("total_amount");
			} else {
				log.info("No Account found with id " + accountId);
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return tf;
		
	}

	@Override
	public float getTransactionTotalAmount(int accountId) throws BankingException {
		float ta=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select total_amount from project0.transaction where accountid=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  ta=resultSet.getFloat("total_amount");
			} else {
				log.info("No Account found with id " + accountId);
				throw new BankingException("No Account found with id " + accountId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return ta;
	}

	@Override
	public List<Transaction> getViewStatement(int accountId) throws BankingException {
		List<Transaction> transactionList=new ArrayList<>();
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select transactionid,transfer,withdraw,deposit,total_amount,accountid from project0.transaction where accountid=?";
			//String sql="select * from project0.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountId);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
			    Transaction transaction=new Transaction();
				transaction.setTransactionId(resultSet.getInt("transactionid"));
				transaction.setTransfer(resultSet.getFloat("transfer"));
				transaction.setWithdraw(resultSet.getFloat("withdraw"));
				transaction.setDeposit(resultSet.getFloat("deposit"));
				transaction.setTotalAmount(resultSet.getFloat("total_amount"));
				transaction.setCustomerid(resultSet.getInt("accountid"));
				transactionList.add(transaction);
			}if(transactionList.size()==0) {
				log.info("No Account exists as of now.. go ahead create one...");
				throw new BankingException("No Account exists as of now.. go ahead create one...");
			}
		}
			catch (ClassNotFoundException | SQLException e) {
				log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		
		return transactionList;
	}

	@Override
	public int getCustomerByCustomerId(String userEmail) throws BankingException {
		 int id=0;
			try (Connection connection = PostgresConnection.getConnection()) {
				String sql  = "select customerid from project0.customer where email_id=?";
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1,userEmail);
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
				  id=resultSet.getInt("customerid");
				} else {
					log.info("No Account found with id " + userEmail);
					throw new BankingException("No Account found with id " + userEmail);
				}
			} catch (ClassNotFoundException | SQLException e) {
				log.error(e);
				throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
			}
			return id;
	}

	@Override
	public boolean getCheckForAccount(int id) throws BankingException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="select accountid from project0.transaction where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				return true;
			}else {
				return false;
				
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);;//logger
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
	}

	@Override
	public int updateCustomerId(String userId, String name) throws BankingException {
		int i=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="update project0.customer set customer_name=? where email_id=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,userId);
			i=preparedStatement.executeUpdate(); 
			if(i==0) {
				throw new BankingException("Updation Error...");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured...Kindly conatct SYSADMIN.......");
		}
		return i;
		
	}

	@Override
	public int deleteCustomerId(String userId) throws BankingException {
		int i=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="delete from  project0.customer where  email_id =? ";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,userId);
			i=preparedStatement.executeUpdate(); 
			if(i==0) {
				throw new BankingException("Deletion Error...");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured...Kindly conatct SYSADMIN.......");
		}
		return i;
	}

	@Override
	public int getCustomerId(String userId) throws BankingException {
		int id=0;
		try (Connection connection = PostgresConnection.getConnection()) {
			String sql  = "select accountid from project0.customer where email_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
			  id=resultSet.getInt("accountid");
			} else {
				throw new BankingException("No Account found with id " + userId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured... Kindly conatct SYSADMIN........");
		}
		return id;
	}

	@Override
	public int deleteAccountId(int accountId) throws BankingException {
		int i=0;
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="delete  from project0.transaction  where accountid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1,accountId);
			i=preparedStatement.executeUpdate(); 
			if(i==0) {
				log.info("Deletion Error...");
				throw new BankingException("Deletion Error...");
			}
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured...Kindly conatct SYSADMIN.......");
		}
		return i;
	}

	@Override
	public void updateCustomerId(int userId, int transactionId) throws BankingException {
		try(Connection connection=PostgresConnection.getConnection()){
			String sql="update project0.customer set accountid=? where customerid=?";
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1,transactionId);
			preparedStatement.setInt(2,userId);
			int i=preparedStatement.executeUpdate(); 
			if(i==0) {
				log.info("Not updated");
				System.out.println("Not updated");

			}
			else System.out.println("Updated");
		}catch (ClassNotFoundException | SQLException e) {
			log.error(e);
			throw new BankingException("Internal error occured...Kindly conatct SYSADMIN.......");
		}
		
		
	}
	
}
