package com.gmreview.my.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.gmreview.my.entuty.Question;
import com.gmreview.my.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionService questionService;
	
	@GetMapping("/list")
	public String list(Model model) {
		
		List<Question> quesionlist = this.questionService.getList();
		
		model.addAttribute("questionList",quesionlist);
		
		return "gmboard.html";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model,@PathVariable("id")Integer id) {
		
		Question question = this.questionService.getQuestion(id);
		
		model.addAttribute("question",question);
		
		return "gmdetail.html";
	}
	
	@GetMapping(value = "/user/signup")
	public String signup() {
		return "gmsignup";
	}
	
}
