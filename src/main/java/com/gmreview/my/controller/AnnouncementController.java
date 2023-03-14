package com.gmreview.my.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		return "announcenment/announcementswrite";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/admin/announcements/save")
	public String annowrite(@Valid AnnouncementsFormDto announcementsFormDto, BindingResult bindingResult, Principal principal, Model model) {
	    if (bindingResult.hasErrors()) {
	        return "announcenment/announcementswrite";
	    }
	    try {
	        Members member = this.membersService.getName(principal.getName());
	        this.announcementService.save(announcementsFormDto.getSubject(), announcementsFormDto.getContent(), member);
	    } catch (IllegalStateException e) {
	        model.addAttribute("errorMessage", e.getMessage());
	        return "announcenment/announcementswrite";
	    }

	    model.addAttribute("AnnouncementsFormDto", announcementsFormDto); // Model 객체에 추가해줍니다.

	    return "redirect:/announcementslist";
	}
	
	
	
	
	@GetMapping("/announcementslist")
	public String gmlist(@RequestParam(name = "page", defaultValue = "0") int page,@RequestParam(name = "size", defaultValue = "") String kw,
            Model model) {
		
		Page<Announcement> announcementsList = this.announcementService.getAnnouncementsList(page, kw);
		
        model.addAttribute("paging", announcementsList);
        model.addAttribute("kw", announcementsList);
		
		return "announcenment/announcements";
	}
	
	@GetMapping(value = "/detail/{id}")
	public String detail(Model model,@PathVariable("id")Integer id) {
		
		Announcement announcement = this.announcementService.getannoboard(id);
		announcementService.updateViews(id);
		model.addAttribute("announcement",announcement);
		
		return "announcenment/announcementsdetail";
	}
	
	@GetMapping(value = "/user/signup")
	public String signup() {
		return "gmsignup";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/admin/delete/{id}")
	public String delete(@PathVariable("id") int id) {
	    // 해당 ID 값을 가진 게시글이 존재하는지 확인
	    Announcement announcement = announcementService.getannoboard(id);
	    // 게시글 삭제 수행
	    announcementService.deleteAnnouncement(announcement.getId());
	    return "redirect:/announcementslist";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/admin/update/{id}")
	public String edit(@PathVariable("id") int id, Model model) {
	    // 해당 ID 값을 가진 게시글이 존재하는지 확인
	    Announcement announcement = announcementService.getannoboard(id);
	    // 게시글 정보를 모델에 담아서 수정 페이지로 전달
	    model.addAttribute("announcement", announcement);
	    return "announcenment/announcementsupdate";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/admin/announcements/update")
	public String update(@ModelAttribute("announcement") Announcement announcement) {
	    // 게시글 업데이트 수행
	    announcementService.updateAnnouncement(announcement.getId(),announcement.getSubject(),announcement.getContent());
	    return "redirect:/announcementslist";
	}

	
}
