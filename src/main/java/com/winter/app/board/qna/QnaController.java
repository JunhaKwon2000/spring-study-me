package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;
import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/qna/*")
@Slf4j
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${board.qna}")
	private String name;
	
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	};
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardVO> result = qnaService.noticeList(pager);
		model.addAttribute("pager", pager);
		model.addAttribute("list", result);
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO, MultipartFile[] attaches, HttpSession session, Model model) throws Exception {
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		qnaVO.setBoardWriter(memberVO.getUsername());
		int result = qnaService.add(qnaVO, attaches);
		String msg = "Add Fail";
		String url = "./list";
		if (result > 0) {
			msg = "Add Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@GetMapping("detail")
	public String detail(BoardVO qnaVO, Model model) throws Exception {
		QnaVO result = (QnaVO)qnaService.detail(qnaVO);
		model.addAttribute("notice", result);
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String reply(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("notice", qnaVO);
		return "board/add";
	}
	
	@PostMapping("reply")
	public String replyForm(QnaVO qnaVO, MultipartFile[] attaches, Model model) throws Exception {
		int result = qnaService.reply(qnaVO, attaches);
		String msg = "Reply Fail";
		String url = "./list";
		if (result > 0) {
			msg = "Reply Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@PostMapping("delete")
	public String delete(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.delete(qnaVO);
		String msg = "Delete Fail";
		String url = "./list";
		if (result > 0) msg = "Delete Complete";
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@GetMapping("update")
	public String update(QnaVO qnaVO, Model model) throws Exception {
		QnaVO result = (QnaVO)qnaService.detail(qnaVO);
		model.addAttribute("notice", result);
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(QnaVO qnaVO, MultipartFile[] attaches, Model model) throws Exception {
		int result = qnaService.update(qnaVO, attaches);
		String msg = "Update Fail";
		String url = "./detail?boardNum=" + qnaVO.getBoardNum();
		if (result > 0) {
			msg = "Update Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@PostMapping("fileDelete")
	@ResponseBody
	public int fileDelete(BoardFileVO boardFileVO) throws Exception {
		int result = qnaService.fileDelete(boardFileVO);
		return result;
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileVO boardFileVO, Model model) throws Exception {
		boardFileVO = qnaService.fileDetail(boardFileVO);
		model.addAttribute("file", boardFileVO);
		return "fileDownView";
	}
	
}
