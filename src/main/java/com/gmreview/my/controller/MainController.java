package com.gmreview.my.controller;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gmreview.my.entity.Announcement;
import com.gmreview.my.service.AnnouncementService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final AnnouncementService announcementService;
	
	@GetMapping("/")
	public String index(@RequestParam(name = "page", defaultValue = "0") int pageNum,@RequestParam(name = "size", defaultValue = "10") String kw,
            Model model) {
		
		Page<Announcement> announcementsList = this.announcementService.mainAnnouncementsList(pageNum, kw);
        model.addAttribute("announcements", announcementsList);
        model.addAttribute("paging", announcementsList);
		
		return "index.html";
	}
	
}
