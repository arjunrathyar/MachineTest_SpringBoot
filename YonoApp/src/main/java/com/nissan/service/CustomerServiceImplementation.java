package com.nissan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.model.Customer;
import com.nissan.repo.ICustomerRepo;

@Service
public class CustomerServiceImplementation implements ICustomerService {

	@Autowired
	ICustomerRepo customerRepo;

	@Override
	public Customer getCustomer(long accNo) {
		return customerRepo.findById(accNo)
				.orElseThrow(() -> new RuntimeException("Customer Not Found with accNo: " + accNo));
	}

	@Override
	public int deposit(Customer customer, double amt) {
		try {
			customerRepo.findById(customer.getAccountNo());
			if (amt < 50000) {
				customer.setBalance(customer.getBalance() + amt);
				customerRepo.save(customer);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int withdraw(Customer customer, double amt) {
		try {
			customerRepo.findById(customer.getAccountNo());
			if (amt < customer.getBalance() - customer.getMinimum_Balance()) {
				customer.setBalance(customer.getBalance() - amt);
				customerRepo.save(customer);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public double checkBal(Customer customer) {
		try {
			customerRepo.findById(customer.getAccountNo());
			return customer.getBalance() - customer.getMinimum_Balance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int transfer(Customer customer, Customer customer2, double amt) {
		try {
			customerRepo.findById(customer.getAccountNo());
			customerRepo.findById(customer2.getAccountNo());

			if (amt > customer.getBalance() - customer.getMinimum_Balance()) {
				return 2;
			} else if (amt < customer.getBalance() - customer.getMinimum_Balance() && amt < 50000) {
				withdraw(customer, amt);
				deposit(customer2, amt);
				customerRepo.save(customer);
				customerRepo.save(customer2);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
