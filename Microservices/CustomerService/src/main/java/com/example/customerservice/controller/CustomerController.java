package com.example.customerservice.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.customerservice.entity.Customer;
import com.example.customerservice.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	ICustomerService customerService;
	
	@Autowired
	Environment environment;
	
	@GetMapping("/customer/getById/{id}")
	public ResponseEntity<Customer> getEmployeeById(@PathVariable int id) {
		Customer customer = customerService.getCustomerById(id);
		// Get server port
		String port = environment.getProperty("local.server.port");
		customer.setEnvironment(port);
		return ResponseEntity.ok(customer);
	}
	
	@PostMapping("/customer/add")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = customerService.addCustomer(customer);
		String port = environment.getProperty("local.server.port");
		newCustomer.setEnvironment(port);
		return ResponseEntity.ok(newCustomer);
	}
}
