package com.springStudy.eventSys.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springStudy.eventSys.entity.User;

/**
 * ユーザテーブルを操作するMapperのインタフェース
 */
@Mapper
public interface UserMapper {
	
	/**
	 * ユーザ名を引数にユーザ情報を取得するメソッド
	 * @param username
	 * @return 	User型オブジェクト
	 */
	public User getUserByUsername(String username);

}
