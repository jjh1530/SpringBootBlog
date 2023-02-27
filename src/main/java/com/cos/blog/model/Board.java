package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인됨

	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER)	//board가 여러개 user는 한명 EAGER : 자동으로 user 정보 가져옴 LAZY : 자동으로 가져오지 않음
	@JoinColumn(name="userId")  //실제로 생서되는 컬럼 이름
	private User user; // DB는 오브젝트를 저장할 수 없다. FK. 자바는 오브젝트를 저장할 수 있다.
	
	//하나의 게시글은 여러개의 댓글을 가짐
	@OneToMany(mappedBy = "board",fetch = FetchType.EAGER) //mappedBy 연관관걔의 주인이 아님(db컬럼 생성 x) Reply의 board가 fk 
	private List<Reply> reply; 
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
