package com.springStudy.eventSys.domain.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springStudy.eventSys.domain.entity.CustomUserDetails;
import com.springStudy.eventSys.domain.entity.User;
import com.springStudy.eventSys.domain.mapper.UserMapper;

/**
 * ユーザ情報をDBから取得して登録するサービスクラス
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	/** ユーザ情報を操作するMapperオブジェクト */
	private final UserMapper uesrMapper;
	
	/** コンストラクタインジェクション */
	public CustomUserDetailsService(UserMapper uesrMapper) {
		this.uesrMapper = uesrMapper;
	}
	
	/**
	 * ユーザ情報をDBから取得して登録するメソッド
	 * @param username ユーザ名
	 * @return ユーザ情報を登録する
	 */
	@Override
	public UserDetails loadUserByUsername(
			String username) throws UsernameNotFoundException {
		
		// ユーザ名を引数にユーザ情報を取得
		User user = uesrMapper.getUserByUsername(username);
		
		// ユーザ情報が存在しない場合に実行
		if(user == null) {
			
			// エラーメッセージを投げる
			throw new UsernameNotFoundException(
					"ユーザ名 : " + username + " が見つかりません");
			
		}
		
		// ユーザ情報を登録
		return new CustomUserDetails(user);
	}

}
