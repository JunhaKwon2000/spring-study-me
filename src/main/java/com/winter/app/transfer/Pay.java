package com.winter.app.transfer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class Pay {
	
	@Around("execution (* com.winter.app.transfer.Transfers.takeSubway(..))")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("탑승 전 카드 체크");
		//log.info("Args: {}", joinPoint.getArgs());
		//log.info("Target: {}", joinPoint.getTarget());
		//log.info("Kind: {}", joinPoint.getKind());
		MemberVO mem = new MemberVO();
		mem.setUsername("123");
		MemberVO[] mems = new MemberVO[1];
		mems[0] = mem;
		Object obj = joinPoint.proceed(mems);
		log.info("return: {}", obj);
		System.out.println("탑승 후 카드 체크");
		return obj;
	}
	
}
