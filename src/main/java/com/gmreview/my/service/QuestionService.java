package com.gmreview.my.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gmreview.my.entuty.Question;
import com.gmreview.my.error.DataNotFoundException;
import com.gmreview.my.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		
		return this.questionRepository.findAll();
 
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> question = this.questionRepository.findById(id);
		
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
		
	}
	
}
