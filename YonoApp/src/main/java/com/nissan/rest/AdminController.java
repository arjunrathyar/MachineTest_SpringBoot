package com.nissan.rest;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.common.APIResponse;
import com.nissan.dto.CustomerDTO;
import com.nissan.model.Customer;
import com.nissan.service.IAdminService;
import com.nissan.util.JwtUtil;

@RestController // combination of controller and configuration annotations
@RequestMapping("/api")
public class AdminController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private IAdminService adminService;

	@Autowired
	private APIResponse apiresponse;

	// list
	@GetMapping("/customers/all")
	public List<Customer> getCustomer(@RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		return adminService.getCustomer();
	}
	
	// list
	@GetMapping("/customers")
	public List<CustomerDTO> getCustomerDTO(@RequestHeader(value = "authorization", defaultValue = "") String auth)
			throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		return adminService.getCustomerDTO();
	}

	// search by accNo
	@GetMapping("/customers/{accNo}")
	public Customer getCustomer(@PathVariable long accNo,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		return adminService.getCustomer(accNo);
	}

	// add customer
	@PostMapping("/customers")
	public ResponseEntity<APIResponse> addCustomer(@RequestBody Customer customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if (adminService.saveCustomer(customer) == null) {
			apiresponse.setData("Recheck Name and Mobile No. Details!!");
			apiresponse.setStatus(500);
			apiresponse.setError("Invalid Details!!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		apiresponse.setData("Customer added successfully!!");
		apiresponse.setStatus(200);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}

	// update
	@PutMapping("/customers/{accNo}")
	public ResponseEntity<APIResponse> updateCustomer(@PathVariable long accNo, @RequestBody Customer customer,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		if (adminService.updateCustomer(adminService.getCustomer(accNo), customer)==null){
			apiresponse.setData("Check Mobile No. Details!!");
			apiresponse.setStatus(500);
			apiresponse.setError("Invalid Details!!");
			return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
		}
		apiresponse.setData("Customer updated successfully!!");
		apiresponse.setStatus(200);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);

	}

	// disable or delete customer
	@DeleteMapping("/customers/{accNo}")
	public ResponseEntity<APIResponse> deleteCustomer(@PathVariable long accNo,
			@RequestHeader(value = "authorization", defaultValue = "") String auth) throws AccessDeniedException {
		jwtUtil.verifyAdmin(auth);
		adminService.deleteCustomer(adminService.getCustomer(accNo));
		
		apiresponse.setData("Customer deleted successfully!!");
		apiresponse.setStatus(200);
		return ResponseEntity.status(apiresponse.getStatus()).body(apiresponse);
	}

}
