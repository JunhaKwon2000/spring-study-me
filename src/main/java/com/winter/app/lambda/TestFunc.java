package com.winter.app.lambda;

// 람다식을 쓰기 위한 어노테이션
// 람다식을 쓰려면 인터페이스에 무조건 메서드가 하나만
@FunctionalInterface
public interface TestFunc {
	
	int func1(int n1, int n2);
	
}
