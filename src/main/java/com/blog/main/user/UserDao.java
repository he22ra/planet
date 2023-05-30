package com.blog.main.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

	/*
	 * 회원가입
	 * @Param params - 회원 정보
	*/
	void save(UserRequest params);
	
	/**
     * 회원 상세정보 조회
     * @param id - PK
     * @return 회원 상세정보
     */
	UserResponse findByUserNo(int id);
	
	/**
	 * 회원 상세정보 조회, Security 적용
	 * @param userid - unique
	 * @return 회원 상세정보
	 */
	UserResponse findByUserId(String userid);

}
