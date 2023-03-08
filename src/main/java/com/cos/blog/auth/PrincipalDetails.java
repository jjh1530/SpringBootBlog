package com.cos.blog.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

//시큐리티가 /login 주소 요청을 낚아채서 로그인 진행
//로그인 진행이 완료가 되면 시큐리티 session을 만들어줌(Security ContextHolder)
//오브젝트 -> Authentication 타입 객체
//Authentication 안에 User정보 타입 -> UserDetails 타입 객체

public class PrincipalDetails implements UserDetails{
	
	private User user; // 콤포지션

	public PrincipalDetails(User user) {
		this.user = user;
	}

	//해당 user의 권한을 리턴 Role이 스트링이라 처리해줘야함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collect = new ArrayList<>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole().toString();
			}
		});
		return collect;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	
}
