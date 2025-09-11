package com.green;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.green.repository.ArticleRepository;

@SpringBootTest
class PrjJpa03ApplicationTests {

	@Autowired
	ArticleRepository  articleRepository;
	
	@Test
	void contextLoads() {
	}
	
	// 단위(Unit) 테스트를 위한 코드 작성
	@Test
	void test1() {
		double  d1 = 10;
		double  d2 = 3;
		double  d  = d1/d2;
		System.out.println( d );
		
		// assertEquals( d, 3.3  );		
		assertEquals( d, 3.3333, 0.0001 );	// 오차범위 (-0~+0.1.1) 	
	}
	
	@Test
	void test2() {
		long totCnt =  articleRepository.count();
		System.out.println(totCnt);
		
		assertEquals(totCnt, 0);
	}

	// test 실행방법
	// PrjJpa03ApplicationTests.java 오른쪽 마우스
	// -> Run -> JUnit Test
	// Show View -> JUnit 에서 결과를 확인
}






