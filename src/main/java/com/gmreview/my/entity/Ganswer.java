package com.gmreview.my.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ganswer extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "Ganswer_id")
	private Integer id;
	
	private String content;
	
	private Integer score;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "game_id")
	private GReviewBoard gReviewBoard;

}
