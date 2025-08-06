package com.winter.app.board.notice;

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
@RequestMapping(value="/notice/*")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	};
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String insert(NoticeVO noticeVO) throws Exception {
		int result = noticeService.add(noticeVO);
		if (result > 0) {
			return "redirect:./list";
		} else {
			return null;
		}
	}
	
	@GetMapping("update")
	public String update(BoardVO noticeVO, Model model) throws Exception {
		BoardVO result = noticeService.detail(noticeVO);
		model.addAttribute("notice", result);
		return "board/add";
	}
	
	@PostMapping("update")
	public String update(NoticeVO noticeVO, Model model) throws Exception {
		int result = noticeService.update(noticeVO);
		String msg = "Update Fail";
		String url = "./detail?boardNum=" + noticeVO.getBoardNum();
		if (result > 0) {
			msg = "Update Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@PostMapping("delete")
	public String delete(NoticeVO noticeVO, Model model) throws Exception {
		int result = noticeService.delete(noticeVO);
		String msg = "Delete Fail";
		String url = "./list";
		if (result > 0) {
			msg = "Delete Complete";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "common/result";
	}
	
	@GetMapping("detail")
	public String detail(NoticeVO noticeVO, Model model) throws Exception {
		BoardVO result = noticeService.detail(noticeVO);
		model.addAttribute("notice", result);
		return "board/detail";
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardVO> result = noticeService.noticeList(pager);
		model.addAttribute("pager", pager);
		model.addAttribute("list", result);
		return "board/list";
	}

}