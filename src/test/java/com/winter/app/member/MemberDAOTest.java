package com.winter.app.member;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MemberDAOTest {

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void testPwUp() {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("junharoket");
		memberVO.setPassword(passwordEncoder.encode("youandI31@"));
		int result = memberDAO.pwUp(memberVO);
		System.out.println(result);
	}

}
