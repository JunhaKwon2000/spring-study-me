package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardService {

	// list
	List<BoardVO> noticeList(Pager pager) throws Exception;
	
	// detail
	BoardVO detail(BoardVO boardVO) throws Exception;
	
	// insert
	int add(BoardVO boardVO) throws Exception;
	
	// update
	int update(BoardVO boardVO) throws Exception;
	
	// delete
	int delete(BoardVO boardVO) throws Exception;
	
}
