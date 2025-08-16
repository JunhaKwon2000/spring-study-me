package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.winter.app.interceptors.CheckLoginInterceptor;
import com.winter.app.interceptors.DeleteWriterCheckInterceptor;
import com.winter.app.interceptors.UpdateWriterCheckInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private DeleteWriterCheckInterceptor deleteWriterCheckInterceptor;
	
	@Autowired
	private CheckLoginInterceptor checkLoginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new UpdateWriterCheckInterceptor())
				.addPathPatterns("/notice/update", "/qna/update");
		
		registry.addInterceptor(deleteWriterCheckInterceptor)
				.addPathPatterns("/notice/delete");
		
		registry.addInterceptor(checkLoginInterceptor)
				.addPathPatterns("/account/list");
		
	}
	
}
