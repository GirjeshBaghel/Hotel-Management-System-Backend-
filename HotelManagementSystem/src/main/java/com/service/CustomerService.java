package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerRepository;
import com.entity.*;

@Service
public class CustomerService {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer saveCustomer(Customer customer)
	{
		return customerRepository.save(customer);
		
	}
	
//	 public boolean checkIfCustomerExistsByEmail(String email) {
//	        Customer existingCustomer = (Customer) customerRepository.findByEmail(email);
//	        return existingCustomer != null;
//	    }
	
	
	
}
