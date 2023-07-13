package com.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.service.CustomUserDetailsService;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig  {


	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	
	@Bean
	public DaoAuthenticationProvider daouthenticationProvider()
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(customUserDetailsService);
		
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
		
	}

	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf().disable()
	        .cors().disable()
	        .authorizeRequests()
	            //.antMatchers("/addMembers").hasRole("ADMIN")
	           // .antMatchers("/admin/getAllRoom").hasAnyRole("ADMIN", "MANAGER")
	           // .antMatchers("/room/availableRoom").hasAnyRole("ADMIN", "MANAGER")
	            //.anyRequest().authenticated()
	            .and()
	        .httpBasic()
	            .and()
	            
	        .logout().permitAll()
	            .and()
	        .exceptionHandling().accessDeniedPage("/403");

	    return http.build();
	}

	
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(daouthenticationProvider());
	    }

	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	
}
