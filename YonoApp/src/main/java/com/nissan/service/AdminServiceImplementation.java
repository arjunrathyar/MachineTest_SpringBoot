package com.nissan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.Validation;
import com.nissan.dto.CustomerDTO;
import com.nissan.model.Customer;
import com.nissan.repo.IAdminRepo;

@Service
public class AdminServiceImplementation implements IAdminService {

	@Autowired
	private IAdminRepo customerRepo;

	@Autowired
	private Validation validation;

	@Override
	public List<Customer> getCustomer() {
		return (List<Customer>) customerRepo.findAll();
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		if (validation.isNameValid(customer.getCustomerName())
				&& validation.isMobileValid(Long.toString(customer.getMobileNumber()))) {
			customer.setAccountNo((long) (Math.random() * 1000000000));
			customer.setAtmPin((int) (Math.random() * 10000));
			return customerRepo.save(customer);
		}
		return null;
	}


	@Override
	public Customer getCustomer(long accNo) {
		return customerRepo.findById(accNo)
				.orElseThrow(() -> new RuntimeException("Customer Not Found with accNo: " + accNo));
	}

	@Override
	public void deleteCustomer(long accNo) {
		try {
			customerRepo.findById(accNo);
			customerRepo.deleteById(accNo);
		}catch (Exception e) {
			new RuntimeException("Customer Not Found with accNo: " + accNo);
		}
	}

	@Override
	public Customer updateCustomer(Customer customer, Customer customer2) {
		if (validation.isMobileValid(Long.toString(customer2.getMobileNumber()))) {
			customer.setMobileNumber(customer2.getMobileNumber());
			customer.setEmailId(customer2.getEmailId());
			return customerRepo.save(customer);
		}
		return null;

	}

	@Override
	public void deleteCustomer(Customer customer) {
		customer.setActive(false);
		customerRepo.save(customer);
	}


	@Override
	public List<CustomerDTO> getCustomerDTO() {
		// TODO Auto-generated method stub
		List<Customer> customerList = (List<Customer>) customerRepo.findAll();
		List<CustomerDTO> customerListView = new ArrayList<CustomerDTO>();
		System.out.println(customerList);
		for(Customer a:customerList) {
			if(a.isActive()) {
				CustomerDTO customerDTO = new CustomerDTO();
				customerDTO.setAccountNo(a.getAccountNo());
				customerDTO.setAccountType(a.getAccountType());
				customerDTO.setCustomerName(a.getCustomerName());
				customerDTO.setMinimum_Balance(a.getMinimum_Balance());
				customerDTO.setMobileNumber(a.getMobileNumber());
				customerDTO.setPanNo(a.getPanNo());
				customerDTO.setEmailId(a.getEmailId());
				customerDTO.setBalance(a.getBalance());
				customerListView.add(customerDTO);
			}
		}
		return customerListView;
	}

}
