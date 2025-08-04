package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private BoardDAO noticeDAO;
	
	@Override
	public List<BoardVO> noticeList() throws Exception {
		return noticeDAO.noticeList();
	}

	@Override
	public BoardVO detail(BoardVO noticeVO) throws Exception {
		return noticeDAO.detail(noticeVO);
	}

	@Override
	public int add(BoardVO noticeVO) throws Exception {
		return noticeDAO.insert(noticeVO);
	}

	@Override
	public int update(BoardVO noticeVO) throws Exception {
		return noticeDAO.update(noticeVO);
	}

	@Override
	public int delete(BoardVO noticeVO) throws Exception {
		return noticeDAO.delete(noticeVO);
	}
	
}
