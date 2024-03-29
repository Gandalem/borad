package com.gmreview.my.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FreeAnswerFromDto {
	
	@NotEmpty(message = "내용이 비어있습니다. 반드시 입력하세요")
	private String content;

}
