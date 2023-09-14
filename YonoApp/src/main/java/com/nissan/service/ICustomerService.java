package com.nissan.service;

import com.nissan.model.Customer;

public interface ICustomerService {

	public int deposit(Customer customer, double amt);

	public int withdraw(Customer customer, double amt);

	public double checkBal(Customer customer);

	public int transfer(Customer customer, Customer customer2, double amt);

	public Customer getCustomer(long id);

}
