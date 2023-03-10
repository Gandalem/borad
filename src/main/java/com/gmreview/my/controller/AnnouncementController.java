package com.gmreview.my.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.gmreview.my.dto.AnnouncementsFormDto;
import com.gmreview.my.entity.Announcement;
import com.gmreview.my.entity.Members;
import com.gmreview.my.service.AnnouncementService;
import com.gmreview.my.service.MembersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AnnouncementController {
	
	private final AnnouncementService announcementService;
	private final MembersService membersService;
	
	@GetMapping("/admin/announcementswrite")
	public String write() {
		return "announcementswrite";
	}
	
	
	@PostMapping("/admin/announcements/save")
	public String annowrite(@Valid AnnouncementsFormDto announcementsFormDto, BindingResult bindingResult,Principal principal) {
		
		if (bindingResult.hasErrors()) {
			return "announcementswrite";
		}

		Members member = this.membersService.getName(principal.getName());

		this.announcementService.save(announcementsFormDto.getSubject(), announcementsFormDto.getContent(), member);

		// 값을 DB에 저장후 List페이지로 리다이렉트 (질문 목록으로 이동)

		
		return "redirect:/announcementslist";
	}
	
	
	@GetMapping("/announcementslist")
	public String gmlist(Model model) {
		
		List<Announcement> announcements = this.announcementService.getList();
		
		model.addAttribute("announcements",announcements);
		
		return "announcements.html";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model,@PathVariable("id")Integer id) {
		
		Announcement announcement = this.announcementService.getannoboard(id);
		
		model.addAttribute("announcement",announcement);
		
		return "announcementsdetail.html";
	}
	
	@GetMapping(value = "/user/signup")
	public String signup() {
		return "gmsignup";
	}
	
}
