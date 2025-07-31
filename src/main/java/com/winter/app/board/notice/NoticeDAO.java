package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.board.BoardDAO;

@Mapper // 이거 뭐임? 인터페이스인데 객체 생성이 가능 => @Mapper
public interface NoticeDAO extends BoardDAO {
	
}
