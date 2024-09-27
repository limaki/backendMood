package com.users.dto;




import com.users.enums.UserRole;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;

	private String name;
	
	private String email;
	
	 //importar userrole creado en enums
	
	private UserRole userRole;
	//definiendo constructores para mostrar usuarios
	


    // Constructor vacío (sin argumentos)
    public UserDto() {
    }

	
    




	public UserDto(Long id, String name, String email, UserRole userRole) {
		this.id = id;
		this.name = name;
		this.email = email;

		this.userRole = userRole;
	}







	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	
	

	
	


}
