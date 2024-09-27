package com.users.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.users.entity.User;

import com.users.enums.UserRole;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findFirstByEmail(String email);
	
	
	//agregar este metodo para corroborar si es admin o no
	
	User findByUserRole(UserRole userRole);
}

