package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;

	@Override
	public List<BoardVO> noticeList() throws Exception {
		return qnaDAO.noticeList();
	}


	public QnaVO detail(QnaVO qnaVO) throws Exception {
		return qnaDAO.qnaDetail(qnaVO);
	}
	
	public int reply(QnaVO qnaVO) throws Exception {
		QnaVO parent = qnaDAO.qnaDetail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaDAO.replyUpdate(parent);
		result = qnaDAO.replyInsert(qnaVO);
		
		return result;
	}

	@Override
	public int add(BoardVO qnaVO) throws Exception {
		int result = qnaDAO.insert(qnaVO);
		// ref값 업데이트
		result = qnaDAO.refUpdate(qnaVO);
		return result;
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public BoardVO detail(BoardVO boardVO) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
