package com.springStudy.eventSys.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ログイン中のユーザ情報を保持するクラス
 * ユーザエンティティの情報を表します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
	
	/** ユーザ情報を保持するエンティティ */
	private User user;
	
	/**
	 * ユーザの権限を取得するメソッド
	 * @return ユーザの権限情報(コレクション型の配列)
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		// 権限を格納する権限リストインスタンスを作成
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		// 権限を格納
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
		
		// 権限情報を返却
		return authorities;
	}
	
	/**
	 * ユーザのパスワードを取得するメソッド
	 * @return password ユーザのパスワード(ハッシュ化済)
	 */
	@Override
	public String getPassword() {
		
		// パスワードを返却
		return user.getPassword();
	}
	
	/**
	 * ユーザのユーザ名を取得するメソッド
	 * @return username ユーザのユーザ名
	 */
	@Override
	public String getUsername() {
		
		// ユーザ名を返却
		return user.getUsername();
	}

}
