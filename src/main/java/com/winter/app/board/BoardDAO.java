package com.winter.app.board;

import java.util.List;

import com.winter.app.commons.Pager;

public interface BoardDAO {
	
	 int insert(BoardVO boardVO) throws Exception;
	
	 int update(BoardVO boardVO) throws Exception;
	
	 int delete(BoardVO boardVO) throws Exception;
	
	 BoardVO detail(BoardVO boardVO) throws Exception;
	
	 List<BoardVO> noticeList(Pager pager) throws Exception;
	
	 Long getTotalCount(Pager pager) throws Exception;
	
	 int insertFile(BoardFileVO boardFileVO) throws Exception;
	
	 int fileDelete(BoardVO boardVO) throws Exception;
	
	 BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception;
	 
	 int fileDeleteByFileNum(BoardFileVO boardFileVO) throws Exception;
}
