package com.gmreview.my.entuty;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter //setter은 보안성의 취약하다 원래는 lombok의 builder 를 사용한다.
@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String content;
	
	//카멜케이스 createDate => create_date 로 DB의 저장된다
	private LocalDateTime createDate;
	
	
	@ManyToOne //답변은 하나의 질문에 여러개가 달릴 수 있는 구조이다.
	/*
	답변은 Many(많은 것)가 되고 질문은 One(하나)이 된다. 따라서 @ManyToOne 1대 N 관계
	@ManyToOne 애너테이션을 설정하면 Answer 엔티티의 question 속성과 Question 엔티티가 서로연결된다. 
	(실제 데이터베이스에서는 ForeignKey 관계가 생성된다.)
	역으로 Question 에서 Answer 을 참조하기 위해서는 @OneToMany 어노테이션을 사용한다.
	 */
	private Question question;
}
