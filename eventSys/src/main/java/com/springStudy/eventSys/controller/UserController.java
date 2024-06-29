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
import org.springframework.web.bind.support.SessionStatus;

import com.springStudy.eventSys.entity.User;
import com.springStudy.eventSys.exception.BusinessException;
import com.springStudy.eventSys.form.NewUserForm;
import com.springStudy.eventSys.form.PasswordForm;
import com.springStudy.eventSys.form.UserForm;
import com.springStudy.eventSys.service.UserUpdateService;
import com.springStudy.eventSys.util.EventSysUtil;

/**
 * ユーザ管理画面系のviewを返すコントローラクラス
 */
@Controller
@SessionAttributes(names = "user")
public class UserController {
	
	/** ユーザ更新処理に関するサービスオブジェクト */
	UserUpdateService userUpdateService;
	
	/** パスワードエンコード用オブジェクト */
	PasswordEncoder passwordEncoder;
	
	/** コンストラクタインジェクション */
	public UserController(
			UserUpdateService userUpdateService, 
			PasswordEncoder passwordEncoder) {
		
		this.userUpdateService = userUpdateService;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	/**
	 * ユーザ情報画面を返却するメソッド
	 * @param model
	 * @return ユーザ情報画面
	 */
	@GetMapping("/user/goUserInfo")
	public String goUserInfo(Model model) {
		
		// ログイン中のユーザ情報をモデルに格納
		model.addAttribute(
				"loginUser", EventSysUtil.getUserDetails().getUser());
		
		// ユーザ情報画面を返却
		return "user/user-info";
		
	}
	
	/**
	 * ユーザ情報更新画面を返却するメソッド
	 * @param user ログイン中のユーザ情報を保持するエンティティ
	 * @param model
	 * @return　ユーザ情報更新画面
	 */
	@GetMapping("/user/goUserUpdate")
	public String goUserUpdate(Model model) {
		
		// ユーザフォームオブジェクトをモデルに格納
		model.addAttribute(
				"userForm", 
				new UserForm().buildUserForm(
						EventSysUtil.getUserDetails().getUser())
		);
		
		// ユーザ情報更新画面を返却
		return "user/user-update-input";
		
	}
	
	/**
	 * ユーザ情報更新画面を返却するメソッド
	 * @param user
	 * @param model
	 * @return ユーザ情報更新画面
	 */
	@GetMapping("/user/reUserUpdate")
	public String reUserUpdate(@ModelAttribute("user") User user, Model model) {
		
		// ユーザのセッションオブジェクトが存在しない場合に実行
		if(user != null) {
			
			// ユーザフォームオブジェクトをモデルに格納
			model.addAttribute(
					"userForm", 
					new UserForm().buildUserForm(user)
			);
			
		// ユーザのセッションオブジェクトが存在する場合に実行
		}else {
			
			// ユーザフォームオブジェクトをモデルに格納
			model.addAttribute(
					"userForm", 
					new UserForm().buildUserForm(
							EventSysUtil.getUserDetails().getUser())
			);
			
		}
		
		// ユーザ情報更新画面を返却
		return "user/user-update-input";
		
	}
	
	/**
	 * ユーザ情報更新確認処理を行いユーザ情報更新確認画面を返却するメソッド
	 * @param userForm
	 * @param result
	 * @param model
	 * @return ユーザ情報更新確認画面
	 */
	@PostMapping("/user/goUserUpdateConfirm")
	public String goUserUpdateConfirm(
			@Validated UserForm userForm, BindingResult result, Model model) {
		
		// バリテーションチェック
		if(result.hasErrors()) {
			
			// ユーザ情報更新画面を返却
			return "user/user-update-input";
			
		}
		
		// ユーザ情報をフォームから取得
		User user = new User().buildUser(userForm);
		
		// ユーザIDをログイン情報から設定
		user.setId(EventSysUtil.getUserDetails().getUser().getId());
		
		try {
			
			// ユーザ情報が適切か確認
			userUpdateService.confirmUser(user);
			
		}catch(BusinessException e){
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
			// ユーザ情報更新画面を返却
			return "user/user-update-input";
			
		}
		
		// ユーザ情報をモデルに格納
		model.addAttribute("user", user);
		
		// ユーザ情報更新確認画面を返却
		return "user/user-update-confirm";
	
	}
	
	/**
	 * ユーザ情報更新処理を行いユーザ情報更新完了画面を返却するメソッド
	 * @param user
	 * @param model
	 * @param status
	 * @return　ユーザ情報更新完了画面
	 */
	@PostMapping("/user/goUserUpdateComplete")
	public String goUserUpdateComplete(
			@ModelAttribute("user") User user, 
			Model model, SessionStatus status) {
		
		// ユーザのセッションオブジェクトが存在しない場合に実行
		if(user == null) {
			
			// ユーザフォームオブジェクトをモデルに格納
			model.addAttribute(
					"userForm", 
					new UserForm().buildUserForm(
							EventSysUtil.getUserDetails().getUser())
			);
			
			// ユーザ情報更新画面を返却
			return "user/user-update-input";
			
		}
		
		// ユーザIDをログイン情報から設定
		user.setId(EventSysUtil.getUserDetails().getUser().getId());
		
		try {
			
			// ユーザ情報を登録
			userUpdateService.updateUser(user);
			
		}catch(BusinessException e) {
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
			// ユーザ情報更新画面を返却
			return "user/user-update-input";
			
		}
		
		// セッション状態を完了
		status.setComplete();
		
		// ユーザ情報をモデルに格納
		model.addAttribute("user", user);
		
		// ユーザ情報更新完了画面を返却
		return "user/user-update-complete";
		
	}
	
	/**
	 * パスワード更新画面を返却するメソッド
	 * @param model
	 * @return パスワード情報更新画面
	 */
	@GetMapping("/user/goPasswordUpdate")
	public String goPassowrdUpdate(Model model) {
		
		// パスワードフォームオブジェクトをモデルに格納
		model.addAttribute("passwordForm", new PasswordForm());
		
		// パスワード更新画面を返却
		return "user/password-update-input";
		
	}
	
	/**
	 * パスワード更新処理を行いパスワード更新完了画面を返却するメソッド
	 * @param passwordForm
	 * @param result
	 * @param model
	 * @return パスワード更新完了画面を返却するメソッド
	 */
	@GetMapping("/user/goPasswordUpdateComplete")
	public String goPasswordUpdateComplete(
			@Validated PasswordForm passwordForm, BindingResult result, Model model) {
		
		// バリテーションチェック
		if(result.hasErrors()) {
			
			// パスワード更新画面を返却
			return "user/password-update-input";
			
		}
		
		// ユーザ情報を格納するオブジェクト
		User user = new User();
		
		// ユーザIDをログイン情報から設定
		user.setId(EventSysUtil.getUserDetails().getUser().getId());
		
		// パスワードをユーザ情報に格納
		user.setPassword(passwordEncoder.encode(passwordForm.getPassword()));
		
		try {
			
			// パスワードを登録する
			userUpdateService.updatePassword(user);
			
		}catch(BusinessException e) {
			
			// エラーメッセージをモデルに格納
			model.addAttribute("message", e.getMessage());
			
			// パスワードフォームオブジェクトをモデルに格納
			model.addAttribute("passwordForm", new PasswordForm());
			
			// パスワード更新画面を返却
			return "user/password-update-input";
			
		}
		
		// パスワード更新完了画面を返却
		return "user/password-update-complete";
		
	}
	
}
