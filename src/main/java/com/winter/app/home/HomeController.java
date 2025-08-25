package com.winter.app.home;

import java.security.Principal;
import java.util.Enumeration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping("/")
	public String home(HttpSession session) {
//		Enumeration<String> result = session.getAttributeNames();
//		while (result.hasMoreElements()) {
//			log.info("key: {}", result.nextElement());
//		}
		
		// 여기는 그 Spring Security 그림을 참조해주세용
		if (session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
			// 1. Session 에서 가져오기
			Object result = session.getAttribute("SPRING_SECURITY_CONTEXT");
			// log.info("class name: {}",result.getClass().getName());	
			SecurityContextImpl contextImpl = (SecurityContextImpl) result;
			
			// 2. 
			Authentication authentication = contextImpl.getAuthentication();
			// log.info("Auth: {}", authentication);
			
			/*
			 * 좀 더 쉬운 방법!
			 * ----------------------------------------------------------
			 */
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			MemberVO memberVO = (MemberVO)auth.getPrincipal();
			
		}
		return "index";
	}
	
	@GetMapping("/info")
	public void info(Principal principal) {
		log.info("principal: {}", principal.getName());
	}

}
