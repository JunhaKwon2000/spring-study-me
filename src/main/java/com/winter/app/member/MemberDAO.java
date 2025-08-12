package com.winter.app.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDAO {
	
	int join(MemberVO memberVO) throws Exception;

	int insertProfile(ProfileVO profileVO);
	
	int insertRole(Map<String, Object> map) throws Exception;

	MemberVO getMemberByUsername(MemberVO memberVO);

	MemberVO getMemberByPassword(MemberVO memberVO);

}
