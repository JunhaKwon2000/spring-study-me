package com.winter.app.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.products.ProductsVO;

import lombok.extern.slf4j.Slf4j;

@Transactional(rollbackFor = Exception.class)
@Service
@Slf4j
public class MemberService extends DefaultOAuth2UserService implements UserDetailsService {

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
		String[] arr = (String[]) map.get("items");
		for (String item : arr) {
			Map<String, Object> temp = new HashMap<>();
			temp.put("productNum", item);
			temp.put("username", (String) map.get("username"));
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
		// passwordEncoder.matches(memberVO.getPassword(),
		// passwordEncoder.encode("나의비밀번호"));

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
	/* -------------------스프링 시큐리티--------------------- */

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		// log.info("{}", userRequest.getAccessToken());
		// log.info("{}", userRequest.getAdditionalParameters());
		// log.info("{}", userRequest.getClientRegistration());

		String sns = userRequest.getClientRegistration().getRegistrationId();
		OAuth2User user = null;
		if ("kakao".equalsIgnoreCase(sns)) {
			user = this.useKakao(userRequest);
		}

		return user;

		// return super.loadUser(userRequest);
	}

	private OAuth2User useKakao(OAuth2UserRequest userRequest) {
		OAuth2User user = super.loadUser(userRequest);
		 log.info("{}", user.getName());
		 log.info("{}", user.getAttributes());
		 log.info("{}", user.getAuthorities());

		MemberVO memberVO = new MemberVO();
		Map<String, Object> map = user.getAttributes();
		LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) map.get("properties");
		// log.info("{}", result.get("nickname"));
		memberVO.setUsername(result.get("nickname").toString());

		ProfileVO profileVO = new ProfileVO();
		profileVO.setSaveName(result.get("profile_image").toString());
		memberVO.setProfileVO(profileVO);

		List<RoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleName("ROLE_MEMBER");
		list.add(roleVO);
		memberVO.setRoleVO(list);

		memberVO.setAttributes(map);

		memberVO.setSns("kakao");

		memberVO.setAccessToken(userRequest.getAccessToken().getTokenValue());

		memberVO.setName(user.getName());

		return memberVO;
	}
}
