package com.gmreview.my.controller;

import java.security.Principal;
import java.util.Collections;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.gmreview.my.dto.FreeBoardFormDto;
import com.gmreview.my.entity.FreeBoard;
import com.gmreview.my.entity.Members;
import com.gmreview.my.service.FreeBoardService;
import com.gmreview.my.service.MembersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/free")
public class FreeBoardController {
	
	private final FreeBoardService freeBoardService;
	
	private final MembersService membersService;
	
	@GetMapping("/list")
	public String freelist(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "") String kw,
	            Model model) {    
	    Page<FreeBoard> freeBoard = this.freeBoardService.getFreeBoardList(page, kw);

	    if (freeBoard == null) { // freeBoard가 null인 경우
	        freeBoard = new PageImpl<>(Collections.emptyList()); // 빈 페이지 객체로 대체
	    }
	    model.addAttribute("paging", freeBoard);
	    model.addAttribute("kw", kw); // 여기서는 kw 자체를 추가해주어야 함

	    return "freeboard/freelist";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/write")
	public String freewrite() {	
		return "freeboard/freeboardwrite";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/save")
	public String freesave(@Valid FreeBoardFormDto freeBoardFormDto , BindingResult bindingResult,Model model,Principal principal)  {
		
		 if(bindingResult.hasErrors()){
	            return "freeboard/freeboardwrite";
	        }

	        try {
	        	Members members = this.membersService.getName(principal.getName());
	        	FreeBoard freeBoard = FreeBoard.createFreeBoard(freeBoardFormDto,members);
	        	freeBoardService.saveBoard(freeBoard);
	        } catch (IllegalStateException e){
	            model.addAttribute("errorMessage", e.getMessage());
	            return "freeboard/freeboardwrite";
	        }

	        return "redirect:/free/list";
	    }
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
	    FreeBoard freeBoard = freeBoardService.getFreeBoardById(id);
	    freeBoardService.updateViews(id); // 조회수 증가
	    model.addAttribute("freeBoard", freeBoard);
	    return "freeboard/freedetail";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String edit(@PathVariable("id") int id, Model model,Principal principal) {
	    // 해당 ID 값을 가진 게시글이 존재하는지 확인
		FreeBoard freeBoard = this.freeBoardService.getFreeBoardById(id);
		Members members = this.membersService.getName(principal.getName());
		if (!freeBoard.getMember().getName().equals(members.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
	    // 게시글 정보를 모델에 담아서 수정 페이지로 전달
	    model.addAttribute("freeboard", freeBoard);
	    return "freeboard/freemodify";
	}
	
	
	 @PreAuthorize("isAuthenticated()")
	 @PostMapping("/modify/{id}")
	 public String questionModify(@Valid FreeBoardFormDto freeBoardFormDto, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer id) {
	 if (bindingResult.hasErrors()) {
	 return "freeboard/freemodify";
	 }
	 
	 FreeBoard freeBoard = this.freeBoardService.getFreeBoardById(id);
	 Members members = this.membersService.getName(principal.getName());
	 if (!freeBoard.getMember().getName().equals(members.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
	 this.freeBoardService.modify(freeBoard, freeBoardFormDto.getSubject(), freeBoardFormDto.getContent());
	 return String.format("redirect:/free/detail/%s", id);
	 }
	
	 @PreAuthorize("isAuthenticated()")
	 @GetMapping("/delete/{id}")
	 public String questionDelete(Principal principal, @PathVariable("id") Integer id) {
	 FreeBoard freeBoard = this.freeBoardService.getFreeBoardById(id);
	 Members members = this.membersService.getName(principal.getName());
	 if (!freeBoard.getMember().getName().equals(members.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
	 this.freeBoardService.delete(freeBoard);
	 return "redirect:/";
	 }
	
	
	
	
	
	
}
