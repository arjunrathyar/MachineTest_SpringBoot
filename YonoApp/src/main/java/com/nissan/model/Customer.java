package com.nissan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Customer {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	// @Column(name = "customerID")
	// private int custId;
	@Column(name = "accountNo", nullable = false)
	private long accountNo;

	@Column(name = "customerName", nullable = false, length = 30)
	private String customerName;

	@Column(name = "accountType", nullable = false, length = 20)
	private String accountType;

	@Column(name = "balance", nullable = false)
	private double balance;

	@Column(name = "minimum_Balance", nullable = false)
	private double minimum_Balance;

	@Column(name = "mobileNumber", nullable = false)
	private long mobileNumber;

	@Column(name = "emailId", nullable = false, length = 25)
	private String emailId;

	@Column(name = "atmPin", nullable = false)
	private int atmPin;

	@Column(name = "panNo", nullable = false)
	private String panNo;

	@Column(name = "isActive", nullable = false)
	private boolean isActive = true;

	private Integer userId;
	@OneToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private Users user;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int custId, long accountNo, String customerName, String accountType, double balance,
			double minimum_Balance, long mobileNumber, String emailId, int atmPin, String panNo, boolean isActive,
			Integer userId, Users user) {
		super();
		// this.custId = custId;
		this.accountNo = accountNo;
		this.customerName = customerName;
		this.accountType = accountType;
		this.balance = balance;
		this.minimum_Balance = minimum_Balance;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.atmPin = atmPin;
		this.panNo = panNo;
		this.isActive = isActive;
		this.userId = userId;
		this.user = user;
	}

	/*
	 * public int getCustId() { return custId; }
	 */

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

	public int getAtmPin() {
		return atmPin;
	}

	public String getPanNo() {
		return panNo;
	}

	public boolean isActive() {
		return isActive;
	}

	public Integer getUserId() {
		return userId;
	}

	public Users getUser() {
		return user;
	}

	/*
	 * public void setCustId(int custId) { this.custId = custId; }
	 */

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

	public void setAtmPin(int atmPin) {
		this.atmPin = atmPin;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
