package com.projec1.model;

public class Transaction {
	private int transactionId;
	private float transfer;
	private float withdraw;
	private float deposit;
	private float totalAmount;
	private int customerid;


	public Transaction() {
		super();

	}

	public Transaction(float transfer, float withdraw, float deposit, float totalAmount, int customerid) {
		super();
		this.transfer = transfer;
		this.withdraw = withdraw;
		this.deposit = deposit;
		this.totalAmount = totalAmount;
		this.customerid = customerid;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
		return "Transaction [transactionId=" + transactionId + ", transfer=" + transfer + ", withdraw=" + withdraw
				+ ", deposit=" + deposit + ", totalAmount=" + totalAmount + "]";
	}

}
