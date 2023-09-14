package com.nissan.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nissan.common.APIResponse;
import com.nissan.model.Users;
import com.nissan.repo.IUserRepository;
import com.nissan.util.JwtUtil;

@Service
public class UserServiceImplementation implements IUserService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private APIResponse apiResponse;

	@Override
	public APIResponse findUserByNameAndPassword(Integer role, String userName, String password) {
		// verify user exist or not
		Users user = userRepository.findUserByNameAndPassword(role, userName, password);
		if (user == null) {
			apiResponse.setData("Invalid Credentials!!");
			return apiResponse;
		}

		// credentials are correct, thenL

		if (role==1) {
			String token = jwtUtil.generateJwtAdmin(user);
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("ACCESSTOKEN", token);
			data.put("role", user.getRoleId());
			data.put("UserName", user.getUserName());

			apiResponse.setStatus(200);
			apiResponse.setData(data);

			return apiResponse;
			
		}
		else if (role==2) {
			String token = jwtUtil.generateJwtCustomer(user);
			Map<String, Object> data = new HashMap<String, Object>();
			
			data.put("ACCESSTOKEN", token);
			data.put("role", user.getRoleId());
			data.put("UserName", user.getUserName());

			apiResponse.setStatus(200);
			apiResponse.setData(data);

			return apiResponse;
			
		}

		apiResponse.setStatus(500);
		apiResponse.setData("Invalid Details");

		return apiResponse;
	}

}
