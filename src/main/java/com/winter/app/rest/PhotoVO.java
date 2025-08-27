package com.winter.app.rest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PhotoVO {
	private long albumId;
	private long id;
	private String title;
	private String url;
	private String thumbnailUrl;
}