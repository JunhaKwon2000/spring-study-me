package com.winter.app.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;

@RequestMapping(value = "/account/*")
@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("add")
	public String add(HttpSession session, String[] productNum, Model model) {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		
		Map<String, Object> map = new HashMap<>();
		map.put("productNum", productNum);
		// System.out.println(productNum[0]);
		map.put("username", memberVO.getUsername());
		
		int result = accountService.add(map);
		
		String msg = "Purchase failed";
		String url = "/";
		if (result > 0) {
			msg = "Product purchased. Thank you";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	} 
	
}
