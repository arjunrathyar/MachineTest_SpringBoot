package com.nissan.service;

import com.nissan.common.APIResponse;

public interface IUserService {
	
	public APIResponse findUserByNameAndPassword(Integer role, String userName, String password);
	

}
