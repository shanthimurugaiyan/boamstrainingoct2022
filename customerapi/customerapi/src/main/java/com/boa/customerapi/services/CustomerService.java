package com.boa.customerapi.services;

import java.util.List;

import com.boa.customerapi.models.Customer;

public interface CustomerService {
	
	Customer addCustomer(Customer customer);
	Customer updateCustomer(long customerId, String email, long contactNumber);
	List<Customer> viewAllCustomers();
	Customer viewCustomerById(long customerId);
	boolean deleteCustomerById(long customerId);
	

}
