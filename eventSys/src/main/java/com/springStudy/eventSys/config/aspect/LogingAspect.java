package com.springStudy.eventSys.config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.springStudy.eventSys.entity.CustomUserDetails;

/**
 * ログインユーザ情報をログに出力するアスペクトクラス
 */
@Aspect
@Component
public class LogingAspect {
	
	@Before("execution(* com.springStudy.eventSys.controller.*.*(..))")
	public void beforeControllerMethodExcution() {
		
		// 認証情報を取得
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		// 認証が成功している場合に実行
		if(auth != null && 
				auth.isAuthenticated() && 
				!"anonymousUser".equals(auth.getPrincipal())) {
			
			// ログイン情報を取得
			CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
			
			// ログインユーザ情報をログに出力
			System.out.println(userDetails.getUser().toString());
			
		}else {
			
			// 不明ユーザ情報をログに出力
			System.out.println("anonymous user");
			
		}
		
	}
	
}
