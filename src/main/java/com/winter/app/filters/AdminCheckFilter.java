package com.winter.app.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.winter.app.member.MemberVO;
import com.winter.app.member.RoleVO;

/**
 * Servlet Filter implementation class AdminCheckFilter
 */
public class AdminCheckFilter extends HttpFilter implements Filter {

	/**
	 * 
	 */
	/*
	 * private static final long serialVersionUID = 1L;
	 * 
	 *//**
		 * @see HttpFilter#HttpFilter()
		 */
	/*
	 * public AdminCheckFilter() { super(); // TODO Auto-generated constructor stub
	 * }
	 * 
	 *//**
		 * @see Filter#destroy()
		 */
	/*
	 * public void destroy() { // TODO Auto-generated method stub }
	 * 
	 *//**
		 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
		 */
	/*
	 * public void doFilter(ServletRequest request, ServletResponse response,
	 * FilterChain chain) throws IOException, ServletException { // TODO
	 * Auto-generated method stub // place your code here
	 * 
	 * HttpServletRequest myRequest = (HttpServletRequest) request;
	 * HttpServletResponse myResponse = (HttpServletResponse) response; boolean flag
	 * = false; if (myRequest.getSession(false) != null &&
	 * myRequest.getSession(false).getAttribute("member") != null) { List<RoleVO>
	 * list = ((MemberVO)
	 * myRequest.getSession(false).getAttribute("member")).getRoleVO(); for (RoleVO
	 * r : list) { if ("ROLE_ADMIN".equals(r.getRoleName())) { flag = true; } } }
	 * 
	 * if (flag) { chain.doFilter(request, response); } else {
	 * myRequest.setAttribute("msg",
	 * "You are not an admin Or You are not logged in");
	 * myRequest.setAttribute("url", "/");
	 * myRequest.getRequestDispatcher("/WEB-INF/views/common/result.jsp").forward(
	 * myRequest, myResponse); }
	 * 
	 * // pass the request along the filter chain }
	 * 
	 *//**
		 * @see Filter#init(FilterConfig)
		 *//*
			 * public void init(FilterConfig fConfig) throws ServletException { // TODO
			 * Auto-generated method stub }
			 */

}
