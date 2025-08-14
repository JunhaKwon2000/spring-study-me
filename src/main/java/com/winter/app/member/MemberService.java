package com.winter.app.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.products.ProductsVO;

@Transactional(rollbackFor = Exception.class)
@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.member}")
	private String board;
	
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
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

	public MemberVO login(MemberVO memberVO) {
		MemberVO result = memberDAO.getMemberByUsername(memberVO);
		if (result != null) {
			result = memberDAO.getMemberByPassword(memberVO);
		}
		
		return result;
	}

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

}
