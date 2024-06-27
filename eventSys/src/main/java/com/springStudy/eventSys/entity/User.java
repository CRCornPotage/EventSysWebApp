package com.springStudy.eventSys.entity;

import java.io.Serializable;

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
	
}

