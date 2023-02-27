package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

//스프링 시큐리티의 고유한 세션저장소에 저장
@Getter
public class PrincipalDetail implements UserDetails{ // userDetails 전부 override

	private User user; //콤포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {  
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	//계정이 만료되지 않았는지 리턴 (true : 만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정이 잠겨있는지 리턴
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호가 만료되지 않았는지 리턴  (true : 만료안됨)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정 활성화가 되어있는지 리턴  (true : 활성화)
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	//계정의 권한을 리턴 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<GrantedAuthority>();
		/*
		 * collectors.add(new GrantedAuthority() {
		 * 
		 * @Override public String getAuthority() { return "ROLE_"+ user.getRole(); //
		 * ROLE을 안붙이면 인식불가 } });
		 */
		collectors.add(()->{return "ROLE_" +user.getRole();});
		return collectors;
	}
	
}
