package com.gmreview.my.service;


import org.springframework.stereotype.Service;

import com.gmreview.my.entity.FreeAnswer;
import com.gmreview.my.entity.FreeBoard;
import com.gmreview.my.entity.Members;
import com.gmreview.my.repository.FreeAnswerRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FreeAnswerService {
	
	private final FreeAnswerRepository freeAnswerRepository;
	
	public FreeAnswer create(FreeBoard freeBoard, String content, Members members) {
		// Answer 객체를 생성후 아규먼트로 넘어노는 값을 setter 주입
		FreeAnswer freeAnswer = new FreeAnswer();
		freeAnswer.setContent(content);
		freeAnswer.setFreeBoard(freeBoard);
		freeAnswer.setMember(members);
		
		this.freeAnswerRepository.save(freeAnswer);
		
		return freeAnswer;
	}

}
