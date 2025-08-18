package com.winter.app.account;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.member.MemberDAO;

@Transactional(rollbackFor = Exception.class)
@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private MemberDAO memberDAO;

	public int add(Map<String, Object> map) {
		
		int result = 0;
		
		for (String item : (String[])map.get("productNum")) {
			Map<String, Object> items = new HashMap<>();
			items.put("productNum", Long.parseLong(item));
			items.put("username", (String)map.get("username"));
			int hour = LocalDateTime.now().getHour();
			int min = LocalDateTime.now().getMinute();
			String sec = LocalDateTime.now().getSecond() + "";
			if (sec.length() == 1) sec = "0"+sec;
			int ns = LocalDateTime.now().getNano();
			
			String accountNum = "" + hour + min + sec + ns;
			items.put("accountNum", accountNum);
			result = accountDAO.add(items);
			
			if (result > 0) {
				memberDAO.cartDelete(items); // 혹시 상세에서 바로 지울 수도 있기 때문에 이 떄는 DB에서 지울려고 해봤자 result = 0이기 때문에 result에 안담음
			}
		}
		
		return result;
	}

	public List<AccountVO> getAccount(String username) {
		List<AccountVO> result = accountDAO.getAccount(username);
		return result;
	}

}
