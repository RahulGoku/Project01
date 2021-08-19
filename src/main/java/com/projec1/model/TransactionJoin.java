package com.projec1.model;

public class TransactionJoin {
	private String customerName;
	private String city;
	private String state;
	private String country;
	private String panNumber;
	private float transfer;
	private float withdraw;
	private float deposit;
	private float totalAmount;
	private int customerid;
	
	
	public TransactionJoin() {
		super();
		// TODO Auto-generated constructor stub
	}


	public TransactionJoin(String customerName, String city, String state, String country, String panNumber,
			float transfer, float withdraw, float deposit, float totalAmount, int customerid) {
		super();
		this.customerName = customerName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.panNumber = panNumber;
		this.transfer = transfer;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.totalAmount = totalAmount;
		this.customerid = customerid;
	}
    
	

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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


	public float getTransfer() {
		return transfer;
	}


	public void setTransfer(float transfer) {
		this.transfer = transfer;
	}


	public float getWithdraw() {
		return withdraw;
	}


	public void setWithdraw(float withdraw) {
		this.withdraw = withdraw;
	}


	public float getDeposit() {
		return deposit;
	}


	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}


	public float getTotalAmount() {
		return totalAmount;
	}


	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}


	public int getCustomerid() {
		return customerid;
	}


	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}


	@Override
	public String toString() {
		return "TransactionJoin [customerName=" + customerName + ", city=" + city + ", state=" + state + ", country="
				+ country + ", panNumber=" + panNumber + ", transfer=" + transfer + ", withdraw=" + withdraw
				+ ", deposit=" + deposit + ", totalAmount=" + totalAmount + ", customerid=" + customerid + "]";
	}
	
	
	
}
