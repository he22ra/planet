package com.blog.main.ott;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectRequest {
	
	private Long id;						//pk	수정(update)할때 where조건으로 필요함
	private String title;					//제목
	private String contents;				//내용
	private Long writer;					//작성자
	private int viewCnt;					//조회수
	private Integer noticeYn;				//공지글 여부
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdDate;			//작성 일시
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifiedDate;			//최종 수정일시
	
}
