package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor//빈생성자
@AllArgsConstructor
@Builder 
@Entity //User 클래스를 통해 테이블 생성
public class Reply {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name ="boardId")  //댓글의 boardId FK
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;  //user의 아이디 정보를 가져오기위해
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
