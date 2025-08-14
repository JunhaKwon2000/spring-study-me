package com.winter.app.account;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class AccountDAOTest {
	
	@Autowired
	private AccountDAO accountDAO;

	@Test
	@DisplayName("Account Add Test")
	void testAdd() {
		Map<String, Object> map = new HashMap<>();
		map.put("username", "junharoket");
		map.put("productNum", 9L);
		map.put("accountNum", "1");
		
		int result = accountDAO.add(map);
		
		assertNotEquals(0, result);
	}

}
