package com.blog.main.ott;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class OttResponse {

	private Long id;						//pk	수정(update)할때 where조건으로 필요함
	private String title;					//제목
	private String contents;				//내용
	private String writer;					//작성자
	private int viewCnt;					//조회수
	private Integer noticeYn;				//공지글 여부
	private Integer deleteYn;				//삭제 여부
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;			//작성 일시
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;			//최종 수정일시
	
	private int rating;						//평점
	private Date viewDts;					//시청 시작 날짜	
	private Date viewDte;					//시청 종료 날짜	
	private String platform;				//플랫폼
	private String thumbnail;				//썸네일
	
}
