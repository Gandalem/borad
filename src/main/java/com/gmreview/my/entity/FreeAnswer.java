package com.gmreview.my.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FreeAnswer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	@Column(length = 200)
	private String content;
	
	@ManyToOne
	private FreeBoard freeBoard;
	
	@ManyToOne
	private Members member;
	
}
