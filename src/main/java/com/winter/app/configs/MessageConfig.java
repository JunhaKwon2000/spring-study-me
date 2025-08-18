package com.winter.app.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
	
	@Bean
	LocaleResolver localeResolver() {
		
		// 언어 정보를 계속해서 웹 브라우저를 이용하면서 보관하고 싶을 때 쿠키와 세션이 정답
		
		// 1. 만약 session을 이용하고 싶을 경우
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN); // 여기서 Locale.KOREAN으로 설정했기 때문에 서버를 처음 시작하면 자동으로 _ko를 찾아서 언어 설정을 해줌

		// 2. 만약 쿠키를 이용하고 싶을 경우
		CookieLocaleResolver resolver2 = new CookieLocaleResolver("lang");
		resolver2.setDefaultLocale(Locale.KOREAN);
		
		return resolver2; // 현재는 쿠키 사용
		
		// 이후 인터셉터에서 html이 구성되고 난 이후 언어를 변환하는 과정을 거침(인터셉터로)
		
	}
	
	// Message Interceptor를 여기에 바로 선언
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		
		changeInterceptor.setParamName("lang");
		// ex: url?lang=ko
		
		return changeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.changeInterceptor())
				.addPathPatterns("/**");
	}
	
}
