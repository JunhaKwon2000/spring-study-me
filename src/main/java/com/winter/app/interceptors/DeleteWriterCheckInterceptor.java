package com.winter.app.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeDAO;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class DeleteWriterCheckInterceptor implements HandlerInterceptor {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		boolean flag = false;
		
		MemberVO memberVO = (MemberVO)request.getSession(false).getAttribute("member");
		
		Long boardNum = Long.parseLong(request.getParameter("boardNum"));
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardNum(boardNum);
		
		boardVO = noticeDAO.detail(boardVO);
		
		if (memberVO != null && boardVO != null) {
			String loginUsername = memberVO.getUsername();
			String writer = boardVO.getBoardWriter();
			if (loginUsername.equals(writer)) flag = true;
			else {
				request.setAttribute("msg", "Only writer can delete");
				request.setAttribute("url", "/notice/list");
				request.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(request, response);
				return false;
			}
		} 

		return flag;

		// return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
