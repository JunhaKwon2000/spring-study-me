package com.winter.app.board;

import java.util.List;
import java.util.Map;

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
	int update(BoardVO boardVO, MultipartFile[] attaches) throws Exception;
	
	// delete
	int delete(BoardVO boardVO) throws Exception;
	
	// file delete
	int fileDelete(BoardFileVO boardFileVO) throws Exception;
	
	// file detail
	BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception;
	
	// board detail images content temporal upload
	String boardFile(MultipartFile boardFile) throws Exception;
	
	// board detail images content delete
	boolean boardFileDelete(String fileName) throws Exception;
	
	
}
