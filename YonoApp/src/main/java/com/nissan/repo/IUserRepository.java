package com.nissan.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nissan.model.Users;

@Repository
public interface IUserRepository extends CrudRepository<Users, Integer> {

	@Query("from Users WHERE roleId=?1 AND userName=?2 AND password=?3")
	public Users findUserByNameAndPassword(Integer roleId, String userName, String password);

}
