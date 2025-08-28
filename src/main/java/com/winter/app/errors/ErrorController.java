package com.winter.app.errors;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 예외 처리 전역 설정
@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(exception = Exception.class)
	public String error(Model model) {
		model.addAttribute("msg", "오류가 발생했습니다. 불편을 드려 죄송합니다");
		return "errors/error";
	}
	
//	@ExceptionHandler(exception = NullPointerException.class)
//	public String error2(Model model, NullPointerException msg) {
//		model.addAttribute("msg", msg);
//		return "errors/error";
//	}
	
}
