package com.gmreview.my.dto;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GReviewFormDto {
	
	@NotEmpty(message ="게임명을 입력해주세요")
	private String gname;
	@NotEmpty(message ="게임에 대한설명을 넣어주세요")
	private String gcontent;
	
	@Lob
	@NotEmpty(message ="게임에 가격을 넣어주세요")
	private Integer price;

}
