package com.ProfileManagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(	name = "Employee")
public class Employee {

	
	@Id
	@NotNull(message = "The Associate Id may not be null")
	@Size(min = 5, max = 30, message ="The Associate Id must be between 5 to 30 characters length")
	@Pattern(regexp = "^CTS.*$", message = "The Associate Id must be start with CTS.")
	private String associateId;
	
	
	@NotNull(message = "The Name may not be null")
	@Size(min = 5, max = 30, message ="The Name must be between 5 to 30 characters length")
	private String name;
	
	@NotNull(message = "The Mobile may not be null")
	private Long mobile;
	

	@NotNull(message = "The Email may not be null")
	@Email
	private String email;
	
	
	private Float experience;
	
	private String designation;
	
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "associateid")
	public String getAssociateId() {
		return this.associateId;
	}
	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}
	@Column(name= "mobile")
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	@Column(name = "email_address", nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "Experience", nullable = false)
	public Float getExperience() {
		return experience;
	}
	public void setExperience(Float experience) {
		this.experience = experience;
	}
	@Column(name = "designation", nullable = false)
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
		
}
	