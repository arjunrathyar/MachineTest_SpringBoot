package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Users;
import com.nissan.service.ISignUpService;

@RestController
@RequestMapping("/api")
public class SignUpController {

	@Autowired
	private ISignUpService signUpService;

	@Autowired
	private APIResponse apiresponse;

	@PostMapping("/signup")
	public ResponseEntity<APIResponse> addUser(@RequestBody Users user,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		// jwtUtil.verifyAdmin(auth);
		if (signUpService.saveUser(user) == null) {
			apiresponse.setData("Recheck Details!!");
			apiresponse.setStatus(500);
			apiresponse.setError("Invalid Details!!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		apiresponse.setData("User added successfully!!");
		apiresponse.setStatus(200);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}
}
