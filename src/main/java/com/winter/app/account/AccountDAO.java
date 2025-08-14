package com.winter.app.account;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	int add(Map<String, Object> map);
	
}
