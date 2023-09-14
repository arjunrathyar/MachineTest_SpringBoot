package com.nissan.repo;

import org.springframework.data.repository.CrudRepository;

import com.nissan.model.Users;

public interface ISignUpRepo extends CrudRepository<Users, Integer> {

}
