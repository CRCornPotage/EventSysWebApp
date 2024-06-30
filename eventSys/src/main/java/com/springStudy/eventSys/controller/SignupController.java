package com.springStudy.eventSys.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	 * 新規登録画面を返却するメソッド
	 * @param user
	 * @param model
	 * @return 新規登録画面
	 */
	@GetMapping("/signup/reSignup")
	public String reSignup(
			@ModelAttribute("user") User user, Model model) {
		
		// ユーザ情報のセッションオブジェクトが存在する場合に実行
		if(user != null) {
			
			// モデルにユーザ情報を追加して新規登録フォームを登録
			model.addAttribute("newUserForm", new NewUserForm().buildUserForm(user));
		
		// ユーザ情報のセッションオブジェクトが存在しない場合に実行
		}else {
			
			// 新規登録フォームをモデルに格納
			model.addAttribute("newUserForm", new NewUserForm());
			
		}
		
		// 新規登録画面を返却
		return "signup/signup-input";
		
	}
	
	/**
	 * 新規登録情報確認処理を行い新規登録確認画面を返却するメソッド
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
		
		// パスワードをハッシュ化
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		try {
			
			// ユーザ情報が適切か確認
			signupService.confirmUser(user);
			
		}catch(BusinessException e) {
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
			// 例外が発生したフィールドを削除した新規登録フォームをモデルに格納
			model.addAttribute("newUserForm", new NewUserForm().buildUserForm(e.getUser()));
			
			// 新規登録画面を返却
			return "signup/signup-input";
			
		}
		
		// ユーザ情報をモデルに格納
		model.addAttribute("user", user);
		
		// 新規登録確認画面を返却
		return "signup/signup-comfirm";
		
	}
	
	/**
	 * 新規登録処理を行い新規登録完了画面を返却するメソッド
	 * @param user
	 * @param model
	 * @return 新規登録完了画面
	 */
	@PostMapping("/signup/goSignupComplete")
	public String goSignupComplete(
			@ModelAttribute("user") User user, Model model) {
		
		// ユーザ情報のセッションオブジェクトが存在しない場合に実行
		if(user == null) {
			
			// 新規登録フォームをモデルに格納
			model.addAttribute("newUserForm", new NewUserForm());
			
			// 新規登録画面を返却
			return "signup/signup-input";
			
		}
		
		// ユーザ権限を付与
		user.setRole("USER");
		
		try {
			
			// 新規登録処理を実行
			signupService.insertUser(user);
			
		}catch(BusinessException e) {
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
			// 例外が発生したフィールドを削除した新規登録フォームをモデルに格納
			model.addAttribute("newUserForm", new NewUserForm().buildUserForm(e.getUser()));
			
		}
			
		// 新規登録完了画面を返却
		return "signup/signup-complete";
		
	}
	
}
