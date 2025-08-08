package com.winter.app.sample;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SampleTest {

	@Test
	void test() {
		// 0 => 0 0 + 6 * 0
		// 1 => 1 1 + 6 * 0
		// 2 => 2 2 + 6 * 0
		// 3 => 3 3 + 6 * 0
		
		// 4 => 10 4 + 6 * 1
		// 5 => 11 5 + 6 * 1
		// 6 => 12 6 + 6 * 1
		// 7 => 13 7 + 6 * 1
		
		// 8 => 20 8 + 6 * 2
		// 9 => 21 9 + 6 * 2
		// 10 => 22 10 + 6 * 2
		// 11 => 23 11 + 6 * 2
		
		// 12 => 30 12 + 6 * 3
		
		for (int n = 0; n <= 30; n++) {
			System.out.println(n + 6 * (n / 4)); 
			System.out.println(10*(n/4) + n%4);
		}
		
	}
	
	@Test
	public void test2() {
		// 편의점 자동화
		int price = 34500;
		int money = 50000;
		int change = money - price;
		
		int man = change / 10000;
		int chon = (change - man * 10000) / 1000;
	}

}
