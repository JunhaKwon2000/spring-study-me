package com.winter.app.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
	private long id;
	private String name;
	private String username;
	private String email;
}
