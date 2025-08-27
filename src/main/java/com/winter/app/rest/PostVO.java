package com.winter.app.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostVO {
	private long id;
	private long userId;
	private String title;
	private String body;
}
