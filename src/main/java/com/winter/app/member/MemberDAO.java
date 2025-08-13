package com.winter.app.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.winter.app.products.ProductsVO;

@Mapper
public interface MemberDAO {
	
	int join(MemberVO memberVO) throws Exception;

	int insertProfile(ProfileVO profileVO);
	
	int insertRole(Map<String, Object> map) throws Exception;

	MemberVO getMemberByUsername(MemberVO memberVO);

	MemberVO getMemberByPassword(MemberVO memberVO);

	int cartAdd(Map<String, Object> map);

	List<ProductsVO> cartList(String username);

}
