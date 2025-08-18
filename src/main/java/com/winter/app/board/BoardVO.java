package com.winter.app.board;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {

	private Long boardNum;
	@NotBlank // Null 허용 X, 최소 문자 1개 이상
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private LocalDateTime boardDate;
	private Long boardHit;
	
	private List<BoardFileVO> boardFileVO;
	
}
