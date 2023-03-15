package com.gmreview.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/gm")
public class GmListController {
	
	@GetMapping("/write")
	public String gmwrite() {
		return "gmlist/gmwrite";
	}
	

}
