package com.springStudy.eventSys.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.springStudy.eventSys.entity.CustomUserDetails;

/**
 * イベント管理システムで使用する各メソッドを定義するユーティリティクラス
 */
public class EventSysUtil {
	
	/**
	 * ログイン中のユーザ情報を取得するメソッド
	 * @return userDetails ログイン中のユーザ情報
	 */
	public static CustomUserDetails getUserDetails() {
		
		// ログイン中のユーザ情報を返却
		return (CustomUserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
	}

}
