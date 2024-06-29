package com.springStudy.eventSys.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.springStudy.eventSys.entity.User;

/**
 * ユーザテーブルを操作するMapperのインタフェース
 */
@Mapper
public interface UserMapper {
	
	/**
	 * ユーザIDを引数にユーザ情報を取得するメソッド
	 * @param id
	 * @return User型オブジェクト
	 */
	public User getUserById(int id);
	
	/**
	 * ユーザ名を引数にユーザ情報を取得するメソッド
	 * @param username
	 * @return 	User型オブジェクト
	 */
	public User getUserByUsername(String username);
	
	/**
	 * メールアドレスを引数にユーザ情報を取得するメソッド
	 * @param email
	 * @return User型オブジェクト
	 */
	public User getUserByEmail(String email);
	
	/**
	 * ユーザ情報を更新するメソッド
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * パスワードを更新するメソッド
	 * @param user
	 */
	public void updateUserPassword(User user);
	
	/**
	 * ユーザ情報を登録するメソッド
	 * @param user
	 */
	public void insertUser(User user);

}
