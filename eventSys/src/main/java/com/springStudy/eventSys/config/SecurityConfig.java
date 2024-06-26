package com.springStudy.eventSys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringSecurityに関するセキュリティ設定用クラス
 */
@Configuration
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
     * @return セキュリティ情報
     */
}