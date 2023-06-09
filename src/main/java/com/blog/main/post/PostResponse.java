package com.blog.main.post;

import java.util.Date;

import lombok.Getter;

@Getter
public class PostResponse {
	
	private Long id;						//pk	수정(update)할때 where조건으로 필요함
	private String title;					//제목
	private String contents;				//내용
	private String writer;					//작성자
	private int viewCnt;					//조회수
	private Boolean noticeYn;				//공지글 여부
	private Boolean deleteYn;				//삭제 여부
	private Date createdDate;				//생성 일시
	private Date modifiedDate;				//최종 수정일시
	
}