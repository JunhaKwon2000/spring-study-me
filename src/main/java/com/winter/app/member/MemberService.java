package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.FileManager;
import com.winter.app.trnasaction.Transaction;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private Transaction transaction;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.member}")
	private String board;
	
	public int join(MemberVO memberVO, MultipartFile profile) throws Exception {
		transaction.t();
		int result = memberDAO.join(memberVO);
		
		ProfileVO profileVO = new ProfileVO();
		profileVO.setUsername(memberVO.getUsername());
		profileVO.setOriName("default.jpg");
		profileVO.setSaveName("default.jpg");
		
		if (profile != null && !profile.isEmpty()) {
			String saveName = fileManager.fileSave(upload + board, profile);
			profileVO.setOriName(profile.getOriginalFilename());
			profileVO.setSaveName(saveName);
			
			if (profile != null) throw new Exception();
		}
		result = memberDAO.insertProfile(profileVO);
		
		transaction.t2();
		return result;
	}

}
