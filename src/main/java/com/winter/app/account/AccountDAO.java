package com.winter.app.account;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountDAO {

	int add(Map<String, Object> map);

	List<AccountVO> getAccount(String username);
	
}
