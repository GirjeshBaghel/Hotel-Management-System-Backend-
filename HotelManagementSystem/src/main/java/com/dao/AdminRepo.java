package com.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.entity.Admin;


@Repository 
public interface AdminRepo extends JpaRepository<Admin, Long>{

	Admin findByAdEmail(String email);
	Admin findByAdName(String  name);
	
	@Query("SELECT a FROM Admin a WHERE a.role = :role")
	List<Admin> findByRole(int role);
	
	 @Query("SELECT a.role FROM Admin a WHERE a.adminId = :adminId")
	    String getRoleAsString(@Param("adminId") long adminId);
	
}
