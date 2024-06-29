package com.springStudy.eventSys.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springStudy.eventSys.entity.User;
import com.springStudy.eventSys.exception.BusinessException;
import com.springStudy.eventSys.mapper.UserMapper;

@Service
@Transactional
public class SignupServiceImpl implements SignupService {
	
	/** ユーザ情報を操作するMapperオブジェクト */
	private final UserMapper userMapper;
	
	/** コンストラクタインジェクション */
	public SignupServiceImpl(UserMapper userMapper) {
		
		this.userMapper = userMapper;
		
	}
	
	/**
	 * 新規登録処理を行うメソッド
	 * @param user
	 * @throws BusinessException
	 */
	@Override
	@Transactional
	public void confirmUser(User user) throws BusinessException {
		
		// エラーメッセージを格納する変数
		String message = "";
		
		/** ユーザ情報の存在チェック */
		// ユーザIDを引数にしてユーザ情報を取得出来なかった場合に実行
		if(userMapper.getUserById(user.getId()) == null) {
			
			// エラーメッセージを設定
			message = "ユーザーIDが存在しません";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}
		
		/** ユーザ名の重複チェック */
		// ユーザ名を引数にしてユーザ情報を取得出来た場合に実行
		if(userMapper.getUserByUsername(user.getUsername()) != null) {
			
			// エラーメッセージを設定
			message = "ユーザー名が重複しています";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}
		
		/* メールアドレスの重複チェック */
		// メールアドレスを引数にしてユーザ情報を取得出来た場合に実行
		if(userMapper.getUserByEmail(user.getEmail()) != null) {
			
			// エラーメッセージを設定
			message = "メールアドレスが重複しています";
			
			// 業務エラーを投げる
			throw new BusinessException(message);
			
		}

	}

	@Override
	@Transactional
	public void insertUser(User user) throws BusinessException {
		
		try {
			
			// ユーザ情報が適切か確認
			confirmUser(user);
			
		} catch(BusinessException e) {
			
			// 業務エラーを投げる
			throw e;
			
		}
		
		// 新規登録処理を行う
		userMapper.insertUser(user);
		
	}

}
