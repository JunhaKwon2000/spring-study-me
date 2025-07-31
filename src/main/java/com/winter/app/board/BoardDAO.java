package com.winter.app.board;

public interface BoardDAO {
	
	// insert
	public int insert(BoardVO boardVO) throws Exception;
	
	public int update(BoardVO boardVO) throws Exception;
	
	public int delete(BoardVO boardVO) throws Exception;
	
	public BoardVO detail(BoardVO boardVO) throws Exception;
}
