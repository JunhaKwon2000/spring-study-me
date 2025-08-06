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

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

@Controller
@RequestMapping(value = "/qna/*")
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
		System.out.println(pager.getTotalPage());
		System.out.println(pager.getStartNum());
		System.out.println(pager.getEndNum());
		model.addAttribute("pager", pager);
		model.addAttribute("list", result);
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.add(qnaVO);
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
	public String detail(QnaVO qnaVO, Model model) throws Exception {
		QnaVO result = qnaService.detail(qnaVO);
		model.addAttribute("notice", result);
		return "board/detail";
	}
	
	@GetMapping("reply")
	public String reply(QnaVO qnaVO, Model model) throws Exception {
		model.addAttribute("notice", qnaVO);
		return "board/add";
	}
	
	@PostMapping("reply")
	public String replyForm(QnaVO qnaVO, Model model) throws Exception {
		int result = qnaService.reply(qnaVO);
		String msg = "Reply Fail";
		String url = "./list";
		if (result > 0) {
			msg = "Reply Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
}
