package com.green.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;

// 실제 데이터베아스에 테이블을 생성해준다
// import jakarta.peristence.
@Entity           // db table  에 대응하는 클래스 : 애노테이션   
@Builder
public class Article {
	@Id                         // primary key
	@GeneratedValue             // sequence : 번호 자동증가 
	private   Long     id;      // Long : null 입력가능, long <= null X  
	
	@Column                     // 데이터베이스 칼럼으 로인식
	private   String   title;
	
	@Column                     // 데이터베이스 칼럼으 로인식
	private   String   content;

	// 기본생성자   @NoArgsConstructor
	public Article() {}
	// 생성자  @AllArgsConstructor
	public Article(Long id, String title, String content) {
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	// toString   :  @ToString 
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	// Getter  / Setter
	public Long getId() {		
		return this.id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
		
	
}





