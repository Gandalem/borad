package com.gmreview.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmreview.my.entity.Review;

public interface AnswerRepository extends JpaRepository<Review,Integer> {
	
	

}
