package com.gmreview.my.question;

import java.time.LocalDateTime;
import java.util.List;

import com.gmreview.my.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Question {
	
	@Id //기본 키로 지정하면 이제 id 속성의 값은 데이터베이스에 저장할 때 동일한 값으로 저장할 수 없다.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //strategy 는 고유번호를 생성하는 옵션 
														//GenerationType.IDENTITY 는 해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬때 사용한다
	private Integer id;
	
	//컬럼의 세부 설정을 위해 @Column 애너테이션을 사용한다
	//length 는 컬럼의 길이를 설정할때
	@Column(length = 200) 
	private String subjec;
	//columnDefinition 속성을 정의할 때 사용한다.
	//columnDefinition = "TEXT"은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용한다.
	@Column(columnDefinition = "TEXT")
	private String content;
	
	/*
	 즉 createDate 처럼 대소문자 형태의 카멜케이스(Camel Case) 이름은
	 create_date 처럼 모두 소문자로 변경되고 언더바(_)로 단어가 구분되어 실제 테이블 컬럼명이 된다.
	 */
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
	//mappedBy 는 참조 엔티티의 속성명을 의미한다.
	//Answer 엔티티에서 Question 엔티티를 참조한 속성명 question 을 mappedBy에 전달해야 한다.
	
	//CascadeType.REMOVE 
	//질문 하나에는 여러개의 답변이 작성될 수 있다. 이때 질문을 삭제하면 그에 달린 답변들도 모두
	//함께 삭제하기 위해서 @OneToMany 의 속성으로 cascade = CascadeType.REMOVE 를 사용했다
	private List<Answer> answer;
	
}

/*
 @Builder 어노테이션을 통한 빌드패턴을 사용하고, 데이터를
 변경해야 할 경우에는 그에 해당되는 메서드를 엔티티에 추가하여 데이터를 변경하면 된다.
 */

//테이블 컬럼으로 인식하고 싶지 않은 경우에만 @Transient