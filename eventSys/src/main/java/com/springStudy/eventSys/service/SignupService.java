package com.springStudy.eventSys.service;

import com.springStudy.eventSys.entity.User;
import com.springStudy.eventSys.exception.BusinessException;

/**
 * 新規登録処理を行うサービスインタフェース
 */
public interface SignupService {
	
	/**
	 * ユーザ情報が適切か確認する抽象メソッド
	 * @param user
	 * @throws BusinessException
	 */
	public void confirmUser(User user) throws BusinessException;
	
	/**
	 * 新規登録を行う抽象メソッド
	 * @param user
	 * @throws BusinessException
	 */
	public void insertUser(User user) throws BusinessException;
}
