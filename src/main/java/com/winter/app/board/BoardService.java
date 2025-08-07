package com.winter.app.board;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.winter.app.commons.Pager;

public interface BoardService {

	// list
	List<BoardVO> noticeList(Pager pager) throws Exception;
	
	// detail
	BoardVO detail(BoardVO boardVO) throws Exception;
	
	// insert
	int add(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	
	// update
	int update(BoardVO boardVO) throws Exception;
	
	// delete
	int delete(BoardVO boardVO) throws Exception;
	
}
