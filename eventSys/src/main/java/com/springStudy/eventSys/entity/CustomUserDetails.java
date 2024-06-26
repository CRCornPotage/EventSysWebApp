package com.springStudy.eventSys.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * ログイン中のユーザ情報を保持するクラス
 * ユーザエンティティの情報を表します。
 */
@Data
@Accessors(chain = true)
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
		return user.getRoles();
	}
	
	/**
	 * ユーザのパスワードを取得するメソッド
	 * @return password ユーザのパスワード(ハッシュ化済)
	 */
	@Override
	public String getPassword() {
		return user.getPassword();
	}
	
	/**
	 * ユーザのユーザ名を取得するメソッド
	 * @return username ユーザのユーザ名
	 */
	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
