package com.winter.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardVO;

@Mapper
public interface QnaDAO extends BoardDAO {

	public int refUpdate(BoardVO boardVO) throws Exception;
	
	public int replyUpdate(BoardVO boardVO) throws Exception;

	public int replyInsert(QnaVO qnaVO) throws Exception;
	
}
