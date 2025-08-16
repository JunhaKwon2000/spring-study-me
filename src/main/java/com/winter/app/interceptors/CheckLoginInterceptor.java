package com.winter.app.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CheckLoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean flag = false;
		
		if (request.getSession() != null && request.getSession(false).getAttribute("member") != null) {
			flag = true;
		} else {
			request.setAttribute("msg", "Login First to See Accounts");
			request.setAttribute("url", "/member/login");
			request.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(request, response);
			return flag;
		}
		
		return flag;
	}

}
