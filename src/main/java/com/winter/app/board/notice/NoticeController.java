package com.winter.app.board.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;

@Controller
@RequestMapping(value="/notice/*")
public class NoticeController {

	@Autowired
	private NoticeDAO noticeDAO;
	
	@GetMapping("add")
	public void insert() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("안녕하세요!");
		noticeVO.setBoardContent("본문입니당~");
		noticeVO.setBoardWriter("권준하");
		
		int result = noticeDAO.insert(noticeVO);
	}
	
	@GetMapping("update")
	public void update() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("ㅋㅋ");
		noticeVO.setBoardContent("ㅋㅌㅊ");
		noticeVO.setBoardWriter("준하");
		
		int result = noticeDAO.update(noticeVO);
	}
	
	@GetMapping("delete")
	public void delete() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(Long.valueOf("1"));
		int result = noticeDAO.delete(noticeVO);
	}
	
	public BoardVO detail(BoardVO boardVO) throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardNum(Long.valueOf("27"));
		BoardVO result = noticeDAO.detail(noticeVO);
		return result;
	}
	
}