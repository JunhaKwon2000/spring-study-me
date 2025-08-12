package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value="/member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("join")
	public String join() {
		return "member/join";
	}
	
	@PostMapping("join")
	public String join(MemberVO memberVO, MultipartFile profile) throws Exception {
		int result = memberService.join(memberVO, profile);
		return "redirect:/";
	}
	
}
