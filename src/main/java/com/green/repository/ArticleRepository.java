package com.green.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.green.dto.Article;

@Repository
public interface ArticleRepository 
        extends  CrudRepository<Article, Long>  {
	// extends : JPA 함수들을 상속받는다(.save(), .findAll(), ...)_
	
	@Override
	List<Article> findAll(); 
	// Iterable<> findAll() return ->  List<Article> findAll()
	// 상속관계을 이용하여 List를 Iterable  로 UpCasting 하여 Casting
}




