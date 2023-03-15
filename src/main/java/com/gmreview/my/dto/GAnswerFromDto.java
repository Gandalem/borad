package com.gmreview.my.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GAnswerFromDto {
	
	@NotEmpty(message = "댓글을 입력해주세요.")
	private String content; 

}
