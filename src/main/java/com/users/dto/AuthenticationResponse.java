package com.users.dto;

import com.users.enums.UserRole;

import lombok.Data;

@Data
public class AuthenticationResponse {

	private String jwt;
	private UserRole UserRole;
	private Long userId;
	
	
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public UserRole getUserRole() {
		return UserRole;
	}
	public void setUserRole(UserRole userRole) {
		UserRole = userRole;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	


	
	
}