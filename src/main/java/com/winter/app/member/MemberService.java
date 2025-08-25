package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.products.ProductsVO;

@Transactional(rollbackFor = Exception.class)
@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.member}")
	private String board;
	
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.join(memberVO);
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUsername(memberVO.getUsername());
		profileVO.setOriName("default.jpg");
		profileVO.setSaveName("default.jpg");
		
		if (profile != null && !profile.isEmpty()) {
			String saveName = fileManager.fileSave(upload + board, profile);
			profileVO.setOriName(profile.getOriginalFilename());
			profileVO.setSaveName(saveName);
			
			// if (profile != null) throw new Exception();
		}
		result = memberDAO.insertProfile(profileVO);
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", memberVO.getUsername());
		map.put("roleNum", 3);
		result = memberDAO.insertRole(map);
		return result;
	}

	/*
	 * public MemberVO login(MemberVO memberVO) { MemberVO result =
	 * memberDAO.getMemberByUsername(memberVO); if (result != null) { result =
	 * memberDAO.getMemberByPassword(memberVO); }
	 * 
	 * return result; }
	 */

	public int cartAdd(Map<String, Object> map) {
		int result = memberDAO.cartAdd(map);
		return result;
	}

	public List<ProductsVO> cartList(String username) {
		List<ProductsVO> result = memberDAO.cartList(username);
		return result;
	}

	@Transactional
	public int cartDelete(Map<String, Object> map) {
		int result = 0;
		String[] arr = (String[])map.get("items");
		for (String item : arr) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("productNum", item);
			temp.put("username", (String)map.get("username"));
			result = memberDAO.cartDelete(temp);
		}
		return result;
	}
	
	// 검증 메서드
	public boolean hasMemberError(MemberVO memberVO, BindingResult bindingResult) throws Exception {
		boolean flag = false;
		// flag = true 는 검증 실패(둘이 다름)
		// flag = false 는 검증 성공(둘이 같음)
		
		// 1. 어노테이션으로 검증
		flag = bindingResult.hasErrors();
		
		// 2. 사용자 정의로 검증(비밀번호 일치)
		if (!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			bindingResult.rejectValue("passwordCheck", "member.password.check");
			flag = true;
		}
		
		// 3. ID 중복 검사
		MemberVO duplicate = memberDAO.getMemberByUsername(memberVO);
		if (duplicate != null) {
			bindingResult.rejectValue("username", "member.username.check");
			flag = true;
		}
		
		return flag;
	}

	public int update(MemberVO memberVO) {
		/* 만약 비밀번호를 검증해봐야하는 경우 */
		// passwordEncoder.matches(memberVO.getPassword(), passwordEncoder.encode("나의비밀번호"));
		
		
		int result = memberDAO.update(memberVO);
		return result;
	}

	public MemberVO detail(MemberVO memberVO) throws Exception {
		MemberVO result = memberDAO.getMemberByPassword(memberVO);
		return result;
	}

	/* -------------------스프링 시큐리티--------------------- */
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		// System.out.println("로그인 서비스");
		try {
			memberVO = memberDAO.getMemberByPassword(memberVO);
			return memberVO;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
