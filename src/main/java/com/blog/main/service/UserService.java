package com.blog.main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.main.user.UserDao;
import com.blog.main.user.UserRequest;
import com.blog.main.user.UserResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	private UserDao userDao;

	/**
     * 회원 정보 저장
     * @param params - 회원 정보
     * @return Generated PK
     */
	 @Transactional
    public int saveUser(final UserRequest params) {
		System.out.println(params.getUserId());
		userDao.save(params);
		return params.getUserNo();
    }
	
	 /**
	     * 회원 상세정보 조회
	     * @param id - PK
	     * @return 회원 상세정보
	     */ 
	public UserResponse findByUserNo(final int id) {
		return userDao.findByUserNo(id);
	}
	
	/**
	 * 회원 상세정보 조회, Security 적용
	 * @param userid - unique
	 * @return 회원 상세정보
	 */ 
	public UserResponse findByUserId(final String userId) {
		return userDao.findByUserId(userId);
	}

}
