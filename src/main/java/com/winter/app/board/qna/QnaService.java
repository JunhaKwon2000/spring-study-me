package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.qna}")
	private String board;

	@Override
	public List<BoardVO> noticeList(Pager pager) throws Exception {
		Long totalCount = qnaDAO.getTotalCount();
		pager.makeNum(totalCount);
		return qnaDAO.noticeList(pager);
	}

	@Override
	public BoardVO detail(BoardVO qnaVO) throws Exception {
		return qnaDAO.detail(qnaVO);
	}
	
	public int reply(QnaVO qnaVO) throws Exception {
		QnaVO parent = (QnaVO)qnaDAO.detail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaDAO.replyUpdate(parent);
		result = qnaDAO.replyInsert(qnaVO);
		
		return result;
	}

	@Override
	public int add(BoardVO qnaVO, MultipartFile attaches) throws Exception {
		int result = qnaDAO.insert(qnaVO);
		
		String fileName = fileManager.fileSave(upload + board, attaches);
		
		BoardFileVO boardFileVO = new BoardFileVO();
		boardFileVO.setOriName(attaches.getOriginalFilename());
		boardFileVO.setSaveName(fileName);
		boardFileVO.setBoardNum(qnaVO.getBoardNum());
		result = qnaDAO.insertFile(boardFileVO);
		
		// ref값 업데이트
		result = qnaDAO.refUpdate(qnaVO);
		return result;
	}

	@Override
	public int update(BoardVO boardVO) throws Exception {

		return 0;
	}

	@Override
	public int delete(BoardVO boardVO) throws Exception {
		
		return 0;
	}

}
