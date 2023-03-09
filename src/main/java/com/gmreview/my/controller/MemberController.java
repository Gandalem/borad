package com.gmreview.my.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gmreview.my.dto.MemberFormDto;
import com.gmreview.my.entuty.Members;
import com.gmreview.my.service.MembersService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MembersService memberService;
	private final PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/sign")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "gmsignup";
    }

    @PostMapping(value = "/sign")
    public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            return "gmsignup";
        }

        try {
            Members member = Members.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "gmsignup";
        }

        return "redirect:/";
    }
    
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/";
    }
	
}
