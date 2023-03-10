package com.gmreview.my;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//사용자가 생성한 예외 : 실행시 처리
@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "요청한 레코드를 찾지 못했다")
public class DateNotFoundExceptoin extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public DateNotFoundExceptoin(String massage) {
		super(massage);
	}
	
}
