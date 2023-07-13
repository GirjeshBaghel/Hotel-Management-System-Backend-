package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exception.ResourceNotFoundException;
import com.dao.AdminRepo;
import com.dao.BookingRepository;
import com.dao.CustomerRepository;
import com.entity.Admin;
import com.entity.Booking;
import com.entity.Customer;

@Service
public class AdminService {

	@Autowired
	public AdminRepo adminRepo;
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public BookingRepository bookingRepository;
		
	public String deleteAdmin(long adminId) {
		String message=null;
		
		Optional<Admin> admin = adminRepo.findById(adminId);
		if(admin.isPresent()) {
			adminRepo.deleteById(adminId);
			message=new String("Members  deleted successfully!!");
		}
		else {
			throw new ResourceNotFoundException("Admin"," Id",adminId);
		}
		return message;
	}
	
	
	public Admin getUserByEmail(String email)
	{
		return adminRepo.findByAdEmail(email);
	}
	
	
}
