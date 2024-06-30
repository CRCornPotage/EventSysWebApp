package com.springStudy.eventSys.form;

import com.springStudy.eventSys.entity.User;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新規登録するフォームオブジェクト
 * ユーザ名、パスワード(2回)、メールアドレス、権限を表します。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserForm {
	
	/** ユーザ名 */
	@NotBlank
	@Size(max = 20)
	private String username;
	
	/** パスワード(1回目) */
	@NotBlank
	@Size(max = 20)
	@Pattern(
	        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$",
	        message = "パスワードは大文字、小文字、数字を含む必要があります"
	)
	private String password;
	
	/** パスワード(2回目) */
	@NotBlank
	@Size(max = 20)
	private String passwordConfirm;
	
	/** メールアドレス */
	@NotBlank
	@Size(max = 100)
	@Pattern(
		    regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$",
		    message = "有効なメールアドレスを入力してください"
	)
	private String email;
	
	/**
	 * パスワードの入力チェックをするメソッド
	 * @return 入力チェックの結果
	 */
	@AssertTrue(message = "パスワードが違います")
	public boolean isPasswordValid() {
		
		// パスワードがnullまたは空の際に実行
		if(password == null || password.isEmpty()) {
			
			// trueを返却
			return true;
			
		// パスワードが入力されている場合に実行
		}else {
			
			// 2つのパスワードを比較した結果を返却
			return password.equals(passwordConfirm);
		
		}
		
	}
	
	/**
	 * ユーザフォームをビルドするメソッド
	 * @param user
	 * @return UserFormのインスタンス
	 */
	public NewUserForm buildUserForm(User user) {
		
		// ユーザ情報を格納
		this.username = user.getUsername();
		this.email = user.getEmail();
		
		// インスタンスを返却
		return this;
	}
	
}
