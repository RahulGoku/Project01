package com.project1.service.impl;

import com.projec1.model.User;
import com.project1.dao.LoginDAO;
import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;
import com.project1.service.LoginService;

public class LoginServiceImpl implements LoginService {
private LoginDAO loginDAO=new LoginDAOImpl();
	
	
	public boolean isValidLoginCredentials(User user) throws BankingException {
		
		boolean b=false;
		if(user!=null && user.getUsername()!=null && user.getPassword()!=null ) {
			
			//code here for DAO
			b=loginDAO.isValidLoginCredentials(user);
		}else {
			throw new BankingException("Invalid Username or Password");
		}
		
		return b;
	}


	@Override
	public boolean isValidLoginCredentialsCustmoer(User user) throws BankingException {
		boolean b=false;
		if(user!=null && user.getUsername()!=null && user.getPassword()!=null ) {
			
			//code here for DAO
			b=loginDAO.isValidLoginCredentialsCustomer(user);
		}else {
			throw new BankingException("Invalid Username or Password");
		}
		
		return b;
	}

}
