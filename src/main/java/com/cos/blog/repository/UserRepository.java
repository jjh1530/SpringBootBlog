package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	//Jpa Naming 전략
	// User findByUsernameAndPassword(String username, String password);
	
	Optional<User> findByUsername(String username);
}
