package com.nissan.repo;

import org.springframework.data.repository.CrudRepository;

import com.nissan.model.Customer;

public interface ICustomerRepo extends CrudRepository<Customer, Long> {

	/*
	@Modifying
	@Query("UPDATE FROM com.nissan.model.Customer SET balance = balance+ ?2 WHERE accountNo = ?1")
	@Transactional
	void deposit(long accountNo, double amt);

	@Modifying
	@Query("UPDATE FROM com.nissan.model.Customer SET balance = balance- ?2 WHERE accountNo = ?1")
	@Transactional
	void withdraw(long accountNo, double amt);

	@Modifying
	@Query("SELECT balance FROM com.nissan.model.Customer WHERE accountNo = ?1")
	@Transactional
	double checkBal(long accountNo);
	*/

}
