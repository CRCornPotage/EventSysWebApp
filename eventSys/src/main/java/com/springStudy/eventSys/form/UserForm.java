package com.springStudy.eventSys.form;

import com.springStudy.eventSys.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ユーザ情報を入力するフォームオブジェクト
 * ユーザ名、メールアドレス、権限を表します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
	
	/** ユーザ名 */
	@NotBlank
	@Size(max = 20)
	private String username;
	
	/** メールアドレス */
	@NotBlank
	@Size(max = 100)
	@Pattern(
		    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
		    message = "有効なメールアドレスを入力してください"
	)
	private String email;
	
	/** 権限 */
	private String role;
	
	/**
	 * ユーザ情報をフォームオブジェクトに格納するメソッド
	 * @param user
	 * @return UserFormのインスタンス
	 */
	public UserForm buildUserForm(User user) {
		
		// ユーザ情報を格納
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.role = user.getRole();
		
		return this;
	}
	
}
