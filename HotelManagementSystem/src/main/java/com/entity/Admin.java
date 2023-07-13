package com.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Table(name="user")
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long adminId;
	
	@NotBlank
    private String adName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String adEmail;

    @NotBlank(message = "Password is required")
    private String adPassword;
    @NotBlank
    private String cusAddress;
	@NotBlank
    private String phoneno;
    @NotBlank
	private int role;
  
    
	
	public String getCusAddress() {
		return cusAddress;
	}

	public void setCusAddress(String cusAddress) {
		this.cusAddress = cusAddress;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdEmail() {
		return adEmail;
	}

	public void setAdEmail(String adEmail) {
		this.adEmail = adEmail;
	}

	public String getAdPassword() {
		return adPassword;
	}

	public void setAdPassword(String adPassword) {
		this.adPassword = adPassword;
	}

	public Admin(long adminId, String adName,
			@NotBlank(message = "Email is required") @Email(message = "Invalid email format") String adEmail,
			@NotBlank(message = "Password is required") String adPassword, String cusAddress, String phoneno,
			int role) {
		super();
		this.adminId = adminId;
		this.adName = adName;
		this.adEmail = adEmail;
		this.adPassword = adPassword;
		this.cusAddress = cusAddress;
		this.phoneno = phoneno;
		this.role = role;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adName=" + adName + ", adEmail=" + adEmail + ", adPassword="
				+ adPassword + ", cusAddress=" + cusAddress + ", phoneno=" + phoneno + ", role=" + role + "]";
	}
	

	
}
