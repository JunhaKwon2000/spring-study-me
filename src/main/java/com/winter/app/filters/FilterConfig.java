package com.winter.app.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

@Configuration
public class FilterConfig implements WebMvcConfigurer {
	// This class can be used to configure filters if needed in the future.
	
//	@Bean // Filter를 빈으로 등록
//	FilterRegistrationBean<Filter> filterRegistrationBean() {
//		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//		
//		registrationBean.setFilter(new TestFilter());
//		registrationBean.addUrlPatterns("/notice/*", "/qna/*");
//		registrationBean.setOrder(1); // Filter의 순서를 지정합니다. 낮은 숫자가 먼저 실행됩니다.
//		
//		return registrationBean; // Filter를 적용할 URL 패턴을 지정합니다.
//	}
	
//	@Bean
//	FilterRegistrationBean<Filter> adminCheckFilterBean() {
//		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
//		
//		registrationBean.setFilter(new AdminCheckFilter());
//		registrationBean.addUrlPatterns("/notice/add", "/notice/update", "/notice/delete");
//		registrationBean.setOrder(0);
//		
//		return registrationBean;
//	}
	
	
}
