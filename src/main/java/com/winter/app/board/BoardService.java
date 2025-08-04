package com.winter.app.board;

import java.util.List;

public interface BoardService {

	// list
	List<BoardVO> noticeList() throws Exception;
	
	// detail
	BoardVO detail(BoardVO boardVO) throws Exception;
	
	// insert
	int add(BoardVO boardVO) throws Exception;
	
	// update
	int update(BoardVO boardVO) throws Exception;
	
	// delete
	int delete(BoardVO boardVO) throws Exception;
	
}
