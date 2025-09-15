package com.green.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
import com.green.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository  articleRepository;
	
	public  List<Article>  getList() {
		
		List<Article>  articleList = articleRepository.findAll();
		return         articleList;
		
	}

	public Article create(ArticleDto articleDto) {
		
		Article   article  =  articleDto.toEntity();
		//  입력된 id 가 있다면( 있으면 안됨, 자동증가 ) 
		if( article.getId() != null  )
			return null;
		
		Article   saved    =  articleRepository.save( article );
		
		return  saved; 
	}

	public Article getOne(Long id) {
		
		// db id 로 조회
	    Article  article  = articleRepository.findById(id).orElse(null);
		log.info(  "article:" + article ); 
		return   article;
	}

	public Article delete(Long id) {
		
		// 삭재할 때는 미리 검색을 하고 cache memory 에서
		Article   target  =  articleRepository.findById(id).orElse(null);
		
		if(target == null)
			return  null;
		
		articleRepository.delete( target );
		
		return target;
	}

	public Article update(Article article) {
		
		// 0. 수정할 자료의 id 
		Long     id      =  article.getId();
		
		// 1. 수정할 데이터를 검색
		Article  target  =  articleRepository.findById(id).orElse(null);
		
		// 2. target 이 null 이면
		if( target == null ||  target.getId() != id  ) {
			log.info("id:{} article:{}", id, article );
			return null;  // 잘못된 요청 400
		}
		
		// 3. 업데이트 ( JPA -> .save())
		Article updated = articleRepository.save( article );
		
		return  updated;
	}
	
	// 실패 케이스 : 예약처리 
	// 테스트 : TalendApi 로 테스트 /api/transaction-test1 , POST
	// 오류가 있어 모두 저장되면 안됨  -> ㅈ너부취소되어야 한다	
	public List<Article> createArticleList(List<ArticleDto> dtos) {
		
		// 1.넘어온 Dto 들을 Article 엔티티 묶음으로 변환 
		List<Article>  articleList = new ArrayList<>();
		for ( ArticleDto dto : dtos) {
			Article  article  =  dto.toEntity();
			articleList.add( article );
		}
		
		// 2. db 에 반복저장
		for (Article article : articleList) {
			articleRepository.save( article );
		}
		
		// 3. 강제로 에러 발생 - 찾다가 없으면 예외발생 (id  :-1L) 
		articleRepository.findById(-1L)
	        .orElseThrow(
	        	() -> new IllegalArgumentException("결재 실패") );  // 500 발생		
		return articleList;
	}
	
	// 결재 취소 성공 케이스 : 예약처리 
	// 테스트 : TalendApi 로 테스트 /api/transaction-test1 , POST
	// 오류가 있어 모두 저장되면 안됨  -> ㅈ너부취소되어야 한다	
	@Transactional
	public List<Article> createArticleList2(List<ArticleDto> dtos) {
		
		// 1.넘어온 Dto 들을 Article 엔티티 묶음으로 변환 
		List<Article>  articleList = new ArrayList<>();
		for ( ArticleDto dto : dtos) {
			Article  article  =  dto.toEntity();
			articleList.add( article );
		}
		
		// 2. db 에 반복저장
		for (Article article : articleList) {
			articleRepository.save( article );
		}
		
		// 3. 강제로 에러 발생 - 찾다가 없으면 예외발생 (id  :-1L) 
		articleRepository.findById(-1L)
		.orElseThrow(
				() -> new IllegalArgumentException("결재 실패") );  // 500 발생		
		return articleList;
	}
	
}









