package com.projec1.model;

public class Customer {
	
	private int customerId;
	private String name;
	private int contactNumber;
	private String emailId;
	private String city;
	private String state;
	private String country;
	private String panNumber;
	private String password;
	private int  accountId;
	
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, int contactNumber, String emailId, String city, String state, String country,
			String panNumber, String password) {
		super();
		this.name = name;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.city = city;
		this.state = state;
		this.country = country;
		this.panNumber = panNumber;
		this.password = password;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", contactNumber=" + contactNumber
				+ ", emailId=" + emailId + ", city=" + city + ", state=" + state + ", country=" + country
				+ ", panNumber=" + panNumber + ", password=" + password + "]";
	}

	

}
