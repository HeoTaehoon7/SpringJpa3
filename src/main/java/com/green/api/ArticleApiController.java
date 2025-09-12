package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
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
			// produces = "application/xml;charset=UTF-8" )			
	        // produces = MediaType.APPLICATION_JSON_VALUE )
	public  List<Article> list() {
		
		List<Article>  list = articleService.getList();
		log.info("list", list );
		System.out.println(list);
		return list;
		
		/*
		XmlMapper xmlMapper = new XmlMapper();
	    List<Article> list = articleService.getList();
	    return xmlMapper.writeValueAsString(list);
	    */
		
	}
	
	// ResponsEntity<Article>
	//  Artcile  : DATA
	//  +상태 코드  ResponseEntity.status( HttpStatus.ok  ) 
	//             또는 ResponseEntity.status( HttpStatus.BAD_REQUEST  ) 
	
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create( 
		@RequestBody	ArticleDto  articleDto   ) {  
		   //  @RequestBody : 넘어오는 정보는 json 문자열
		
		Article  created  =  articleService.create( articleDto  );
		// saved : 저장된 id, title. content  를 돌려받늗다
		
		ResponseEntity<Article>  result = 
		  ( created != null )	
		     ? ResponseEntity.status(HttpStatus.OK).body( created )   // 200(ok) , 201(created)	  
			 : ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400	 (Error)	
		
		return  result;
	}
	
}







