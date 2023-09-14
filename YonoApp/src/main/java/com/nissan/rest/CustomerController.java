package com.nissan.rest;

import java.nio.file.AccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.model.Customer;
import com.nissan.service.ICustomerService;
import com.nissan.util.JwtUtil;

@RestController // combination of controller and configuration annotations
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private APIResponse apiresponse;

	@PostMapping("/customers/{accNo}/deposit/{bal}")
	public ResponseEntity<APIResponse> deposit(@PathVariable long accNo, @PathVariable double bal,
			@RequestBody Customer customer, @RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		if (customerService.deposit(customerService.getCustomer(accNo), bal) == 0) {
			apiresponse.setData("PAN Verification Required!");
			apiresponse.setStatus(500);
			apiresponse.setError("PAN Verification Required!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		customerService.deposit(customerService.getCustomer(accNo), bal);
		apiresponse.setData("Amount Deposited successfully!!");
		apiresponse.setStatus(200);
		apiresponse.setError(null);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}

	@PostMapping("/customers/{accNo}/withdraw/{bal}")
	public ResponseEntity<APIResponse> withdraw(@PathVariable long accNo, @PathVariable double bal,
			@RequestBody Customer customer, @RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		if (customerService.withdraw(customerService.getCustomer(accNo), bal) == 0) {
			apiresponse.setData("Insufficient funds..!!!");
			apiresponse.setStatus(500);
			apiresponse.setError("Insufficient funds..!!!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		customerService.withdraw(customerService.getCustomer(accNo), bal);
		apiresponse.setData("Amount Withdrawn successfully!!");
		apiresponse.setStatus(200);
		apiresponse.setError(null);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}

	@GetMapping("/customers/{accNo}/getbal")
	public double checkBal(@PathVariable long accNo, @RequestBody Customer customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		return customerService.checkBal(customerService.getCustomer(accNo));
	}

	@PostMapping("/customers/{accNo}/transferto/{accNo2}/{bal}")
	public ResponseEntity<APIResponse> transfer(@PathVariable long accNo, @PathVariable long accNo2,
			@PathVariable double bal, @RequestBody Customer customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyCustomer(auth);
		if (customerService.transfer(customerService.getCustomer(accNo), customerService.getCustomer(accNo2),
				bal) == 0) {
			apiresponse.setData("PAN Verification Required!");
			apiresponse.setStatus(500);
			apiresponse.setError("PAN Verification Required!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		if (customerService.transfer(customerService.getCustomer(accNo), customerService.getCustomer(accNo2),
				bal) == 2) {
			apiresponse.setData("Insufficient funds..!!!");
			apiresponse.setStatus(500);
			apiresponse.setError("Insufficient funds..!!!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		customerService.transfer(customerService.getCustomer(accNo), customerService.getCustomer(accNo2), bal);
		apiresponse.setData("Transferred Amount Successfully!!");
		apiresponse.setStatus(200);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}

}
