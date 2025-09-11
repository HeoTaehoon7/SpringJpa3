package com.green.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.Article;
import com.green.repository.ArticleRepository;

@Service
public class ArticleService {
	
	@Autowired
	ArticleRepository  articleRepository;
	
	public  List<Article>  getList() {
		
		List<Article>  articleList = articleRepository.findAll();
		return         articleList;
		
	}
	
}









