package com.gmreview.my.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gmreview.my.dto.FreeBoardFormDto;
import com.gmreview.my.entity.FreeAnswer;
import com.gmreview.my.entity.FreeBoard;
import com.gmreview.my.entity.Members;
import com.gmreview.my.service.FreeAnswerService;
import com.gmreview.my.service.FreeBoardService;
import com.gmreview.my.service.MembersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/answer")
public class FreeAnswerController {
	
	private final FreeBoardService freeBoardService;
	private final FreeAnswerService freeAnswerService;
	private final MembersService membersService;
	
	@PostMapping("/new/{id}")
	public String create(@PathVariable("id") Integer id, Model model, Principal principal,
			@Valid FreeBoardFormDto freeBoardFormDto,BindingResult bindingResult) {
		
		FreeBoard freeBoard = this.freeBoardService.getFreeBoardById(id);
		
		Members members = this.membersService.getName(principal.getName());
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("freeBoard", freeBoard);
			return "freeboard/freedetail";
		}
		
		FreeAnswer freeAnswer = this.freeAnswerService.create(freeBoard, freeBoardFormDto.getContent(), members);
		
		return String.format("redirect:/free/detail/%s#freeAnswer_%s", freeAnswer.getFreeBoard(),freeAnswer.getId());
	}
	
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/create/{id}") // answer 생략됨
//	public String createAnswer(
//			// Model model,@PathVariable("id") Integer id,@RequestParam String content
//
//			// content 유효성 검사
//			Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult,
//			Principal principal) {
//
//		Question question = this.questionService.getQuestion(id);
//
//		SiteUser siteUser = this.userService.getUser(principal.getName());
//
//		if (bindingResult.hasErrors()) {
//			model.addAttribute("question", question);
//			return "question_detail";
//		}
//
//		Answer answer = this.answerService.create(question, answerForm.getContent(), siteUser);
//
//		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(),answer.getId());
//		// 세롭게 요청하는 명령문
//	}

}
