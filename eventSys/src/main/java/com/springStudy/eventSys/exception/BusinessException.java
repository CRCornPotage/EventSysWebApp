package com.springStudy.eventSys.exception;

/**
 * 業務例外を定義するクラス
 */
public final class BusinessException extends Exception {
	
	/**
	 * 業務例外のコンストラクタ
	 * @pram message
	 */
	public  BusinessException(String message) {
		
		// エラーメッセージを設定
		super(message);
		
	}
	
}
