package com.green.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.green.dto.Article;
import com.green.dto.ArticleDto;
import com.green.repository.ArticleRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ArticleController {

	@Autowired
	private  ArticleRepository   articleRepository;
	
	// 새글 쓰기 화면으로 이동
	@GetMapping("/article/WriteForm")
	public  String  writeForm() {
		return  "article/write";    // templates/article/write.mustache
	}  
	
	//  게시글 등록
	@PostMapping("/article/Write")
	public  String  write( ArticleDto  articleDto) {
		System.out.println(articleDto);
		log.info("정보:{}", articleDto);
		
		// h2 db 에 Article table 에 자징
		// toEntity() : articleDto -> article
		Article article = articleDto.toEntity();
		
		// .save(entity type class  필요) -> insert into
		Article saved = articleRepository.save( article ); 
		// return 값을 saved : 저장된 record 정보가 돌아온다
		System.out.println( saved.getId() );  // 저장된 글번호를 조회
		System.out.println( saved );          // 저장된 정보를 조회
		
		return "redirect:/article/List"; // 목록조회로 이동
	}
	
	// 목록조회
	@GetMapping("/article/List")
	public  String   list( Model model ) {
		
		long totCount = articleRepository.count();
		System.out.println("전체자료수:" + totCount);
		// SELECT COUNT(*) FROM ARTICLE 
		
		// db 조회
		// 오류발생
		// List<Article> articleEntityList = articleRepository.findAll();
		// 해결책 1
		//List<Article> articleList = (List<Article>) articleRepository.findAll();
		
		// 해결책 2 : 비추
		// Iterable<Article>  articleEntityList = articleRepository.findAll();
				
		// 해결책 3 : 우리가 사용할 방법
		//  articleRepository 에 있는 findAll()을 재정의한다
		List<Article> articleList = articleRepository.findAll();				
		// System.out.println( articleList );
		
		//model.addAttribute("cont", articleList.get(0).getContent()); 
		model.addAttribute("articleList",  articleList );
		
		return "article/list";    //  templates/article/list.mustache
	}
	
	//  주소줄에 조회할 글 번호(10) 를 넣어서 조회 
	//  PathVariable   -> 무조건 GET 방식이다
	// /article/10  :  10번 게시글 조회 id=10
	@GetMapping("/article/{id}")
	public  String  view(
		@PathVariable("id") Long id,  Model model
			) {
		
		// 조회방법 1, 조회 결과 O : article, X : null
		// Optional<Article> : null 이 들어와도 null point exception 이 발생하지
		// 않도록 OPtional 로 반환받는다
		// Optional<Article>  article =  articleRepository.findById(id);
		
		//Optional<Article> optArticle = articleRepository.findById(id);
		//   DB에 row가 있으면 : Optional.of(article)
        //   DB에 없으면       : null 이 아니라  -> Optional.empty() 
		
		// 조회방법2
		// .orElse(null) : 값이 없을때 null 이 retur 되도록 변경가능
		Article  article   =  articleRepository.findById(id).orElse(null);
			
		model.addAttribute("article", article);
		
		return "article/view";		
	}
	
	// 삭제
	// /article/3/Delete
	@GetMapping("/article/{id}/Delete")
	public  String   delete(
		@PathVariable("id")  Long id, RedirectAttributes  rttr
			) {
		/* 기존 방식 
		//articleRepository.deleteById(id);
		Article  article = new Article( id, null, null);
		articleRepository.delete(article);
		*/
		
		// redirect 의 사용이유
		//List<Article>  articleList = articleRepository.findAll();
		//model.addAttribute("articleList", articleList);
		//return   "article/list";
		
		log.info(id + "번의 삭제요청이 들어왔습니다");
		
		// 1. 삭제 대상을 조회한다
		// 조회한 자려가 있으면 target 저장, 없으면 target <- null
		Article  target = articleRepository.findById(id).orElse(null);
		
		// 2. 자로가 있으면 삭제
		if( target != null ) {
			articleRepository.delete( target );
			// 메세지를 만든다 -> redirect 한 페이지로 msg 가 전달된다
			rttr.addFlashAttribute("msg", id + "번이 삭제 되었습니다");			
		}
		
		return   "redirect:/article/List";
	}
	
	// http://localhost:8080/article/2/UpdateForm
	@GetMapping("/article/{id}/UpdateForm")
	public   String   updateForm(
			@PathVariable("id")  Long    id,
			                     Model   model
			) {
		// 수정할 정보를 조회 -> model 
		Article   article = articleRepository.findById(id).orElse(null);
		
		model.addAttribute("article", article );
		
		return  "article/update";   // article/update.mustache
	}
	
	// 파라이터로 HashMap 을 사용할때 @RequestParam   필수 
	// http://localhost:8080/article/Update
	@PostMapping("/article/Update")
	public  String   update( 
		@RequestParam  HashMap<String, Object>  map
		// Article  article
			) {
		
		// map.get("id")                                 : Object
		// String.valueOf(map.get("id"))                 : String
		// Long.praseLong(String.valueOf(map.get("id"))_ : Long
		// 넘어온값 처리
		// Long     id      =  (Long) map.get("id");  // 안됨
		Long     id      =  Long.parseLong( String.valueOf(map.get("id") ) );  // 안됨
		String   title   =  String.valueOf( map.get("title") ) ;
		String   content =  String.valueOf( map.get("content") );
		
		Article  article =  new Article(id, title, content);
		
		// 수정 .save(entity)
		articleRepository.save( article ); 
		
		// 목록으로 돌아가시
		return  "redirect:/article/List";
	}
	
}












