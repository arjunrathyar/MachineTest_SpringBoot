package com.nissan.dto;

public class CustomerDTO {

	private long accountNo;
	private String customerName;
	private String accountType;
	private double balance;
	private double minimum_Balance;
	private long mobileNumber;
	private String emailId;
	private String panNo;

	public CustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDTO(long accountNo, String customerName, String accountType, double balance, double minimum_Balance,
			long mobileNumber, String emailId, String panNo) {
		super();
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.accountType = accountType;
		this.balance = balance;
		this.minimum_Balance = minimum_Balance;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.panNo = panNo;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}

	public double getMinimum_Balance() {
		return minimum_Balance;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void setMinimum_Balance(double minimum_Balance) {
		this.minimum_Balance = minimum_Balance;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

}
