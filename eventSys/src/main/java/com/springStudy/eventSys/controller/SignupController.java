package com.springStudy.eventSys.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springStudy.eventSys.entity.User;
import com.springStudy.eventSys.exception.BusinessException;
import com.springStudy.eventSys.form.NewUserForm;
import com.springStudy.eventSys.service.SignupService;

/**
 * 新規登録画面系のviewを返すコントローラクラス
 */
@Controller
@SessionAttributes(names = "user")
public class SignupController {
	
	/** ユーザ更新処理に関するサービスオブジェクト */
	SignupService signupService;
	
	/** パスワードエンコード用オブジェクト */
	PasswordEncoder passwordEncoder;
	
	/** コンストラクタインジェクション */
	public SignupController(
			SignupService signupService, 
			PasswordEncoder passwordEncoder) {
		
		this.signupService = signupService;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	/**
	 * 新規登録画面を返却するメソッド
	 * @param model
	 * @return 新規登録画面
	 */
	@GetMapping("/signup/goSignup")
	public String goSignup(Model model) {
		
		// 新規登録フォームをモデルに格納
		model.addAttribute("newUserForm", new NewUserForm());
		
		// 新規登録画面を返却
		return "signup/signup-input";
		
	}
	
	/**
	 * 新規登録確認画面を返却するメソッド
	 * @param newUserForm
	 * @param result
	 * @param model
	 * @return 新規登録確認画面
	 */
	@PostMapping("/signup/goSignupConfirm")
	public String goSignupConfirm(
			@Validated NewUserForm newUserForm, BindingResult result, Model model) {
		
		// バリテーションチェック
		if(result.hasErrors()) {
			
			// 新規登録画面を返却
			return "signup/signup-input";
			
		}
		
		// ユーザ情報をフォームから取得
		User user = new User().buildUser(newUserForm);
		
		try {
			
			// ユーザ情報が適切か確認
			signupService.confirmUser(user);
			
		}catch(BusinessException e) {
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
		}
		
		// ユーザ情報をモデルに格納
		model.addAttribute("user", user);
		
		// 新規登録確認画面
		return "signup/signup-confirm";
		
	}
	
}
