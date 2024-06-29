package com.springStudy.eventSys.form;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * パスワードを入力するフォームオブジェクト
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {
	
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
	
}
