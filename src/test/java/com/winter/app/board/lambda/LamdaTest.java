package com.winter.app.board.lambda;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.lambda.TestFunc;

@SpringBootTest
class LamdaTest {

	@Test
	void test() {
		int a1 = 10;
		int a2 = 20;
		TestFunc testFunc = (int n1, int n2) -> n1 + n2;
		int res = testFunc.func1(a1, a2);
		
		// 내장
		// consumer: accept 메서드를 람다식으로 오버라이드
		Consumer<Integer> consumer = (n) -> System.out.println(n.intValue());
		
		System.out.println(res);
		consumer.accept(20);
	}

}
