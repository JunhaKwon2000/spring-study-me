package com.winter.app.board.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import jakarta.validation.Valid;
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
	
	// 매개변수는 오로지 유효성 검사 로직을 위해서
	@GetMapping("add")
	public String add(@ModelAttribute("boardVO") BoardVO noticeVO, Model model) throws Exception {
		// model.addAttribute("boardVO", new NoticeVO());
		return "board/add";
	}
	
	// 순서 중요!! @Valid가 있는 noticeVO 바로 다음에 BindingResult가 와야함!!!
	@PostMapping("add")
	public String insert(@ModelAttribute @Valid BoardVO noticeVO, BindingResult bindingResult, HttpSession session, MultipartFile[] attaches, Model model) throws Exception {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("msg", "Title cant be empty");
			model.addAttribute("url", "./add");
			return "common/result";
		}
		
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		noticeVO.setBoardWriter(memberVO.getUsername());
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
	
	// @CrossOrigin // 다른 서버에서 이 요청에 접근하는 것을 허용함
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
	
	@PostMapping("boardFile")
	@ResponseBody
	public String boardFile(MultipartFile boardFile) throws Exception {
		// log.info("{}",boardFile.getOriginalFilename());
		String result = noticeService.boardFile(boardFile);
		log.info("{}", result);
		return result;
	}
	
	@PostMapping("boardFileDelete")
	@ResponseBody
	public boolean boardFileDelete(String fileName) throws Exception {
		boolean result = noticeService.boardFileDelete(fileName);
		return result;
	}

}