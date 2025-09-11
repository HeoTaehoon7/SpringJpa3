package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.Article;
import com.green.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleRepository  articleRepository;
	
	@GetMapping(value="/api/articles")
	public  List<Article> list() {
		
		List<Article>  list = articleRepository.findAll();
		log.info("list", list );
		System.out.println(list);
		return list;
	}
}







