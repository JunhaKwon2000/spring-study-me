package com.winter.app.board.qna;

import com.winter.app.board.BoardFileVO;
import com.winter.app.board.BoardVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaVO extends BoardVO {
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
}
