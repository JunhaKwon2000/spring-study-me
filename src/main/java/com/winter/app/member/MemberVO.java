package com.winter.app.member;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	
	private String username;
	private String password;
	private String name;
	private String email;
	private String phone;
	private LocalDate birth;
	private Long accountNonExpired;
	private Long accountNonLocked;
	private Long credentialsNonExpired;
	private Long enabled;
	
	private ProfileVO profileVO;
	private List<RoleVO> roleVO;
	
}
