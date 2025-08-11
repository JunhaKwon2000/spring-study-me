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
		Long totalCount = qnaDAO.getTotalCount(pager);
		pager.makeNum(totalCount);
		return qnaDAO.noticeList(pager);
	}

	@Override
	public BoardVO detail(BoardVO qnaVO) throws Exception {
		return qnaDAO.detail(qnaVO);
	}
	
	public int reply(QnaVO qnaVO, MultipartFile[] attaches) throws Exception {
		QnaVO parent = (QnaVO)qnaDAO.detail(qnaVO);
		qnaVO.setBoardRef(parent.getBoardRef());
		qnaVO.setBoardStep(parent.getBoardStep() + 1);
		qnaVO.setBoardDepth(parent.getBoardDepth() + 1);
		
		int result = qnaDAO.replyUpdate(parent);
		result = qnaDAO.replyInsert(qnaVO);
		
		for (MultipartFile file : attaches) {
			if (file == null || file.isEmpty()) continue;
			
			// 1. 파일을 하드에 저장
			String fileName = fileManager.fileSave(upload + board, file);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(qnaVO.getBoardNum());
			result = qnaDAO.insertFile(boardFileVO);			
		}
		
		return result;
	}

	@Override
	public int add(BoardVO qnaVO, MultipartFile[] attaches) throws Exception {
		int result = qnaDAO.insert(qnaVO);
		
		if (attaches == null) return result; // 글만 있을 경우
		
		for (MultipartFile file : attaches) {
			if (file == null || file.isEmpty()) continue;
			
			// 1. 파일을 하드에 저장
			String fileName = fileManager.fileSave(upload + board, file);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(qnaVO.getBoardNum());
			result = qnaDAO.insertFile(boardFileVO);			
		}
		
		
		// ref값 업데이트
		result = qnaDAO.refUpdate(qnaVO);
		
		return result;
	}

	@Override
	public int update(BoardVO qnaVO, MultipartFile[] attaches) throws Exception {
		int result = qnaDAO.update(qnaVO);
		
		if (attaches == null) return result;
		
		for (MultipartFile file : attaches) {
			if (file == null || file.isEmpty()) continue;
			
			// 1. 파일을 하드에 저장
			String fileName = fileManager.fileSave(upload + board, file);
			
			// 2. 파일정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(qnaVO.getBoardNum());
			result = qnaDAO.insertFile(boardFileVO);
		}
		
		return result;
	}

	@Override
	public int delete(BoardVO qnaVO) throws Exception {
		qnaVO = qnaDAO.detail(qnaVO);
		for (BoardFileVO file : qnaVO.getBoardFileVO()) {
			fileManager.fileDelete(upload + board, file.getSaveName());
		}
		int result = qnaDAO.fileDelete(qnaVO);
		result = qnaDAO.delete(qnaVO);
		return result;
	}

	@Override
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		// 1. file 조회
		boardFileVO = qnaDAO.fileDetail(boardFileVO);
		
		// 2. file 삭제
		boolean fileDelteResult = fileManager.fileDelete(upload + board, boardFileVO.getSaveName());
		
		// 3. DB 삭제
		int dbDeleteResult = qnaDAO.fileDeleteByFileNum(boardFileVO);
		
		return dbDeleteResult;
	}

	@Override
	public BoardFileVO fileDetail(BoardFileVO boardFileVO) throws Exception {
		return qnaDAO.fileDetail(boardFileVO);
	}

}
