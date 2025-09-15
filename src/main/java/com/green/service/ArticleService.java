package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
import com.green.repository.ArticleRepository;

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
	
}









