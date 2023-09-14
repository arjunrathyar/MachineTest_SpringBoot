package com.nissan.service;

import java.util.List;

import com.nissan.dto.CustomerDTO;
import com.nissan.model.Customer;

public interface IAdminService {

	public List<Customer> getCustomer();

	public Customer getCustomer(long id);

	public Customer saveCustomer(Customer customer);

	public void deleteCustomer(long id); // for deleting from db

	public Customer updateCustomer(Customer customer, Customer customer2);

	public void deleteCustomer(Customer customer); // set active 0

	public List<CustomerDTO> getCustomerDTO();

}
