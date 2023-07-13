package com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dao.AdminRepo;
import com.entity.Admin;
import com.service.AdminService;

import com.service.RoomService;

@Controller
public class HomeController {
	
	
	@Autowired
	public AdminService adminService;
	
	@Autowired
	public RoomService roomService;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/adminUser")
	public String dashboard()
	{
		return "home";
	}
	@PreAuthorize("hasRole('MANAGER')")
	@GetMapping("/normalUser")
	public String normal()
	{
		return "normal";
	}
	
	 @GetMapping("/index")
	    public String home(){
	        return "index";
	    }
	 
	 @GetMapping("/customer/getCustomers")
	    public String showCreateForm(Model model) {
	        model.addAttribute("admin", new Admin());
	        return "customerFrom";
	    }

	    @PostMapping("/customer/create")
	    public String createAdmin(@Valid @ModelAttribute("admin") Admin admin, BindingResult bindingResult) {
	      if (bindingResult.hasErrors()) {
	            return "customerFrom";
	        }
    //AdminRepo adminRepo.createAdmin(admin);

	        return "home";
	    }

	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "Signin";
	    }

	    @PostMapping("/login")
	    public String login(Model model, String adEmail, String adPassword) {
	        
	    	Admin admin = adminService.getUserByEmail(adEmail);
	    	if (admin != null && admin.getAdPassword().equals(adPassword))
	    	  {
	          
	              return "home";
	        } else {
	        	
	            model.addAttribute("error", "Invalid email or password");
	            return "Signin";
	        }
	    }

}
