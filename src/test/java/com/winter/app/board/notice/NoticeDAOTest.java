package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;

	@Test
	void insertTest() throws Exception {
		for (int i = 0; i < 105; i++) {			
			NoticeVO noticeVO = new NoticeVO();
			noticeVO.setBoardTitle("title" + i);
			noticeVO.setBoardContent("content" + i);
			noticeVO.setBoardWriter("writer" + i);
			int result = noticeDAO.insert(noticeVO);
			if (i % 10 == 0) Thread.sleep(500);
		}
		
		// 단정문
		// assertEquals(1, result);
	}
	
	@Test
	void updateTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("title2");
		noticeVO.setBoardContent("content");
		noticeVO.setBoardWriter("writer");
		int result = noticeDAO.update(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void deleteTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(Long.valueOf("3"));
		int result = noticeDAO.delete(noticeVO);
		
		// 단정문
		assertEquals(1, result);
	}
	
	@Test
	void detailTest() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(27L);
		BoardVO result = noticeDAO.detail(noticeVO);
		log.info("result: {}", result);
		
		// 단정문
		assertNotNull(result);
	}
	
	@Test 
	void noticeListTest() throws Exception {
		List<BoardVO> result = noticeDAO.noticeList();
		int testResult = result.size();
		assertEquals(31, testResult);
	}

}
