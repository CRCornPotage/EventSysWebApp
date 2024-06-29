package com.springStudy.eventSys.entity;

import java.io.Serializable;

import com.springStudy.eventSys.form.NewUserForm;
import com.springStudy.eventSys.form.UserForm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザ情報を保持するエンティティクラス
 * ユーザID、ユーザ名、パスワード(ハッシュ化済)、
 * メールアドレス、権限の情報を表します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	
	/** パスワード */
	
	/** ユーザID */
	private int id;
	
	/** ユーザ名 */
	private String username;
	
	/** パスワード(ハッシュ化済) */
	private String password;
	
	/** メールアドレス */
	private String email;
	
	/** 権限 */
	private String role;
	
	/**
	 * ユーザエンティティをビルドするメソッド
	 * @param newUserForm
	 * @return
	 */
	public User buildUser(NewUserForm newUserForm) {
		
		// ユーザ情報を格納
		this.username = newUserForm.getUsername();
		this.password = newUserForm.getPassword();
		this.email = newUserForm.getEmail();
		this.role = newUserForm.getRole();
		
		// インスタンスを返却
		return this;
		
	}
	
	/**
	 * ユーザエンティティをビルドするメソッド
	 * @param userForm
	 * @return
	 */
	public User buildUser(UserForm userForm) {
		
		// ユーザ情報を格納
		this.username = userForm.getUsername();
		this.email = userForm.getEmail();
		this.role = userForm.getRole();
		
		// インスタンスを返却
		return this;
		
	}
	
}
