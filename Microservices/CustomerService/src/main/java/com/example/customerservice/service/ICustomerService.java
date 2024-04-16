package com.example.customerservice.service;

import com.example.customerservice.entity.Customer;

public interface ICustomerService {
	Customer addCustomer(Customer customer);
	Customer getCustomerById(int custId);

}
