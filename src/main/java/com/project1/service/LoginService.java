package com.project1.service;

import com.projec1.model.User;
import com.project1.exception.BankingException;

public interface LoginService {
	public boolean isValidLoginCredentials(User user) throws BankingException;
	public boolean isValidLoginCredentialsCustmoer(User user) throws BankingException;
}
