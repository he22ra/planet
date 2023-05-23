package com.blog.main.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.blog.main.common.dto.SearchDto;

@Mapper
public interface PostDao {
	/*
	 * 게시글 저장
	 * @Param params - 게시글 정보
	*/
	void save(PostRequest params);

	/*
	 * 게시글 상세정보 조회
	 * @Param id - PK
	 * @return 게시글 상세정보
	 */
	PostResponse findById(Long id);
	
	/*
	 * 게시글 수정
	 * @Param params - 게시글 정보
	 */
	void update(PostRequest params);
	
	/*
	 * 게시글 삭제
	 * @Param id - PK
	 */
	void deleteById(Long id);
	
	/*
	 * 게시글 리스트 조회
	 * @return 게시글 리스트
	 */
	List<PostResponse> findAll(SearchDto params);

	
	/*
	 * 게시글 수 카운팅
	 * @return 게시글 수
	 */
	int count(SearchDto params);
	
}
