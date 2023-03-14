package com.gmreview.my.entity;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.gmreview.my.constant.Role;
import com.gmreview.my.dto.FreeBoardFormDto;
import com.gmreview.my.dto.MemberFormDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class FreeBoard extends BaseEntity {
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(length = 4000)
	private String content;
	
	@Column(nullable = false)
    private Integer views = 0;
	
	@ManyToOne
	private Members member;
	
	@OneToMany(mappedBy = "freeBoard", cascade = CascadeType.REMOVE)
	private List<FreeAnswer> answer;
	

	
	public static FreeBoard createFreeBoard(FreeBoardFormDto freeBoardFormDto,Members members){
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setSubject(freeBoardFormDto.getSubject());
        freeBoard.setContent(freeBoardFormDto.getContent());
        freeBoard.setMember(members);
        return freeBoard;
	}
	
}
