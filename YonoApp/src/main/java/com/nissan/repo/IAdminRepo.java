package com.nissan.repo;

import org.springframework.data.repository.CrudRepository;

import com.nissan.model.Customer;

public interface IAdminRepo extends CrudRepository<Customer, Long> {

}
