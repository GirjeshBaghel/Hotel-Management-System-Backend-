package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Exception.ResourceNotFoundException;
import com.dao.AdminRepo;
import com.entity.Admin;
import com.entity.ApiResponse;

import com.service.AdminService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	public AdminService adminService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AdminRepo adminRepo;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/addMembers")  
	public ResponseEntity<?> addUsers(@Valid @RequestBody Admin admin) {
	   

	    if (adminRepo.findByAdEmail(admin.getAdEmail()) != null) {
	        return new ResponseEntity<>(new ApiResponse(false, "This email is already registered."), HttpStatus.BAD_REQUEST);
	    }
	 // Encode the password
	    String encodedPassword = passwordEncoder.encode(admin.getAdPassword());
	    admin.setAdPassword(encodedPassword);

	    Admin createdAdmin = adminRepo.save(admin);
	    if (createdAdmin != null) {
	        String successMessage = "Staff Members Add Successfully.";
	        return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
	    } else {
	        String errorMessage = "Failed to add member.";
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
	    }
	}
	
	//@PreAuthorize("hasRole('ADMIN') OR hasRole('STAFF') OR hasRole('MANAGER')")
	@PostMapping("/signinMember")
	//public ResponseEntity<HashMap<String, String>>(@RequestBody Admin admin) throws Exception {
	public ResponseEntity<?> signinAdmin(@RequestBody Admin admin) throws Exception {
		try {
			 Admin existingAdmin = adminService.getUserByEmail(admin.getAdEmail());
			
			if (existingAdmin == null) {
	            throw new ResourceNotFoundException("User", "Email", "This Email is Incorrect.");
	        }

			 // Compare the entered password with the decrypted stored password
	        if (!passwordEncoder.matches(admin.getAdPassword(), existingAdmin.getAdPassword())) {
	            throw new ResourceNotFoundException("User", "Password", "This Password is Incorrect");
	        }
 
	        int role = existingAdmin.getRole();
	        String successMessage;
	        String dashboardPath;

	        if (role == 1) {
	            successMessage = "Successfully Login. You are a Admin.";
	            dashboardPath = "/adminDashboard";
	            System.out.println("Admin Login");
	            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	        } else if (role ==2) {
	            successMessage = "Successfully Login. You are an Manager.";
	            dashboardPath = "/managerDashboard";
	            System.out.println("Manager Login");
	            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	        } else if (role == 0) {
	            successMessage = "Successfully Login. You are a Staff Member.";
	            dashboardPath = "/staffDashboard";
	            System.out.println("Staff Login");
	            return ResponseEntity.status(HttpStatus.OK).body(successMessage);
	        } else {
	            
	            throw new ResourceNotFoundException("Admin", "Role", "Unrecognized Role.");
	        } 
//	        HashMap<String, String> responseData = new HashMap<>();
//	        responseData.put("successMessage", successMessage);
//	        responseData.put("dashboardPath", dashboardPath);
//	        responseData.put("role", role);
//	        return ResponseEntity.status(HttpStatus.OK).body(responseData);

	       
		    
		} catch (ResourceNotFoundException ex) {
		    return new ResponseEntity<>(new ApiResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/getAllMembers")
	public List<Admin> getAllUser()
	{
		List<Admin> admin = (List<Admin>) adminRepo.findAll();
		System.out.println("demo");
		return admin;
	}


	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteMemberById/{id}")
	public String deleteAdmin(@PathVariable("id") long adminId ) {
		
		return adminService.deleteAdmin(adminId);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findMemberByEmail/{email}")
	public Admin getUserByEmail(@PathVariable("email") String email)
	{
		
		return adminRepo.findByAdEmail(email);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findMemberByName/{name}")
	public Admin getUserByName(@PathVariable("name") String name)
	{
		return adminRepo.findByAdName(name);
	}
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/findMemberByRole/{role}")
	public List<Admin> getUserByRole(@PathVariable("role")  int role)
	{
		List<Admin> admin = adminRepo.findByRole(role);
		return admin; 
	}
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteAllMember")
	public String deleteAllAdmins() {
	    String message = null;

	    long count = adminRepo.count();
	    if (count > 0) {
	        adminRepo.deleteAll();
	        message = "All admins deleted successfully!";
	    } else {
	        throw new ResourceNotFoundException("Admins", "No admins found to delete.", count);
	    }

	    return message;
	}


}
