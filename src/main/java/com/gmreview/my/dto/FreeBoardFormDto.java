package com.gmreview.my.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import com.gmreview.my.entity.Members;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Getter @Setter
public class FreeBoardFormDto {

    @NotBlank(message = "제목을 입력해주세요.")
    private String subject;

    @NotEmpty(message = "내용을 입력해주세요.")
    private String content;
    
}