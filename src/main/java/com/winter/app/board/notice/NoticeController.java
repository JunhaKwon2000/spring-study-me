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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value="/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Value("${board.notice}")
	private String name;
	
	@ModelAttribute("board")
	public String getBoard() {
		return name;
	}
	
	@GetMapping("add")
	public String add() throws Exception {
		return "board/add";
	}
	
	@PostMapping("add")
	public String insert(@ModelAttribute NoticeVO noticeVO, MultipartFile[] attaches) throws Exception {
		int result = noticeService.add(noticeVO, attaches);
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
	public String update(NoticeVO noticeVO, MultipartFile[] attaches, Model model) throws Exception {
		int result = noticeService.update(noticeVO, attaches);
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
	
	@PostMapping("fileDelete")
	@ResponseBody // return 하는 친구를 바로 json 으로 바꿔서 return
	public int fileDelete(BoardFileVO boardFileVO, Model model) throws Exception {
		int result = noticeService.fileDelete(boardFileVO);
		return result;
	}
	
	@GetMapping("fileDown")
	public String fileDown(BoardFileVO boardFileVO, Model model) throws Exception {
		boardFileVO = noticeService.fileDetail(boardFileVO); // 객체 재사용(파라미터로 온 것에다가 넣음)
		model.addAttribute("file", boardFileVO);
		return "fileDownView"; // custom view 가 제일 먼저임(jsp보다 먼저) - bean의 이름을 먼저 찾아봄(모든 bean이 되는 것이 아니라, view인 bean만), 없으면 jsp로 감
	}

}