package com.springStudy.eventSys.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringSecurityに関するセキュリティ設定用クラス
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	/**
	 * BCryptによるハッシュ化処理を行うメソッド
	 * @return BCryptでハッシュ化されたパスワード
	 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * セキュリティフィルターチェインを設定するメソッド
     * @return ビルドされたセキュリティ設定
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	
    	// ログイン設定
    	http.formLogin(login -> login
    			.loginPage("/common/goLogin") // ログイン画面URLの設定
    			.loginProcessingUrl("/login") // ログインプロセス用URLの設定
    			.defaultSuccessUrl("/") // ログイン成功時の遷移先URL設定
    			.failureUrl("/common/goLogin?error=true") // ログイン失敗時の遷移先URL設定
    			.permitAll() // ログイン処理は全ユーザを許可
    	
    	// ログアウト設定
    	).logout(logout -> logout
    			.logoutUrl("/logout") // ログアウトプロセス用URLの設定
    			.logoutSuccessUrl("/common/goLogin?logout=true") // ログアウト成功時の遷移先URL設定
    			.permitAll() // ログアウト処理は全ユーザを許可
    	
    	// 認証・認可設定
		).authorizeHttpRequests(authz -> authz
    			.requestMatchers(
    					PathRequest.toStaticResources().atCommonLocations()
    					).permitAll() // 静的リソースへのアクセスは非認証ユーザもアクセス可能
    			.requestMatchers("/").permitAll() // トップ画面へのアクセスは非認証ユーザもアクセス可能
    			.requestMatchers("/error").permitAll() // エラー画面へのアクセスは非認証ユーザもアクセス
    			.requestMatchers("/common/**").permitAll() // 共通処理画面への遷移は非認証ユーザもアクセス可能
    			.requestMatchers("/signup/**").permitAll() // サインアップ処理画面への遷移は非認証ユーザもアクセス可能
    			.anyRequest().authenticated() // それ以外は認証済ユーザのみアクセス可能
    	
		);
    	
    	// セキュリティ設定をビルド
    	return http.build();
    }
    
}