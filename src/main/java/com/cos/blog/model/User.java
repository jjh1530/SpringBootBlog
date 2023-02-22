package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//ORM -> Java(or 다른언어) Object - >테이블로 매핑해주는 기술
@Data
@NoArgsConstructor//빈생성자
@AllArgsConstructor
@Builder 
@Entity //User 클래스를 통해 테이블 생성
public class User {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
	private int id;
	
	@Column(nullable = false, length = 20)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; // Enum
	
	@CreationTimestamp // 시간 자동 입력
	private Timestamp createDate;
	
}
