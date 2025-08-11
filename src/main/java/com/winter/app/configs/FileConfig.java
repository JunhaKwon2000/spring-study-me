package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FileConfig implements WebMvcConfigurer {
	
	@Value("${app.upload}")
	private String path; // D:/upload/ 이걸로 치환되고 이거 뒤에 요청 url을 붙힘
	
	@Value("${app.url}")
	private String url; // /files/ 이게
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(url).addResourceLocations("file:" + path);
	}
}
