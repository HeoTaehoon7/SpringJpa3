package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
import com.green.repository.ArticleRepository;

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
	
}









