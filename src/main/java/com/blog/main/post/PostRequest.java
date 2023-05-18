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
	
	
	
	public PostRequest() {
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getNoticeYn() {
		return noticeYn;
	}
	public void setNoticeYn(int noticeYn) {
		this.noticeYn = noticeYn;
	}
	 
}
