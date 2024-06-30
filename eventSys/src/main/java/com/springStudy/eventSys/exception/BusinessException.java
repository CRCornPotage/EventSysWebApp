package com.springStudy.eventSys.exception;

import com.springStudy.eventSys.entity.User;

import lombok.Getter;

/**
 * 業務例外を定義するクラス
 */
@Getter
public final class BusinessException extends Exception {
	
	/** 業務例外時にスローするユーザエンティティを定義するフィールド */
	private User user;
	
	/**
	 * 業務例外のコンストラクタ
	 * @pram message
	 */
	public BusinessException(String message) {
		
		// エラーメッセージを設定
		super(message);
		
	}
	
	/**
	 * 業務例外のコンストラクタ
	 * @param user
	 */
	public BusinessException(User user) {
		
		// オブジェクトを設定
		this.user = user;
		
	}
	
	/**
	 * 業務例外のコンストラクタ
	 * @param message
	 * @param cause
	 */
	public BusinessException(String message, User user) {
		
		// メッセージとオブジェクトを設定
		super(message);
		
		// オブジェクトを設定
		this.user = user;
		
	}
	
}
