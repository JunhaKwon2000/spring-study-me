package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	public int update(BoardVO boardVO) throws Exception;
	
	public int delete(BoardVO boardVO) throws Exception;
	
	public BoardVO detail(BoardVO boardVO) throws Exception;
	
	public List<BoardVO> noticeList(Pager pager) throws Exception;
	
	public Long getTotalCount(Pager pager) throws Exception;
	
	public int insertFile(BoardFileVO boardFileVO) throws Exception;
	
	public int fileDelete(BoardVO boardVO) throws Exception;
}
