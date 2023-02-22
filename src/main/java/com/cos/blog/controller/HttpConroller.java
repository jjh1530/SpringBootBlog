package com.cos.blog.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpConroller {

	@GetMapping("/http/get")
	public String getTest(Member vo) {
		return "get 요청" + vo.getId() + ", " + vo.getUsername();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member vo) { // text/plain // text/json
		return "post 요청" + vo.getId() + ", " + vo.getUsername() + vo.getPassword();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청" + m.getEmail();
	}
	
	@DeleteMapping("http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
	@GetMapping("/lombok/test")
	public String lombok() {
		Member  m = Member.builder().username("ssar").password("1234").email("wogns").build();
		System.out.println(m.getEmail());
		System.out.println(m.getUsername());
		return "lombok test";
	}

	
}
