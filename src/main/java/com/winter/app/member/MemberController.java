package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.products.ProductsVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public String join() throws Exception {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberService.join(memberVO, profile);
		return "redirect:/";
	}
	
	@GetMapping("login")
	public String login() throws Exception {
		return "member/login";
	}
	
	@PostMapping("login")
	public String login(MemberVO memberVO, HttpSession session) throws Exception {
		MemberVO result = memberService.login(memberVO);
		if (result != null) {
			session.setAttribute("member", result);
			session.setMaxInactiveInterval(60 * 30);
		}
		return "redirect:/";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session) throws Exception {
		session.removeAttribute("member");
		return "redirect:/";
	}
	
	@GetMapping("detail")
	public String myPage() throws Exception {
		return "/member/myPage";
	}
	
	@PostMapping("cartAdd")
	@ResponseBody
	public boolean cartAdd(String productNum, HttpSession session) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		Map<String, Object> map = new HashMap<>();
		map.put("productNum", Long.parseLong(productNum));
		map.put("username", memberVO.getUsername());
		int result = memberService.cartAdd(map);
		if (result > 0) return true;
		else return false;
	}
	
	@GetMapping("cartList")
	public String cartList(HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		List<ProductsVO> result = memberService.cartList(memberVO.getUsername());
		model.addAttribute("list", result);
		
		// 나중에 페이징 처리도 해야함(게시판 형식으로 했으면)
		return "/member/cartList";
	}
	
	@PostMapping("cartDelete")
	public String cartDelete(HttpSession session, String productNum, Model model) {
		String[] arr = productNum.split(",");
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("items", arr);
		int result = memberService.cartDelete(map);
		
		
		String msg = "Delete Fail";
		String url = "/member/cartList";
		if (result > 0) {
			msg = "Delete Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
}
