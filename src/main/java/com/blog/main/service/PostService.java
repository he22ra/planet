package com.blog.main.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.main.common.dto.SearchDto;
import com.blog.main.common.paging.Pagination;
import com.blog.main.common.paging.PagingResponse;
import com.blog.main.post.PostDao;
import com.blog.main.post.PostRequest;
import com.blog.main.post.PostResponse;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
	
	@Autowired
	private PostDao postDao;
	 
	/**
     * 게시글 저장
     * @param params - 게시글 정보
     * @return Generated PK
     */
	 @Transactional
    public Long savePost(final PostRequest params) {
		postDao.save(params);
	 	Long id = params.getId();
        return id;
    }
	 /**
	  * 게시글 저장
	  * @param params - 게시글 정보
	  */
	 @Transactional
	 public void save(final PostRequest params) {
		postDao.save(params);
	 }
 
 	/**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     */
    public PostResponse findPostById(final Long id) {
        return postDao.findById(id);
    }

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     */
    @Transactional
    public Long updatePost(final PostRequest params) {
        postDao.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     */
    public Long deletePost(final Long id) {
        postDao.deleteById(id);
        return id;
    }

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */
    public PagingResponse<PostResponse> findAllPost(final SearchDto params) {
    	//조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 반환
    	int count = postDao.count(params);
    	if (count < 1) {
    		return new PagingResponse<>(Collections.emptyList(), null);
    	}
    	
    	//Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 저장
    	Pagination pagination = new Pagination(count, params);
    	params.setPagination(pagination);
    	
    	//계산된 페이지 정보의 일부 (limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
    	List<PostResponse> list = postDao.findAll(params);
        return new PagingResponse<>(list, pagination);
    }
	
}
