package com.project1.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.project1.dao.impl.LoginDAOImpl;
import com.project1.exception.BankingException;

class LoginDAOTest {

	private static LoginDAO service;

	@BeforeAll
	public static void setup() {
		service = new LoginDAOImpl();
	}

	@Test
	void testCreateAccount() {
		fail("Not yet implemented");
	}


	@Test
	void testGetEmployeeByEmailId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeByPassword() {
		fail("Not yet implemented");
	}

	@Test
	void testOpenAccount() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTransactionCredit() {
		try {
			assertEquals(1000f,service.getTransactionCredit(4));
		} catch (BankingException e) {
			fail("No Account Found");
		}
	}

	@Test
	void testGetTransactionDebit() {
		try {
			assertEquals(1000f,service.getTransactionCredit(4));
		} catch (BankingException e) {
			fail("No Account Found");
		}
	}

	@Test
	void testGetTransactionTransfer() {
		try {
			assertEquals(1000f,service.getTransactionCredit(4));
		} catch (BankingException e) {
			fail("No Account Found");
		}
	}

	@Test
	void testGetTransactionTotalAmount() {
		try {
			assertEquals(1000f,service.getTransactionCredit(4));
		} catch (BankingException e) {
			fail("No Account Found");
		}
	}

	

	

	@Test
	void testGetCustomerByCustomerIdString() {
		try {
			assertEquals(4,service.getCustomerByCustomerId("rrjn@1gmail.com"));
		} catch (BankingException e) {
		 fail("Account not Found");
			
		}
		
	}

	@Test
	void testGetCustomerByCustomerIdInt() {
		try {
			assertEquals("rrjn@1gmail.com",service.getCustomerByCustomerId("rrjn@1gmail.com"));
		} catch (BankingException e) {
		 fail("Account not Found");
			
		}
	}


	@Test
	void testGetCheckForAccount() {
		try {
			assertTrue(service.getCheckForAccount(10));
		} catch (BankingException e) {
			fail("No Account");
		}
	}

}
