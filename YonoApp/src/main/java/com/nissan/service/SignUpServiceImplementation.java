package com.nissan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.model.Users;
import com.nissan.repo.ISignUpRepo;

@Service
public class SignUpServiceImplementation implements ISignUpService {

	@Autowired
	private ISignUpRepo signUpRepo;

	@Override
	public Users saveUser(Users user) {
		return signUpRepo.save(user);
	}
}
