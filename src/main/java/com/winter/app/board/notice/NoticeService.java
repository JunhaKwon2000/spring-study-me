package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardDAO;
import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.FileManager;
import com.winter.app.commons.Pager;

@Service
public class NoticeService implements BoardService {

	@Autowired
	private BoardDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload}")
	private String upload;
	
	@Value("${board.notice}")
	private String board;
	
	@Override
	public List<BoardVO> noticeList(Pager pager) throws Exception {
		Long totalCount = noticeDAO.getTotalCount(pager);
		pager.makeNum(totalCount);
		return noticeDAO.noticeList(pager);
	}

	@Override
	public BoardVO detail(BoardVO noticeVO) throws Exception {
		return noticeDAO.detail(noticeVO);
	}

	@Override
	public int add(BoardVO noticeVO, MultipartFile[] attaches) throws Exception {
		int result = noticeDAO.insert(noticeVO);
		
		for (MultipartFile file : attaches) {			
			if (file == null || file.isEmpty()) continue;
			
			// 1. 파일을 하드에 저장
			String fileName = fileManager.fileSave(upload + board, file);
			
			// 2. 저장된 파일의 정보를 DB에 저장
			BoardFileVO boardFileVO = new BoardFileVO();
			boardFileVO.setOriName(file.getOriginalFilename());
			boardFileVO.setSaveName(fileName);
			boardFileVO.setBoardNum(noticeVO.getBoardNum());
			result = noticeDAO.insertFile(boardFileVO);			
			
		}

		return result;
		
	}

	@Override
	public int update(BoardVO noticeVO) throws Exception {
		return noticeDAO.update(noticeVO);
	}

	@Override
	public int delete(BoardVO noticeVO) throws Exception {
		noticeVO = noticeDAO.detail(noticeVO);
		for (BoardFileVO file : noticeVO.getBoardFileVO()) {
			fileManager.fileDelete(upload + board, file.getSaveName());
		}
		int result = noticeDAO.fileDelete(noticeVO);
		return noticeDAO.delete(noticeVO);
	}
	
}
