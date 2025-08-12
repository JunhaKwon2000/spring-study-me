package com.winter.app.transfer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.member.MemberVO;

@SpringBootTest
class TransferTest {

	@Autowired
	private Transfers transfers;
	
	@Autowired
	private Pay pay;
	
	@Test
	void test() {
		transfers.takeBus("111");

		MemberVO m = new MemberVO();
		m.setUsername("cr7");
		m = transfers.takeSubway(m);
	}

}
