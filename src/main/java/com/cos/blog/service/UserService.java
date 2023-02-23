package com.cos.blog.service;


import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Transactional
	public void join(User user) {
		userRepository.save(user);
	}
}
