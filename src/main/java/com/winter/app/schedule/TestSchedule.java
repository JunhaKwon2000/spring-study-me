package com.winter.app.schedule;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;

@Component
public class TestSchedule {
	
	@Autowired
	private MemberDAO memberDAO;

	//@Scheduled(fixedRate = 2000, initialDelay = 1000) // 그냥 무조건 2초 뒤에 + 서버가 시작 후 1초 뒤에
	public void m1() throws InterruptedException {
		System.out.println(LocalDateTime.now());
		System.out.println("=========== SCHEDULE ==========="); 
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
	}

	//@Scheduled(fixedDelay = 1000 * 60 * 60 * 24) // 해당 메서드가 종류 이후 2초 뒤에 시작
	public void m2() throws InterruptedException  {
		System.out.println(LocalDateTime.now());
		System.out.println("=========== SCHEDULE ==========="); 
		Thread.sleep(3000);
		System.out.println(LocalDateTime.now());
	}
	
	@Scheduled(cron = "3 * * * * *")
	public void m3() throws Exception {
		System.out.println(LocalDateTime.now());
		System.out.println("=========== SCHEDULE ===========");
	}
}
