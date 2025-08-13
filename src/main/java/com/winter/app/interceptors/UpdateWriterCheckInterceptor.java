package com.winter.app.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardVO;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UpdateWriterCheckInterceptor implements HandlerInterceptor {
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		MemberVO loginMember = (MemberVO)request.getSession(false).getAttribute("member");
		BoardVO boardVO = (BoardVO)modelAndView.getModel().get("notice");
		String writer = boardVO.getBoardWriter();
		
		if (request.getMethod().toUpperCase().equals("POST")) {
			return;
		}
		
		if (loginMember != null && boardVO != null) {			
			if (!loginMember.getUsername().equals(writer)) {
				modelAndView.setViewName("common/result"); // Redirect to a result page
				modelAndView.addObject("msg", "Only the writer can update this notice");
				modelAndView.addObject("url", "./list"); // Redirect to the list page
			}
		}
		
		// HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	
}
