package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dao.AdminRepo;
import com.entity.Admin;

@Service
public class CustomUserDetailsService  implements UserDetailsService{

	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Admin admin = adminRepo.findByAdEmail(username);
		return User.builder()
				.username(admin.getAdEmail())
				.password(admin.getAdPassword())
				.roles(getRolesForAdmin(admin))
				.build();
		
	}
	
	private String[] getRolesForAdmin(Admin admin) {
//        String role = adminRepo.getRoleAsString(admin.getAdminId());
//        return new String[]{role};
		
		List<String> roles = new ArrayList<>();
		if(admin.getRole() == 1)
		{
			roles.add("ADMIN");
		}
		else if(admin.getRole() == 2)
		{
			roles.add("MANAGER");
		}
		else
		{
			roles.add("STAFF");
		}
		return roles.toArray(new String[0]);
    }

	
	
}
