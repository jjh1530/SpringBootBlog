package com.cos.blog.service;


import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	
	@Transactional
	public void join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void update(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고 영속화된 User오브젝트를 수정
		//Select 해서 User 오브젝트를 DB로부터 가져와 영속화를 시킴
		//영속화된 오브젝트를 변경하면 자동으로 DB에 UPDATE문을 날려줌
		User persistance = userRepository.findById(user.getId()).orElseThrow(()-> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		
		if (persistance.getOauth() == null || persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword(); //입력받은 패스워드
			String encPassword = encoder.encode(rawPassword);
			persistance.setPassword(encPassword);
			persistance.setEmail(user.getEmail());
		}
		
	
		
		//회원 수정 함수 종료시 서비스 종료 트랜잭션 종료 후 커밋 
	}
	
	@Transactional(readOnly = true)
	public User findKaKao(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()-> {  // NULL일경우 빈객체를 넣어줌
			return new User();
		});
		return user;
	}
}
