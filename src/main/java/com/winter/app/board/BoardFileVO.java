package com.winter.app.board;

import com.winter.app.commons.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileVO extends FileVO {
	private Long boardNum;
}
