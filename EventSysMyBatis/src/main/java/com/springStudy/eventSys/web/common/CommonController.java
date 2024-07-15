package com.springStudy.eventSys.web.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 共通画面系のviewを返すコントローラクラス
 */
@Controller
public class CommonController {
	
	/**
	 * トップ画面に遷移するメソッド
	 * @return common/top.html
	 */
	@GetMapping("/")
	public String goTop() {
		
		// トップ画面を返却
		return "common/top";
		
	}
	
	/**
	 * ログイン画面に遷移するメソッド
	 * @return common/login.html
	 */
	@GetMapping("/common/goLogin")
	public String goLogin(
			// ログイン失敗時に渡されるパラメータ
			@RequestParam(name = "error", required = false, defaultValue = "false") 
			boolean isError,
			// ログアウト時に渡されるパラメータ
			@RequestParam(name = "logout", required = false, defaultValue = "false") 
			boolean isLogouted,
			Model model) {
		
		// エラーメッセージ用変数
		String message = "";
		
		// エラー時に実行
		if(isError) {
			
			// エラーメッセージを設定
			message = "ユーザー名かパスワードが違います";
			model.addAttribute("error", message);
			
		}
		
		// ログアウト時に実行
		if(isLogouted) {
			
			// ログアウトメッセージを設定
			message = "ログアウトしました";
			model.addAttribute("logout", message);
			
		}
		
		// ログインページを返却
		return "common/login";
		
	}

}
