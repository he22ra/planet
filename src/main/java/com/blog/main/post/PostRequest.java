package com.blog.main.post;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {
	
	private Long id;				//pk	수정(update)할때 where조건으로 필요함
	private String title;			//제목
	private String contents;		//내용
	private String writer;			//작성자
	private int noticeYn;		//공지글 여부
	 
}
