package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.Article;
import com.green.repository.ArticleRepository;
import com.green.service.ArticleService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ArticleApiController {

	@Autowired
	private ArticleService  articleService;
	
	// 결과 data 로 출력 : JSON 기본값
	@GetMapping(value="/api/articles",
			produces = "application/json;charset=UTF-8" )
	        // produces = MediaType.APPLICATION_JSON_VALUE )
	public  List<Article> list() {
		
		List<Article>  list = articleService.getList();
		log.info("list", list );
		System.out.println(list);
		return list;
		
	}
}







